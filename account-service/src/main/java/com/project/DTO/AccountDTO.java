package com.project.DTO;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AccountDTO{

    @NotNull(message = "accountNumber cannot be null")
    @Length(min = 18, max = 18, message = "accountNumber must be 18 digits")
    @Schema(
        description = "account_number",
        example = "123456789012345678"
    )
    private Long account_number;

    @NotEmpty(message = "accountType cannot be null")
    @Schema(
        description = "account_type",
        example = "SAVINGS"
    )
    private String account_type;

    @NotEmpty(message = "branchAddress cannot be null")
    @Schema(
        description = "branch_address",
        example = "123 Main St"
    )
    private String branch_address;

    @NotNull(message = "customerDTO cannot be null")
    @Schema(
        description = "customerDTO",
        example = "customerDTO"
    )
    private CustomerDTO customerDTO;


}
