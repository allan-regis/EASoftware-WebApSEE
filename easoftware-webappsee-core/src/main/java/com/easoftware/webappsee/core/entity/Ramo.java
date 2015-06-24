/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.entity;

import com.easoftware.webappsse.core.entity.enums.Ambiente;
import com.easoftware.webappsse.core.entity.enums.Disciplina;
import com.easoftware.webappsse.core.entity.enums.SituacaoRamo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author allan.santos
 */
@Entity
@Table(name = "ramo")
public class Ramo implements Serializable
{
   @ManyToOne(cascade = CascadeType.ALL)
   private Agente responsavel;
   @Enumerated(EnumType.STRING)
   private Ambiente ambiente;
   @Enumerated(EnumType.STRING)
   private Disciplina disciplina;
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JoinTable(name = "RAMO_HAS_TAREFAS", joinColumns =
   {
      @JoinColumn(name = "RAMO_ID", referencedColumnName = "id")
   }
   , inverseJoinColumns =
   {
      @JoinColumn(name = "TAREFA_ID", referencedColumnName = "id")
   }
   )
   private List<Tarefa> tarefas;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Enumerated(EnumType.ORDINAL)
   private SituacaoRamo situacao;
   @Column
   private String url;
   @Column
   private String versaoDestino;
   @Column
   private String versaoOrigem;

   /**
    *
   DOCUMENT ME!
    *
    * @return the ambiente
    */
   public Ambiente getAmbiente()
   {
      return ambiente;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the disciplina
    */
   public Disciplina getDisciplina()
   {
      return disciplina;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the id
    */
   public Long getId()
   {
      return id;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the responsavel
    */
   public Agente getResponsavel()
   {
      return responsavel;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the situacao
    */
   public SituacaoRamo getSituacao()
   {
      return situacao;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the tarefas
    */
   public List<Tarefa> getTarefas()
   {
      return tarefas;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the url
    */
   public String getUrl()
   {
      return url;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the versaoDestino
    */
   public String getVersaoDestino()
   {
      return versaoDestino;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the versaoOrigem
    */
   public String getVersaoOrigem()
   {
      return versaoOrigem;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param ambiente the ambiente to set
    */
   public void setAmbiente(Ambiente ambiente)
   {
      this.ambiente = ambiente;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param disciplina the disciplina to set
    */
   public void setDisciplina(Disciplina disciplina)
   {
      this.disciplina = disciplina;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param id the id to set
    */
   public void setId(Long id)
   {
      this.id = id;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param responsavel the responsavel to set
    */
   public void setResponsavel(Agente responsavel)
   {
      this.responsavel = responsavel;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param situacao the situacao to set
    */
   public void setSituacao(SituacaoRamo situacao)
   {
      this.situacao = situacao;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param tarefas the tarefas to set
    */
   public void setTarefas(List<Tarefa> tarefas)
   {
      this.tarefas = tarefas;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param url the url to set
    */
   public void setUrl(String url)
   {
      this.url = url;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param versaoDestino the versaoDestino to set
    */
   public void setVersaoDestino(String versaoDestino)
   {
      this.versaoDestino = versaoDestino;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param versaoOrigem the versaoOrigem to set
    */
   public void setVersaoOrigem(String versaoOrigem)
   {
      this.versaoOrigem = versaoOrigem;
   }
}
