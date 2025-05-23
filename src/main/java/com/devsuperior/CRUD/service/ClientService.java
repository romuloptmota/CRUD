package com.devsuperior.CRUD.service;

import com.devsuperior.CRUD.dto.ClientDTO;
import com.devsuperior.CRUD.entities.Client;
import com.devsuperior.CRUD.repository.ClientRepository;
import com.devsuperior.CRUD.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = repository.findAll(pageable);
        return clients.map(client -> new ClientDTO(client));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDtoToEntity(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id) {
        try {
            Client client = repository.findById(id).get();
            copyDtoToEntity(dto, client);
            client = repository.save(client);
            return new ClientDTO(client);
        }catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
        repository.deleteById(id);
    }


    public void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
