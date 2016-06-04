/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubs;

import java.util.Scanner;
import static ubs.Cadastrar.*;
import static ubs.Sessao.*;

/**
 *
 * @author Lukas
 */
public class Programa {
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        do{
            showOptions();
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
                case 1:
                    Session();
                    break;
                case 2:
                    OptionsCadastrar();
                    break;
                default:
                    break;
            }
            
        } while(opcao != 3);
        
    }

    public static void showOptions() {
        System.out.println("\n1 - Logar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Finalizar");
    }
    public static void OptionsCadastrar(){
        Scanner leitor = new Scanner(System.in);
        int opcao;
            showOptionsCadastrar();
            System.out.print("R: ");
            opcao = leitor.nextInt();
            switch(opcao){
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarMedico();
                    break;
                case 3:
                    cadastrarEnfermeiro();
                    break;
                case 4:
                    cadastrarDentista();
                    break;
                default:
                    System.out.println("\nOps! Opção inválida.");
                    break;
            }
    }
    public static void showOptionsCadastrar(){
        System.out.println("\n1 - Paciente");
        System.out.println("2 - Medico");
        System.out.println("3 - Enfermeiro");
        System.out.println("4 - Dentista");
        System.out.println("5 - Voltar");
    }
}
