package com.aimeikongjian.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DecodeUser
 */
@WebServlet(name = "DecodeUser",urlPatterns="/DecodeUser")
public class DecodeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget.....");
		String encryptedData = request.getParameter("encryptedData");
		String sessionId = request.getParameter("sessionId");
		String iv = request.getParameter("iv");
		System.out.println("e:"+encryptedData+":se:"+sessionId+":iv:"+iv);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
