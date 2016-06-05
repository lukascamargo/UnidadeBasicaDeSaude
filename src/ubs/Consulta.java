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
import java.util.Scanner;
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
    Triagem triagem = new Triagem();
    PEP prontuario = new PEP();
    
    
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
            todas = (ArrayList<Consulta>) xstr.fromXML(ler);
        
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void adicionarNaLista(Consulta obj){
        todas.add(obj);
    }
    public void verificarConsultas(String emailRecebido){
        lerConsultas();
        Usuario usr = new Usuario();
        String nome = usr.procurarPaciente(emailRecebido);
        System.out.println("\n");
        for(int i = 0; i < todas.size(); i++){
            if(todas.get(i).nomePaciente.equals(nome)){
                System.out.print("- Consulta dia " + todas.get(i).getData());
                System.out.println(", com " + todas.get(i).nomeMedico);
            }
        }
    };
    public void consultarProntuario(String emailRecebido){
        lerConsultas();
        Usuario usr = new Usuario();
        String nome = usr.procurarPaciente(emailRecebido);
        System.out.println("\n");
        
        for(int i = 0; i < todas.size(); i++){
            if(todas.get(i).nomePaciente.equals(nome)){
               System.out.println("\nAutor: " + todas.get(i).prontuario.autor);
               System.out.println("Texto: " + todas.get(i).prontuario.texto);
               System.out.println("Ultima atualizacao: " + todas.get(i).prontuario.data);
            }
        }
    }
    public void mostrarTriagensAbertas(){
        lerConsultas();
        int j = 0;
        int i = 0;
        do{
            System.out.println("chegou aqui");
            if(todas.get(i).triagem.finalizado == false){
                System.out.println(++j + " - Nome: " + todas.get(i).nomePaciente);
            }
            i++;
        }while(i < todas.size());
    }
    public void escolherTriagem(String emailRecebido){
        Usuario usr = new Usuario();
        String nomeEnfermeiro = usr.procurarPaciente(emailRecebido);
        mostrarTriagensAbertas();
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        int triagemEscolhida = 0;
        do{
            System.out.print("Opcao: ");
            opcao = leitor.nextInt();
            int j = 0;
            for(int i = 0; i < todas.size(); i++){
                if(todas.get(i).triagem.finalizado == false){
                    if(opcao == i){
                        System.out.println("Você escolheu o paciente " + todas.get(i).nomePaciente);
                        triagemEscolhida = i;
                    }
                }
            }
        }while(triagemEscolhida != opcao);
        
        System.out.println("\nRealizando atendimento...");
        System.out.print("Temperatura do paciente(XX.XX): ");
        double temperatura = leitor.nextDouble();
        System.out.println("Pressão: ");
        String pressao = leitor.nextLine();
        System.out.println("Digite 1 para Alto risco, 2 para médio e 3 para baixo.");
        System.out.print("Classificação de Risco: ");
        int risco = leitor.nextInt();
        
        todas.get(triagemEscolhida).triagem.classificacaoDeRisco = risco;
        todas.get(triagemEscolhida).triagem.pressão = pressao;
        todas.get(triagemEscolhida).triagem.temperatura = temperatura;
        todas.get(triagemEscolhida).triagem.autor = nomeEnfermeiro;
        todas.get(triagemEscolhida).triagem.finalizado = true;
        
        salvarConsultas();
    }

}
