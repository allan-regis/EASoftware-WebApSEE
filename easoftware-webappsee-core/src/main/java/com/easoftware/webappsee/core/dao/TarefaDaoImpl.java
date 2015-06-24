/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityNotFoundException;
import com.easoftware.webappsee.core.entity.Ramo;
import com.easoftware.webappsee.core.entity.Tarefa;
import javax.persistence.EntityManager;

/**
 *
 * @author Allan
 */
public class TarefaDaoImpl extends DaoImpl {

   
    public void create(Tarefa tarefa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Ramo ramo = tarefa.getRamo();
            if (ramo != null) {
                ramo = getEntityManager().getReference(ramo.getClass(), ramo.getId());
                tarefa.setRamo(ramo);
            }
            getEntityManager().persist(tarefa);
            if (ramo != null) {
                ramo.getTarefas().add(tarefa);
                ramo = getEntityManager().merge(ramo);
            }
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

    public void edit(Tarefa tarefa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Tarefa persistentTarefa = getEntityManager().find(Tarefa.class, tarefa.getId());
            Ramo ramoOld = persistentTarefa.getRamo();
            Ramo ramoNew = tarefa.getRamo();
            if (ramoNew != null) {
                ramoNew = getEntityManager().getReference(ramoNew.getClass(), ramoNew.getId());
                tarefa.setRamo(ramoNew);
            }
            tarefa = getEntityManager().merge(tarefa);
            if (ramoOld != null && !ramoOld.equals(ramoNew)) {
                ramoOld.getTarefas().remove(tarefa);
                ramoOld = getEntityManager().merge(ramoOld);
            }
            if (ramoNew != null && !ramoNew.equals(ramoOld)) {
                ramoNew.getTarefas().add(tarefa);
                ramoNew = getEntityManager().merge(ramoNew);
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tarefa.getId();
                if (recObjeto(id) == null) {
                    throw new NonexistentEntityException("The tarefa with id " + id + " no longer exists.");
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
            Tarefa tarefa;
            try {
                tarefa = getEntityManager().getReference(Tarefa.class, id);
                tarefa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarefa with id " + id + " no longer exists.", enfe);
            }
            Ramo ramo = tarefa.getRamo();
            if (ramo != null) {
                ramo.getTarefas().remove(tarefa);
                ramo = getEntityManager().merge(ramo);
            }
            getEntityManager().remove(tarefa);
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

}
