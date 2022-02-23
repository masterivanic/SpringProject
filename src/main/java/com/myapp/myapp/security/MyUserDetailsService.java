package com.myapp.myapp.security;

import java.util.Collections;
import java.util.Optional;

import com.myapp.myapp.dao.UserDetailsService;
import com.myapp.myapp.dao.UserRepository;
import com.myapp.myapp.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// public class MyUserDetailsService implements UserDetailsService {

//     // @Autowired
//     // private UserRepository userRepo;

//     // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//     //     Optional<User> userRes = userRepo.findByEmail(email);
//     //     if(userRes.isEmpty()){
//     //         throw new UsernameNotFoundException("Could not findUser with email = " +email);
//     //         User user = userRes.get();
//     //         return new org.springframework.security.core.userdetails.User(email, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))

//     //     }
//     // }
// }
