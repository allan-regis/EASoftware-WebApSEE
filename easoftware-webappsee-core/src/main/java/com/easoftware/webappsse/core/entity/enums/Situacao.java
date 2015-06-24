/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsse.core.entity.enums;

/**
 *
 * @author allan.santos
 */
public enum Situacao
{SOLICITADA(1),
   ATRIBUIDA(2),
   EM_EXECUCAO(3),
   SUSPENSA(4),
   CANCELADA(5),
   CONCLUIDA(6);

   private Integer codigo;

   /**
    * Cria uma nova instância do tipo Situacao.
    *
    * @param codigo DOCUMENT ME!
    */
   private Situacao(Integer codigo)
   {
      this.codigo = codigo;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the codigo
    */
   public Integer getCodigo()
   {
      return codigo;
   }
}
