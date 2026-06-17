package services;

import com.mongodb.client.model.Filters;
import dao.GenericDAO;
import java.time.LocalDateTime;
import objects.User;
import util.SecurityUtils;

public class AuthService {

    private final GenericDAO<User> userDAO = new GenericDAO<>("Admin", User.class);

    public boolean login(String username, String plainPassword) {
        String hashedInput = SecurityUtils.getHash(plainPassword, SecurityUtils.SHA_256);

        User user = userDAO.findOne(Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", hashedInput)
        ));

        if (user != null) {
            user.setLastLogin(LocalDateTime.now());
            userDAO.update(Filters.eq("username", username), user);
            return true;
        }
        return false;
    }

    public void registerUser(String fullname, String username, String plainPassword) {
        String hashedPassword = SecurityUtils.getHash(plainPassword, SecurityUtils.SHA_256);
        User newUser = new User(fullname, username, hashedPassword, null);
        userDAO.save(newUser);
    }
}