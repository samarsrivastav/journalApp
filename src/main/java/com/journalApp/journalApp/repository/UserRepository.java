package com.journalApp.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalApp.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId>{
    User findByUsername(String username);
}
