package br.com.psw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Entity
@Table(name = "cliente", catalog = "psw")
public class Cliente implements java.io.Serializable {

    private Integer id;
    private String email;
    private String nome;
    private String celular;
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public Cliente(String email, String nome, String celular, Endereco endereco) {
        this.email = email;
        this.nome = nome;
        this.celular = celular;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "O usuário " + nome + " tem id " + id + ", celular " + celular + ", email para contato " + email + " e mora em " + endereco.toString();
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotBlank
    @Column(name = "email", unique = true, nullable = false, length = 512)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @Column(name = "nome", nullable = false, length = 512)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Pattern(regexp = "\\([1-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "Celular deve ser preenchido corretamente")
    @Column(name = "celular", length = 24)
    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco")
    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
