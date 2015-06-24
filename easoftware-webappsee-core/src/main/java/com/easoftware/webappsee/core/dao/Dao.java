/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.core.dao;

import com.easoftware.webappsee.core.dao.exceptions.PreexistingEntityException;
import java.util.List;

/**
 *
 * @author Allan
 */
public interface Dao {

    public void inserir(Object objeto) throws PreexistingEntityException, Exception;

    public void remover(Object objeto);

    public Object recObjeto(Long id);

    public int getQuantidadeRegistros(Class classe);

    public List recLista(Class classe);

    public List recListaPaginada(Class classe, int maxResults, int firstResult);

}
