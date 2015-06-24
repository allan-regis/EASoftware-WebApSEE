/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsse.core.entity.enums;

/**
 *
 * @author allan.santos
 */
public enum Ambiente
{DESENVOLVIMENTO("Desenvolvimento"),
   TESTE("Teste"),
   HOMOLOGACAO("Homologação"),
   PRODUCAO("Produção");

   private String codigo;

   /**
    * Cria uma nova instância do tipo Disciplina.
    *
    * @param codigo DOCUMENT ME!
    */
   private Ambiente(String codigo)
   {
      this.codigo = codigo;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the codigo
    */
   public String getCodigo()
   {
      return codigo;
   }
}
