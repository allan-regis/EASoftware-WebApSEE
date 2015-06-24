/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.ejb;

import com.easoftware.webappsee.core.entity.Ramo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author allan.santos
 */
@Stateless
public class RamoFacade extends AbstractFacade<Ramo> {
    @PersistenceContext(unitName = "WEBAPSEE_PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RamoFacade() {
        super(Ramo.class);
    }

}
