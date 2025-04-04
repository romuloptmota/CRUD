package com.devsuperior.CRUD.service;

import com.devsuperior.CRUD.dto.ClientDTO;
import com.devsuperior.CRUD.entities.Client;
import com.devsuperior.CRUD.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).get();
        return new ClientDTO(client);
    }
}
