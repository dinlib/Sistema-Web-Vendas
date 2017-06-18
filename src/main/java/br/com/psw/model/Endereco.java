package br.com.psw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Entity
@Table(name = "endereco", catalog = "psw"
)
public class Endereco implements java.io.Serializable {

    private Integer id;
    private String rua;
    private String numero;
    private String bairro;
    private Cidade cidade;
    private String cep;

    public Endereco() {
    }

    public Endereco(String rua, String numero, String bairro, Cidade cidade, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
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
    @Column(name = "rua", nullable = false, length = 256)
    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Column(name = "numero", length = 10)
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "bairro", length = 50)
    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @NotBlank
    @Column(name = "cep", length = 10)
    public String getCep() {
        return this.cep;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cidade", referencedColumnName = "id")
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {

        StringBuilder concatenaEnd = new StringBuilder();
        concatenaEnd.append(rua).append(", ").append(numero);
        if (bairro != null && bairro.length() > 0) {
            concatenaEnd.append(" - ").append(bairro);
        }
        concatenaEnd.append(" (").append(cep).append(")")
                .append(" - ").append(cidade.getNome()).append("/").append(cidade.getEstado().getSigla());

        return concatenaEnd.toString();
    }

}
