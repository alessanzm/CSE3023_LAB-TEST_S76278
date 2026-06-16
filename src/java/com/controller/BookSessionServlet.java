/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.DAO.SessionDAO;
import com.Model.SessionBean;

/**
 *
 * @author MP2-4
 */
@WebServlet("/")
public class BookSessionServlet extends HttpServlet {
    
    private SessionDAO sessionDAO = new SessionDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        
        if ("book".equals(action)) {
            SessionBean newStudent = new SessionBean();
            newStudent.setStudent_name(request.getParameter("student_name"));
            newStudent.setBranch_location(request.getParameter("branch_location"));
            newStudent.setLesson_type(request.getParameter("lesson_type"));
            
            boolean isSuccess = sessionDAO.bookSession();
            if (isSuccess) {
                response.getWriter().println("Book Successful!<a href='schedule.jsp'>SCHEDULE</a>");
            } else {
                response.getWriter().println("Book Failed!");
            }
        }
    }
}


    

    