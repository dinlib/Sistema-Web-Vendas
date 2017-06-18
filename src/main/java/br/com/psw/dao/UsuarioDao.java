package br.com.psw.dao;

import br.com.psw.model.Usuario;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
public class UsuarioDao extends Dao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public Usuario logaUsuario(Usuario usuario) {
        Query query = em.createQuery("SELECT c FROM Usuario c WHERE c.email=:email AND"
                + " c.senha = :senha");
        query.setParameter("email", usuario.getEmail());
        query.setParameter("senha", usuario.getSenha());
        query.setMaxResults(1);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
