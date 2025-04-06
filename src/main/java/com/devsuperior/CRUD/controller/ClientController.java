package com.devsuperior.CRUD.controller;

import com.devsuperior.CRUD.dto.ClientDTO;
import com.devsuperior.CRUD.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO clientDTO = service.findById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> clientDTOPage = service.findAll(pageable);
        return ResponseEntity.ok(clientDTOPage);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO clientDTOInsert = service.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTOInsert.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDTOInsert);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
        ClientDTO clientDTOUpdate = service.update(clientDTO, id);
        return ResponseEntity.ok(clientDTOUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
