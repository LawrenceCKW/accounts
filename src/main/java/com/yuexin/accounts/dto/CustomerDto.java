package com.yuexin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * DTO representing a customer together with (optional) account details.
 * <p>
 * Validations enforce non-empty name/email with length and format checks.
 * Mobile number is validated as 8 digits (current regex also permits empty).
 */
@Data
@Builder
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    /** Customer display name (5–30 chars). */
    @Schema(
            description = "Name of the customer", example = "Wong YueXin"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    /** Contact email address. */
    @Schema(
            description = "Email address of the customer", example = "yuexin@gmail.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    /** Mobile number (8 digits). Note: regex (^$|[0-9]{8}) allows empty value. */
    @Schema(
            description = "Mobile Number of the customer", example = "12345678"
    )
    @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile number must be 8 digits")
    private String mobileNumber;

    /** Associated account details for this customer. */
    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}