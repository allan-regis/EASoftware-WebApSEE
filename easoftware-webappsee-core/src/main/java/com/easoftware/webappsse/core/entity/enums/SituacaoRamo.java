/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsse.core.entity.enums;

/**
 *
 * @author allan.santos
 */
public enum SituacaoRamo
{SOLICITADO(1),
   DISPONIBILIZADO(2),
   AGUARDANDO_MARCACAO(3),
   AGUARDANDO_REINTEGRACAO(4),
   REINTEGRADO(5),
   CANCELADO(6);

   private Integer codigo;

   /**
    * Cria uma nova instância do tipo Situacao.
    *
    * @param codigo DOCUMENT ME!
    */
   private SituacaoRamo(Integer codigo)
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
