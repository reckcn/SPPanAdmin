package net.sppan.base.controller.admin;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult login(String username,String password,boolean rememberMe) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			return JsonResult.failure("用户名或者密码不能为空");
		}
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return redirect("/admin/login");
	}

}
