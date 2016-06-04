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
public class Enfermeiro extends Usuario{
    protected String coren;
    public void showOptions(){
        System.out.println("\n1 - Atender");
        System.out.println("2 - Encerrar");
    }
    public String getTitulo(){
        return titulo;
    }
    
}
