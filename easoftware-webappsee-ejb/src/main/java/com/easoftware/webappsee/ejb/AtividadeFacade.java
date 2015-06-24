/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.ejb;

import com.easoftware.webappsee.core.entity.Atividade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author allan.santos
 */
@Stateless
public class AtividadeFacade extends AbstractFacade<Atividade> {
    @PersistenceContext(unitName = "WEBAPSEE_PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AtividadeFacade() {
        super(Atividade.class);
    }

}
