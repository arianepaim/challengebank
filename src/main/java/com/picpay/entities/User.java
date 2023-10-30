package com.picpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @NotNull
    @NotBlank
    private String nameFirst;
    @NotNull
    @NotBlank
    private String nameLast;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String document;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotNull
    @NotBlank
    private String password;
    private Double balance;
}
