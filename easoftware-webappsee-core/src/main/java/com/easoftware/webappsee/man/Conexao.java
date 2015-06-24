/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsee.man;

import com.easoftware.webappsee.core.dao.ProjetoDaoImpl;
import org.apache.log4j.Logger;

/**
 *
 * @author Allan
 */
public class Conexao {
    public static void main(String[] args)
    {
        Logger logger = Logger.getLogger(ProjetoDaoImpl.class);
        logger.info("iniciando aplicação");
        ProjetoDaoImpl dao = new ProjetoDaoImpl();
    }
}
