package com.agh.zlotowka.dto;

import com.agh.zlotowka.validation.MaxDecimalPlaces;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record SubplanRequest (
    @NotNull(message = "User Id cannot be null")
    @Positive(message = "User Id must be positive")
    Integer userId,

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 512, message = "Name cannot exceed 512 characters")
    String name,

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    @MaxDecimalPlaces(2)
    BigDecimal amount,

    @Size(max = 512, message = "Description cannot exceed 512 characters")
    String description,

    @NotNull(message = "Plan Id cannot be null")
    @Positive(message = "Plan Id must be positive")
    Integer planId
) {
}
