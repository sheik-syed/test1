package com.ecomshop.deskplus.services;

import com.ecomshop.deskplus.models.RolesEntity;
import com.ecomshop.deskplus.models.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Sheik Syed Ali
 * Date: 24 Sep 2021
 */
public class AuthUserDetails implements UserDetails {

    private UsersEntity user;

    public AuthUserDetails(UsersEntity usersEntity){
        user = usersEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<RolesEntity> userRolesEntities = user.getRoles();
        for(RolesEntity userRolesEntity : userRolesEntities){
            authorities.add(new SimpleGrantedAuthority(userRolesEntity.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
