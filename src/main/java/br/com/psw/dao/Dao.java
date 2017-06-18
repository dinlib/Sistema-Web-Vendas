package br.com.psw.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

/**
 * @version 1.0;
 * @author Max; Date: 18/03/2013 Description: Esse DAO (Data Acess Object) é a
 * forma pelo qual acessa-se os dados do Banco de Dados. O DAO aqui apresentado
 * é genérico e funciona para qualquer objeto que possua um Entity. O DAO tem um
 * EntityManager, por padrão chamado de "UP". As operações CRUD são
 * implementadas aqui.
 *
 * Para se instanciar um Dao de um Objeto é preciso da classe do objeto. Ex:
 *
 * Dao<Objeto> = new Dao<Objeto>(Objeto.class);
 *
 * ATENÇÃO: Recomenda-se criar um DAO específico para cada entidade, para poder
 * fazer pesquisas e métodos de acesso ao dados específicos para cada entidade.
 * 
 * Modificado F.Carvalho / M. Hirose / V.Camargo
 *
 */
public class Dao<T> {

    protected static EntityManager em = Persistence.createEntityManagerFactory("UP").createEntityManager();
    private Class classe;

    public Class getClasse() {
        return classe;
    }

    public Dao(Class classe) {
        this.classe = classe;
    }

    /* Description:
     * O método insert() serve para inserir um objeto no banco de dados
     */
    public void insert(T entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.setFlushMode(FlushModeType.COMMIT);
        em.flush();
        em.getTransaction().commit();
    }

    /* Description:
     * O método update() serve para editar um objeto no banco de dados
     */
    public void update(T entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    /* Description:
     * O método remove() serve para remover um objeto no banco de dados
     */
    public void remove(T entidade) {
        entidade = em.merge(entidade);
        em.getTransaction().begin();
        em.remove(entidade);
        em.getTransaction().commit();

    }

    /* Description:
     * O método get() serve para ler um objeto no banco de dados
     */
    public T get(int id) {
        return (T) em.find(classe, id);
    }

    public T get(long id) {
        return (T) em.find(classe, id);
    }

    /* Description:
     * O método list() serve para listar todos os elementos dessa entidade
     * no banco de dados
     */
    public List<T> list(Class<T> clazz) {
        return em.createQuery(
                new StringBuilder("SELECT ob FROM " + clazz.getName() + "  ob ")
                .toString(),
                clazz).getResultList();
    }

    /**
     *  Procura uma lista de clazz e ordena por orderBy
     * 
     * @param clazz
     * @param orderBy
     * @return
     */
    public List<T> searchAllOrderBy(Class<T> clazz, String orderBy) {
        return em.createQuery(
                new StringBuilder("SELECT ob FROM " + clazz.getName() + "  ob ORDER BY ob." + orderBy + " DESC")
                .toString(),
                clazz).getResultList();
    }

}
