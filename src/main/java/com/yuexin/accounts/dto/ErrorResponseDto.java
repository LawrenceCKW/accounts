package com.yuexin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Standardized error response payload for API endpoints.
 * <p>
 * Captures the request path, an {@link org.springframework.http.HttpStatus} code,
 * a human-readable message, and the timestamp when the error occurred.
 * Documented for OpenAPI via {@link io.swagger.v3.oas.annotations.media.Schema}.
 */
@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    /** API path invoked by the client when the error occurred. */
    @Schema(
            description = "API path invoked by client"
    )
    private  String apiPath;

    /** HTTP status code describing the error (e.g., 400, 404, 500). */
    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    /** Human-readable explanation of the error. */
    @Schema(
            description = "Error message representing the error happened"
    )
    private  String errorMessage;

    /** Timestamp of when the error occurred. */
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;

}