package org.tech11.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EditUserDto implements Serializable {


    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    private String otherNames;
}
