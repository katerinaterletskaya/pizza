package ru.mail.terletskaya.katerina.controller;

import ru.mail.terletskaya.katerina.service.model.PizzaDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<PizzaDTO> cart = new ArrayList<>();

    public synchronized void addItem(PizzaDTO pizza) {
        cart.add(pizza);
    }

    public synchronized List<PizzaDTO> getIds() {
        return new ArrayList<>(cart);
    }

    public static Cart get(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
