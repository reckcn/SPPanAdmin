package net.sppan.base.common.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sppan.base.entity.User;

import org.springframework.util.StringUtils;

/**
 * Web相关工具类
 * 
 * @author SPPan
 */
public final class WebUtils {

	private WebUtils() {}


	private static final String USER_COOKIE_KEY    = "uid";
	private static String USER_COOKIE_SECRET = "&#%!&*";

	/**
	 * 设置用户账户cookie 密钥
	 * @param userSecret
	 */
	public static void setUserSecret(String userSecret) {
		if (StringUtils.isEmpty(userSecret)) {
			return;
		}
		USER_COOKIE_SECRET = userSecret;
	}

	/**
	 * 返回当前用户
	 * @param request
	 * @param response
	 * @return 
	 */
	public static Long currentUser(HttpServletRequest request, HttpServletResponse response) {
		String cookieKey = USER_COOKIE_KEY;
		// 获取cookie信息
		String userCookie = getCookie(request, cookieKey);
		// 1.cookie为空，直接清除
		if (StringUtils.isEmpty(userCookie)) {
			removeCookie(request, response, cookieKey);
			return null;
		}
		// 2.解密cookie
		String cookieInfo = null;
		// cookie 私钥
		String secret = USER_COOKIE_SECRET;
		try {
			cookieInfo = AESUtils.decrypt(secret, userCookie);
		} catch (RuntimeException e) {
			// ignore
		}
		// 3.异常或解密问题，直接清除cookie信息
		if (StringUtils.isEmpty(cookieInfo)) {
			removeCookie(request, response, cookieKey);
			return null;
		}
		String[] userInfo = cookieInfo.split("~");
		// 4.规则不匹配
		if (userInfo.length < 3) {
			removeCookie(request, response, cookieKey);
			return null;
		}
		String userId   = userInfo[0];
		String oldTime  = userInfo[1];
		String maxAge   = userInfo[2];
		// 5.判定时间区间，超时的cookie清理掉
		if (!"-1".equals(maxAge)) {
			long now  = System.currentTimeMillis();
			long time = Long.parseLong(oldTime) + (Long.parseLong(maxAge) * 1000);
			if (time <= now) {
				removeCookie(request, response, cookieKey);
				return null;
			}
		}
		return Long.valueOf(userId);
	}

	/**
	 * 用户登陆状态维持
	 * 
	 * cookie设计为: des(私钥).encode(userId~time~maxAge~ip)
	 * 
	 * @param c 控制器
	 * @param user  用户model
	 * @param remember   是否记住密码、此参数控制cookie的 maxAge，默认为-1（只在当前会话）<br>
	 *                   记住密码默认为一周
	 * @return void
	 * @throws UnsupportedEncodingException 
	 */
	public static void loginUser(HttpServletRequest request,HttpServletResponse response, User user, boolean... remember) throws UnsupportedEncodingException {
		// 获取用户的id、nickName
		String uid     = user.getId() + "";
		// 当前毫秒数
		long   now      = System.currentTimeMillis();
		// 超时时间
		int    maxAge   = -1;
		if (remember.length > 0 && remember[0]) {
			maxAge      = 60 * 60 * 24 * 7;
		}
		// 用户id地址
		String ip = getIP(request);
		// 构造cookie
		StringBuilder cookieBuilder = new StringBuilder()
			.append(uid).append("~")
			.append(now).append("~")
			.append(maxAge).append("~")
			.append(ip);

		// cookie 私钥
		String secret = USER_COOKIE_SECRET;
		// 加密cookie
		String userCookie = AESUtils.encrypt(secret, cookieBuilder.toString());

		String cookieKey = USER_COOKIE_KEY;
		// 设置用户的cookie、 -1 维持成session的状态
		setCookie(request,response, cookieKey, userCookie, maxAge);
	}

	/**
	 * 退出即删除用户信息
	 * @param c
	 * @return void
	 */
	public static void logoutUser(HttpServletRequest request,HttpServletResponse response) {
		removeCookie(request,response, USER_COOKIE_KEY);
	}

	/**
	 * 读取cookie
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if(null != cookies){
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 清除 某个指定的cookie 
	 * @param response
	 * @param key
	 */
	public static void removeCookie(HttpServletRequest request,HttpServletResponse response, String key) {
		setCookie(request,response, key, null, 0);
	}

	/**
	 * 设置cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAgeInSeconds
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		// 指定为httpOnly保证安全性
		int version = request.getServletContext().getMajorVersion();
		if (version >= 3) {
			cookie.setHttpOnly(true);
		}
		response.addCookie(cookie);
	}

	/**
	 * 获取浏览器信息
	 * @param request
	 * @return String
	 */
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
