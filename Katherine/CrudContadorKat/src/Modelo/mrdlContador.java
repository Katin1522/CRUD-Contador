
package Modelo;

import Vista.jfrCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class mrdlContador {
    
     private String UUID_CONTADOR; 
    private String NOMBRE_CONTADOR;
    private int EDAD_CONTADOR;
    private int PESO_CONTADOR;
     private String CORREO_CONTADOR;
     

    public String getUUID_CONTADOR() {
        return UUID_CONTADOR;
    }

    public void setUUID_CONTADOR(String UUID_CONTADOR) {
        this.UUID_CONTADOR = UUID_CONTADOR;
    }

    public String getNOMBRE_CONTADOR() {
        return NOMBRE_CONTADOR;
    }

    public void setNOMBRE_CONTADOR(String NOMBRE_CONTADOR) {
        this.NOMBRE_CONTADOR = NOMBRE_CONTADOR;
    }

    public int getEDAD_CONTADOR() {
        return EDAD_CONTADOR;
    }

    public void setEDAD_CONTADOR(int EDAD_CONTADOR) {
        this.EDAD_CONTADOR = EDAD_CONTADOR;
    }

    public int getPESO_CONTADOR() {
        return PESO_CONTADOR;
    }

    public void setPESO_CONTADOR(int PESO_CONTADOR) {
        this.PESO_CONTADOR = PESO_CONTADOR;
    }

    public String getCORREO_CONTADOR() {
        return CORREO_CONTADOR;
    }

    public void setCORREO_CONTADOR(String CORREO_CONTADOR) {
        this.CORREO_CONTADOR = CORREO_CONTADOR;
    }
    
   

  

  
    
     public void Guardar() {
       
        Connection conexion = ClaseConexion.getConexion();
        try {
            
            String sql = "INSERT INTO TBCONTADOR (UUID_CONTADOR, NOMBRE_CONTADOR, EDAD_CONTADOR, PESO_CONTADOR,CORREO_CONTADOR) VALUES (?, ?, ?, ?, ?)";
           
            PreparedStatement pstmt = conexion.prepareStatement(sql);
           
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNOMBRE_CONTADOR());
            pstmt.setInt(3, getEDAD_CONTADOR());
            pstmt.setDouble(4, getPESO_CONTADOR());
            pstmt.setString(5,getCORREO_CONTADOR());

         
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
     
     public void Mostrar(JTable tabla) {
        
        Connection conexion = ClaseConexion.getConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_CONTADOR", "NOMBRE_CONTADOR", "EDAD_CONTADOR", "PESO_CONTADOR","CORREO_CONTADOR"});
        try {
           
            String query = "SELECT * FROM TBCONTADOR ";
          
            Statement statement = conexion.createStatement();
       
            ResultSet rs = statement.executeQuery(query);
           
            while (rs.next()) {
               
                modelo.addRow(new Object[]{rs.getString("UUID_CONTADOR"), 
                    rs.getString("NOMBRE_CONTADOR"), 
                     rs.getInt("EDAD_CONTADOR"),
                    rs.getDouble("PESO_CONTADOR"), 
                    rs.getString("CORREO_CONTADOR")});
            }
            
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
             tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
     
      public void Eliminar(JTable tabla) {
       
        Connection conexion = ClaseConexion.getConexion();

        
        int filaSeleccionada = tabla.getSelectedRow();
       

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
     
        try {
            String sql = "delete from TBCONTADOR  where UUID = ?";
            PreparedStatement deleteContador = conexion.prepareStatement(sql);
            deleteContador.setString(1, miId);
            deleteContador.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
      public void Actualizar(JTable tabla) {
       
        Connection conexion = ClaseConexion.getConexion();

       
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
           
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            
           

            try {
                
                String sql = "update TBCONTADORset NOMBRE_CONTADOR= ?, EDAD_CONTADOR = ?, PESO_CONTADOR = ?, getCORREO_CONTADOR = ?, where UUID = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNOMBRE_CONTADOR());
                updateUser.setInt(2, getEDAD_CONTADOR());
                updateUser.setDouble(3, getPESO_CONTADOR());
                updateUser.setString(1, getCORREO_CONTADOR());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
      
      public void limpiar(jfrCrud vista) {
        vista.txtNombreC.setText("");
        vista.txtEdadC.setText("");
        vista.txtPesoC.setText("");
        vista.txtCorreoC.setText("");
    }
      
        public void cargarDatosTabla(jfrCrud vista) {
       
        int filaSeleccionada = vista.jTableC.getSelectedRow();

 
       
        if (filaSeleccionada != -1) {
            String NOMBRE = vista.jTableC.getValueAt(filaSeleccionada, 1).toString();
            String EDAD = vista.jTableC.getValueAt(filaSeleccionada, 2).toString();
            String PESO = vista.jTableC.getValueAt(filaSeleccionada, 3).toString();
             String CORREO  = vista.jTableC.getValueAt(filaSeleccionada, 0).toString();

           
            vista.txtNombreC.setText(NOMBRE);
            vista.txtEdadC.setText(EDAD);
            vista.txtPesoC.setText(PESO);
            vista.txtCorreoC.setText(CORREO);
        }
    }
      
}
