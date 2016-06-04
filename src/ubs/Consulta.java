/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lukas
 */
public class Consulta {
    ArrayList<Consulta> todas = new ArrayList<Consulta>() {};
    protected String nomePaciente;
    protected String nomeMedico;
    protected int dia;
    protected int mes;
    protected int ano;
    Triagem triagem = new Triagem();
    PEP prontuario = new PEP();
    
    public void novaConsulta(){
        
    } 
    
    public void setData(int day, int month, int year){
        this.dia = day;
        this.mes = month;
        this.ano = year;
    }
    
    

}
