package com.internship.ratingbackend.service;

import com.internship.ratingbackend.model.AppUser;
import com.internship.ratingbackend.model.MyUserDetails;
import com.internship.ratingbackend.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsImp implements UserDetailsService {

    @Autowired
    private CustomUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findCustomUsersByEmail(email);

        if (user.isPresent()) {
            return new MyUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Could not find user");
    }
}
