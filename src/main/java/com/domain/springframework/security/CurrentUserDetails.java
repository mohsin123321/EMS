package com.domain.springframework.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class CurrentUserDetails implements UserDetails {
    private String email;
    private String username;
    private List<String> groups;
    private Role role;
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    /**
     * Empty implementation.
     * @return null.
     */
    @Override
    public String getPassword() {
        return null;
    }
}
