package org.softwire.training.slideshowbob.security.jwt;

import org.softwire.training.slideshowbob.security.UserPrincipal;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SlideShowBobUserDetailsService implements UserDetailsService {

    private final UsersService usersService;


    @Autowired
    public SlideShowBobUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(usersService.getUserByUsername(username));
    }
}
