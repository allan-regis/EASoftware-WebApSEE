/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.entity;

import com.easoftware.webappsse.core.entity.enums.Situacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import java.io.Serializable;

import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;


/**
 * DOCUMENT ME!
 *
 * @author allan.santos
 */
@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable
{
   @Temporal(javax.persistence.TemporalType.DATE)
   @Column
   private Calendar dataFinal;
   @Temporal(javax.persistence.TemporalType.DATE)
   @Column
   private Calendar dataInicial;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column
   private Situacao situacao;
   @Column
   private String descricao;
   @Column
   private String nome;
   @ManyToOne(cascade= CascadeType.ALL)
   private Ramo ramo;

    /**
     * @return the dataFinal
     */
    public Calendar getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Calendar dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * @return the dataInicial
     */
    public Calendar getDataInicial() {
        return dataInicial;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the situacao
     */
    public Situacao getSituacao() {
        return situacao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the ramo
     */
    public Ramo getRamo() {
        return ramo;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Calendar dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param ramo the ramo to set
     */
    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }
}
