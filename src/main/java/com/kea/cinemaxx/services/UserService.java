package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.UserDTO;
import com.kea.cinemaxx.entities.Ticket;
import com.kea.cinemaxx.entities.User;
import com.kea.cinemaxx.repositiories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return UserDTO.UserDTOSfromUser(users);
    }

}
