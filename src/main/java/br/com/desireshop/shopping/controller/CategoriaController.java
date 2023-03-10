package br.com.desireshop.shopping.controller;

import br.com.desireshop.shopping.model.Categoria;
import br.com.desireshop.shopping.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity <List<Categoria>> getAll(){
            return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Categoria> getById(@PathVariable Long id){
        return categoriaRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/tipoCategoria/{/tipoCategoria}")
    public ResponseEntity <List<Categoria>> getTipoCategoria(@PathVariable String tipoCategoria){
        return ResponseEntity.ok(categoriaRepository.findAllByTipoCategoriaContainingIgnoreCase(tipoCategoria));
    }

    @PostMapping
    public ResponseEntity <Categoria> post(@Valid @RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity <Categoria> put(@Valid @RequestBody Categoria categoria){
        return categoriaRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(categoriaRepository.save(categoria)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
            if (categoria.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            categoriaRepository.deleteById(id);
    }


}
