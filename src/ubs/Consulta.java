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
    protected String tipo;
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
            if(todas.get(i).nomeMedico.equals(doctor) && todas.get(i).getData().equals(data) ){
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
        File file = new File("C:\\Users\\Lukas\\Documents\\GitHub\\UnidadeBasicaDeSaude\\src\\xml\\consultas_gerais.xml");
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
            FileReader ler = new FileReader("C:\\Users\\Lukas\\Documents\\GitHub\\UnidadeBasicaDeSaude\\src\\xml\\consultas_gerais.xml");
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
            if(todas.get(i).nomePaciente.equals(nome) && todas.get(i).prontuario.finalizado == false){
                System.out.print("- Consulta dia " + todas.get(i).getData());
                System.out.println(", com Dr(a). " + todas.get(i).nomeMedico);
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
    public boolean mostrarTriagensAbertas(){
        lerConsultas();
        boolean retorno = false;
        int j = 0;
        int i = 0;
        do{
            if(todas.get(i).triagem.finalizado == false && todas.get(i).tipo.equals("medico")){
                System.out.println(++j + " - Nome: " + todas.get(i).nomePaciente + ". Medico desta consulta: " + todas.get(i).nomeMedico);
            }
            i++;
        }while(i < todas.size());
        if(j == 0){
            System.out.println("Nenhum atendimento a ser realizado");
            retorno = false;
        } else {
            //System.out.print("Opcao: ");
            retorno = true;
        }
        return retorno;
    }
    public void escolherTriagem(String emailRecebido){
        Usuario usr = new Usuario();
        String nomeEnfermeiro = usr.procurarPaciente(emailRecebido);
        //mostrarTriagensAbertas();
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        int triagemEscolhida = -1;
        int opcaoEscolhida = -1;
        //System.out.print("Opcao: ");
        if(mostrarTriagensAbertas()){
            do{
                System.out.print("Opcao: ");
                opcao = leitor.nextInt();
                int j = 0;
                for(int i = 0; i < todas.size(); i++){
                    if(todas.get(i).triagem.finalizado == false && todas.get(i).tipo.equals("medico")){
                        ++j;
                        if(opcao == j){
                            System.out.println("Você escolheu o paciente " + todas.get(i).nomePaciente);
                            opcaoEscolhida = j;
                            triagemEscolhida = i;
                            break;
                        }
                    }
                }
            }while(opcaoEscolhida != opcao);
            do{
                System.out.println("\nRealizando atendimento...");
                System.out.print("Temperatura do paciente(XX.XX): ");
                double temperatura = leitor.nextDouble();
                System.out.println("Pressão: ");
                Scanner read = new Scanner(System.in);
                String pressao = read.nextLine();
                System.out.println("Digite 1 para Alto risco, 2 para médio e 3 para baixo.");
                System.out.print("Classificação de Risco: ");
                int risco = leitor.nextInt();

                todas.get(triagemEscolhida).triagem.classificacaoDeRisco = risco;
                todas.get(triagemEscolhida).triagem.pressão = pressao;
                todas.get(triagemEscolhida).triagem.temperatura = temperatura;
                todas.get(triagemEscolhida).triagem.autor = nomeEnfermeiro;
                todas.get(triagemEscolhida).triagem.finalizado = true;

                salvarConsultas();
                break;
            }while(triagemEscolhida > -1);
        }
    }
    public void mostrarAgendaMedico(String emailRecebido){
        lerConsultas();
        Usuario usr = new Usuario();
        String nameMedico = usr.procurarPaciente(emailRecebido);
        int i = 0;
        int j = 0;
        do{
            if(todas.get(i).triagem.finalizado == true && todas.get(i).nomeMedico.equals(nameMedico) && todas.get(i).prontuario.finalizado == false){
                System.out.print(++j + " - Paciente: " + todas.get(i).nomePaciente + ". Dia: " + todas.get(i).getData());
                System.out.println("Risco de morte nível: " + todas.get(i).triagem.classificacaoDeRisco);
            }
            i++;
        }while(i < todas.size());
        if(j == 0){
            System.out.println("Sua agenda está vazia por enquanto.");
        }
    }
    public void medicoAtender(String emailRecebido){
        mostrarAgendaMedico(emailRecebido);
        Usuario usr = new Usuario();
        String nameMedico = usr.procurarPaciente(emailRecebido);
        System.out.println("Escolha um ítem da agenda. ");
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        int pacienteEscolhido = -1;
        int opcaoEscolhida = -1;
        do{
            System.out.print("Opcao: ");
            opcao = leitor.nextInt();
            int j = 0;
            int i = 0;
            do{
                    //if(opcao == i){
                        if(todas.get(i).triagem.finalizado == true && todas.get(i).nomeMedico.equals(nameMedico) && todas.get(i).prontuario.finalizado == false){
                            ++j;
                            if(opcao == j){
                                System.out.println("Você escolheu o paciente " + todas.get(i).nomePaciente);
                                pacienteEscolhido = i;
                                opcaoEscolhida = j;
                                
                            } 
                        }
                i++;
            }while(i < todas.size());
        }while(opcaoEscolhida != opcao);
        
        do{
            System.out.println("Dados do paciente " + todas.get(pacienteEscolhido).nomePaciente + ": ");
            System.out.println("Classificaçao de risco: " + todas.get(pacienteEscolhido).triagem.classificacaoDeRisco);
            System.out.println("Temperatura: " + todas.get(pacienteEscolhido).triagem.temperatura);
            System.out.println("Pressão: " + todas.get(pacienteEscolhido).triagem.pressão);
            
            System.out.println("O paciente está com dor no corpo?");
            todas.get(pacienteEscolhido).prontuario.dorNoCorpo = simOuNao();
            System.out.println("\nO paciente está com dor atras dos olhos?");
            todas.get(pacienteEscolhido).prontuario.dorAtrasOlhos = simOuNao();
            System.out.println("\nO paciente está com fraqueza?");
            todas.get(pacienteEscolhido).prontuario.fraqueza = simOuNao();
            System.out.println("\nO paciente vomitou ou continua vomitando?");
            todas.get(pacienteEscolhido).prontuario.vomitos = simOuNao();
            System.out.println("\nO paciente está com dores e inchaços nas articulações dos pés, maos, tornozelos e pulsos?");
            todas.get(pacienteEscolhido).prontuario.dorEInchacoArticulacoes = simOuNao();
            System.out.println("\nO paciente está com manchas vermelhas na pele?");
            todas.get(pacienteEscolhido).prontuario.manchesVermelhas = simOuNao();
            System.out.println("\nO paciente está com coceira?");
            todas.get(pacienteEscolhido).prontuario.coceira = simOuNao();
            System.out.println("\nO paciente está com dores musculares?");
            todas.get(pacienteEscolhido).prontuario.doresMusculares = simOuNao();

            if(todas.get(pacienteEscolhido).triagem.temperatura > 38 && 
               todas.get(pacienteEscolhido).prontuario.dorNoCorpo == true &&
               todas.get(pacienteEscolhido).prontuario.dorAtrasOlhos == true &&
               todas.get(pacienteEscolhido).prontuario.fraqueza == true &&
               todas.get(pacienteEscolhido).prontuario.vomitos == true){
                System.out.println("Paciente diagnosticado com dengue");
                todas.get(pacienteEscolhido).prontuario.dengue = true;
            } else if(todas.get(pacienteEscolhido).prontuario.dorEInchacoArticulacoes == true){
                System.out.println("Paciente diagnosticado com Chikungunya");
                todas.get(pacienteEscolhido).prontuario.chikungunya = true;
            } else if(todas.get(pacienteEscolhido).triagem.temperatura < 38 && 
                      todas.get(pacienteEscolhido).triagem.temperatura < 37 &&
                      todas.get(pacienteEscolhido).prontuario.manchesVermelhas == true &&
                      todas.get(pacienteEscolhido).prontuario.coceira == true && 
                      todas.get(pacienteEscolhido).prontuario.doresMusculares == true){
                System.out.println("Paciente diagnosticado com Zika");
                todas.get(pacienteEscolhido).prontuario.zika = true;
            } else {
                System.out.println("Paciente não possui dengue, chikungunya ou zika. Está saudavel");
                todas.get(pacienteEscolhido).prontuario.saudavel = true;
            }
            
            todas.get(pacienteEscolhido).prontuario.finalizado = true;
            System.out.println("\nDescreva as informações no Prontuario do paciente de forma resumida: ");
            Scanner read = new Scanner(System.in);
            String textoMedico = read.nextLine();
            todas.get(pacienteEscolhido).prontuario.texto = textoMedico;
            todas.get(pacienteEscolhido).prontuario.data = new Date();
            salvarConsultas();
            break;
        
        }while(pacienteEscolhido > -1);
    }
    public boolean simOuNao(){
        System.out.println("Sim ou nao? (sem acentos)");
        System.out.print("R: ");
        Scanner leitor = new Scanner(System.in);
        boolean retorno = false;
        boolean identi = false;
        do{
            String entrada = leitor.nextLine();
            switch(entrada){
                case "sim":
                   retorno = true;
                   identi = true;
                   break;
                case "nao":
                    retorno = false;
                    identi = true;
                    break;
                default:
                    identi = false;
                    System.out.println("Não identificado");
            }
        }while(!identi);
        
        return retorno;
        
    }
    public void atendimentosFinalizados(String emailRecebido){
        lerConsultas();
        Usuario usr = new Usuario();
        String nameMedico = usr.procurarPaciente(emailRecebido);
        int i = 0;
        int j = 0;
        do{
            if(todas.get(i).nomeMedico.equals(nameMedico) && todas.get(i).prontuario.finalizado == true){
                System.out.println(++j + " - Paciente: " + todas.get(i).nomePaciente + ". Dia: " + todas.get(i).getData());
            }
            i++;
        }while(i < todas.size());
        if(j == 0){
            System.out.println("Você não finalizou nenhum atendimento.");
        }
    }
    public void dentistaAtender(String emailRecebido){
        mostrarAgendaDentista(emailRecebido);
        Usuario usr = new Usuario();
        String nameMedico = usr.procurarPaciente(emailRecebido);
        System.out.println("Escolha um item da agenda");
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        int pacienteEscolhido = -1;
        int opcaoEscolhida = -1;
        do{
            System.out.print("Opcao: ");
            opcao = leitor.nextInt();
            int j = 0;
            int i = 0;
            do{
                    //if(opcao == i){
                        if(todas.get(i).nomeMedico.equals(nameMedico) && todas.get(i).prontuario.finalizado == false){
                            ++j;
                            if(opcao == j){
                                System.out.println("Você escolheu o paciente " + todas.get(i).nomePaciente);
                                pacienteEscolhido = i;
                                opcaoEscolhida = j;
                                
                            } 
                        }
                i++;
            }while(i < todas.size());
        }while(opcaoEscolhida != opcao);
        do{
            System.out.println("O paciente vai fazer limpeza?");
            todas.get(pacienteEscolhido).prontuario.limpeza = simOuNao();
            System.out.println("O paciente vai fazer obturação?");
            todas.get(pacienteEscolhido).prontuario.obturacao = simOuNao();
            
            System.out.print("\nAtendimento finalizado. ");
            System.out.println("Adicione as informações necessárias de forma resumida no prontuario do paciente: ");
            Scanner entrada = new Scanner(System.in);
            String texto = entrada.nextLine();
            todas.get(pacienteEscolhido).prontuario.texto = texto;
            todas.get(pacienteEscolhido).prontuario.finalizado = true;
            todas.get(pacienteEscolhido).prontuario.data = new Date();
            salvarConsultas();
            break;
        
        }while(pacienteEscolhido > -1);
    }
    public void mostrarAgendaDentista(String emailRecebido){
        lerConsultas();
        Usuario usr = new Usuario();
        String nameMedico = usr.procurarPaciente(emailRecebido);
        int i = 0;
        int j = 0;
        do{
            if(todas.get(i).nomeMedico.equals(nameMedico) && todas.get(i).prontuario.finalizado == false){
                System.out.println(++j + " - Paciente: " + todas.get(i).nomePaciente + ". Dia: " + todas.get(i).getData());
            }
            i++;
        }while(i < todas.size());
        if(j == 0){
            System.out.println("Sua agenda está vazia por enquanto.");
        }
    }
}
