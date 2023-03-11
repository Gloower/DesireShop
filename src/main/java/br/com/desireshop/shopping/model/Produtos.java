package br.com.desireshop.shopping.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@Entity
@Table(name = "TB_PRODUTOS")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome do produto é obrigatório!")
    @Size(min = 8, max = 100)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória!")
    @Size(min = 8, max = 255)
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Preço origatório!")
    @Positive(message = "Preço deve ser maior que zero!")
    private BigDecimal preco;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Clientes clientes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
