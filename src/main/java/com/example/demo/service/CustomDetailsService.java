package com.example.demo.service;

import com.example.demo.domain.CustomUser;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class CustomDetailsService implements UserDetailsService {

    @Value("${dummy.authorized.username}")
    private String dummyUsername;

    @Value("${dummy.authorized.password}")
    private String dummyPassword;

    public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity authorizedUser = new UserEntity();
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
        grantedAuthoritiesList.add(grantedAuthority);
        authorizedUser.setUsername(dummyUsername);
        authorizedUser.setPassword(dummyPassword);
        authorizedUser.setGrantedAuthoritiesList(grantedAuthoritiesList);

        if (username.equals(authorizedUser.getUsername())) {
            log.info("authenticated!");
            CustomUser customUser = new CustomUser(authorizedUser);
            return customUser;
        } else {
            throw new UserNotFoundException("USER001", "USER NOT FOUND", "REF00001");
        }

    }
}
