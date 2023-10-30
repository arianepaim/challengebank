package com.picpay.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tb_shopkeeper")
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Shopkeeper extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
