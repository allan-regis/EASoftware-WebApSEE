/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.entity;

import com.easoftware.webappsse.core.entity.enums.Disciplina;
import com.easoftware.webappsse.core.entity.enums.Situacao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

import java.util.List;


/**
 *
DOCUMENT ME!
 *
 * @author allan.santos
 */
@Entity
@Table(name = "atividade")
public class Atividade implements Serializable
{
   @OneToOne(cascade = CascadeType.ALL)
   private Atividade antecessora;
   @OneToOne(cascade = CascadeType.ALL)
   private Atividade sucessora;
   @Enumerated(EnumType.ORDINAL)
   private Disciplina disciplina;
   @OneToMany
   private List<Tarefa> tarefas;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @ManyToOne(cascade = CascadeType.ALL)
   private Projeto projeto;
   @Enumerated(EnumType.ORDINAL)
   private Situacao situacao;
   @Column
   private String descricao;
   @Column
   private String nome;

   /**
    *
   DOCUMENT ME!
    *
    * @return the antecessora
    */
   public Atividade getAntecessora()
   {
      return antecessora;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the descricao
    */
   public String getDescricao()
   {
      return descricao;
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
    * @return the nome
    */
   public String getNome()
   {
      return nome;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the projeto
    */
   public Projeto getProjeto()
   {
      return projeto;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the situacao
    */
   public Situacao getSituacao()
   {
      return situacao;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the sucessora
    */
   public Atividade getSucessora()
   {
      return sucessora;
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
    * @param antecessora the antecessora to set
    */
   public void setAntecessora(Atividade antecessora)
   {
      this.antecessora = antecessora;
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
    * @param sucessora the sucessora to set
    */
   public void setSucessora(Atividade sucessora)
   {
      this.sucessora = sucessora;
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
}
