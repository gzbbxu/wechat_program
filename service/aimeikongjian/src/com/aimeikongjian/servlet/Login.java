package com.aimeikongjian.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aimeikongjian.service.WXService;
import com.aimeikongjian.util.ConstantUtils;
import com.aimeikongjian.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name = "Login",urlPatterns="/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		String code = request.getParameter("code");
		System.out.println("code:"+code);
		//response.getWriter().write("code="+code);
		 Map<String,Object>result=new HashMap<>();
		String url=ConstantUtils.getSessionKeyUrl+"?appid="+ ConstantUtils.appId+
                "&secret="+ConstantUtils.secret+"&js_code="+code+"&grant_type="+ConstantUtils.grantType;
		JSONObject httpResult=HttpUtils.httpGet(url);
		//success:{"openid":"okSgY45i02oZZ2sQxC_S0FmYIk68","session_key":"unqqjx7ZOISIEA09VmLujw=="}
		System.out.println("http:"+httpResult);
		if(httpResult.getString("openid")==null) {
			   response.getWriter().write("error");
			   return;
		}
		String openid = httpResult.get("openid").toString();
		String sessionkey = httpResult.get("session_key").toString();
        result.put("openid",openid);
        result.put("session_key",sessionkey);
       // result.put("expires_in",httpResult.get("expires_in"));
        System.out.println("success:"+openid+":sessionkey:"+sessionkey);
        String thirdSession = WXService.create3rdSession(openid, sessionkey);
        System.out.println("thridSession:"+thirdSession);
        response.getWriter().write(thirdSession);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.getWriter().write("Hello Servlet3.0");
	}

}
