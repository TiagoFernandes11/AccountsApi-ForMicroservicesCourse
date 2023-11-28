package com.udemy.Accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
    private Integer accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be null or empty")
    private String branchAddress;
}
