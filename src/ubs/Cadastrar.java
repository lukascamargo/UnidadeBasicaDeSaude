/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lukas
 */
public class Cadastrar {
    public static Usuario cadastrarUsuario(){
        Usuario usr = new Usuario();
        usr.lerUsuarios();
        Scanner leitor = new Scanner(System.in);
        boolean valor = true;
        
        do{
            //cadastrar email
            System.out.println("Digite um email: ");
            System.out.print("R: ");
            String email = leitor.nextLine();
            if(usr.emailIndisponivel(email)){
                valor = true;
            } else {
                valor = false;
                usr.email = email;
                break;
            }
        } while (valor = true);
        
        //determinar um titulo
        usr.titulo = "paciente";
        
        //cadastrar nome
        System.out.println("Digite seu primeiro nome: ");
        System.out.print("R: ");
        usr.nome = leitor.nextLine();
        
        //cadastrar sobrenome
        System.out.println("Digite seu sobrenome: ");
        System.out.print("R: ");
        usr.sobrenome = leitor.nextLine();
        
        //cadastrar senha
        System.out.println("Digite sua senha: ");
        System.out.print("R: ");
        usr.senha = leitor.nextLine();
        
        System.out.println("\nNome: " + usr.nome + " " + usr.sobrenome);
        System.out.println("Email: " + usr.email);
        System.out.println("Digite 1 caso queira confirmar ou 2 caso queira cancelar cadastro.");
        int opcao = 0;
        do{
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
               case 1:
                   usr.adicionarNaLista(usr); //adiciona usuario ao banco de dados
                   usr.salvarUsuarios(); //salvando banco de dados como xml
                   break;
               case 2:
                   usr.email = null;
                   System.out.println("\nCadastro cancelado.");
                   break;
               default:
                   System.out.println("\nOps! Opção não identificada. Tente novamente.");
                   break;
           }
        }while(opcao != 1 && opcao != 2);
        
        //Adicionando o usuário ao banco de dados
        //usr.adicionarNaLista(usr);
        //Salvando banco de dados como xml
        //usr.salvarUsuarios();
        
        if(usr.email != null){
            System.out.println("\nUsuário " + usr.nome + " cadastrado com sucesso!\n"); 
        } 
        
