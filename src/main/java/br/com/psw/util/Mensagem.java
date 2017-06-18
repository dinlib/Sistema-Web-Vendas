package br.com.psw.util;

/**
 * Classe de modelo de mensagens a serem geradas e exportadas em formato HTML
 * aliado ao framework Bootstrap. ATENCAO: Vide nos diretorios de páginas web, o
 * arquivo "interfaceMensagens.jsp"
 *
 * @author Max N. Roecker
 *
 * modificado por F.Carvalho / M. Hirose / V.Camargo
 */
public class Mensagem {

    public static final String TYPE_SUCCESS = "success";
    public static final String TYPE_INFO = "info";
    public static final String TYPE_WARNING = "warning";
    public static final String TYPE_DANGER = "danger";
    private static final String TITULO_SUCCESS = "Sucesso!";
    private static final String TITULO_INFO = "Informação!";
    private static final String TITULO_WARNING = "Aviso!";
    private static final String TITULO_DANGER = "Erro!";
    private String text;
    private String type;

    public Mensagem() {
    }

    public Mensagem(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setTypeInfo() {
        this.type = Mensagem.TYPE_INFO;
    }

    public void setTypeSuccess() {
        this.type = Mensagem.TYPE_SUCCESS;
    }

    public void setTypeDanger() {
        this.type = Mensagem.TYPE_DANGER;
    }

    public void setTypeWarning() {
        this.type = Mensagem.TYPE_WARNING;
    }

    public String printMessage() {
        String titulo;
        switch (type) {
            case TYPE_DANGER:
                titulo = TITULO_DANGER;
                break;
            case TYPE_INFO:
                titulo = TITULO_INFO;
                break;
            case TYPE_SUCCESS:
                titulo = TITULO_SUCCESS;
                break;
            case TYPE_WARNING:
                titulo = TITULO_WARNING;
                break;
            default:
                titulo = TITULO_INFO;
        }
        return "<div class=\"alert container alert-" + type + "\"><strong >" + titulo + "</strong > " + text + "</ div>";
    }
}
