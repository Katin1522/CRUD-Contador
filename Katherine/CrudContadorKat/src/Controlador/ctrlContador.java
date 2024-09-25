
package Controlador;

import Modelo.mrdlContador;
import Vista.jfrCrud;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlContador implements MouseListener, KeyListener {
    
    private mrdlContador Modelo; 
    private jfrCrud Vista; 
    
    
     public ctrlContador(mrdlContador Modelo, jfrCrud Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;

       
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.jTableC.addMouseListener(this);

        Modelo.Mostrar(Vista.jTableC);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
       
        if (e.getSource() == Vista.btnAgregar) {
            if (Vista.txtNombreC.getText().isEmpty() || Vista.txtEdadC.getText().isEmpty() || Vista.txtPesoC.getText().isEmpty()|| Vista.txtCorreoC.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    
                    Modelo.setNOMBRE_CONTADOR(Vista.txtNombreC.getText());
                    Modelo.setEDAD_CONTADOR(Integer.parseInt(Vista.txtEdadC.getText()));
                    Modelo.setPESO_CONTADOR(Integer.parseInt(Vista.txtPesoC.getText()));
                    Modelo.setCORREO_CONTADOR(Vista.txtCorreoC.getText());
                    
                    Modelo.Guardar();
                    Modelo.Mostrar(Vista.jTableC);
                    Modelo.limpiar(Vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == Vista.btnEliminar) {
            if (Vista.txtNombreC.getText().isEmpty() || Vista.txtEdadC.getText().isEmpty() || Vista.txtPesoC.getText().isEmpty()|| Vista.txtCorreoC.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(Vista.jTableC);
                Modelo.Mostrar(Vista.jTableC);
                Modelo.limpiar(Vista);
            }
        }

        if (e.getSource() == Vista.btnActualizar) {
            if (Vista.txtNombreC.getText().isEmpty() || Vista.txtEdadC.getText().isEmpty() || Vista.txtPesoC.getText().isEmpty()|| Vista.txtCorreoC.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                 
                   Modelo.setNOMBRE_CONTADOR(Vista.txtNombreC.getText());
                    Modelo.setEDAD_CONTADOR(Integer.parseInt(Vista.txtEdadC.getText()));
                    Modelo.setPESO_CONTADOR(Integer.parseInt(Vista.txtPesoC.getText()));
                    Modelo.setCORREO_CONTADOR(Vista.txtCorreoC.getText());

                      
                    Modelo.Actualizar(Vista.jTableC);
                    Modelo.Mostrar(Vista.jTableC);
                    Modelo.limpiar(Vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        

        if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.jTableC) {
            Modelo.cargarDatosTabla(Vista);
        }
    }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
