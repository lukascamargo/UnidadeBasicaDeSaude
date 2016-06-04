/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

/**
 *
 * @author Lukas
 */
public class Dentista extends Usuario {
    protected String cfo;
    public void showOptions(){
        System.out.println("\n1 - Meus agendamentos");
        System.out.println("2 - Atender");
        System.out.println("3 - Encerrar");
    }    
    
    public String getTitulo(){
        return titulo;
    }
}
