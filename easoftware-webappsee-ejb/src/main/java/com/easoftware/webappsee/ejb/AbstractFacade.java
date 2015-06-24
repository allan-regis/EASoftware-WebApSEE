/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.ejb;

import javax.persistence.EntityManager;

import java.util.List;


/**
 *
DOCUMENT ME!
 *
 * @author allan.santos
 *
 * @param <T> DOCUMENT ME!
 */
public abstract class AbstractFacade<T>
{
   private Class<T> entityClass;

   /**
    * Cria uma nova instância do tipo AbstractFacade.
    *
    * @param entityClass DOCUMENT ME!
    */
   public AbstractFacade(Class<T> entityClass)
   {
      this.entityClass = entityClass;
   }

   /**
    * DOCUMENT ME!
    *
    * @return
    */
   public int count()
   {
      javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
      javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
      cq.select(getEntityManager().getCriteriaBuilder().count(rt));

      javax.persistence.Query q = getEntityManager().createQuery(cq);

      return ((Long) q.getSingleResult()).intValue();
   }

   /**
    * DOCUMENT ME!
    *
    * @param  entity
    */
   public void create(T entity)
   {
      getEntityManager().persist(entity);
   }

   /**
    * DOCUMENT ME!
    *
    * @param  entity
    */
   public void edit(T entity)
   {
      getEntityManager().merge(entity);
   }

   /**
    * DOCUMENT ME!
    *
    * @param  id
    *
    * @return
    */
   public T find(Object id)
   {
      return getEntityManager().find(entityClass, id);
   }

   /**
    * DOCUMENT ME!
    *
    * @return
    */
   public List<T> findAll()
   {
      javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
      cq.select(cq.from(entityClass));

      return getEntityManager().createQuery(cq).getResultList();
   }

   /**
    * DOCUMENT ME!
    *
    * @param  range
    *
    * @return
    */
   public List<T> findRange(int[] range)
   {
      javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
      cq.select(cq.from(entityClass));

      javax.persistence.Query q = getEntityManager().createQuery(cq);
      q.setMaxResults(range[1] - range[0]);
      q.setFirstResult(range[0]);

      return q.getResultList();
   }

   /**
    * DOCUMENT ME!
    *
    * @param  entity
    */
   public void remove(T entity)
   {
      getEntityManager().remove(getEntityManager().merge(entity));
   }

   /**
    * DOCUMENT ME!
    *
    * @return
    */
   protected abstract EntityManager getEntityManager();
}
