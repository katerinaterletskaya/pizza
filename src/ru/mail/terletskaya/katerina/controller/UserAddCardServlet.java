package ru.mail.terletskaya.katerina.controller;

import ru.mail.terletskaya.katerina.controller.validator.NumberCardValidator;
import ru.mail.terletskaya.katerina.service.CardService;
import ru.mail.terletskaya.katerina.service.CardServiceImpl;
import ru.mail.terletskaya.katerina.service.model.CardDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAddCardServlet extends HttpServlet{
    private NumberCardValidator numberCardValidator= new NumberCardValidator();
    private CardService cardService= CardServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/userCardAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numberCard = req.getParameter("numberCard");
        if(numberCardValidator.validateNumberCard(numberCard)){
            HttpSession session = req.getSession();
            String userName=(String)session.getAttribute("user");
            Integer month= Integer.parseInt(req.getParameter("month"));
            Integer year = Integer.parseInt(req.getParameter("year"));
            String description = req.getParameter("description");
            CardDTO infoAboutNewCard = CardDTO.newBuilder()
                    .number(numberCard)
                    .month(month)
                    .year(year)
                    .description(description)
                    .build();
            cardService.addCardForUser(infoAboutNewCard, userName);
            resp.sendRedirect("/user/card");
        } else{
            resp.sendRedirect("/user/card/add");
        }
    }
}
