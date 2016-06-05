/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

import java.util.Scanner;

/**
 *
 * @author Lukas
 */
public class Sessao {
    public static Object logar(){
        Usuario usr = new Usuario();
        usr.lerUsuarios();
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite seu email: ");
        System.out.print("R: ");
        String email = leitor.nextLine();
        System.out.println("Digite sua senha: ");
        System.out.print("R: ");
        String senha = leitor.nextLine();
        
        //autentica o usuário e retorna qual o objeto para poder consultar
        //usr.logar(email, senha);
        
        
        return usr.logar(email, senha);
        
    }
    
    public static void Session(){
        String mail = (String) logar();
        Scanner leitor = new Scanner(System.in);
        String titulo;
        if(mail == null){
            System.out.println("Email ou senha incorretos");
        } else {
            Usuario usr = new Usuario();
            titulo = (String) usr.findTitutlo(mail);
            System.out.println("Você é um " + titulo + ". Portanto escolha uma opção: ");
            
            //System.out.println(titulo.equals("paciente"));
            if(titulo.equals("paciente")){
                //System.out.println("Entrou no if");
                int opcao = 0;
                do{
                    usr.showOptionsUsuario();
                    System.out.print("R: ");
                    opcao = leitor.nextInt();
                    Consulta consulta = new Consulta();
                    switch(opcao){
                        case 1:
                            consulta.verificarConsultas(mail);
                            break;
                        case 2:
                            usr.escolherEspecialidade(mail);
                            break;
                        case 3:
                            consulta.consultarProntuario(mail);
                            break;
                        default:
                            System.out.println("Ops! Opção inválida");
                            break;
                    }
                } while(opcao != 4);
                
            } else if(titulo.equals("enfermeiro")){
                int opcao = 0;
                do {
                    usr.showOptionsEnfermeiro();
                    System.out.print("R: ");
                    opcao = leitor.nextInt();
                    Consulta con = new Consulta();
                    switch(opcao){
                        case 1:
                            con.escolherTriagem(mail);
                            break;
                        default:
                            System.out.println("Ops! Opção inválida.");
                    }
                } while(opcao != 2);
            }
        }
        
    }
    
}
