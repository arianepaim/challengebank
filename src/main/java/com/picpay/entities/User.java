package com.picpay.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
