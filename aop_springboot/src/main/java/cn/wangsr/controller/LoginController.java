package cn.wangsr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.wangsr.entits.User;

@Controller
public class LoginController {

	@RequestMapping("/lgs")
	public String Login() {
		
		return "logs/logger.html";
	}
	
	
	
	@RequestMapping("/lg")
	@ResponseBody
	public String   withLog(User user) {
		 user.setAge(19);
		 user.setUsername("jjjj");
			
	     Object   json_user=JSONArray.toJSON(user);
	     String result = "{\"code\":\""+00+"\",\"msg\":\""+"gg"+"\",\"count\":"+99+",\"data\":"+json_user+"}";
		 System.out.println("true--test---execute");
		return result;
	}

	
	@RequestMapping("/lg2")
	@ResponseBody
	public String withLog2() {
		@SuppressWarnings("unused")
		int a=10/0;
		return "jjjjjjjjjjjjj";
	}
	
}
