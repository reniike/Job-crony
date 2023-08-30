package com.example.jobcrony.security;

import com.example.jobcrony.data.models.User;
import com.example.jobcrony.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.USER_WITH_EMAIL_NOT_FOUND;

@Service
@AllArgsConstructor
public class JobCronyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND, email)
        ));
        return new JobCronyUserDetails(user);
    }
}
