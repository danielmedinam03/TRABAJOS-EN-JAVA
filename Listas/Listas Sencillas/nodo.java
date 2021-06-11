/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

/**
 *
 * @author PC
 */
public class nodo {
    String id;
    String noms;
    String eps;
    String genero;
    String fecha;
    double peso;
    double nGluc;
    double estatura;
    double nHemo;
    nodo sig;
    
    

    public nodo(String id, String noms, String eps, String genero, String fecha, double peso, double nGluc, double estatura, double nHemo) {
        this.id = id;
        this.noms = noms;
        this.eps = eps;
        this.genero = genero;
        this.fecha = fecha;
        this.peso = peso;
        this.nGluc = nGluc;
        this.estatura = estatura;
        this.nHemo = nHemo;
        sig=null;
    }

    
    public double getPromIMC(){
        double IMC = peso/(estatura*estatura);
        return IMC;
    }
    public double getPromGluc(){
        double glucosa=nGluc;
        return glucosa;
    }
    public double getPromHemo(){
        double hemo=nHemo;
        return hemo;
    }
}
