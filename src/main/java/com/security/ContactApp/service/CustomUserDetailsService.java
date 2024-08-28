package com.security.ContactApp.service;

import com.security.ContactApp.dao.User_Registration;
import com.security.ContactApp.repository.RegistrationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RegistrationRepository registrationRepository;

    public CustomUserDetailsService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_Registration userRegistration = registrationRepository.findByuserName(username);
        if (userRegistration == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.withUsername(userRegistration.getUserName())
                .password("{noop}"+userRegistration.getPassword())
                .authorities("ROLE_USER") // Specify roles or authorities
                .build();
    }
}
