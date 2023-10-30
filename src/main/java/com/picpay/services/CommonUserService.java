package com.picpay.services;

import com.picpay.entities.CommonUser;
import com.picpay.repositories.CommonUserRepository;
import com.picpay.shared.CommonUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUserService {

    @Autowired
    CommonUserRepository commonUserRepository;

    public CommonUserDTO findById(Long id) throws Exception {
        CommonUser user = commonUserRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));
        return new ModelMapper().map(user, CommonUserDTO.class);
    }

    public CommonUserDTO insert(CommonUserDTO dto) {
        dto.setId(null);

        CommonUser user = new ModelMapper().map(dto, CommonUser.class);
        user = commonUserRepository.save(user);
        dto.setId(user.getId());

        return dto;
    }

    public CommonUserDTO update(CommonUserDTO dto, Long id) throws Exception {
        if (!commonUserRepository.existsById(id)) {
            throw new Exception("Usuário não encontrado");
        }
        dto.setId(id);

        CommonUser user = new ModelMapper().map(dto, CommonUser.class);
        user = commonUserRepository.save(user);

        return dto;
    }

    public boolean delete(Long id) {
        if (commonUserRepository.existsById(id)) {
            commonUserRepository.deleteById(id);
        } else {
            return false;
        }
        return true;
    }
}
