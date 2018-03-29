package ru.mail.terletskaya.katerina.controller;

import ru.mail.terletskaya.katerina.service.OrderService;
import ru.mail.terletskaya.katerina.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminOrderChangeServlet extends HttpServlet {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.updateStatusOrder(Integer.parseInt(req.getParameter("change")));
        resp.sendRedirect("/admin");
    }
}
