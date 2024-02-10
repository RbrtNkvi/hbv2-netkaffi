package is.hi.hbv501g.netkaffi.Controllers;

import is.hi.hbv501g.netkaffi.Persistence.Entities.User;
import is.hi.hbv501g.netkaffi.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates new user
     *
     * @param user new user information
     * @return user if successful
     */
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public User signupPost(@RequestBody User user) {
        if (checkSignupForBlanks(user.getUsername(), user.getPassword())) {
            return null;
        }
        User exists = userService.findByUsername((user.getUsername()));
        if (exists == null) {
            return userService.save(user);
        }
        return null;
    }

    public boolean checkSignupForBlanks(String username, String password) {
        return !username.isBlank() && !password.isBlank();
    }

    @GetMapping(value="/users/{name}")
    public User getUser(@PathVariable String name) {
        return userService.findByUsername(name);
    }

    /**
     * Verifies user exists and logs them in
     *
     * @param user user information
     * @return redirect to main.html or product.html depending on if user is an admin
     */
    @PostMapping(value="/login",consumes = "application/json", produces = "application/json")
    public User loginPost(@RequestBody User user) {
        return userService.login(user);
    }
}
