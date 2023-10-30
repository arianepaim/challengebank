package com.picpay.services;

import com.picpay.entities.CommonUser;
import com.picpay.entities.Shopkeeper;
import com.picpay.entities.exceptions.ResourceNotFoundException;
import com.picpay.repositories.CommonUserRepository;
import com.picpay.repositories.ShopkeeperRepository;
import com.picpay.shared.CommonUserDTO;
import com.picpay.shared.ShopkeeperDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopkeeperService {

    @Autowired
    ShopkeeperRepository shopkeeperRepository;

    public ShopkeeperDTO findById(Long id) {
        Shopkeeper user = shopkeeperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        return new ModelMapper().map(user, ShopkeeperDTO.class);
    }

    public ShopkeeperDTO insert(ShopkeeperDTO dto) {
        dto.setId(null);

        Shopkeeper user = new ModelMapper().map(dto, Shopkeeper.class);
        user = shopkeeperRepository.save(user);
        dto.setId(user.getId());

        return dto;
    }

    public ShopkeeperDTO update(ShopkeeperDTO dto, Long id) {
        if (!shopkeeperRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        dto.setId(id);

        Shopkeeper user = new ModelMapper().map(dto, Shopkeeper.class);
        user = shopkeeperRepository.save(user);

        return dto;
    }

    public boolean delete(Long id) {
        if (shopkeeperRepository.existsById(id)) {
            shopkeeperRepository.deleteById(id);
        } else {
            return false;
        }
        return true;
    }
}
