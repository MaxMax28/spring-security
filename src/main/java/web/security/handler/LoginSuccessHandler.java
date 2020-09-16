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
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        Set<Role> userRolesList = user.getRoles();

        if (userRolesList.size() > 1) {
            httpServletResponse.sendRedirect("/admin/allUsers");
        } else {
                httpServletResponse.sendRedirect("/user" + "?name=" + user.getName());
        }


//        for (Role role : userRolesList) {
//            if (role.getName().equals("ROLE_ADMIN")) {
//                httpServletResponse.sendRedirect("/admin/allUsers");
//            } else {
//                httpServletResponse.sendRedirect("/user" + "?name=" + user.getName());
//            }
//            //userRolesList.add(role.getName());
//        }
////        if (userRolesList.contains("ROLE_USER") && !userRolesList.contains("ROLE_ADMIN")) {
//            httpServletResponse.sendRedirect("/user" + "?name=" + user.getName());
//        } else if (userRolesList.contains("ROLE_ADMIN")) {
//            httpServletResponse.sendRedirect("/admin/allUsers");
//        } else {
//            httpServletResponse.sendRedirect("/");
//        }
    }
}