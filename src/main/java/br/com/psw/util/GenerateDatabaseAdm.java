package br.com.psw.util;

import br.com.psw.dao.CidadeDao;
import br.com.psw.dao.ClienteDao;
import br.com.psw.dao.EstadoDao;
import br.com.psw.dao.ProdutoDao;
import br.com.psw.dao.UsuarioDao;
import br.com.psw.enums.TipoProduto;
import br.com.psw.model.Cidade;
import br.com.psw.model.Cliente;
import br.com.psw.model.Endereco;
import br.com.psw.model.Estado;
import br.com.psw.model.Produto;
import br.com.psw.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilizada para iniciar um banco de dados do sistema.
 *
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
public class GenerateDatabaseAdm {

    private static void recreateDataBase(String dataBase, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost";
            try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {
                statement.execute("DROP DATABASE IF EXISTS " + dataBase);
                statement.execute("CREATE DATABASE " + dataBase);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicia um banco com algumas entidades
     *
     * @param args
     */
    public static void main(String args[]) {
        recreateDataBase("psw", "root", "root");
        new ProdutoDao().insert(new Produto("Panela", TipoProduto.PRODUTO_ACABADO.toString(), "A melhor panela da região cozinha até feijão."));

        Estado parana = new Estado("PR", "Paraná");
        new EstadoDao().insert(parana);

        Cidade maringa = new Cidade("Maringá");
        maringa.setEstado(parana);
        new CidadeDao().insert(maringa);

        Endereco endereco = new Endereco("Rua larga", "1", "Centro", maringa, "87020-000");
        new UsuarioDao().insert(new Usuario("teste@teste.com", Util.converteSenhaDigitadaParaMd5("teste"), "Usuario Teste", "(44) 4444-4444"));
        new ClienteDao().insert(new Cliente("teste@teste.com", "teste", "(44) 4444-4444", endereco));

        System.exit(1);
    }
}
