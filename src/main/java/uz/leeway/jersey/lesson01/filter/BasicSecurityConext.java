package uz.leeway.jersey.lesson01.filter;

import uz.leeway.jersey.lesson01.entity.UserEntity;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class BasicSecurityConext implements SecurityContext {

    private  UserEntity user;
    private  boolean secure;

    public void BasicSecurityContext(UserEntity user, boolean secure) {
        this.user = user;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return user.getUsername();
            }
        };
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }@Override
    public boolean isSecure() { return secure; }

    @Override
    public boolean isUserInRole(String role) {
        return user.getRoles().contains(role);
    }
}

