/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsse.core.entity.enums;

/**
 *
 * @author allan.santos
 */
public enum Disciplina
{REQUISITOS(1),
   PROJETO(2),
   INTERFACE(3),
   IMPLEMENTACAO(4),
   TESTE(5),
   METRICAS(6),
   GERENCIA_PROJETO(7),
   IMPLANTACAO(8);

   private Integer codigo;

   /**
    * Cria uma nova instância do tipo Disciplina.
    *
    * @param codigo DOCUMENT ME!
    */
   private Disciplina(Integer codigo)
   {
      this.codigo = codigo;
   }

   /**
    *
   DOCUMENT ME!
    *
    * @return the codigo
    */
   public Integer getCodigo()
   {
      return codigo;
   }
}
