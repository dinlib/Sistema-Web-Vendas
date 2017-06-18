package br.com.psw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Entity
@Table(name = "usuario", catalog = "psw", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")}
)
public class Usuario implements java.io.Serializable {

    private Integer id;
    private String email;
    private String senha;
    private String nome;
    private String celular;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String celular) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "O usuário " + nome + " tem id " + id + ", celular " + celular + " e o seguinte email para contato " + email;
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
    @Column(name = "senha", nullable = false, length = 32)
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

}
