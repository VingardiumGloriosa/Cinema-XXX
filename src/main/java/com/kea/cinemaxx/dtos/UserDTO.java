package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Ticket;
import com.kea.cinemaxx.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    int userId;
    boolean admin;

    public UserDTO(boolean admin) {
        this.admin = admin;
    }

    public UserDTO(User user) {
        this.admin = user.isAdmin();
        this.userId = user.getUserId();
    }

    public static List<UserDTO> UserDTOSfromUser(Iterable<User> users){
        List<UserDTO> dtos = StreamSupport.stream(users.spliterator(), false)
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        return dtos;
    }

    public static User userFromUserDTO(UserDTO user){
        return new User(user.isAdmin());
    }

}
