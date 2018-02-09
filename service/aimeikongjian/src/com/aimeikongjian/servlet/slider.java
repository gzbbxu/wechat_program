package com.aimeikongjian.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aimeikongjian.bean.Banner;
import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class slider
 */
@WebServlet(name = "slider",urlPatterns="/slider")
public class slider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1084418797,2535675717&fm=200&gp=0.jpg
		
		List<Banner> list = new ArrayList<Banner>(); 
		Banner banner = new Banner();
		banner.setUrl("http://127.0.0.1:8080/aimeikongjian/resources/aaa.jpg");
		
		Banner banner1 = new Banner();
		banner1.setUrl("http://127.0.0.1:8080/aimeikongjian/resources/aaa.jpg");
		
		list.add(banner);
		list.add(banner1);
		 String bannerstring = JSON.toJSONString(list);
		System.out.println("banner:"+bannerstring);
		response.getWriter().append(bannerstring);
	}


}
