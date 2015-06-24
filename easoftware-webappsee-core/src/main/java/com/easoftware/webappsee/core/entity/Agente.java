/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.entity;

import com.easoftware.webappsse.core.entity.enums.Cargo;

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
@Table(name = "agente")
public class Agente implements Serializable
{
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "AGENTE_SUPERIOR", referencedColumnName = "superior", updatable = false, insertable = false)
   private Agente superior;
   @Enumerated(EnumType.STRING)
   private Cargo cargo;
   @OneToMany(fetch = FetchType.LAZY, mappedBy = "superior")
   private List<Agente> subordinados;
   @OneToMany(cascade = CascadeType.ALL)
   private List<Tarefa> tarefas;
   @Column
   private Long cpf;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column
   private Long rg;
   @Column
   private Long telefone;
   @Column
   private String email;
   @Column
   private String nome;
   @Column
   private String senha;
   @Column
   private String usuario;

   /**
    * DOCUMENT ME!
    *
    * @return the cargo
    */
   public Cargo getCargo()
   {
      return cargo;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the cpf
    */
   public Long getCpf()
   {
      return cpf;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the email
    */
   public String getEmail()
   {
      return email;
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
    *
   DOCUMENT ME!
    *
    * @return the rg
    */
   public Long getRg()
   {
      return rg;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the senha
    */
   public String getSenha()
   {
      return senha;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the subordinados
    */
   public List<Agente> getSubordinados()
   {
      return subordinados;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the superior
    */
   public Agente getSuperior()
   {
      return superior;
   }

   /**
    * DOCUMENT ME!
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
    * @return the telefone
    */
   public Long getTelefone()
   {
      return telefone;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the usuario
    */
   public String getUsuario()
   {
      return usuario;
   }

   /**
    * DOCUMENT ME!
    *
    * @param cargo the cargo to set
    */
   public void setCargo(Cargo cargo)
   {
      this.cargo = cargo;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @param cpf the cpf to set
    */
   public void setCpf(Long cpf)
   {
      this.cpf = cpf;
   }

   /**
    * DOCUMENT ME!
    *
    * @param email the email to set
    */
   public void setEmail(String email)
   {
      this.email = email;
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
    *
   DOCUMENT ME!
    *
    * @param rg the rg to set
    */
   public void setRg(Long rg)
   {
      this.rg = rg;
   }

   /**
    * DOCUMENT ME!
    *
    * @param senha the senha to set
    */
   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   /**
    * DOCUMENT ME!
    *
    * @param subordinados the subordinados to set
    */
   public void setSubordinados(List<Agente> subordinados)
   {
      this.subordinados = subordinados;
   }

   /**
    * DOCUMENT ME!
    *
    * @param superior the superior to set
    */
   public void setSuperior(Agente superior)
   {
      this.superior = superior;
   }

   /**
    * DOCUMENT ME!
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
    * @param telefone the telefone to set
    */
   public void setTelefone(Long telefone)
   {
      this.telefone = telefone;
   }

   /**
    * DOCUMENT ME!
    *
    * @param usuario the usuario to set
    */
   public void setUsuario(String usuario)
   {
      this.usuario = usuario;
   }
}
