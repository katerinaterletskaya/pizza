package ru.mail.terletskaya.katerina.controller;

import ru.mail.terletskaya.katerina.service.PizzaService;
import ru.mail.terletskaya.katerina.service.PizzaServiceImpl;
import ru.mail.terletskaya.katerina.service.model.OrderStatus;
import ru.mail.terletskaya.katerina.service.model.PizzaDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCatalogServlet extends HttpServlet {
    private PizzaService pizzaService = PizzaServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PizzaDTO> listAllPizzas = pizzaService.getPizzas();
        req.setAttribute("listPizzas", listAllPizzas);
        req.getRequestDispatcher("/WEB-INF/pages/userCatalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PizzaDTO pizzaByImageName = pizzaService.getPizzaByImageName(req.getParameter("pizzaImageName"));
        Cart cart = Cart.get(session);
        cart.addItem(pizzaByImageName);
        int number = cart.getIds().size();
        session.setAttribute("number", number);
        doGet(req, resp);
    }
}
