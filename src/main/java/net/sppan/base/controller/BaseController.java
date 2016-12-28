package net.sppan.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sppan.base.common.utils.WebUtils;
import net.sppan.base.entity.User;
import net.sppan.base.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	private IUserService userService;
	
	/**
     * 带参重定向
     *
     * @param path
     * @return
     */
    protected String redirect(String path) {
        return "redirect:" + path;
    }

    /**
     * 不带参重定向
     *
     * @param response
     * @param path
     * @return
     */
    protected String redirect(HttpServletResponse response, String path) {
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取当前登录的用户
     * @return
     */
    protected User currentUser() {
		Long userId = WebUtils.currentUser(request, response);
		if(userId != null){
			return userService.selectById(userId);
		}
		return null;
	}

}
