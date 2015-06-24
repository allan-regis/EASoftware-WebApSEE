/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import java.io.Serializable;

import java.util.Calendar;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author allan.santos
 */
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable
{
   @Temporal(javax.persistence.TemporalType.DATE)
   private Calendar dataFinal;
   @Temporal(javax.persistence.TemporalType.DATE)
   private Calendar dataInicial;
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(name = "PROJETO_HAS_ATIVIDADES", joinColumns =
   {
      @JoinColumn(name = "PROJETO_ID", referencedColumnName = "id")
   }
   , inverseJoinColumns =
   {
      @JoinColumn(name = "ATIVIDADE_ID", referencedColumnName = "id")
   }
   )
   private List<Atividade> atividades;
   @Id
   private Long id;
   @Column
   private String cliente;
   @Column
   private String nome;
   @Column
   private String versao;

   /**
    * DOCUMENT ME!
    *
    * @return the atividades
    */
   public List<Atividade> getAtividades()
   {
      return atividades;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the cliente
    */
   public String getCliente()
   {
      return cliente;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the dataFinal
    */
   public Calendar getDataFinal()
   {
      return dataFinal;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the dataInicial
    */
   public Calendar getDataInicial()
   {
      return dataInicial;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the id
    */
   public Long getId()
   {
      return id;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the nome
    */
   public String getNome()
   {
      return nome;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the versao
    */
   public String getVersao()
   {
      return versao;
   }

   /**
    * DOCUMENT ME!
    *
    * @param atividades the atividades to set
    */
   public void setAtividades(List<Atividade> atividades)
   {
      this.atividades = atividades;
   }

   /**
    * DOCUMENT ME!
    *
    * @param cliente the cliente to set
    */
   public void setCliente(String cliente)
   {
      this.cliente = cliente;
   }

   /**
    * DOCUMENT ME!
    *
    * @param dataFinal the dataFinal to set
    */
   public void setDataFinal(Calendar dataFinal)
   {
      this.dataFinal = dataFinal;
   }

   /**
    * DOCUMENT ME!
    *
    * @param dataInicial the dataInicial to set
    */
   public void setDataInicial(Calendar dataInicial)
   {
      this.dataInicial = dataInicial;
   }

   /**
    * DOCUMENT ME!
    *
    * @param id the id to set
    */
   public void setId(Long id)
   {
      this.id = id;
   }

   /**
    * DOCUMENT ME!
    *
    * @param nome the nome to set
    */
   public void setNome(String nome)
   {
      this.nome = nome;
   }

   /**
    * DOCUMENT ME!
    *
    * @param versao the versao to set
    */
   public void setVersao(String versao)
   {
      this.versao = versao;
   }
}
