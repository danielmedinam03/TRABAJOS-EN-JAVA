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
 * @author Ruben
 */
public class ListaD {
    //Definimos la cabecera de la lista
    nodo cab;
    
    //Se crea la lista vacía
    public ListaD(){
        cab=null;
    }
    
    //Buscar si un código de empleado se encuentra
    //en la lista.  Este método retorna un nodo
    //apuntando a donde se encuentre la información o
    //de lo contrario retorna null.
    public nodo getBuscarCod(String codE){
        if(cab==null)
            return null;
        else{
            nodo p = cab;
            while(p!=null){
                if((p.cod).equals(codE))
                    return p;
                else
                    p=p.sig;  //Avanza un posición en la lista
            }
            return null;
        }
    }
    
    //Método que retorna true si la lista
    //esta vacía dlc (de lo contrario) retorna null
    public boolean getEsVacia(){
        return cab==null?true:false;
        /*
        if(cab==null)return true;else return false;
        */
    }
    
    //Método para determinar el número de elementos de 
    //la lista
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
    
    //Este método crea un nuevo nodo sin código de 
    //empleado repetido en la lista.
    public nodo getCrearNodo(
        JTextField Jtfcod,
        JTextField Jtfnoms,
        JTextField JtfNHorasT,
        JTextField JtfVHoraT,
        JTextField JtfNhorasE){
        nodo buscar = null;        
        try {                        
            buscar = getBuscarCod(Jtfcod.getText());
            if (buscar != null) {
                JOptionPane.showMessageDialog(null,
                    "Error: Este código ya se encuentra "
                    + "registrado.  Reemplazar código!"); 
                Jtfcod.setText("");
                Jtfcod.requestFocus();                
                return null;
            }            
            //Una vez capturados los datos, se crea en memoria el nodo
            nodo info = new nodo(
                Jtfcod.getText(), 
                Jtfnoms.getText(), 
                Double.parseDouble(JtfNHorasT.getText()), 
                Double.parseDouble(JtfVHoraT.getText()), 
                Double.parseDouble(JtfNhorasE.getText()));
            return info;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
            return null;
        }
    }
    
