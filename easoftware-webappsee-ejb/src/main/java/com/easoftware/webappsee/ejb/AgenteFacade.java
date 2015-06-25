/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.ejb;

import com.easoftware.webappsee.core.entity.Agente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author allan.santos
 */
@Stateless
public class AgenteFacade extends AbstractFacade<Agente> {
    @PersistenceContext(unitName = "WEBAPSEE")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AgenteFacade() {
        super(Agente.class);
    }

}
