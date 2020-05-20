package main.java.bean;



import main.java.dao.UserDAO;
import main.java.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean(name = "UserController")
@RequestScoped
public class UserController {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String checkUserExistence(User user) throws Exception {
        UserDAO userDAO = new UserDAO();
        FacesContext ctx = FacesContext.getCurrentInstance();
        int userId = userDAO.checkCredentials(user);
        if (userId == -1) {
            ctx.addMessage("loginForm:credentials", new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong password", "Check login/password couple"));
        } else {
            Map<String, Object> sessionMap = ctx.getExternalContext().getSessionMap();
            sessionMap.put("userId", userId);
            ctx.addMessage("loginForm:credentials", new FacesMessage(FacesMessage.SEVERITY_WARN, "your id is" + String.valueOf(userId), "Check login/password couple"));
            return "pay";
        }
        return "/";
    }

    private User user = new User();
}
