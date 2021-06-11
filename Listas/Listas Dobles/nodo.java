/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

/**
 *
 * @author Ruben
 */
public class nodo {
    //Atributos: Información
    String cod;
    String noms;
    double nHorasT;
    double valorHora;
    double nHorasE;
    //Apuntadores
    nodo sig, ant;

    public nodo(String cod, String noms, double nHorasT, double valorHora, double nHorasE) {
        this.cod = cod;
        this.noms = noms;
        this.nHorasT = nHorasT;
        this.valorHora = valorHora;
        this.nHorasE = nHorasE;
        sig=ant=null;
    }
    
    public double getSalarioNeto(){
        double pagoP = nHorasT * valorHora;
        double pagoE = nHorasE * (valorHora * 2);
        double pagoN = pagoP + pagoE;
        return pagoN;
    }
       
}
