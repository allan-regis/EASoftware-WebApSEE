/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easoftware.webappsse.core.entity.enums;

/**
 *
 * @author allan.santos
 */
public enum Cargo
{GERENTE_DE_PROJETOS("Gerente de Projetos"),
   REQUISITOS("Requisito"),
   ARQUITETO("Arquiteto"),
   PROJETISTA("Projetista"),
   DESIGNER("Designer"),
   IMPLEMENTADOR("Implementador"),
   TESTER("Tester"),
   DOCUMENTADOR("Documentador");

   private String cargo;

   /**
    * Cria uma nova instância do tipo Cargo.
    *
    * @param cargo DOCUMENT ME!
    */
   private Cargo(String cargo)
   {
      this.cargo = cargo;
   }

   /**
    * DOCUMENT ME!
    *
    * @return the cargo
    */
   public String getCargo()
   {
      return cargo;
   }
}
