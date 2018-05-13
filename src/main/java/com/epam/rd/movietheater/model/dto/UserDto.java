package com.epam.rd.movietheater.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto implements Dto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String nickName;
    private String password;
}
