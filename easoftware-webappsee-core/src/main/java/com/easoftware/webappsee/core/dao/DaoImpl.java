/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.PreexistingEntityException;
import com.easoftware.webappsee.core.entity.Projeto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Allan
 */
public class DaoImpl implements Serializable, Dao {

    private final static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("WEBAPSEE_PU");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    @Override
    public void inserir(Object objeto) throws PreexistingEntityException, Exception {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(objeto);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (getEntityManager() != null) {
                getEntityManager().close();
            }
        }

    }

    @Override
    public void remover(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recObjeto(Long id) {
        try {
            return getEntityManager().find(Projeto.class, id);
        } finally {
            getEntityManager().close();
        }
    }

    /**
     *
     * @param classe
     * @return
     */
    @Override
    public int getQuantidadeRegistros(Class classe) {
        try {
            CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            Root<Projeto> rt = cq.from(classe);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            Query q = getEntityManager().createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public List recLista(Class classe) {
        return recLista(classe, true, -1, -1);
    }

    @Override
    public List recListaPaginada(Class classe, int maxResults, int firstResult) {
        return recLista(classe, false, maxResults, firstResult);
    }

    private List<Projeto> recLista(Class classe, boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(classe));
            Query q = getEntityManager().createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            getEntityManager().close();
        }
    }

}
