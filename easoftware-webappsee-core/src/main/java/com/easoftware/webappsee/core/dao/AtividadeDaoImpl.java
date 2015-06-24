/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityNotFoundException;
import com.easoftware.webappsee.core.entity.Atividade;
import com.easoftware.webappsee.core.entity.Projeto;
import javax.persistence.EntityManager;

/**
 *
 * @author Allan
 */
public class AtividadeDaoImpl extends DaoImpl{


    public void create(Atividade atividade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Atividade antecessora = atividade.getAntecessora();
            if (antecessora != null) {
                antecessora = getEntityManager().getReference(antecessora.getClass(), antecessora.getId());
                atividade.setAntecessora(antecessora);
            }
            Projeto projeto = atividade.getProjeto();
            if (projeto != null) {
                projeto = getEntityManager().getReference(projeto.getClass(), projeto.getId());
                atividade.setProjeto(projeto);
            }
            Atividade sucessora = atividade.getSucessora();
            if (sucessora != null) {
                sucessora = getEntityManager().getReference(sucessora.getClass(), sucessora.getId());
                atividade.setSucessora(sucessora);
            }
            getEntityManager().persist(atividade);
            if (antecessora != null) {
                Atividade oldAntecessoraOfAntecessora = antecessora.getAntecessora();
                if (oldAntecessoraOfAntecessora != null) {
                    oldAntecessoraOfAntecessora.setAntecessora(null);
                    oldAntecessoraOfAntecessora = getEntityManager().merge(oldAntecessoraOfAntecessora);
                }
                antecessora.setAntecessora(atividade);
                antecessora = getEntityManager().merge(antecessora);
            }
            if (projeto != null) {
                projeto.getAtividades().add(atividade);
                projeto = getEntityManager().merge(projeto);
            }
            if (sucessora != null) {
                Atividade oldAntecessoraOfSucessora = sucessora.getAntecessora();
                if (oldAntecessoraOfSucessora != null) {
                    oldAntecessoraOfSucessora.setSucessora(null);
                    oldAntecessoraOfSucessora = getEntityManager().merge(oldAntecessoraOfSucessora);
                }
                sucessora.setAntecessora(atividade);
                sucessora = getEntityManager().merge(sucessora);
            }
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }

    public void edit(Atividade atividade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            getEntityManager().getTransaction().begin();
            Atividade persistentAtividade = getEntityManager().find(Atividade.class, atividade.getId());
            Atividade antecessoraOld = persistentAtividade.getAntecessora();
            Atividade antecessoraNew = atividade.getAntecessora();
            Projeto projetoOld = persistentAtividade.getProjeto();
            Projeto projetoNew = atividade.getProjeto();
            Atividade sucessoraOld = persistentAtividade.getSucessora();
            Atividade sucessoraNew = atividade.getSucessora();
            if (antecessoraNew != null) {
                antecessoraNew = getEntityManager().getReference(antecessoraNew.getClass(), antecessoraNew.getId());
                atividade.setAntecessora(antecessoraNew);
            }
            if (projetoNew != null) {
                projetoNew = getEntityManager().getReference(projetoNew.getClass(), projetoNew.getId());
                atividade.setProjeto(projetoNew);
            }
            if (sucessoraNew != null) {
                sucessoraNew = getEntityManager().getReference(sucessoraNew.getClass(), sucessoraNew.getId());
                atividade.setSucessora(sucessoraNew);
            }
            atividade = getEntityManager().merge(atividade);
            if (antecessoraOld != null && !antecessoraOld.equals(antecessoraNew)) {
                antecessoraOld.setAntecessora(null);
                antecessoraOld = getEntityManager().merge(antecessoraOld);
            }
            if (antecessoraNew != null && !antecessoraNew.equals(antecessoraOld)) {
                Atividade oldAntecessoraOfAntecessora = antecessoraNew.getAntecessora();
                if (oldAntecessoraOfAntecessora != null) {
                    oldAntecessoraOfAntecessora.setAntecessora(null);
                    oldAntecessoraOfAntecessora = getEntityManager().merge(oldAntecessoraOfAntecessora);
                }
                antecessoraNew.setAntecessora(atividade);
                antecessoraNew = getEntityManager().merge(antecessoraNew);
            }
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getAtividades().remove(atividade);
                projetoOld = getEntityManager().merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getAtividades().add(atividade);
                projetoNew = getEntityManager().merge(projetoNew);
            }
            if (sucessoraOld != null && !sucessoraOld.equals(sucessoraNew)) {
                sucessoraOld.setAntecessora(null);
                sucessoraOld = getEntityManager().merge(sucessoraOld);
            }
            if (sucessoraNew != null && !sucessoraNew.equals(sucessoraOld)) {
                Atividade oldAntecessoraOfSucessora = sucessoraNew.getAntecessora();
                if (oldAntecessoraOfSucessora != null) {
                    oldAntecessoraOfSucessora.setSucessora(null);
                    oldAntecessoraOfSucessora = getEntityManager().merge(oldAntecessoraOfSucessora);
                }
                sucessoraNew.setAntecessora(atividade);
                sucessoraNew = getEntityManager().merge(sucessoraNew);
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = atividade.getId();
                if (recObjeto(id) == null) {
                    throw new NonexistentEntityException("The atividade with id " + id + " no longer exists.");
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
            Atividade atividade;
            try {
                atividade = getEntityManager().getReference(Atividade.class, id);
                atividade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atividade with id " + id + " no longer exists.", enfe);
            }
            Atividade antecessora = atividade.getAntecessora();
            if (antecessora != null) {
                antecessora.setAntecessora(null);
                antecessora = getEntityManager().merge(antecessora);
            }
            Projeto projeto = atividade.getProjeto();
            if (projeto != null) {
                projeto.getAtividades().remove(atividade);
                projeto = getEntityManager().merge(projeto);
            }
            Atividade sucessora = atividade.getSucessora();
            if (sucessora != null) {
                sucessora.setAntecessora(null);
                sucessora = getEntityManager().merge(sucessora);
            }
            getEntityManager().remove(atividade);
            getEntityManager().getTransaction().commit();
        } finally {
            if (em != null) {
                getEntityManager().close();
            }
        }
    }
    
}
