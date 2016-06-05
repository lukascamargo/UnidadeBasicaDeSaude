/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    public void novaConsulta(){
        
    } 
    
    public void setData(int day, int month, int year){
        this.dia = day;
        this.mes = month;
        this.ano = year;
    }
    public String getData(){
        return this.dia + "/" + this.mes + "/" + this.ano;
    }
    
    public boolean testaData(String data, String doctor){
        boolean retorno = false;
        for(int i = 0; i < todas.size(); i++){
            if(todas.get(i).nomeMedico.equals(doctor) && todas.get(i).getData().equals(ano) ){
                retorno = true;
            }
        }
        
        return retorno;
    }
    
    public void salvarConsultas(){
        XStream xstr = new XStream(new DomDriver());
        xstr.alias("consulta", Consulta.class);
        xstr.alias("triagem", Triagem.class);
        xstr.alias("pep", PEP.class);
        String xml = xstr.toXML(todas);
        geraArquivo(xml);
    }
    
    public void geraArquivo(String xml){
        PrintWriter print = null;
        File file = new File("C:\\Users\\Lukas\\Documents\\GitHub\\UnidadeBasicaDeSaude\\src\\xml\\consultas.xml");
        try {
            print = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        print.write(xml);
        print.flush();
        print.close();
    }
    
    public void lerConsultas(){
        try {
            FileReader ler = new FileReader("C:\\Users\\Lukas\\Documents\\GitHub\\UnidadeBasicaDeSaude\\src\\xml\\consultas.xml");
            XStream xstr = new XStream(new DomDriver());
            xstr.alias("consulta", Consulta.class);
            xstr.alias("triagem", Triagem.class);
            xstr.alias("pep", PEP.class);
            todas = (ArrayList<Consulta>) xstr.fromXML(ler);
        
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void adicionarNaLista(Consulta obj){
        todas.add(obj);
    }
    
    public void verificarConsultas(String email){
        lerConsultas();
        for(int i = 0; i < todas.size(); i++){
        
        }
    };
    

}
