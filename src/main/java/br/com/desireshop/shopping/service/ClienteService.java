package br.com.desireshop.shopping.service;

import br.com.desireshop.shopping.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired(required = true)
    private ClientesRepository clientesRepository;
}
