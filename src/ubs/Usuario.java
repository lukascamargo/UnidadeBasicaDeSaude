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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lukas
 */
public class Usuario {
    protected String email;
    protected String nome;
    protected String sobrenome;
    protected String senha;
    ArrayList<Usuario> todos = new ArrayList<Usuario>() {};
    protected static Usuario atual;
    protected String titulo = "usuario";
    int mostrarQuantosMedicos;
    
    public String getNome(){
        return this.nome;
    }
    public String getTitulo(){
        return this.titulo;
    }
    
    public String findTitutlo(String emailRecebido){
        String tittle = null;
        lerUsuarios();
        for(int i = 0;i < todos.size(); i++){
            if(todos.get(i).email.equals(emailRecebido)){
                tittle = todos.get(i).getTitulo();
            }
        }
        return tittle;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    public void adicionarNaLista(Usuario obj){
        todos.add(obj);
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void lerUsuarios(){
        try {
            FileReader ler = new FileReader("C:\\Users\\Lukas\\Documents\\GitHub\\UnidadeBasicaDeSaude\\src\\xml\\usuarios.xml");
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("usuario", Usuario.class);
            xstream.alias("medico", Medico.class);
            xstream.alias("dentista", Dentista.class);
            xstream.alias("enfermeiro", Enfermeiro.class);
            todos = (ArrayList<Usuario>) xstream.fromXML(ler);
        
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void salvarUsuarios(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("usuario", Usuario.class);
        xstream.alias("medico", Medico.class);
        xstream.alias("dentista", Dentista.class);
        xstream.alias("enfermeiro", Enfermeiro.class);
        String xml = xstream.toXML(todos);
        geraArquivo(xml);        
    }
    public void geraArquivo(String xml){
        PrintWriter print = null;
        File file = new File("C:\\Users\\Lukas\\Documents\\GitHub\\UnidadeBasicaDeSaude\\src\\xml\\usuarios.xml");
        
        //Tratamento de exception de printwriter
        try{
            print = new PrintWriter(file);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        print.write(xml);
        print.flush();
        print.close();
    }
    public boolean emailIndisponivel(String emailPassado){
        boolean retorno = false;
        for(int i = 0; i < todos.size(); i++){
           if(todos.get(i).email.equals(emailPassado)){
               retorno = true;
               System.out.println("Email indisponível");
               break;
           }
        } 
        return retorno;
    }
    
    public String logar(String emailPassado, String senhaPassada){
        String retorno = null;
        for(int i = 0; i < todos.size(); i++){
            if(todos.get(i).email.equals(emailPassado) && todos.get(i).senha.equals(senhaPassada)){
                System.out.println("Seja bem vindo " + todos.get(i).nome);
                retorno = todos.get(i).getEmail();
            }
        }
          
        return retorno;
    }
    private Boolean validarLogin(String emailUsuario, String senha){
        return this.email.equals(emailUsuario) && this.senha.equals(senha);
    }
    
    public void showOptions(){
        System.out.println("\n1 - Verificar meus agendamentos");
        System.out.println("2 -  Agendar uma nova consulta");
        System.out.println("3 - Consultar meu prontuário");
        System.out.println("4 - Encerrar");
    }
    public void showOptionsEspecialidade(){
        System.out.println("\n1 - Médico");
        System.out.println("2 - Dentista");
        System.out.println("3 - Sair");
    }
    public void showOptionsMedicos(){
        int j = 0;
        for(int i = 0; i < todos.size(); i++){
            if(todos.get(i).getTitulo().equals("medico")){
                System.out.println("" + ++j + " - " + todos.get(i).sobrenome + ", " + todos.get(i).nome);
            }
        }
    }
    public void showOptionsDentistas(){
    }
    
    public void escolherEspecialidade(String email){
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        showOptionsEspecialidade();
        do{
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
                case 1:
                    agendamento(email);
                    break;
                case 2:
                    break;
                case 3:
                    
                    break;
                default:
                    break;
            }
        }while(opcao != 3);
        
    }
    
    public void agendamento(String email){
        //System.out.println("entrou no agendamento");
        showOptionsMedicos();
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        int medicoEscolhido = 0;
        System.out.print("R: ");
        opcao = leitor.nextInt();
        int j = 0;
        for(int i = 0; i < todos.size(); i++){
            if(todos.get(i).getTitulo().equals("medico")){
                ++j;
                if(opcao == j){
                    System.out.println("Você escolheu " + todos.get(i).nome + " " + todos.get(i).sobrenome);
                    medicoEscolhido = i;
                }
            }
        }
        int paciente = findPaciente(email);
        Consulta consulta = new Consulta();
        consulta.nomePaciente = todos.get(paciente).nome + " " + todos.get(paciente).sobrenome;
        consulta.nomeMedico = todos.get(medicoEscolhido).nome + " " + todos.get(medicoEscolhido).sobrenome;
        
        System.out.println("Digite a data desejada: ");
        //To be continued 
        
        System.out.print("Dia: ");
        int dia = leitor.nextInt();
        System.out.print("Mes: ");
        int mes = leitor.nextInt();
        System.out.print("Ano: ");
        int ano = leitor.nextInt();
        
        consulta.setData(dia, mes, ano);
        
        
        
        
    }
    
    public int findPaciente(String emailRecebido){
        int paciente = -1;
        
        for(int i = 0; i < todos.size(); i++){
            if(todos.get(i).email.equals(emailRecebido)){
                paciente = i;
            }
        }
        return paciente;
        
    }
    
}
