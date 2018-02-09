package com.aimeikongjian.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.aimeikongjian.util.AES;
import com.aimeikongjian.util.RedisUtil;

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
		
		String wxSessionObj = RedisUtil.getString(sessionId);
		if(null == wxSessionObj){
			response.getWriter().append("error");
			return;
		}
		System.out.println("wxSessionObj:"+wxSessionObj);
		String sessionKey = wxSessionObj.split("#")[0];
		

		try {
			AES aes = new AES();
			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
			if(null != resultByte && resultByte.length > 0){
				String userInfo = new String(resultByte, "UTF-8");
				System.out.println("userinof:"+userInfo);
				response.getWriter().append(userInfo);
				return;
			}
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		response.getWriter().append("error");
	}

}
