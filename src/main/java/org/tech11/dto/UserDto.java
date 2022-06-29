package org.tech11.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
public class UserDto implements Serializable {

    private String id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String birthday;
    private LocalDate dateOfBirth;
    private String lastName;
    private String otherNames;
    private String created;
    private String updated;
}
