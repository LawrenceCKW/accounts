package com.yuexin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Simple DTO for standardized API success responses.
 * <p>
 * Carries a status code and human-readable message.
 * Annotated with OpenAPI {@link io.swagger.v3.oas.annotations.media.Schema} for documentation.
 * Uses Lombok {@code @Data} and {@code @AllArgsConstructor} to generate boilerplate.
 */
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDto {

    /** Machine-friendly status code (e.g., "200", "OK"). */
    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    /** Human-friendly explanation of the outcome. */
    @Schema(
            description = "Status message in the response"
    )
    private String statusMsg;

}