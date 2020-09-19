package web.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        Set<Role> userRolesList = user.getRoles();

        if (userRolesList.contains(new Role("ROLE_ADMIN"))) {
            httpServletResponse.sendRedirect("/admin/allUsers");
        } else {
                httpServletResponse.sendRedirect("/user");
        }
    }
}