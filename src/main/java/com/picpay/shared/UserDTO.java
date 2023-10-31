package com.picpay.shared;

import com.picpay.entities.UserType;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String nameFirst;
    private String nameLast;
    private String document;
    private String email;
    private String password;
    private Double balance;
    private UserType userType;
}
