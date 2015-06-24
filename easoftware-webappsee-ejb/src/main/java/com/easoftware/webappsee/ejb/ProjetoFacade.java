/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.ejb;

import com.easoftware.webappsee.core.entity.Projeto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author allan.santos
 */
@Stateless
public class ProjetoFacade extends AbstractFacade<Projeto> {
    @PersistenceContext(unitName = "WEBAPSEE_PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjetoFacade() {
        super(Projeto.class);
    }

}
