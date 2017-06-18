package br.com.psw.enums;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
public enum TipoProduto {

    PRODUTO_ACABADO("PRODUTO ACABADO"), PRODUTO_SEMI_ACABADO(
            "PRODUTO SEMI ACABADO"), MATERIAL_USO_CONSUMO(
            "MATERIAL USO CONSUMO"), MATERIA_PRIMA("MATÉRIA PRIMA"), KIT("KIT"), SERVICO(
            "SERVIÇO"), PRODUTO_CUSTO("PRODUTO DE CUSTO"), PRODUTO_REVENDA(
            "PRODUTO PARA REVENDA");

    private final String tipo;

    private TipoProduto(final String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

}
