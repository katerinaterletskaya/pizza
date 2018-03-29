package ru.mail.terletskaya.katerina.controller;

import ru.mail.terletskaya.katerina.service.OrderService;
import ru.mail.terletskaya.katerina.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserOrderAddServlet extends HttpServlet {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/userCart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        orderService.addOrder(req.getParameter("userName"), req.getParameterValues("idPizza"));
        Cart cart = Cart.get(session);
        cart.getIds().clear();
        session.removeAttribute("cart");
        session.removeAttribute("number");
        resp.sendRedirect("/user/cart");
    }
}