    public void setAddInicio(
        JTextField Jtfcod,
        JTextField Jtfnoms,
        JTextField JtfNHorasT,
        JTextField JtfVHoraT,
        JTextField JtfNhorasE){
        //Creamos el nodo que se desea registrar
        //en la lista
        nodo info = getCrearNodo(
            Jtfcod,
            Jtfnoms, 
            JtfNHorasT, 
            JtfVHoraT, 
            JtfNhorasE
        );
        
        if (info != null) {
            if (cab == null) {
                cab = info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento "
                        + "a la lista.  La lista estaba vacía.");
            } else {
                //Enlazamos el nuevo nodo a la lista
                info.sig = cab;
                cab.ant = info;
                //Ahora se debe mover cab al primer 
                //nuevo elemento
                cab = info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento al "
                        + "inicio de la lista.");
            }
        }else{            
        }
    }
    
    //Método: getUltimo
    //Coloca un apuntador en el último nodo de la lista.
    public nodo getUltimo(){
        if(cab==null)
            return null;
        else{
            nodo p = cab;
            while (p.sig != null) {
                p = p.sig;
            }
            return p;
        }
    }
    
    //Este método agrega un nuevo nodo al final de la lista.
    public void setAddFinal(
        JTextField Jtfcod,
        JTextField Jtfnoms,
        JTextField JtfNHorasT,
        JTextField JtfVHoraT,
        JTextField JtfNhorasE){
        //Creamos el nuevo nodo a registrar
        nodo info = getCrearNodo(
            Jtfcod,
            Jtfnoms, 
            JtfNHorasT, 
            JtfVHoraT, 
            JtfNhorasE
        );
        nodo p;
        if (info != null) {
            if (cab == null) {
                cab = info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento "
                        + "a la lista.  La lista estaba vacía.");
            } else {
                
                nodo ultimo=getUltimo();
                ultimo.sig = info;
                info.ant = ultimo;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento al "
                        + "final de la lista.");
            }
        }else{            
        }
    }
    
    public void setRegistrarFilaJTable(DefaultTableModel miModelo,
        int Fila, nodo info){
        miModelo.setValueAt(info.cod, Fila, 0);
        miModelo.setValueAt(info.noms, Fila, 1);
        miModelo.setValueAt(info.nHorasT, Fila, 2);
        miModelo.setValueAt(info.valorHora, Fila, 3);
        miModelo.setValueAt(info.nHorasE, Fila, 4);
    }
    
    public void setLlenarJTable(JTable tab){
        nodo p=cab;
        int i=0;
        DefaultTableModel miModelo=new DefaultTableModel();
        miModelo.addColumn("Código");
        miModelo.addColumn("Nombres y Apellidos");
        miModelo.addColumn("# Horas Trabajadas");
        miModelo.addColumn("Valor Hora Trabajada");
        miModelo.addColumn("# Horas Extras");
        while(p!=null){                        
            miModelo.addRow(new Object[]{"", "", "", "", ""});              
            setRegistrarFilaJTable(miModelo,i,p);
            p=p.sig;
            i++;
        }
        tab.setModel(miModelo);
    }
    
    public void setModificarData(
        String jtfcod,
        String jtfnoms,
        Double jtfNH,
        Double jtfvalorH,
        Double jtfNE
    ){
        if(cab==null)
            JOptionPane.showMessageDialog(null, 
                "Lista vacía");
        else{
            nodo buscar=getBuscarCod(jtfcod);
            if(buscar==null)
                JOptionPane.showMessageDialog(null, 
                    "El código seleccionado no existe!");
            else{
                try{
                    buscar.noms = jtfnoms;
                    buscar.nHorasT = jtfNH;
                    buscar.valorHora = jtfvalorH;
                    buscar.nHorasE = jtfNE;
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, 
                        ""+e);                    
                }
            }
        }
    }
    
    //Este método dado un código, lo busca y si existe
    //lo elimina de la lista.
    public void setElimCodigo(String cod){
        if(getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista no tiene elementos!");
        }else{
            nodo p, q;            
            p=getBuscarCod(cod);
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
                    cab.ant=null;
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
                    p.ant=null;
                    p=null;
                    JOptionPane.showMessageDialog(null, 
                    "Elemento eliminado, al final de la"
                    + " lista!");
                }else{                    
                    p.ant.sig=p.sig;
                    p.sig.ant=p.ant;
                    p.sig=p.ant=null;
                    p=null;
                    JOptionPane.showMessageDialog(null, 
                    "Elemento eliminado!");
                }
            }
        }
    }
    
    //Este método determina el pago de un empleado
    //dado su código
    public void getCalcularPago(String cod){
        if(getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista se encuentra vacía!");
        }else{
            nodo buscar=getBuscarCod(cod);
            if(buscar==null)
                JOptionPane.showMessageDialog(null, 
                    "El código de empleado no esta registrado!");
            else{
                String info="La información de pago del "
                    + "empleado es: \n";
                double pagoP = buscar.nHorasT * buscar.valorHora;
                double pagoE = buscar.nHorasE * (buscar.valorHora * 2);
                double pagoN = pagoP + pagoE;
                info += "Código: "+buscar.cod+"\n";
                info += "Nombres: "+buscar.noms+"\n";
                info += "# Horas Trabajadas: "+buscar.nHorasT+"\n";
                info += "# Horas Extras: "+buscar.nHorasE+"\n";
                info += "Valor hora: "+buscar.valorHora+"\n";
                info += "Valor Parcial: "+pagoP+"\n";
                info += "Valor Extras: "+pagoE+"\n";
                info += "Neto a pagar: "+pagoN+"\n";
                JOptionPane.showMessageDialog(null, info);
            }
        }
    }      
    
    //Este método determinar al empleado con mayor 
    //sueldo, retorna un nodo apuntado a la información
    //en la lista.
    public nodo getMayorSueldo(){
        double mayor=-1;
        nodo p, pmay=null;
        if(cab==null)
            return null;
        else{
            p=cab;
            while(p!=null){
                if(p.getSalarioNeto()>mayor){
                    mayor=p.getSalarioNeto();
                    pmay=p;
                }
                p=p.sig;
            }
            return pmay;
        }
    }
    
    //Este método determinar al empleado con menor 
    //sueldo, retorna un nodo apuntado a la información
    //en la lista.
    public nodo getMenorSueldo(){
        double menor;
        nodo p, pmen=null;
        if(cab==null)
            return null;
        else{
            p=cab;
            menor=p.getSalarioNeto();
            while(p!=null){
                if(p.getSalarioNeto()<menor){
                    menor=p.getSalarioNeto();
                    pmen=p;
                }
                p=p.sig;
            }
            return pmen;
        }
    }
    
    //Este método muestra en pantalla la información
    //de pago del mayor y menor sueldo
    public void getInfoMayMen(){
        if(getEsVacia()){
            JOptionPane.showMessageDialog(null, 
                "La lista se encuentra vacía!");
        }else{
            nodo infoMay=getMayorSueldo();
            nodo infoMen=getMenorSueldo();
            
            if(infoMay==null)
                JOptionPane.showMessageDialog(null, 
                    "Sin datos del mayor!");
            else{
                String info="La información de pago del "
                    + "empleado con mayor sueldo es: \n";
                double pagoP = infoMay.nHorasT * infoMay.valorHora;
                double pagoE = infoMay.nHorasE * (infoMay.valorHora * 2);
                double pagoN = pagoP + pagoE;
                info += "Código: "+infoMay.cod+"\n";
                info += "Nombres: "+infoMay.noms+"\n";
                info += "# Horas Trabajadas: "+infoMay.nHorasT+"\n";
                info += "# Horas Extras: "+infoMay.nHorasE+"\n";
                info += "Valor hora: "+infoMay.valorHora+"\n";
                info += "Valor Parcial: "+pagoP+"\n";
                info += "Valor Extras: "+pagoE+"\n";
                info += "Neto a pagar: "+pagoN+"\n";
                JOptionPane.showMessageDialog(null, info);
            }
            
            if(infoMen==null)
                JOptionPane.showMessageDialog(null, 
                    "Sin datos del menor!");
            else{
                String info="La información de pago del "
                    + "empleado con menor sueldo es: \n";
                double pagoP = infoMen.nHorasT * infoMen.valorHora;
                double pagoE = infoMen.nHorasE * (infoMen.valorHora * 2);
                double pagoN = pagoP + pagoE;
                info += "Código: "+infoMen.cod+"\n";
                info += "Nombres: "+infoMen.noms+"\n";
                info += "# Horas Trabajadas: "+infoMen.nHorasT+"\n";
                info += "# Horas Extras: "+infoMen.nHorasE+"\n";
                info += "Valor hora: "+infoMen.valorHora+"\n";
                info += "Valor Parcial: "+pagoP+"\n";
                info += "Valor Extras: "+pagoE+"\n";
                info += "Neto a pagar: "+pagoN+"\n";
                JOptionPane.showMessageDialog(null, info);
            }
        }
    }
    
}
