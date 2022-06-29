package org.tech11.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
public class CreateUser implements Serializable {

    @Email //for some reason this validator isn't working
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    @Size(min = 8)
    private String confirmPassword;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String dateOfBirth;

    @NotEmpty
    private String lastName;
    private String otherNames;
}
