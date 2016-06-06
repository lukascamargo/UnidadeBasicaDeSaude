/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Lukas
 */
public class PEP {
    protected String autor = "Não iniciado";
    protected Date data;
    protected String texto = "Não iniciado";
    boolean dorNoCorpo = false;
    boolean dorAtrasOlhos = false;
    boolean fraqueza = false;
    boolean vomitos = false;
    boolean dorEInchacoArticulacoes = false;
    boolean manchesVermelhas = false;
    boolean coceira = false;
    boolean doresMusculares = false;
    boolean finalizado = false;
    boolean dengue = false;
    boolean chikungunya = false;
    boolean zika = false;
    boolean saudavel = false;
    boolean limpeza = false; //dentista 
    boolean obturacao = false; //dentista
    
    public PEP(){
        this.data = new Date();
    }
    
    
    
}
