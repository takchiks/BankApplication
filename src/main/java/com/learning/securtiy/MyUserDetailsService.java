package com.learning.securtiy;

import com.learning.entity.User;
import com.learning.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userRes = userRepo.findByUserName(username);
        if (userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with username = " + username);
        User user = userRes.get();
        System.out.println(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
        List authList = new ArrayList();
        authList.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassWord(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}