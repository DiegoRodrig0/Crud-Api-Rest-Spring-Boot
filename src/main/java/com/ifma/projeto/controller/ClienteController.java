package com.ifma.projeto.controller;

import com.ifma.projeto.model.Cliente;
import com.ifma.projeto.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/listar/{xid}")
    public ResponseEntity<Optional<Cliente>> buscarPor(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()) {
            return new ResponseEntity<Optional<Cliente>>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
