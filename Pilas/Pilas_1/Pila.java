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
public class Pila {

    nodo tope;

    Pila() {
        tope = null;
    }

    public boolean getEsVacia() {
        return tope == null ? true : false;
    }

    public int getLongPila() {
        if (getEsVacia()) {
            return 0;
        } else {
            nodo p = tope;
            int cont = 0;
            do {
                cont++;
                p = p.sig;
            } while (p != tope);
            return cont;
        }
    }

    public nodo getBuscarCod(String cod) {
        if (getEsVacia()) {
            return null;
        } else {
            nodo p = tope;
            do {
                if (cod.equals(p.codP)) {
                    return p;
                } else {
                    p = p.sig;
                }
            } while (p != tope);
            return null;
        }
    }

    public nodo getCrearNodo(
            JTextField jtfCodP,
            JTextField jtfNomP,
            JTextField jtfExis,
            JTextField jtfCompra,
            JTextField jtfVenta
    ) {
        nodo info = null;
        try {
            nodo buscar = getBuscarCod(
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
                info = new nodo(
                        jtfCodP.getText(),
                        jtfNomP.getText(),
                        Float.parseFloat(jtfExis.getText()),
                        Float.parseFloat(jtfCompra.getText()),
                        Float.parseFloat(jtfVenta.getText())
                );
                return info;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public nodo getBase() {
        if (getEsVacia()) {
            return null;
        } else {
            nodo p = tope;
            do {
                p = p.sig;
            } while (p.sig != tope);
            return p;
        }
    }

    public void setPush(
            JTextField jtfCodP,
            JTextField jtfNom,
            JTextField jtfExis,
            JTextField jtfCompra,
            JTextField jtfVenta
    ) {
        nodo info = getCrearNodo(
                jtfCodP,
                jtfNom,
                jtfExis,
                jtfCompra,
                jtfVenta);
        if (info != null) {
            if (getEsVacia()) {
                tope = info;
                tope.sig = tope;
            } else {
                nodo base = getBase();
                info.sig = tope;
                base.sig = info;
                tope = info;
                
            }
        }
    }
    
    //Este método Push agrega elementos a una pila
    //auxiliar, aquí no se validan elementos repetidos
    //porque son tomados de la pila original.
    public void set_Push(
            String CodP,
            String NomP,
            float Existencia,
            float PrecioC,
            float PrecioV
    ) {
        nodo info = new nodo(CodP, NomP, Existencia, PrecioC, PrecioV);
        if (getEsVacia()) {
            tope = info;
            tope.sig = tope;
        } else {
            nodo base = getBase();
            info.sig = tope;
            base.sig = info;
            tope = info;
        }

    }

    public void setPop() {
        if (getEsVacia()) {
            JOptionPane.showMessageDialog(null,
                    "Error: Pila esta vacía!");
        } else {
            if (tope.sig == tope) {
                tope = null;
                JOptionPane.showMessageDialog(null,
                        "Elemento eliminado del tope!, Pila vacía.");
            } else {
                nodo base = getBase();
                nodo temp = tope;
                tope = tope.sig;
                base.sig = tope;
                temp.sig = null;
                temp = null;
                JOptionPane.showMessageDialog(null,
                        "Elemento eliminado !");
            }
        }
    }
    
    public void set_Pop() {
        if (getEsVacia()) {
            JOptionPane.showMessageDialog(null,
                    "Error: Pila esta vacía!");
        } else {
            if (tope.sig == tope) {
                tope = null;                
            } else {
                nodo base = getBase();
                nodo temp = tope;
                tope = tope.sig;
                base.sig = tope;
                temp.sig = null;
                temp = null;                
            }
        }
    }    

    //Este método dado un Código de producto, lo elimina
    //de la Pila, apilando y desapilando elementos.
    public void setDelCodp(String cod) {
        //Pila auxiliar para desapilar elementos
        Pila Daux = new Pila();
        if (getEsVacia()) {
            JOptionPane.showMessageDialog(null,
                    "Pila vacía! Imposible eliminar.");
        } else {
            nodo b = getBuscarCod(cod);
            if (b == null) {
                JOptionPane.showMessageDialog(null,
                        "El código a eliminar no se "
                        + "encuentra registrado!");
            } else {
                if ((tope.sig == tope) && (b == tope)) {
                    tope = null;
                    JOptionPane.showMessageDialog(null,
                            "Elemento eliminado ! "
                            + "La Pila quedo vacía.");
                } else if ((tope.sig != null) && (b == tope)) {
                    setPop();
                } else {
                    nodo temp = null;
                    temp = tope;
                    do {
                        Daux.set_Push(
                                temp.codP,
                                temp.nomP,
                                temp.existencia,
                                temp.precioC,
                                temp.precioV
                        );
                        temp = temp.sig;
                        set_Pop();
                    } while (temp != b);
                    setPop();

                    //tope=Daux.tope; 
                    while (Daux.tope != null) {
                        set_Push(
                            Daux.tope.codP,
                            Daux.tope.nomP,
                            Daux.tope.existencia,
                            Daux.tope.precioC,
                            Daux.tope.precioV
                        );
                        Daux.set_Pop();
                    }
                }
            }
        }
    }
    public void setRegistrarFilaJTable(DefaultTableModel miModelo,
            int Fila, nodo info) {
        miModelo.setValueAt(info.codP, Fila, 0);
        miModelo.setValueAt(info.nomP, Fila, 1);
        miModelo.setValueAt(info.existencia, Fila, 2);
        miModelo.setValueAt(info.precioC, Fila, 3);
        miModelo.setValueAt(info.precioV, Fila, 4);
        miModelo.setValueAt(info.getGanancia(), Fila, 5);

    }

    public void setLlenarJTable(JTable tab) {
        nodo p = tope;
        int i;
        DefaultTableModel miModelo = new DefaultTableModel();
        miModelo.addColumn("Codigo");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Existencia");
        miModelo.addColumn("Precio Compra");
        miModelo.addColumn("Precio Venta");
        miModelo.addColumn("Ganancia");
        if (p!= null){
            for (i=0; i<getLongPila(); i++){
                miModelo.addRow(new Object[]{"", "", "", "", "", ""});
                setRegistrarFilaJTable(miModelo, i, p);
                p = p.sig;
                //i++;
            }
        }
        tab.setModel(miModelo);
    }
}
