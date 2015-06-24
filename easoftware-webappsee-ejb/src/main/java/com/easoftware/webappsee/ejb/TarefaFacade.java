/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.ejb;

import com.easoftware.webappsee.core.entity.Tarefa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author allan.santos
 */
@Stateless
public class TarefaFacade extends AbstractFacade<Tarefa> {
    @PersistenceContext(unitName = "WEBAPSEE_PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TarefaFacade() {
        super(Tarefa.class);
    }

}
