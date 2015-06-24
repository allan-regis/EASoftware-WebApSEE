/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.NonexistentEntityException;
import com.easoftware.webappsee.core.entity.Ramo;
import javax.persistence.EntityNotFoundException;
import com.easoftware.webappsee.core.entity.Tarefa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Allan
 */
public class RamoDaoImpl extends DaoImpl {


    public void create(Ramo ramo) {
        if (ramo.getTarefas() == null) {
            ramo.setTarefas(new ArrayList<Tarefa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            List<Tarefa> attachedTarefas = new ArrayList<Tarefa>();
            for (Tarefa tarefasTarefaToAttach : ramo.getTarefas()) {
                tarefasTarefaToAttach = getEntityManager().getReference(tarefasTarefaToAttach.getClass(), tarefasTarefaToAttach.getId());
                attachedTarefas.add(tarefasTarefaToAttach);
            }
            ramo.setTarefas(attachedTarefas);
            getEntityManager().persist(ramo);
            for (Tarefa tarefasTarefa : ramo.getTarefas()) {
                Ramo oldRamoOfTarefasTarefa = tarefasTarefa.getRamo();
                tarefasTarefa.setRamo(ramo);
                tarefasTarefa = getEntityManager().merge(tarefasTarefa);
                if (oldRamoOfTarefasTarefa != null) {
                    oldRamoOfTarefasTarefa.getTarefas().remove(tarefasTarefa);
                    oldRamoOfTarefasTarefa = getEntityManager().merge(oldRamoOfTarefasTarefa);
                }
            }
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

    public void edit(Ramo ramo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Ramo persistentRamo = getEntityManager().find(Ramo.class, ramo.getId());
            List<Tarefa> tarefasOld = persistentRamo.getTarefas();
            List<Tarefa> tarefasNew = ramo.getTarefas();
            List<Tarefa> attachedTarefasNew = new ArrayList<Tarefa>();
            for (Tarefa tarefasNewTarefaToAttach : tarefasNew) {
                tarefasNewTarefaToAttach = getEntityManager().getReference(tarefasNewTarefaToAttach.getClass(), tarefasNewTarefaToAttach.getId());
                attachedTarefasNew.add(tarefasNewTarefaToAttach);
            }
            tarefasNew = attachedTarefasNew;
            ramo.setTarefas(tarefasNew);
            ramo = getEntityManager().merge(ramo);
            for (Tarefa tarefasOldTarefa : tarefasOld) {
                if (!tarefasNew.contains(tarefasOldTarefa)) {
                    tarefasOldTarefa.setRamo(null);
                    tarefasOldTarefa = getEntityManager().merge(tarefasOldTarefa);
                }
            }
            for (Tarefa tarefasNewTarefa : tarefasNew) {
                if (!tarefasOld.contains(tarefasNewTarefa)) {
                    Ramo oldRamoOfTarefasNewTarefa = tarefasNewTarefa.getRamo();
                    tarefasNewTarefa.setRamo(ramo);
                    tarefasNewTarefa = getEntityManager().merge(tarefasNewTarefa);
                    if (oldRamoOfTarefasNewTarefa != null && !oldRamoOfTarefasNewTarefa.equals(ramo)) {
                        oldRamoOfTarefasNewTarefa.getTarefas().remove(tarefasNewTarefa);
                        oldRamoOfTarefasNewTarefa = getEntityManager().merge(oldRamoOfTarefasNewTarefa);
                    }
                }
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ramo.getId();
                if (recObjeto(id) == null) {
                    throw new NonexistentEntityException("The ramo with id " + id + " no longer exists.");
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
            Ramo ramo;
            try {
                ramo = getEntityManager().getReference(Ramo.class, id);
                ramo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ramo with id " + id + " no longer exists.", enfe);
            }
            List<Tarefa> tarefas = ramo.getTarefas();
            for (Tarefa tarefasTarefa : tarefas) {
                tarefasTarefa.setRamo(null);
                tarefasTarefa = getEntityManager().merge(tarefasTarefa);
            }
            getEntityManager().remove(ramo);
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }
    
}
