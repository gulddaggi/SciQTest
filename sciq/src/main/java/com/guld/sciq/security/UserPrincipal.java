package com.guld.sciq.security;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import com.guld.sciq.user.entity.User;

@Getter
public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String email;
    private final String nickName;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String email, String nickName, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
		this.nickName = nickName;
		this.password = password;
        this.authorities = authorities;
    }

    @Builder
    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getNickName(),
                user.getPassword(),
                authorities
        );
    }


    //For UserDetails
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}