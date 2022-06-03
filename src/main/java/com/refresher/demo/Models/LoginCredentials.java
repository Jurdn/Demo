package com.refresher.demo.Models;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginCredentials {
    @NonNull
    @NotEmpty
    @NotBlank
    private String username;
    @NonNull
    @NotEmpty
    @NotBlank
    private String password;
}
