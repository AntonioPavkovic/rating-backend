package com.internship.ratingbackend.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * CustomUserDetails class represents an entity
 *
 * @see CustomUser
 */

public class CustomUserDetails implements UserDetails {

    private final CustomUser customUser;

    /**
     * Custom constructor
     *
     * @param customUser
     */

    public CustomUserDetails(CustomUser customUser) {
        this.customUser = customUser;
    }

    /**
     * Method that grants authorities to user
     *
     * @return authorities
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + customUser.getCustomUserRole()));

        return authorities;
    }

    /**
     * Custom getters that return either null or false
     *
     * @return null/false
     */

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
