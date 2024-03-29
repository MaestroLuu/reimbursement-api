package com.revature.project2.common;

import com.revature.project2.common.exceptions.AuthenticationException;
import com.revature.project2.common.exceptions.AuthorizationException;
import com.revature.project2.users.UserResponse;

import javax.servlet.http.HttpSession;

public class SecurityUtils {

    private SecurityUtils() {
        super();
    }

    public static void enforceAuthentication(HttpSession userSession) {
        if (userSession == null) {
            throw new AuthenticationException("Could not find HTTP session on request. Please log in to access this endpoint.");
        }
    }

    // TODO look into NPE that occurs when GET /users with no session
    public static void enforcePermissions(HttpSession userSession, String expectedRole) {
        enforceAuthentication(userSession);
        if (!((UserResponse) userSession.getAttribute("authUser")).getRole().equals(expectedRole)) {
            throw new AuthorizationException();
        }
    }

}
