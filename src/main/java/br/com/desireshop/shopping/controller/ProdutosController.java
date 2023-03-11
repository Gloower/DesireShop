package br.com.desireshop.shopping.controller;


import br.com.desireshop.shopping.model.Produtos;
import br.com.desireshop.shopping.repository.CategoriaRepository;
import br.com.desireshop.shopping.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produtos>> getAll() {
        return ResponseEntity.ok(produtosRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id) {
        return produtosRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produtos) {
        if (produtosRepository.existsById(produtos.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtosRepository.save(produtos));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PutMapping
    public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produtos) {
        if (produtosRepository.existsById(produtos.getId())) {
            if (categoriaRepository.existsById(produtos.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtosRepository.save(produtos));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{/id}")
    public void delete(@PathVariable Long id){
        Optional<Produtos> produtos = produtosRepository.findById(id);
        if (produtos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtosRepository.deleteById(id);
    }

}
