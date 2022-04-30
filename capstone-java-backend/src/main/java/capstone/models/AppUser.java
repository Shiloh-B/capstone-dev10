package capstone.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppUser extends User {

    private int appUserId;
    private String username;
    private String passwordHash;
    private boolean isDisabled;

    private static final String AUTHORITY_PREFIX = "ROLE_";

    public AppUser(int appUserId, String username, String passwordHash, boolean isDisabled, List<String> roles) {

        super(username, passwordHash, !isDisabled, true, true, true,
                convertRolesToAuthorities(roles));

        this.appUserId = appUserId;
    }

    private List<String> roles = new ArrayList<>();

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public static List<GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for(String role : roles) {
            Assert.isTrue(!role.startsWith(AUTHORITY_PREFIX),
                    () -> String.format("%s cannot start with %s (it is automatically added)",
                            role, AUTHORITY_PREFIX));
            authorities.add(new SimpleGrantedAuthority(AUTHORITY_PREFIX + role));
        }
        return authorities;
    }

    public static List<String> convertAuthoritiesToRoles(Collection<GrantedAuthority> authorities) {
        return authorities.stream()
                .map(a -> a.getAuthority().substring(AUTHORITY_PREFIX.length()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppUser appUser = (AppUser) o;
        return getAppUserId() == appUser.getAppUserId() && isDisabled == appUser.isDisabled && getUsername().equals(appUser.getUsername()) && passwordHash.equals(appUser.passwordHash) && roles.equals(appUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAppUserId(), getUsername(), passwordHash, isDisabled, roles);
    }
}
