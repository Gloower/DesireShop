package br.com.desireshop.shopping.security;

import br.com.desireshop.shopping.model.Clientes;
import br.com.desireshop.shopping.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<Clientes> clientes = clientesRepository.findByEmail(userName);
        clientes.orElseThrow(() -> new UsernameNotFoundException(userName + "not found"));
        return clientes.map(UserDetailsImpl::new).get();
    }
}
