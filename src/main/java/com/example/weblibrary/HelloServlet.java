package com.example.weblibrary;

import dao.EducationDao;
import dao.UserDao;
import dao.connection.ConnectionBulder;
import dao.connection.SimpleConnectionBuilder;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(urlPatterns = "/hel")
public class HelloServlet extends HttpServlet {
    private String message;
    ConnectionBulder connectionBulder;
    UserDao userDao;
    UserService userService;
    EducationDao educationDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        message = "Hello World!";
        connectionBulder = new SimpleConnectionBuilder();
        userDao= new UserDao(connectionBulder);
        educationDao = new EducationDao(connectionBulder);
        userService = new UserService(userDao, educationDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(message);
    }

    public void destroy() {
    }
}