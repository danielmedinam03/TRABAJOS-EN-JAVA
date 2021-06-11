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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ruben
 */
public class Pila {
    Stack miData;
    
    
    
    Pila(){
        miData = new Stack();
    }
    
    public Productos getBuscarCod(String cod){
        Productos temp=null;
        int i;        
        for(i=0; i<miData.size(); i++){
            temp=(Productos) miData.get(i);
            if(temp.codP.equals(cod))
                return temp;
        }        
        return null;
    }
    
    public Productos getCrearNodo(
            JTextField jtfCodP,
            JTextField jtfNomP,
            JTextField jtfExistencia,
            JTextField jtfPrecioC,
            JTextField jtfPrecioV
    ) {
        Productos info = null;
        try {
            Productos buscar = getBuscarCod(
                    jtfCodP.getText()
            );
            if (buscar != null) {
                JOptionPane.showMessageDialog(null,
                        "El código de producto ya se encuentra "
                        + "en la pila, intente nuevamente!");
                jtfCodP.setText("");
                jtfCodP.requestFocus();
                return null;
            } else {
                info = new Productos(
                        jtfCodP.getText(),
                        jtfNomP.getText(),
                        Float.parseFloat(jtfExistencia.getText()),
                        Float.parseFloat(jtfPrecioC.getText()),
                        Float.parseFloat(jtfPrecioV.getText())
                );
                return info;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public void setPush(
        JTextField jtfCod,
        JTextField jtfnoms,
        JTextField jtfExis,
        JTextField jtfPrecioC,
        JTextField jtfPrecioV
    ){
        Productos info=getCrearNodo( 
                jtfCod,
                jtfnoms, 
                jtfExis, 
                jtfPrecioC,
                jtfPrecioV);
        if(info!=null){
            miData.push(info);
            //int pos=miData.search(info);
            JOptionPane.showMessageDialog(null, 
                "El producto se ha registro en la "
                + "ultima posicion de la pila !"  
            
            );
            
        }
        
    }
    public void setPop() {
        if (!miData.isEmpty()) {
            miData.pop();
            JOptionPane.showMessageDialog(null,
                    "Se ha eliminado de la pila.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error: Pila esta vacía!");
        }
    }
    public void setDelCodp(String cod) {
        Productos temp;
        if (!miData.isEmpty()) {
            temp=getBuscarCod(cod);
            if ((temp!=null)){
                miData.remove(temp);
                JOptionPane.showMessageDialog(null,
                    "Elemento eliminado!!");
            }            
        } else{
            JOptionPane.showMessageDialog(null,
                    "Lista Vacía!!");
        }        
    }
    

    public void setRegistrarFilaJTable(DefaultTableModel miModelo,
            int Fila, Productos info) {
        miModelo.setValueAt(info.codP, Fila, 0);
        miModelo.setValueAt(info.nomP, Fila, 1);
        miModelo.setValueAt(info.existencia, Fila, 2);
        miModelo.setValueAt(info.precioC, Fila, 3);
        miModelo.setValueAt(info.precioV, Fila, 4);
        miModelo.setValueAt(info.getGanancia(), Fila, 5);

    }

    public void setLlenarJTable(JTable tab) {
        Productos p;
        int i;
        DefaultTableModel miModelo = new DefaultTableModel();
        miModelo.addColumn("Codigo");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Existencia");
        miModelo.addColumn("Precio Compra");
        miModelo.addColumn("Precio Venta");
        miModelo.addColumn("Ganancia");
        for (i=0; i<miData.size(); i++){
            p = (Productos)miData.get(i); 
            miModelo.addRow(new Object[]{"", "", "", "", "", ""});
            setRegistrarFilaJTable(miModelo,i, p);
        
        }
        tab.setModel(miModelo);
    }
}
//p = (Productos)miData.get(i);
        /*if (p!= null){   
            while (i<miData.size()){
                p = (Productos)miData.get(i); 
                miModelo.addRow(new Object[]{"", "", "", "", "", ""});
                setRegistrarFilaJTable(miModelo,i, p);
                
                i++;
            }
            for (i=0; i<miData.size(); i++)
                 
                miModelo.addRow(new Object[]{"", "", "", "", "", ""});
                setRegistrarFilaJTable(miModelo,i, p);
                    */