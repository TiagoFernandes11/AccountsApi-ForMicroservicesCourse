package com.udemy.Accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountsDTO {
    @Schema(
            description = "Number of Eazy Bank account"
    )
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
    private Integer accountNumber;

    @Schema(
            description = "Account type for Eazy Bank account", example = "Savings"
    )
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Eazy Bank BranchAddress"
    )
    @NotEmpty(message = "BranchAddress can not be null or empty")
    private String branchAddress;
}
