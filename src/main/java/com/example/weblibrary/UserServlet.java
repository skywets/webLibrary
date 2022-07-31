package com.example.weblibrary;

import dao.EducationDao;
import dao.UserDao;
import dao.connection.ConnectionBulder;
import dao.connection.SimpleConnectionBuilder;
import entity.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    ConnectionBulder connectionBulder;
    UserDao userDao;
    UserService userService;
    EducationDao educationDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        connectionBulder = new SimpleConnectionBuilder();
        userDao= new UserDao(connectionBulder);
        educationDao = new EducationDao(connectionBulder);
        userService = new UserService(userDao, educationDao);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request,response);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException{
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException | IOException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDao.getAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User getuser = userDao.get(id).orElseThrow();
        User existingUser = getuser;
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String educatioinStr = request.getParameter("educationId");
        long educationId = Long.parseLong(educatioinStr);
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String language = request.getParameter("language");
        String birthdaystr = request.getParameter("birthday");
        Date birthday = Date.valueOf(birthdaystr);
        User newUser = new User(login, password, educationId,name, country, language, birthday);
        userDao.create(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String educatioinStr = request.getParameter("educationId");
        long educationId = Long.parseLong(educatioinStr);
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String language = request.getParameter("language");
        String birthdaystr = request.getParameter("birthday");
        Date birthday = Date.valueOf(birthdaystr);

        User newUser = new User(login, password, educationId,name, country, language, birthday);
        userService.editUser(id, newUser);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.get(id).orElseThrow();
        userDao.delete(user);
        response.sendRedirect("list");

    }
}