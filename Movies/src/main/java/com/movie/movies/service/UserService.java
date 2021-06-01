package com.movie.movies.service;

import com.movie.movies.accessingdatamysql.UserRepository;
import com.movie.movies.models.info.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);

        return user;
    }
}
