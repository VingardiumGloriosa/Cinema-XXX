package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByAdmin(boolean admin);
    List<User> findByAdminIsTrue();

}
