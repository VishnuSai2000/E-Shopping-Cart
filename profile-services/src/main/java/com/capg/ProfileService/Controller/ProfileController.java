package com.capg.ProfileService.Controller;

import com.capg.ProfileService.Model.Profile;
import com.capg.ProfileService.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService service;

    @PostMapping("/save")
    public Profile addNewCustomerProfile(@RequestBody Profile user) {
        return service.addNewCustomerProfile(user);
    }

    @GetMapping("/show")
    public List<Profile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @PutMapping("update/{id}")
    public Profile updateProfile(@RequestBody Profile user, @PathVariable("id") int profileId) {
        return service.updateProfile(user, profileId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfileById(@PathVariable("id") int profileId) {
        service.deleteProfileById(profileId);
    }

}
