/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro
 */
public class UserFormDialog extends javax.swing.JDialog {

    /**
     * Creates new form UserInfoDialog
     */
    public UserFormDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        etiquetaAccion.setText("Nuevo Usuario");
        
        DefaultComboBoxModel<String> cmRoles = new DefaultComboBoxModel<>();
        cmRoles.addElement(Rol.USER + "");
        cmRoles.addElement(Rol.ADMIN + "");
        cmRoles.addElement(Rol.LEADER + "");
        comboBoxRoles.setModel(cmRoles);
        
        DefaultComboBoxModel<String> cmProyectos = new DefaultComboBoxModel<>();
        cmProyectos.addElement("Ninguno");
        for (Proyecto p : Proyecto.getProyectos()){
            cmProyectos.addElement(p.getNombre());
        }
        comboBoxProyectos.setModel(cmProyectos);
        comboBoxProyectos.addItemListener(
                new ItemListener(){
                    public void itemStateChanged(ItemEvent event){
                        if (event.getSource() == comboBoxProyectos){
                            if(comboBoxProyectos.getSelectedIndex() == 0){
                                DefaultComboBoxModel<String> cmEquipos = new DefaultComboBoxModel<>();
                                cmEquipos.addElement("Ninguno");
                                comboBoxEquipos.setModel(cmEquipos);
                            }
                            else{
                                //se resta 1 debido a que esta el elemento de mas en el modelo de combobox llamado NINGUNO
                                Proyecto proyecto = Proyecto.getProyectos().get(comboBoxProyectos.getSelectedIndex() - 1); 
                                DefaultComboBoxModel<String> cmEquipos = new DefaultComboBoxModel<>();
                                cmEquipos.addElement("Ninguno");
                                for(TipoItem tipo : TipoItem.buscarPorProyecto(proyecto)){
                                    cmEquipos.addElement(tipo.getNombre());
                                }
                                comboBoxEquipos.setModel(cmEquipos);
                            }
                        }
                    }
                }
        );
        
        DefaultComboBoxModel<String> cmEquipos = new DefaultComboBoxModel<>();
        cmEquipos.addElement("Ninguno");
        comboBoxEquipos.setModel(cmEquipos);
        
