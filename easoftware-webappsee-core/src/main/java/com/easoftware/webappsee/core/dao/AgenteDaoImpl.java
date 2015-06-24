/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityNotFoundException;
import com.easoftware.webappsee.core.entity.Agente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Allan
 */
public class AgenteDaoImpl extends DaoImpl {

    public void create(Agente agente) {
        if (agente.getSubordinados() == null) {
            agente.setSubordinados(new ArrayList<Agente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Agente superior = agente.getSuperior();
            if (superior != null) {
                superior = getEntityManager().getReference(superior.getClass(), superior.getId());
                agente.setSuperior(superior);
            }
            List<Agente> attachedSubordinados = new ArrayList<Agente>();
            for (Agente subordinadosAgenteToAttach : agente.getSubordinados()) {
                subordinadosAgenteToAttach = getEntityManager().getReference(subordinadosAgenteToAttach.getClass(), subordinadosAgenteToAttach.getId());
                attachedSubordinados.add(subordinadosAgenteToAttach);
            }
            agente.setSubordinados(attachedSubordinados);
            getEntityManager().persist(agente);
            if (superior != null) {
                superior.getSubordinados().add(agente);
                superior = getEntityManager().merge(superior);
            }
            for (Agente subordinadosAgente : agente.getSubordinados()) {
                Agente oldSuperiorOfSubordinadosAgente = subordinadosAgente.getSuperior();
                subordinadosAgente.setSuperior(agente);
                subordinadosAgente = getEntityManager().merge(subordinadosAgente);
                if (oldSuperiorOfSubordinadosAgente != null) {
                    oldSuperiorOfSubordinadosAgente.getSubordinados().remove(subordinadosAgente);
                    oldSuperiorOfSubordinadosAgente = getEntityManager().merge(oldSuperiorOfSubordinadosAgente);
                }
            }
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

    public void edit(Agente agente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Agente persistentAgente = getEntityManager().find(Agente.class, agente.getId());
            Agente superiorOld = persistentAgente.getSuperior();
            Agente superiorNew = agente.getSuperior();
            List<Agente> subordinadosOld = persistentAgente.getSubordinados();
            List<Agente> subordinadosNew = agente.getSubordinados();
            if (superiorNew != null) {
                superiorNew = getEntityManager().getReference(superiorNew.getClass(), superiorNew.getId());
                agente.setSuperior(superiorNew);
            }
            List<Agente> attachedSubordinadosNew = new ArrayList<Agente>();
            for (Agente subordinadosNewAgenteToAttach : subordinadosNew) {
                subordinadosNewAgenteToAttach = getEntityManager().getReference(subordinadosNewAgenteToAttach.getClass(), subordinadosNewAgenteToAttach.getId());
                attachedSubordinadosNew.add(subordinadosNewAgenteToAttach);
            }
            subordinadosNew = attachedSubordinadosNew;
            agente.setSubordinados(subordinadosNew);
            agente = getEntityManager().merge(agente);
            if (superiorOld != null && !superiorOld.equals(superiorNew)) {
                superiorOld.getSubordinados().remove(agente);
                superiorOld = getEntityManager().merge(superiorOld);
            }
            if (superiorNew != null && !superiorNew.equals(superiorOld)) {
                superiorNew.getSubordinados().add(agente);
                superiorNew = getEntityManager().merge(superiorNew);
            }
            for (Agente subordinadosOldAgente : subordinadosOld) {
                if (!subordinadosNew.contains(subordinadosOldAgente)) {
                    subordinadosOldAgente.setSuperior(null);
                    subordinadosOldAgente = getEntityManager().merge(subordinadosOldAgente);
                }
            }
            for (Agente subordinadosNewAgente : subordinadosNew) {
                if (!subordinadosOld.contains(subordinadosNewAgente)) {
                    Agente oldSuperiorOfSubordinadosNewAgente = subordinadosNewAgente.getSuperior();
                    subordinadosNewAgente.setSuperior(agente);
                    subordinadosNewAgente = getEntityManager().merge(subordinadosNewAgente);
                    if (oldSuperiorOfSubordinadosNewAgente != null && !oldSuperiorOfSubordinadosNewAgente.equals(agente)) {
                        oldSuperiorOfSubordinadosNewAgente.getSubordinados().remove(subordinadosNewAgente);
                        oldSuperiorOfSubordinadosNewAgente = getEntityManager().merge(oldSuperiorOfSubordinadosNewAgente);
                    }
                }
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = agente.getId();
                if (recObjeto(id) == null) {
                    throw new NonexistentEntityException("The agente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Agente agente;
            try {
                agente = getEntityManager().getReference(Agente.class, id);
                agente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agente with id " + id + " no longer exists.", enfe);
            }
            Agente superior = agente.getSuperior();
            if (superior != null) {
                superior.getSubordinados().remove(agente);
                superior = getEntityManager().merge(superior);
            }
            List<Agente> subordinados = agente.getSubordinados();
            for (Agente subordinadosAgente : subordinados) {
                subordinadosAgente.setSuperior(null);
                subordinadosAgente = getEntityManager().merge(subordinadosAgente);
            }
            getEntityManager().remove(agente);
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

 
    
}
