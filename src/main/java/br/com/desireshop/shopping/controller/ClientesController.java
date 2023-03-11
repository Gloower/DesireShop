package br.com.desireshop.shopping.controller;


import br.com.desireshop.shopping.model.Clientes;
import br.com.desireshop.shopping.model.ClientesLogin;
import br.com.desireshop.shopping.repository.ClientesRepository;
import br.com.desireshop.shopping.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientesController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Clientes>> getAll(){

        return ResponseEntity.ok(clientesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable Long id){
        return clientesRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/login")
    public ResponseEntity<ClientesLogin> login(@RequestBody Optional<ClientesLogin> clientesLogin){
        return clienteService.autenticarClientes(clientesLogin)
                .map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }

    @PostMapping("/register")
    public ResponseEntity<Clientes> postClientes(@Valid @RequestBody Clientes clientes){
        return clienteService.cadastrarClientes(clientes)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping
    public ResponseEntity <Clientes> putClientes(@Valid @RequestBody Clientes clientes) {
        return clienteService.atualizarClientes(clientes)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
