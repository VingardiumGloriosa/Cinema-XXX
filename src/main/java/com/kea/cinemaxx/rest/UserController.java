package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.TicketDTO;
import com.kea.cinemaxx.dtos.UserDTO;
import com.kea.cinemaxx.entities.User;
import com.kea.cinemaxx.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/get-all")
    @ResponseBody
    List<UserDTO> getUsers() {
        return userService.getUsers();
    }



}
