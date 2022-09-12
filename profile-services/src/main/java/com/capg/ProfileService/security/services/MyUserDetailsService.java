package com.capg.ProfileService.security.services;




import com.capg.ProfileService.Model.Profile;
import com.capg.ProfileService.Repository.ProfileRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

@Autowired
   private ProfileRepository  profileRepository;

    @Override
    public Profile loadUserByUsername(String email) throws UsernameNotFoundException {
//        List<SimpleGrantedAuthority> roles=null;
        Profile  adminInfo = profileRepository.findByEmail(email);
//        if(adminInfo== null) {
//            return new UsernameNotFoundException(email);
//        }
//        else {
           // roles= Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
           String username=adminInfo.getEmailId();
           String password=adminInfo.getPassword();
            return new User(username, password, new ArrayList<>());
//        }
        //return new User("foo", "foo", new ArrayList<>());
    }
}

