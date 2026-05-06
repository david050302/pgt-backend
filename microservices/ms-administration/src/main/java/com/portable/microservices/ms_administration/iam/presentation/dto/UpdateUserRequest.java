package com.portable.microservices.ms_administration.iam.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @NotBlank @Size(max = 80) String firstName,
    @NotBlank @Size(max = 80) String lastName,
    @NotNull Long headquarterId,
    String roleName
) {}
