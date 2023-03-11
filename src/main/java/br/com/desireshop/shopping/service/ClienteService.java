package br.com.desireshop.shopping.service;

import br.com.desireshop.shopping.model.Clientes;
import br.com.desireshop.shopping.model.ClientesLogin;
import br.com.desireshop.shopping.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired(required = true)
    private ClientesRepository clientesRepository;

    public Optional<Clientes> cadastrarClientes(Clientes clientes){
        if(clientesRepository.findByEmail(clientes.getEmail())
        .isPresent())
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ja cadastrado", null);

        clientes.setSenha(criptografarSenha(clientes.getSenha()));
        return Optional.of(clientesRepository.save(clientes));

    }

    public Optional<Clientes> atualizarClientes(Clientes clientes){
        if (clientesRepository.findById(clientes.getId()).isPresent()){
            Optional <Clientes> buscaClientes = clientesRepository.findByEmail(clientes.getEmail());
            if (buscaClientes.isPresent()){
                if (buscaClientes.get().getId() != clientes.getId())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Email ja cadastrado", null);
            }
            clientes.setSenha(criptografarSenha(clientes.getSenha()));
            return Optional.of(clientesRepository.save(clientes));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Email ja cadastrado", null);
    }

    public Optional <ClientesLogin> autenticarClientes(
            Optional<ClientesLogin> clientesLogin){
        Optional<Clientes> clientes  = clientesRepository.findByEmail(clientesLogin.get().getEmail());

        if (clientes.isPresent()){
            if (compararSenhas(clientesLogin.get().getSenha(),
                    clientes.get().getSenha())){
                clientesLogin.get().setId(clientes.get().getId());
                clientesLogin.get().setNome(clientes.get().getNome());
                clientesLogin.get().setEmail(clientes.get().getEmail());
                clientesLogin.get().setToken(
                        gerarBasicToken(clientesLogin.get().getEmail(),
                                clientesLogin.get().getSenha()));
                clientesLogin.get().setSenha(clientes.get().getSenha());
                return clientesLogin;
            }
        }
throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
    }

    private String criptografarSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncoder = encoder.encode(senha);
        return senhaEncoder;
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaBanco, senhaDigitada);
    }

    private String gerarBasicToken(String email, String password){
        String estrutura = email + "*" + password;
        byte[] estruturaBase64 = Base64.encodeBase64(
                estrutura.getBytes(Charset.forName("US-ASCII")));
        return "Basic" + new String(estruturaBase64);

    }
}
