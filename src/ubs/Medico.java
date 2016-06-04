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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lukas
 */
public class Medico extends Usuario{
    protected String crm;
    
    public void showOptions(){
        System.out.println("\n1 - Ver meus agendamentos");
        System.out.println("2 - Atender");
        System.out.println("3 - Encerrar");
    }
    
    public String getTitulo(){
        return titulo;
    }
}
