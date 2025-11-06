package com.yuexin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

/**
 * DTO carrying account details exposed via the API.
 * <p>Validation ensures a 10-digit numeric account number when represented as a number.</p>
 */
@Data
@Builder
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    /** 10-digit numeric identifier (no leading zeros preserved). */
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    @Schema(
            description = "Account Number of Bank account", example = "5504433243"
    )
    private Long accountNumber;

    /** Account type of bank account */
    @NotEmpty(message = "AccountType can not be a null or empty")
    @Schema(
            description = "Account type of Bank account", example = "Savings"
    )
    private String accountType;

    /** Branch address of bank account */
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    @Schema(
            description = "Bank branch address", example = "123 Yishun"
    )
    private String branchAddress;
}