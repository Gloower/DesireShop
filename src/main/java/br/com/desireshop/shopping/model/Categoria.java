package br.com.desireshop.shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import java.util.List;

@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 8, max = 255)
    private String tipoCategoria;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("categoria")
    private List<Produtos> produtos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
}
