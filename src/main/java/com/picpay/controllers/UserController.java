package com.picpay.controllers;

import com.picpay.entities.exceptions.ResourceNotFoundException;
import com.picpay.services.UserService;
import com.picpay.shared.TransactionDTO;
import com.picpay.shared.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto, @PathVariable Long id) {
        dto = service.update(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        }
        throw new ResourceNotFoundException("Usuário não encontrado.");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransactionDTO transactionDTO) {
        try {
            service.transfer(transactionDTO);
            return ResponseEntity.ok("Transferência realizada com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
