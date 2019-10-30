package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) 
					throws ServletException, IOException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
			
		} catch(Exception ex) {
			System.out.println("forward오류 : " + ex);
		}
	}
}
