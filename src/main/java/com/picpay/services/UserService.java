package com.picpay.services;

import com.picpay.entities.User;
import com.picpay.entities.UserType;
import com.picpay.entities.exceptions.ResourceNotFoundException;
import com.picpay.repositories.UserRepository;
import com.picpay.shared.TransactionDTO;
import com.picpay.shared.UserDTO;
import com.picpay.view.AuthorizationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorizationService authorizationService;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com o id " + id + " não encontrado."));
        return new ModelMapper().map(user, UserDTO.class);
    }

    public UserDTO insert(UserDTO dto) {
        dto.setId(null);

        User user = new ModelMapper().map(dto, User.class);
        user = userRepository.save(user);
        dto.setId(user.getId());

        return dto;
    }
    public UserDTO update(UserDTO dto, Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário com o id " + id + " não encontrado.");
        }
        dto.setId(id);

        User user = new ModelMapper().map(dto, User.class);
        user = userRepository.save(user);

        return dto;
    }

    public boolean delete(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            return false;
        }
        return true;
    }

    @Transactional
    public void transfer(TransactionDTO dto) {
        if (dto.value() <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }
        User payer = userRepository.findById(dto.payerId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário pagador com o id " + dto.payerId() + " não encontrado."));
        User payee = userRepository.findById(dto.payeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário beneficiário com o id " + dto.payeeId() + " não encontrado."));

        if (payer.getBalance() < dto.value()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência.");
        }

        if (payer.getUserType() == UserType.SHOPKEEPER) {
            throw new IllegalArgumentException("Lojistas não podem fazer transferências.");
        }

        AuthorizationResponse authorizationResponse = authorizationService.checkAuthorization();

        if (!authorizationResponse.isAuthorized()) {
            throw new IllegalStateException("A transferência não foi autorizada: " + authorizationResponse.getMessage());
        }

        payer.setBalance(payer.getBalance() - dto.value());
        payee.setBalance(payee.getBalance() + dto.value());

        userRepository.save(payer);
        userRepository.save(payee);
    }
}
