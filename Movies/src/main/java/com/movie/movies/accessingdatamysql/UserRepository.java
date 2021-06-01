package com.movie.movies.accessingdatamysql;

import com.movie.movies.models.info.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmail(String email);

}
