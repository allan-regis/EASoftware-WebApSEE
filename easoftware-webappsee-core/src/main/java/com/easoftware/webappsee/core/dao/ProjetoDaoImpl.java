/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.NonexistentEntityException;
import com.easoftware.webappsee.core.dao.exceptions.PreexistingEntityException;
import javax.persistence.EntityNotFoundException;
import com.easoftware.webappsee.core.entity.Atividade;
import com.easoftware.webappsee.core.entity.Projeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Allan
 */
public class ProjetoDaoImpl extends DaoImpl{

   
    public void inserir(Projeto projeto) throws PreexistingEntityException, Exception {
        if (projeto.getAtividades() == null) {
            projeto.setAtividades(new ArrayList<Atividade>());
        }
        EntityManager em = null;
        try {
            
            List<Atividade> attachedAtividades = new ArrayList<Atividade>();
            for (Atividade atividadesAtividadeToAttach : projeto.getAtividades()) {
                atividadesAtividadeToAttach = getEntityManager().getReference(atividadesAtividadeToAttach.getClass(), atividadesAtividadeToAttach.getId());
                attachedAtividades.add(atividadesAtividadeToAttach);
            }
            projeto.setAtividades(attachedAtividades);
            getEntityManager().persist(projeto);
            for (Atividade atividadesAtividade : projeto.getAtividades()) {
                Projeto oldProjetoOfAtividadesAtividade = atividadesAtividade.getProjeto();
                atividadesAtividade.setProjeto(projeto);
                atividadesAtividade = getEntityManager().merge(atividadesAtividade);
                if (oldProjetoOfAtividadesAtividade != null) {
                    oldProjetoOfAtividadesAtividade.getAtividades().remove(atividadesAtividade);
                    oldProjetoOfAtividadesAtividade = getEntityManager().merge(oldProjetoOfAtividadesAtividade);
                }
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            if (recObjeto(projeto.getId()) != null) {
                throw new PreexistingEntityException("Projeto " + projeto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

    public void edit(Projeto projeto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Projeto persistentProjeto = getEntityManager().find(Projeto.class, projeto.getId());
            List<Atividade> atividadesOld = persistentProjeto.getAtividades();
            List<Atividade> atividadesNew = projeto.getAtividades();
            List<Atividade> attachedAtividadesNew = new ArrayList<Atividade>();
            for (Atividade atividadesNewAtividadeToAttach : atividadesNew) {
                atividadesNewAtividadeToAttach = getEntityManager().getReference(atividadesNewAtividadeToAttach.getClass(), atividadesNewAtividadeToAttach.getId());
                attachedAtividadesNew.add(atividadesNewAtividadeToAttach);
            }
            atividadesNew = attachedAtividadesNew;
            projeto.setAtividades(atividadesNew);
            projeto = getEntityManager().merge(projeto);
            for (Atividade atividadesOldAtividade : atividadesOld) {
                if (!atividadesNew.contains(atividadesOldAtividade)) {
                    atividadesOldAtividade.setProjeto(null);
                    atividadesOldAtividade = getEntityManager().merge(atividadesOldAtividade);
                }
            }
            for (Atividade atividadesNewAtividade : atividadesNew) {
                if (!atividadesOld.contains(atividadesNewAtividade)) {
                    Projeto oldProjetoOfAtividadesNewAtividade = atividadesNewAtividade.getProjeto();
                    atividadesNewAtividade.setProjeto(projeto);
                    atividadesNewAtividade = getEntityManager().merge(atividadesNewAtividade);
                    if (oldProjetoOfAtividadesNewAtividade != null && !oldProjetoOfAtividadesNewAtividade.equals(projeto)) {
                        oldProjetoOfAtividadesNewAtividade.getAtividades().remove(atividadesNewAtividade);
                        oldProjetoOfAtividadesNewAtividade = getEntityManager().merge(oldProjetoOfAtividadesNewAtividade);
                    }
                }
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = projeto.getId();
                if (recObjeto(id) == null) {
                    throw new NonexistentEntityException("The projeto with id " + id + " no longer exists.");
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
            getEntityManager().getTransaction().begin();
            Projeto projeto;
            try {
                projeto = getEntityManager().getReference(Projeto.class, id);
                projeto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projeto with id " + id + " no longer exists.", enfe);
            }
            List<Atividade> atividades = projeto.getAtividades();
            for (Atividade atividadesAtividade : atividades) {
                atividadesAtividade.setProjeto(null);
                atividadesAtividade = getEntityManager().merge(atividadesAtividade);
            }
            getEntityManager().remove(projeto);
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }
}