        edicion = false;
    }
    
    public UserFormDialog(java.awt.Frame parent, boolean modal, Miembro m) {
        super(parent, modal);
        initComponents();
        
        etiquetaAccion.setText("Edición de Usuario");
        campoNombre.setText(m.getNombre());
        campoDni.setText(m.getDni() + "");
        campoUsuario.setText(m.getUsuario());
        campoUsuario.setEditable(false);
        campoPass.setText(m.getPass());
        
        DefaultComboBoxModel<String> cmRoles = new DefaultComboBoxModel<>();
        cmRoles.addElement(Rol.USER + "");
        cmRoles.addElement(Rol.ADMIN + "");
        cmRoles.addElement(Rol.LEADER + "");
        comboBoxRoles.setModel(cmRoles);
        //PRESELECCIONADO EL ROL DEL MIEMBRO A EDITAR
        switch (m.getRol()){
            case USER:
                comboBoxRoles.setSelectedIndex(0);
                break;
            case ADMIN:
                comboBoxRoles.setSelectedIndex(1);
                break;
            case LEADER:
                comboBoxRoles.setSelectedIndex(2);
                break;
        }
        
        DefaultComboBoxModel<String> cmProyectos = new DefaultComboBoxModel<>();
        cmProyectos.addElement("Ninguno");
        for (Proyecto p : Proyecto.getProyectos()){
            cmProyectos.addElement(p.getNombre());
        }
        comboBoxProyectos.setModel(cmProyectos);
        //PRESELECCIONO EL PROYECTO AL QUE EL MIEMBRO YA PERTENECIA
        if(m.getProyecto() != null){
            int i = Proyecto.getProyectos().indexOf(m.getProyecto());
            comboBoxProyectos.setSelectedIndex(i+1);
        }
        comboBoxProyectos.addItemListener(
                new ItemListener(){
                    public void itemStateChanged(ItemEvent event){
                        if (event.getSource() == comboBoxProyectos){
                            if(comboBoxProyectos.getSelectedIndex() == 0){
                                DefaultComboBoxModel<String> cmEquipos = new DefaultComboBoxModel<>();
                                cmEquipos.addElement("Ninguno");
                                comboBoxEquipos.setModel(cmEquipos);
                            }
                            else{
                                //se resta 1 debido a que esta el elemento de mas en el modelo de combobox llamado NINGUNO
                                Proyecto proyecto = Proyecto.getProyectos().get(comboBoxProyectos.getSelectedIndex() - 1); 
                                DefaultComboBoxModel<String> cmEquipos = new DefaultComboBoxModel<>();
                                cmEquipos.addElement("Ninguno");
                                for(TipoItem tipo : TipoItem.buscarPorProyecto(proyecto)){
                                    cmEquipos.addElement(tipo.getNombre());
                                }
                                comboBoxEquipos.setModel(cmEquipos);
                            }
                        }
                    }
                }
        );
        
        DefaultComboBoxModel<String> cmEquipos = new DefaultComboBoxModel<>();
        cmEquipos.addElement("Ninguno");
        int indice = 0;
        if(m.getProyecto() != null){
            int i = 0;
            for (TipoItem tipo : TipoItem.buscarPorProyecto(m.getProyecto())){
                cmEquipos.addElement(tipo.getNombre());
                if(m.getEquipo() == tipo.getEquipo()){
                    indice = i;
                }
                i++;
            }
        }
        comboBoxEquipos.setModel(cmEquipos);
        comboBoxEquipos.setSelectedIndex(indice);
        
        edicion = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaAccion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoDni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboBoxRoles = new javax.swing.JComboBox<>();
        comboBoxProyectos = new javax.swing.JComboBox<>();
        botonConfirmar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        campoPass = new javax.swing.JPasswordField();
        comboBoxEquipos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        etiquetaAccion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        etiquetaAccion.setText("Nuevo Usuario");

        jLabel2.setText("Nombre y Apellido");

        jLabel3.setText("DNI");

        jLabel4.setText("Usuario");

        jLabel5.setText("Contraseña");

        botonConfirmar.setText("Confirmar");
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        jLabel6.setText("Rol");

        jLabel7.setText("Proyecto");

        jLabel1.setText("Equipo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addContainerGap(221, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaAccion)
                .addGap(147, 147, 147))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPass, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaAccion)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonConfirmar)
                    .addComponent(botonCancelar))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
        if(campoNombre.getText() != null && campoDni.getText() != null && campoUsuario.getText() != null && campoPass.getPassword() != null){
            String nombreMiembro = campoNombre.getText();
            Long dniMiembro = Long.parseLong(campoDni.getText());
            String usuarioMiembro = campoUsuario.getText();
            String passMiembro = String.valueOf(campoPass.getPassword());
            Rol rol = Rol.USER;
            switch(comboBoxRoles.getSelectedIndex()){
                case 1:
                    rol = Rol.ADMIN;
                    break;
                case 2:
                    rol = Rol.LEADER;
                    break;
            }
            if(edicion){
                Miembro miembro = Miembro.buscarMiembro(usuarioMiembro);
                if (miembro != null){
                    miembro.setNombre(nombreMiembro);
                    miembro.setDni(dniMiembro);
                    miembro.setPass(passMiembro);
                    miembro.setRol(rol);
                    if (comboBoxProyectos.getSelectedIndex() == 0){
                        miembro.setProyecto(null);  //No se seleccionó ningún proyecto
                        if(miembro.getEquipo() != null){
                            miembro.getEquipo().eliminarIntegrante(miembro);
                            miembro.setEquipo(null);                            
                        }
                    }
                    else{
                        Proyecto proyectoSeleccionado = Proyecto.buscarProyecto(comboBoxProyectos.getItemAt(comboBoxProyectos.getSelectedIndex()));
                        miembro.setProyecto(proyectoSeleccionado);
                        if(comboBoxEquipos.getSelectedIndex() == 0){
                            if(miembro.getEquipo() != null){
                                miembro.getEquipo().eliminarIntegrante(miembro);
                                miembro.setEquipo(null);                            
                            }
                        }
                        else{
                            TipoItem tipo = TipoItem.buscarTipoItem(comboBoxEquipos.getItemAt(comboBoxEquipos.getSelectedIndex()));
                            tipo.getEquipo().addIntegrante(miembro);
                            miembro.setEquipo(tipo.getEquipo());                            
                        }
                    }
                    JOptionPane.showMessageDialog(this, 
                                                   "¡Usuario editado con éxito!",
                                                   "Operación exitosa",
                                                   JOptionPane.PLAIN_MESSAGE); 
                }
                else{
                    JOptionPane.showMessageDialog(this, 
                                                   "Usuario no encontrado.",
                                                   "Error",
                                                   JOptionPane.ERROR_MESSAGE);                    
                }
            }
            else{
                if (comboBoxProyectos.getSelectedIndex() == 0){
                    Miembro nuevoMiembro = new Miembro(nombreMiembro, dniMiembro, usuarioMiembro, passMiembro, rol);
                    Miembro.addMiembro(nuevoMiembro);
                }
                else{
                    Proyecto proyectoSeleccionado = Proyecto.buscarProyecto(comboBoxProyectos.getItemAt(comboBoxProyectos.getSelectedIndex()));
                    Miembro nuevoMiembro = new Miembro(nombreMiembro, dniMiembro, usuarioMiembro, passMiembro, rol, proyectoSeleccionado);
                    Miembro.addMiembro(nuevoMiembro);  
                    if(comboBoxEquipos.getSelectedIndex() != 0){
                        TipoItem tipo = TipoItem.buscarTipoItem(comboBoxEquipos.getItemAt(comboBoxEquipos.getSelectedIndex()));
                        tipo.getEquipo().addIntegrante(nuevoMiembro);
                        nuevoMiembro.setEquipo(tipo.getEquipo());
                    }
                }
                JOptionPane.showMessageDialog(this, 
                                               "¡Usuario creado con éxito!",
                                               "Operación exitosa",
                                               JOptionPane.PLAIN_MESSAGE);                
            }   
            setVisible(false);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Por favor, complete todos los campos para poder continuar.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);            
        }
    }//GEN-LAST:event_botonConfirmarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserFormDialog dialog = new UserFormDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private boolean edicion;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JTextField campoDni;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JPasswordField campoPass;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JComboBox<String> comboBoxEquipos;
    private javax.swing.JComboBox<String> comboBoxProyectos;
    private javax.swing.JComboBox<String> comboBoxRoles;
    private javax.swing.JLabel etiquetaAccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
