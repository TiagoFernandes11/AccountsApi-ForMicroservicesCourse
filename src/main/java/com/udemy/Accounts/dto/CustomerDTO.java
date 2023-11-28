package com.udemy.Accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The lenght of the customer name shoud be between 5 an 30")
    private String name;

    @NotEmpty(message = "E-mail can not be null or empty")
    @Email(message = "E-mail address should be a valid value")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDTO accountsDTO;
}
