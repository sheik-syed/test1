package com.ecomshop.deskplus.services;

import com.ecomshop.deskplus.models.UsersEntity;
import com.ecomshop.deskplus.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author: Sheik Syed Ali
 * Date: 24 Sep 2021
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsersEntity usersEntity = usersRepository.findByEmail(email);
        if(usersEntity == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new AuthUserDetails(usersEntity);
    }
}
