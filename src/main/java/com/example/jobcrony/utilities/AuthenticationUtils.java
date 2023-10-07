package com.example.jobcrony.utilities;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.security.JobCronyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.example.jobcrony.utilities.AppUtils.USER_NOT_AUTHORIZED;

@Component
public class AuthenticationUtils {

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JobCronyUserDetails userDetails = (JobCronyUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }

    public User getCurrentUser(Role role) throws UserNotAuthorizedException {
        User user = this.getCurrentUser();
        if(!user.getRoles().contains(role)){
            throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        } else {
            return user;
        }
    }

    public Boolean isRole(Role role){
        User user = this.getCurrentUser();
        return user.getRoles().contains(role);
    }
}