        return usr;
        
    }
    public static Medico cadastrarMedico(){
        Medico doc = new Medico();
        doc.lerUsuarios();
        Scanner leitor = new Scanner(System.in);
        boolean valor = true;
        
        do{
            //cadastrar email
            System.out.println("Digite um email: ");
            System.out.print("R: ");
            String email = leitor.nextLine();
            if(doc.emailIndisponivel(email)){
                valor = true;
            } else {
                valor = false;
                doc.email = email;
                break;
            }
        } while (valor = true);
        
        //determinar titulo
        doc.titulo = "medico";
        
        //cadastrar nome
        System.out.println("Digite seu primeiro nome: ");
        System.out.print("R: ");
        doc.nome = leitor.nextLine();
        
        //cadastrar sobrenome
        System.out.println("Digite seu sobrenome: ");
        System.out.print("R: ");
        doc.sobrenome = leitor.nextLine();
        
        //cadastrar senha
        System.out.println("Digite sua senha: ");
        System.out.print("R: ");
        doc.senha = leitor.nextLine();
        
        //cadastrar crm
        System.out.println("Digite seu CRM: ");
        System.out.print("R: ");
        doc.crm = leitor.nextLine();
        
        System.out.println("\nNome: " + doc.nome + " " + doc.sobrenome + ", CRM " + doc.crm);
        System.out.println("Email: " + doc.email);
        System.out.println("Digite 1 caso queira confirmar ou 2 caso queira cancelar cadastro.");
        int opcao = 0;
        do{
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
               case 1:
                   doc.adicionarNaLista(doc); //adiciona usuario ao banco de dados
                   doc.salvarUsuarios(); //salvando banco de dados como xml
                   break;
               case 2:
                   doc.email = null;
                   System.out.println("\nCadastro cancelado.");
                   break;
               default:
                   System.out.println("\nOps! Opção não identificada. Tente novamente.");
                   break;
           }
        }while(opcao != 1 && opcao != 2);
        
        //Adicionando o usuário ao banco de dados
        //usr.adicionarNaLista(usr);
        //Salvando banco de dados como xml
        //usr.salvarUsuarios();
        
        if(doc.email != null){
            System.out.println("\nUsuário " + doc.nome + " cadastrado com sucesso!\n"); 
        } 
        
        return doc;
        
    }
    public static Enfermeiro cadastrarEnfermeiro(){
        Enfermeiro enf = new Enfermeiro();
        enf.lerUsuarios();
        Scanner leitor = new Scanner(System.in);
        boolean valor = true;
        
        do{
            //cadastrar email
            System.out.println("Digite um email: ");
            System.out.print("R: ");
            String email = leitor.nextLine();
            if(enf.emailIndisponivel(email)){
                valor = true;
            } else {
                valor = false;
                enf.email = email;
                break;
            }
        } while (valor = true);
        
        //deterimnar titulo
        enf.titulo = "enfermeiro";
        
        //cadastrar nome
        System.out.println("Digite seu primeiro nome: ");
        System.out.print("R: ");
        enf.nome = leitor.nextLine();
        
        //cadastrar sobrenome
        System.out.println("Digite seu sobrenome: ");
        System.out.print("R: ");
        enf.sobrenome = leitor.nextLine();
        
        //cadastrar senha
        System.out.println("Digite sua senha: ");
        System.out.print("R: ");
        enf.senha = leitor.nextLine();
        
        //cadastrar crm
        System.out.println("Digite seu CRM: ");
        System.out.print("R: ");
        enf.coren = leitor.nextLine();
        
        System.out.println("\nNome: " + enf.nome + " " + enf.sobrenome + ", CRM " + enf.coren);
        System.out.println("Email: " + enf.email);
        System.out.println("Digite 1 caso queira confirmar ou 2 caso queira cancelar cadastro.");
        int opcao = 0;
        do{
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
               case 1:
                   enf.adicionarNaLista(enf); //adiciona usuario ao banco de dados
                   enf.salvarUsuarios(); //salvando banco de dados como xml
                   break;
               case 2:
                   enf.email = null;
                   System.out.println("\nCadastro cancelado.");
                   break;
               default:
                   System.out.println("\nOps! Opção não identificada. Tente novamente.");
                   break;
           }
        }while(opcao != 1 && opcao != 2);
        
        //Adicionando o usuário ao banco de dados
        //usr.adicionarNaLista(usr);
        //Salvando banco de dados como xml
        //usr.salvarUsuarios();
        
        if(enf.email != null){
            System.out.println("\nUsuário " + enf.nome + " cadastrado com sucesso!\n"); 
        } 
        
        return enf;
        
    }
    public static Dentista cadastrarDentista(){
        Dentista dente = new Dentista();
        dente.lerUsuarios();
        Scanner leitor = new Scanner(System.in);
        boolean valor = true;
        
        do{
            //cadastrar email
            System.out.println("Digite um email: ");
            System.out.print("R: ");
            String email = leitor.nextLine();
            if(dente.emailIndisponivel(email)){
                valor = true;
            } else {
                valor = false;
                dente.email = email;
                break;
            }
        } while (valor = true);
        
        //determinar titulo
        dente.titulo = "dentista";
        
        //cadastrar nome
        System.out.println("Digite seu primeiro nome: ");
        System.out.print("R: ");
        dente.nome = leitor.nextLine();
        
        //cadastrar sobrenome
        System.out.println("Digite seu sobrenome: ");
        System.out.print("R: ");
        dente.sobrenome = leitor.nextLine();
        
        //cadastrar senha
        System.out.println("Digite sua senha: ");
        System.out.print("R: ");
        dente.senha = leitor.nextLine();
        
        //cadastrar crm
        System.out.println("Digite seu CRM: ");
        System.out.print("R: ");
        dente.cfo = leitor.nextLine();
        
        System.out.println("\nNome: " + dente.nome + " " + dente.sobrenome + ", CRM " + dente.cfo);
        System.out.println("Email: " + dente.email);
        System.out.println("Digite 1 caso queira confirmar ou 2 caso queira cancelar cadastro.");
        int opcao = 0;
        do{
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
               case 1:
                   dente.adicionarNaLista(dente); //adiciona usuario ao banco de dados
                   dente.salvarUsuarios(); //salvando banco de dados como xml
                   break;
               case 2:
                   dente.email = null;
                   System.out.println("\nCadastro cancelado.");
                   break;
               default:
                   System.out.println("\nOps! Opção não identificada. Tente novamente.");
                   break;
           }
        }while(opcao != 1 && opcao != 2);
        
        //Adicionando o usuário ao banco de dados
        //usr.adicionarNaLista(usr);
        //Salvando banco de dados como xml
        //usr.salvarUsuarios();
        
        if(dente.email != null){
            System.out.println("\nUsuário " + dente.nome + " cadastrado com sucesso!\n"); 
        } 
        
        return dente;
        
    }
}
