package web.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//                                        HttpServletResponse httpServletResponse,
//                                        Authentication authentication) throws IOException, ServletException {
//        //httpServletResponse.sendRedirect("/hello");
//        httpServletResponse.sendRedirect("/");
//    }

    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse,
                                          Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        List<String> userRolesList = new ArrayList<>();

        for (Role role : user.getRoles()) {
            userRolesList.add(role.getName());
        }
        if (userRolesList.contains("USER")) {
            httpServletResponse.sendRedirect("/user");
        } if (userRolesList.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin/allUsers");
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }
}