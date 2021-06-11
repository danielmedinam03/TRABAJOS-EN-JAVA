/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class lista {
    nodo cab;
    
    public lista() {
        cab = null;
    }

    public nodo getBuscarId(String codE) {
        if (cab == null) {
            return null;
        } else {
            nodo p = cab;
            while (p != null) {
                if ((p.id).equals(codE)) {
                    return p;
                } else {
                    p = p.sig;  //Avanza un posición en la lista
                }
            }
            return null;
        }
    }
    public boolean getEsVacia(){
        return cab==null?true:false;
    }

    public nodo getCrearNodo(
            JTextField JtfId,
            JTextField Jtfnoms,
            JTextField JtfEps,
            JTextField Jtfgenero,
            JTextField Jtffecha,
            JTextField Jtfpeso,
            JTextField Jtfestatura,
            JTextField JtfnGluc,
            JTextField JtfnHemo) {
        nodo buscar = null;
        try {
            buscar = getBuscarId(JtfId.getText());
            if (buscar != null) {
                JOptionPane.showMessageDialog(null,
                        "Error: Este código ya se encuentra "
                        + "registrado.  Reemplazar código!");
                JtfId.setText("");
                JtfId.requestFocus();
                return null;
            }
            //Una vez capturados los datos, se crea en memoria el nodo
            nodo info= new nodo(
                    JtfId.getText(),
                    Jtfnoms.getText(),
                    JtfEps.getText(),
                    Jtfgenero.getText(),
                    Jtffecha.getText(),
                    Double.parseDouble(Jtfpeso.getText()),
                    Double.parseDouble(Jtfestatura.getText()),
                    Double.parseDouble(JtfnGluc.getText()),
                    Double.parseDouble(JtfnHemo.getText()));
            return info;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
            return null;
        }
    }
    public void setAddFinal(
        JTextField JtfId,
        JTextField Jtfnoms,
        JTextField JtfEps,
        JTextField Jtfgenero,
        JTextField Jtffecha,
        JTextField Jtfpeso,
        JTextField Jtfestatura,
        JTextField JtfnGluc,
        JTextField JtfnHemo) {
        nodo info = getCrearNodo(
            JtfId,
            Jtfnoms,
            JtfEps,
            Jtfgenero,
            Jtffecha,
            Jtfpeso,
            Jtfestatura,
            JtfnGluc,
            JtfnHemo
        );
        
        if (info != null) {
            if (cab == null) {
                cab = info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento "
                        + "a la lista.  La lista estaba vacía.");
            } else {
                info.sig = cab;
                cab=info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento al "
                        + "final de la lista.");
            }
        }else{
            JOptionPane.showMessageDialog(null,
                    "Ocurrio algún error al crear el nodo, "
                    + "intente nuevamente!");
        }
    }
    public void getBuscarInfo(String Id){
        if (getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista se encuentra vacía!");
        }else{
            nodo buscar=getBuscarId(Id);
            
            if(buscar==null)
                JOptionPane.showMessageDialog(null, 
                    "El ID de empleado no esta registrado!");
            else {
                String info = "La información del "
                    + "paciente es: \n ";
                //CALCULO DE IMC
                double IMC = 0.0;
                IMC = buscar.estatura/(buscar.peso*buscar.peso); 
                if (IMC<18.5){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "PESO INSUFICIENTE");
                }else if ((IMC >=18.5)&&(IMC<=24.9)){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "PESO NORMAL");
                }else if ((IMC >=25)&&(IMC<=29.9)){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "SOBREPESO");
                }else if ((IMC >=30)&&(IMC<=39.9)){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "OBESIDAD CLINICA");
                }else if ((IMC >=40)&&(IMC<=49.9)){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "OBESIDAD MORBIDA");
                }else if ((IMC >=50)&&(IMC<=59.9)){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "SUPER OBESIDAD MORBIDA");
                }else if ((IMC >=60)&&(IMC<=64.9)){
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "SUPER SUPER OBESIDAD");
                }else{
                    JOptionPane.showMessageDialog(null, 
                        "IMC del empleado: "+ IMC + "OBESIDAD TRIPLE");
                }
               //CALCULO DE LA GLUCOSA
                double glucosa=buscar.nGluc;
                if ((glucosa>=70)&&(glucosa<=100)){
                    JOptionPane.showMessageDialog(null, 
                        "Glucosa del empleado: "+ glucosa 
                            + "Estado: OPTIMO");
                
                }else if ((glucosa>100)&&(glucosa<=125)){
                    JOptionPane.showMessageDialog(null, 
                        "Glucosa del empleado: "+ glucosa 
                            + "Estado: PREDIABETES");
                }else if (glucosa>125){
                    JOptionPane.showMessageDialog(null, 
                        "Glucosa del empleado: "+ glucosa 
                            + "Estado: DIABETES");
                }
                //CALCULO DE LA HEMOGLOBINA
                double hem=buscar.nHemo;
                String sex=buscar.genero;
                
                if(sex=="F"){
                    if((hem<12)){
                        JOptionPane.showMessageDialog(null, 
                        "Hemoglobina del empleado: "+ hem 
                            + "Estado: BAJO");
                    }else if ((hem<=12)&&(hem>=15)){
                        JOptionPane.showMessageDialog(null, 
                        "Hemoglobina del empleado: "+ hem 
                            + "Estado: OPTIMO");
                    }else if (hem>15){
                        JOptionPane.showMessageDialog(null,
                        "Hemoglobina del empleado: "+ hem 
                            + "Estado: ALTO");
                    }
                }else if(sex=="M"){
                    if((hem<13)){
                        JOptionPane.showMessageDialog(null, 
                        "Hemoglobina del empleado: "+ hem 
                            + "Estado: BAJO");
                    }else if ((hem<=13)&&(hem>=17)){
                        JOptionPane.showMessageDialog(null, 
                        "Hemoglobina del empleado: "+ hem 
                            + "Estado: OPTIMO");
                    }else if (hem>17){
                        JOptionPane.showMessageDialog(null,
                        "Hemoglobina del empleado: "+ hem 
                            + "Estado: ALTO");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                        "Genero ingresado es incorrecto !!"+""
                            +"Ingrese nuevamente 'F' para femenino"+""
                            +"'M' para masculino.");
                }        
            }
        }
    }
    public void setElimCodigo(String id ){
        if(getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista no tiene elementos!");
        }else{
            nodo p, q;
            p=getBuscarId(id);
            if(p==null)
               JOptionPane.showMessageDialog(null, 
                "El código buscado para eliminar NO"
                + " se encuentra registrado!");
            else{
                if((p==cab)&&(cab.sig==null)){
                    cab=null;
                    JOptionPane.showMessageDialog(null, 
                    "Elemento eliminado, la lista esta vacía!");
                }
                else if((p==cab)&&(cab.sig!=null)){
                    cab=cab.sig;
                    p.sig=null;
                    p=null;
                    JOptionPane.showMessageDialog(null, 
                    "Elemento eliminado, en el inicio de la"
                    + " lista!");
                }
                else if(p.sig==null){
                    q=cab;
                    while(q.sig!=p)
                        q=q.sig;
                    q.sig=null;
                    p=null;
                    JOptionPane.showMessageDialog(null, 
                    "Elemento eliminado, al final de la"
                    + " lista!");
                }else{
                    q=cab;
                    while(q.sig!=p)
                        q=q.sig;
                    q.sig=p.sig;
                    p.sig=null;
                    p=null;
                    JOptionPane.showMessageDialog(null, 
                    "Elemento eliminado!");
                }
            }
        }
    }
    public int getLongLista(){
        int cont=0;
        nodo p=null;
        if(cab==null)
            return 0;
        else{
            p=cab;
            while(p!=null){
                cont++;
                p=p.sig;
            }
            return cont;
        }
    }
    /*public nodo promIGH(){
        nodo p=null;
        double c,promG = 0, promH=0, promIMC =0;
        c=getLongLista();
        
        if (getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista esta vacía");
        }else{
            p=cab;
            do{
                promG+=p.getPromGluc()/c;
                promH+=p.getPromHemo()/c;
                promIMC+=p.getPromIMC()/c;
            
            }while(p.sig==null);
            JOptionPane.showMessageDialog(null,
                "El promedio del IMC es: "+ promIMC+"\n"
                +"El promedio de la glucosa es: "+ promG +"\n"
                +"El promedio de la Hemoglobina es: "+promH);        
        }
        return null; 
        // crear una variable para meter todos los 
        //promedios y despues retornarla
    }*/
    public nodo getPromNew(){
        
        nodo p =null;
        double c,promG = 0, promH=0, promIMC =0;
        c=getLongLista();
        
        if (getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista esta vacía");
        }else{
            p=cab;
            String info="El promedio de los pacientes es: ";
                    
            while(p!=null){
                promG+=p.getPromGluc()/c;
                promH+=p.getPromHemo()/c;
                promIMC+=p.getPromIMC()/c;
            }
            info+="Promedio de la Glucosa es: "+promG+"\n";
            info+="Promedio de la Hemoglobina es: "+promH+"\n";
            info+="Promedio del IMC es: "+promIMC+"\n";
            p=p.sig;
            
            return p;
        }
        return null;
    }
        
    public void setRegistrarFilaJTable(DefaultTableModel miModelo,
        int Fila, nodo info){
        miModelo.setValueAt(info.id, Fila, 0);
        miModelo.setValueAt(info.noms, Fila, 1);
        miModelo.setValueAt(info.eps, Fila, 2);
        miModelo.setValueAt(info.genero, Fila, 3);
        miModelo.setValueAt(info.fecha, Fila, 4);
        miModelo.setValueAt(info.peso, Fila, 5);
        miModelo.setValueAt(info.estatura, Fila, 6);
        miModelo.setValueAt(info.nGluc, Fila, 7);
        miModelo.setValueAt(info.nHemo, Fila, 8);
    }
    
    public void setLlenarJTable(JTable tab){
        nodo p=cab;
        int i=0;
        DefaultTableModel miModelo=new DefaultTableModel();
        miModelo.addColumn("Id");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("EPS");
        miModelo.addColumn("Genero (F-M)");
        miModelo.addColumn("Fecha de Nacimiento");
        miModelo.addColumn("Peso");
        miModelo.addColumn("Estatura");
        miModelo.addColumn("Glucosa");
        miModelo.addColumn("Hemoglobina");
        while(p!=null){                        
            miModelo.addRow(new Object[]{"", "", "", "", "", "", "", "", ""});              
            setRegistrarFilaJTable(miModelo,i,p);
            p=p.sig;
            i++;
        }
        tab.setModel(miModelo);
    }

    
        
    

}
