package com.epam.rd.movietheater.model.dto;

import java.time.LocalDate;

public class UserDto implements Dto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String nickName;
    private String password;
}
