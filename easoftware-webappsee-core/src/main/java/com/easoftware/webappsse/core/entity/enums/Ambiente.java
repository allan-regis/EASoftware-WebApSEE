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
   HOMOLOGACAO("Homologa��o"),
   PRODUCAO("Produ��o");

   private String codigo;

   /**
    * Cria uma nova inst�ncia do tipo Disciplina.
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
