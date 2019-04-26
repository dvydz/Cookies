package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");	
	PrintWriter pw=response.getWriter();
	String userName="",password="";
	try {
		Cookie[] cookies=request.getCookies();
		for(int i=0;i<cookies.length;i++)
		{
			if(cookies[i].getName().equals("userName"))
			{
				userName=cookies[i].getValue();
			}
			else if(cookies[i].getName().equals("password"))
			{
				password=cookies[i].getValue();
			}
		}
	}									
	catch(Exception ex) {					
		ex.printStackTrace();
	}
	pw.print("<html><body bgcolor=lightyellow><form action=CookieServlet method=Post><table align=center>");
	pw.print("<tr><td>UserId</td><td><input type=text name=user value="+userName+"></td></tr>");   //+userName+ means variable's value
	pw.print("<tr><td>Password</td><td><input type=password name=pwd value="+password+"></td></tr>");  //value=_____ shows the saved id and pw.
	pw.print("<tr><td></td><td><input type=checkbox name=remember value=rememberMe>Remember me</td></tr>");
	pw.print("<tr><td></td><td><input type=submit value=login></td></tr>");
	pw.print("</table></form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String  userName=request.getParameter("user");
		String  password=request.getParameter("pwd");
		String  rememberMe=request.getParameter("remember");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		if(rememberMe.equals("rememberMe"))
		{
			Cookie ck1=new Cookie("userName", userName);
			Cookie ck2=new Cookie("password", password);
			//Cookies expiry date
			ck1.setMaxAge(60*60);
			ck2.setMaxAge(60*60);
			
			//add cookies to client browser
			response.addCookie(ck1);
			response.addCookie(ck2);
			pw.print("<h3>Cookies are created</h3>");
		}
			
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
	}

}
