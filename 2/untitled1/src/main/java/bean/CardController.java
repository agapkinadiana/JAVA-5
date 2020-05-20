package main.java.bean;


import main.java.dao.UserDAO;
import main.java.entity.Card;
import main.java.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean(name = "CardController")
@RequestScoped
public class CardController {
    private Card card = new Card();

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String doPayment() throws Exception {
        System.out.println("Do payment clicked");
        UserDAO userDao = new UserDAO();
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = ctx.getExternalContext().getSessionMap();
        User user = userDao.getUser((int)sessionMap.get("userId"));
        System.out.println(user.getName());
        Card userCard = user.getCard();

        if(userCard.getSum() < card.getSum()) {
            ctx.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Столько нет((((", "Not enough money"));
        } else {
            userCard.setSum(userCard.getSum() - card.getSum());
            ctx.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Успешно списано " + card.getSum() + ", осталось " + userCard.getSum(), ""));
        }

        userDao.updateUser(user);
        return "payed";
    }
}
