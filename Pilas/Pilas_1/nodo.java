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
    String codP;
    String nomP;
    float existencia;
    float precioC;
    float precioV;
    nodo sig;

    public nodo(String codP, String nomP, float existencia, float precioC, float precioV) {
        this.codP = codP;
        this.nomP = nomP;
        this.existencia = existencia;
        this.precioC = precioC;
        this.precioV = precioV;
        sig=null;
    }        
    
    public float getGanancia(){
        return precioV-precioC;
    }
    
    public void setExistencia(float cantV){
        existencia -= cantV;
    }
    
}
