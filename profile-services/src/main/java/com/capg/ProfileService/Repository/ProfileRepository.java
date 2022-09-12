package com.capg.ProfileService.Repository;

import com.capg.ProfileService.Model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProfileRepository extends MongoRepository<Profile, Integer> {

    public Profile findByEmail(String emailId);
}
