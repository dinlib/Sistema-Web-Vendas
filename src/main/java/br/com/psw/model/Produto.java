package br.com.psw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.text.WordUtils;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Entity
@Table(name = "produto", catalog = "psw"
)
public class Produto implements java.io.Serializable {

    public static final String NOME_TABELA = "produtos";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Size(min = 3, message = "Título deve conter ao menos 3 caracteres")
    @Column(name = "titulo", length = 512)
    private String titulo;

    @Column(name = "tipo", length = 60)
    private String tipo;

    @Column(name = "descricao", length = 256)
    private String descricao;

    @Override
    public String toString() {
        return "O Produto " + titulo + " é " + tipo + ". <br/>" + descricao;
    }

    public Produto(String titulo, String tipo, String descricao) {
        this.titulo = titulo;
        setTipo(tipo);
        this.descricao = descricao;
    }

    public Produto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        tipo = tipo.replace("_", " ");
        this.tipo = WordUtils.capitalizeFully(tipo.toLowerCase());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
