package eu.senla.socialnetwork.security.auth;

import eu.senla.socialnetwork.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuth implements Authentication {

    private final User user;
    private boolean auth = true;

    public UserAuth(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user;
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return auth;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.auth = b;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}

