package com.picpay.services;

import com.picpay.entities.Transaction;
import com.picpay.repositories.TransactionRepository;
import com.picpay.shared.CommonUserDTO;
import com.picpay.shared.ShopkeeperDTO;
import com.picpay.shared.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CommonUserService service;

    @Autowired
    ShopkeeperService shopkeeperService;

    public void transfer(TransactionDTO dto) {
        if (dto.value() <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }
        CommonUserDTO payer = service.findById(dto.payerId());
        CommonUserDTO payee = service.findById(dto.payeeId());
        ShopkeeperDTO payeeShop = shopkeeperService.findById(dto.payeeId());


    }
}


