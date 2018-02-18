package com.marketplace.repository;

import com.marketplace.resource.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String userId);

}
