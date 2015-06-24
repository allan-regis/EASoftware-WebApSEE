/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.entity;

import com.easoftware.webappsse.core.entity.enums.Situacao;

/**
 *
 * @author allan.santos
 */
public interface Historico {

    public abstract Situacao situacaoAtual();
}
