package ru.mail.terletskaya.katerina.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.mail.terletskaya.katerina.service.model.PizzaDTO;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = Cart.get(session);
        List<PizzaDTO> listPizzasInCart = cart.getIds();
        req.setAttribute("listCart", listPizzasInCart);
        req.setAttribute("sizeListCart", listPizzasInCart.size());
        req.getRequestDispatcher("/WEB-INF/pages/userCart.jsp").forward(req, resp);
    }
}
