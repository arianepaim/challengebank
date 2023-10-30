package com.picpay.shared;

import lombok.Data;

@Data
public class ShopkeeperDTO {

    private Long id;
    private String nameFirst;
    private String nameLast;
    private String document;
    private String email;
    private String password;
}
