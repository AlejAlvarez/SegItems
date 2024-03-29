/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Alejandro
 */
public class AdminFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public AdminFrame() {
        initComponents();
    }
    
    public AdminFrame(Miembro usuarioLogueado){
        initComponents();
        setTitle("SegItems - Sistema de Seguimiento de Items");
        
        usuarioAdmin.setText(usuarioLogueado.getUsuario());
        nombreAdmin.setText(usuarioLogueado.getNombre());
        rolAdmin.setText(usuarioLogueado.getRol() + "");
        nroDniAdmin.setText(usuarioLogueado.getDni() + "");
        this.usuarioLogueado = usuarioLogueado;
        
        //INICIALIZACION JLIST PROYECTOS
        DefaultListModel<String> lmProyectos = new DefaultListModel<>();
        lmProyectos.addElement("Todos");
        for (Proyecto p : Proyecto.getProyectos()){
            lmProyectos.addElement(p.getNombre());
        }
        listaProyectos.setModel(lmProyectos);
        listaProyectos.setVisibleRowCount(8);
        listaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProyectos.setLayoutOrientation(JList.VERTICAL);
        
        listaProyectos.addListSelectionListener(
                new ListSelectionListener(){
                    public void valueChanged(ListSelectionEvent event){
                        if (listaProyectos.getValueIsAdjusting() == false) {
                            if (listaProyectos.getSelectedValue() == "Todos"){
                                //TODO: MOSTRAR TODOS LOS TIPOS DE ITEM Y LOS ITEMS EN LAS SIGUIENTES 2 PESTAÑAS
                                nombreProyecto.setText("");
                                liderProyecto.setText("");
                                listaMiembrosProyecto.setModel(new DefaultListModel());

                                DefaultListModel<String> lmTiposItem = new DefaultListModel<>();
                                lmTiposItem.addElement("Todos");
                                for (TipoItem tipo : TipoItem.getTiposItem()){
                                    lmTiposItem.addElement(tipo.getNombre());
                                }
                                
                                listaTiposItem.clearSelection();
                                listaTiposItem.setModel(lmTiposItem);
                                listaTiposItem.setVisibleRowCount(8);
                                listaTiposItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                listaTiposItem.setLayoutOrientation(JList.VERTICAL);                                
                            }
                            else{
                                Proyecto p = Proyecto.buscarProyecto(listaProyectos.getSelectedValue());
                                nombreProyecto.setText(p.getNombre());
                                liderProyecto.setText(p.getLider().getNombre());

                                DefaultListModel<String> lmMiembros = new DefaultListModel<>();
                                for (Miembro m : Miembro.buscarPorProyecto(p)){
                                    lmMiembros.addElement(m.getNombre());
                                }

                                listaMiembrosProyecto.setModel(lmMiembros);
                                listaMiembrosProyecto.setVisibleRowCount(8);
                                listaMiembrosProyecto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                listaMiembrosProyecto.setLayoutOrientation(JList.VERTICAL);

                                DefaultListModel<String> lmTiposItem = new DefaultListModel<>();
                                for (TipoItem tipo : TipoItem.buscarPorProyecto(p)){
                                    lmTiposItem.addElement(tipo.getNombre());
                                }
                                
                                listaTiposItem.clearSelection();
                                listaTiposItem.setModel(lmTiposItem);
                                listaTiposItem.setVisibleRowCount(8);
                                listaTiposItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                listaTiposItem.setLayoutOrientation(JList.VERTICAL);                                     
                            }
                        }
                    }
                });
        
        //INICIALIZACION DE LISTA TIPOS ITEM
        DefaultListModel<String> lmTiposItem = new DefaultListModel<>();
        lmTiposItem.addElement("Todos");
        for (TipoItem tipo : TipoItem.getTiposItem()){
            lmTiposItem.addElement(tipo.getNombre());
        }

        listaTiposItem.setModel(lmTiposItem);
        listaTiposItem.setVisibleRowCount(8);
        listaTiposItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTiposItem.setLayoutOrientation(JList.VERTICAL); 
        
        listaTiposItem.addListSelectionListener(
                new ListSelectionListener(){
                    public void valueChanged (ListSelectionEvent event){
                        if (listaTiposItem.getValueIsAdjusting()==false){
                            if (!listaTiposItem.isSelectionEmpty()){
                                if (listaTiposItem.getSelectedValue() == "Todos"){
                                    listaEquipoTipoItem.setModel(new DefaultListModel<>());
                                    listaEstadosTipoItem.setModel(new DefaultListModel<>());
                                    
                                    DefaultListModel<String> lmItems = new DefaultListModel<>();
                                    for (Item item : Item.getItems()){
                                        lmItems.addElement(item.getNombre());
                                    }
                                    listaItems.setModel(lmItems);
                                    listaItems.setVisibleRowCount(8);
                                    listaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                    listaItems.setLayoutOrientation(JList.VERTICAL); 
                                }
                                else{
                                    TipoItem tipo = TipoItem.buscarTipoItem(listaTiposItem.getSelectedValue());

                                    DefaultListModel<String> lmEquipo = new DefaultListModel<>();
                                    for (Miembro m : tipo.getEquipo().getIntegrantes()){
                                        lmEquipo.addElement(m.getNombre());
                                    }

                                    listaEquipoTipoItem.setModel(lmEquipo);
                                    listaEquipoTipoItem.setVisibleRowCount(8);
                                    listaEquipoTipoItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                    listaEquipoTipoItem.setLayoutOrientation(JList.VERTICAL); 

                                    DefaultListModel<String> lmEstados = new DefaultListModel<>();
                                    for (Estado e : tipo.getEstados()){
                                        lmEstados.addElement(e.getNombre());
                                    }

                                    listaEstadosTipoItem.setModel(lmEstados);
                                    listaEstadosTipoItem.setVisibleRowCount(8);
                                    listaEstadosTipoItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                    listaEstadosTipoItem.setLayoutOrientation(JList.VERTICAL);
                                    
                                    DefaultListModel<String> lmItems = new DefaultListModel<>();
                                    for (Item item : Item.buscarPorTipoItem(tipo)){
                                        lmItems.addElement(item.getNombre());
                                    }
                                    listaItems.setModel(lmItems);
                                    listaItems.setVisibleRowCount(8);
                                    listaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                    listaItems.setLayoutOrientation(JList.VERTICAL);                                                                      
                                }
                            }
                            else{
                                listaEquipoTipoItem.setModel(new DefaultListModel<>());
                                listaEstadosTipoItem.setModel(new DefaultListModel<>());
                            }
                        }
                    }
                });
        
        //INICIALIZACION LISTA ITEMS GENERALES
        DefaultListModel<String> lmItems = new DefaultListModel<>();
        for (Item item : Item.getItems()){
            lmItems.addElement(item.getNombre());
        }
        listaItems.setModel(lmItems);
        listaItems.setVisibleRowCount(8);
        listaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaItems.setLayoutOrientation(JList.VERTICAL);
        listaItems.addListSelectionListener(
                new ListSelectionListener(){
                    public void valueChanged (ListSelectionEvent event){
                        if(listaItems.getValueIsAdjusting() == false){
                            if(listaItems.isSelectionEmpty() == false){
                                Item item = Item.buscarItem(listaItems.getSelectedValue());
                                nombreItem.setText(item.getNombre());
                                tipoItem.setText(item.getTipoItem().getNombre());
                                prioridadItem.setText(item.getPrioridad() + "");
                                duenoItem.setText(item.getUsuario().getNombre());
                                estadoItem.setText(item.getEstado().getNombre());
                                responsableItem.setText(item.getResponsable().getNombre());
                                
                                textAreaSecuenciaEstados.setText("");
                                //RELLENAR LA SECUENCIA DE ESTADOS
                                for (SecuenciaEstados sec : item.getSecuenciaEstados()){
                                    textAreaSecuenciaEstados.append("Item: " + sec.getItem().getNombre() + "\n"
                                                            + "Estado: " + sec.getEstado().getNombre() + "\n"
                                                            + "Responsable: " + sec.getResponsable().getNombre() + "\n"
                                                            + "Última Fecha: " + sec.getUltFecha() + "\n"
                                                            + "------------------------------------------- \n");
                                }
                            }
                        }
                    }                    
                }
        );        
        
        //INICIALIZACION JLIST MIEMBROS GENERALES
        DefaultListModel<String> lmMiembros = new DefaultListModel<>();
        for (Miembro m : Miembro.getMiembros()){
            lmMiembros.addElement(m.getNombre());
        }
        listaMiembros.setModel(lmMiembros);
        listaMiembros.setVisibleRowCount(8);
        listaMiembros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMiembros.setLayoutOrientation(JList.VERTICAL);
        
        listaMiembros.addListSelectionListener(
                new ListSelectionListener(){
                    public void valueChanged(ListSelectionEvent event){
                        if (listaMiembros.getValueIsAdjusting() == false) {
                            if(!listaMiembros.isSelectionEmpty()){
                                Miembro m = Miembro.buscarMiembroPorNombre(listaMiembros.getSelectedValue());
                                nombreMiembro.setText(m.getNombre());
                                dniMiembro.setText(m.getDni() + "");
                                rolMiembro.setText(m.getRol() + "");
                                usuarioMiembro.setText(m.getUsuario());
                                
                                DefaultListModel<String> lmItemsEnviadosMiembro = new DefaultListModel<>();
                                for (Item item : m.getItemsEnviados()){
                                    lmItemsEnviadosMiembro.addElement(item.getNombre());
                                }
                                listaItemsEnviadosMiembro.setModel(lmItemsEnviadosMiembro);
                                listaItemsEnviadosMiembro.setVisibleRowCount(8);
                                listaItemsEnviadosMiembro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                listaItemsEnviadosMiembro.setLayoutOrientation(JList.VERTICAL);
                                
                                DefaultListModel<String> lmItemsACargoMiembro = new DefaultListModel<>();
                                for (Item item : m.getResponsabilidadItems()){
                                    lmItemsACargoMiembro.addElement(item.getNombre());
                                }
                                listaItemsACargoMiembro.setModel(lmItemsACargoMiembro);
                                listaItemsACargoMiembro.setVisibleRowCount(8);
                                listaItemsACargoMiembro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                listaItemsACargoMiembro.setLayoutOrientation(JList.VERTICAL); 
                            }
                            else{
                                nombreMiembro.setText("");
                                dniMiembro.setText("");
                                rolMiembro.setText("");
                                usuarioMiembro.setText("");     
                                
                                listaItemsEnviadosMiembro.setModel(new DefaultListModel<>());
                                listaItemsACargoMiembro.setModel(new DefaultListModel<>());
                            }
                        }
                    }
                }
        );
        
        //INICIALIZACION LISTA ITEMS ENVIADOS DEL USUARIO LOGUEADO
        DefaultListModel<String> lmItemsEnviadosAdmin = new DefaultListModel<>();
        for (Item item : usuarioLogueado.getItemsEnviados()){
            lmItemsEnviadosAdmin.addElement(item.getNombre());
        }
        listaItemsEnviadosAdmin.setModel(lmItemsEnviadosAdmin);
        listaItemsEnviadosAdmin.setVisibleRowCount(8);
        listaItemsEnviadosAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaItemsEnviadosAdmin.setLayoutOrientation(JList.VERTICAL);
        
        //INICIALIZACION LISTA ITEMS A CARGO DEL USUARIO LOGUEADO
        DefaultListModel<String> lmItemsACargoAdmin = new DefaultListModel<>();
        for (Item item : usuarioLogueado.getResponsabilidadItems()){
            lmItemsACargoAdmin.addElement(item.getNombre());
        }
        listaItemsACargoAdmin.setModel(lmItemsACargoAdmin);
        listaItemsACargoAdmin.setVisibleRowCount(8);
        listaItemsACargoAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaItemsACargoAdmin.setLayoutOrientation(JList.VERTICAL); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator9 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usuarioAdmin = new javax.swing.JLabel();
        nombreAdmin = new javax.swing.JLabel();
        rolAdmin = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botonEditarInfo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        scrollPaneItemsEnviadosAdmin = new javax.swing.JScrollPane();
        listaItemsEnviadosAdmin = new javax.swing.JList<>();
        botonEnviarItemAdmin = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        scrollPaneItemsACargoAdmin = new javax.swing.JScrollPane();
        listaItemsACargoAdmin = new javax.swing.JList<>();
        botonAvanzarEstadoAdmin = new javax.swing.JButton();
        botonCerrarSesion = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        botonVerAviso = new javax.swing.JButton();
        nroDniAdmin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        scrollPaneProyectos = new javax.swing.JScrollPane();
        listaProyectos = new javax.swing.JList<>();
        scrollPaneMiembrosProyecto = new javax.swing.JScrollPane();
        listaMiembrosProyecto = new javax.swing.JList<>();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        botonAgregarProyecto = new javax.swing.JButton();
        botonEliminarProyecto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        botonAgregarMiembroProyecto = new javax.swing.JButton();
        botonQuitarMiembroProyecto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        nombreProyecto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        liderProyecto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        scrollPaneTiposItem = new javax.swing.JScrollPane();
        listaTiposItem = new javax.swing.JList<>();
        scrollPaneEquipoTipoItem = new javax.swing.JScrollPane();
        listaEquipoTipoItem = new javax.swing.JList<>();
        scrollPaneEstadosTipoItem = new javax.swing.JScrollPane();
        listaEstadosTipoItem = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        botonAgregarTipoItem = new javax.swing.JButton();
        botonEliminarTipoItem = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        botonAgregarMiembroEquipo = new javax.swing.JButton();
        botonQuitarMiembroEquipo = new javax.swing.JButton();
        botonAgregarEstadoTipoItem = new javax.swing.JButton();
        botonQuitarEstadoTipoItem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        scrollPaneItems = new javax.swing.JScrollPane();
        listaItems = new javax.swing.JList<>();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        estadoItem = new javax.swing.JLabel();
        nombreItem = new javax.swing.JLabel();
        tipoItem = new javax.swing.JLabel();
        prioridadItem = new javax.swing.JLabel();
        duenoItem = new javax.swing.JLabel();
        responsableItem = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        botonCambiarResponsable = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaSecuenciaEstados = new javax.swing.JTextArea();
        botonAvanzarEstado = new javax.swing.JButton();
        botonEnviarItem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        scrollPaneMiembros = new javax.swing.JScrollPane();
        listaMiembros = new javax.swing.JList<>();
        scrollPaneItemsEnviadosMiembro = new javax.swing.JScrollPane();
        listaItemsEnviadosMiembro = new javax.swing.JList<>();
        botonNuevoMiembro = new javax.swing.JButton();
        botonEliminarMiembro = new javax.swing.JButton();
        botonEditarMiembro = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        scrollPaneItemsACargoMiembro = new javax.swing.JScrollPane();
        listaItemsACargoMiembro = new javax.swing.JList<>();
        nombreMiembro = new javax.swing.JLabel();
        dniMiembro = new javax.swing.JLabel();
        rolMiembro = new javax.swing.JLabel();
        usuarioMiembro = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segitems/icons/usericon-2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 100, 100));

        usuarioAdmin.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        usuarioAdmin.setText("usuarioAdmin");
        jPanel1.add(usuarioAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        nombreAdmin.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        nombreAdmin.setText("nombreAdmin");
        jPanel1.add(nombreAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        rolAdmin.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        rolAdmin.setText("rolAdmin");
        jPanel1.add(rolAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jLabel5.setText("DNI:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        botonEditarInfo.setText("Editar Información");
        botonEditarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarInfoActionPerformed(evt);
            }
        });
        jPanel1.add(botonEditarInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 160, -1));

        jLabel6.setText("Items enviados");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 206, -1, -1));

        scrollPaneItemsEnviadosAdmin.setViewportView(listaItemsEnviadosAdmin);

        jPanel1.add(scrollPaneItemsEnviadosAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 228, 220, 250));

        botonEnviarItemAdmin.setText("Enviar nuevo Item");
        botonEnviarItemAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarItemAdminActionPerformed(evt);
            }
        });
        jPanel1.add(botonEnviarItemAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 220, -1));

        jLabel7.setText("Items a cargo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 206, -1, -1));

        scrollPaneItemsACargoAdmin.setViewportView(listaItemsACargoAdmin);

        jPanel1.add(scrollPaneItemsACargoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 228, 220, 250));

        botonAvanzarEstadoAdmin.setText("Avanzar Estado en Item");
        botonAvanzarEstadoAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAvanzarEstadoAdminActionPerformed(evt);
            }
        });
        jPanel1.add(botonAvanzarEstadoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 220, -1));

        botonCerrarSesion.setText("Cerrar sesión");
        botonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(botonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 190, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 350, 20));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 10, 160));

        botonVerAviso.setText("Ver avisos");
        botonVerAviso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerAvisoActionPerformed(evt);
            }
        });
        jPanel1.add(botonVerAviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 190, -1));

        nroDniAdmin.setText("nroDniAdmin");
        jPanel1.add(nroDniAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jTabbedPane1.addTab("Inicio", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel8.setText("Proyectos en curso");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        listaProyectos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPaneProyectos.setViewportView(listaProyectos);

        jPanel2.add(scrollPaneProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 340));

        scrollPaneMiembrosProyecto.setViewportView(listaMiembrosProyecto);

        jPanel2.add(scrollPaneMiembrosProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 200, 270));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 780, 10));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 10, 570));

        botonAgregarProyecto.setText("Agregar Proyecto");
        botonAgregarProyecto.setEnabled(false);
        botonAgregarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProyectoActionPerformed(evt);
            }
        });
        jPanel2.add(botonAgregarProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 240, -1));

        botonEliminarProyecto.setText("Eliminar Proyecto");
        botonEliminarProyecto.setEnabled(false);
        jPanel2.add(botonEliminarProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 240, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel9.setText("Información del Proyecto");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Miembros");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        botonAgregarMiembroProyecto.setText("Agregar Miembro");
        botonAgregarMiembroProyecto.setEnabled(false);
        jPanel2.add(botonAgregarMiembroProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 130, -1));

        botonQuitarMiembroProyecto.setText("Quitar Miembro");
        botonQuitarMiembroProyecto.setEnabled(false);
        jPanel2.add(botonQuitarMiembroProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, 130, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Nombre:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        nombreProyecto.setText("-");
        jPanel2.add(nombreProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("Líder:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        liderProyecto.setText("-");
        jPanel2.add(liderProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, -1, -1));

        jTabbedPane1.addTab("Proyectos", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 780, 10));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 10, 570));

        scrollPaneTiposItem.setViewportView(listaTiposItem);

        jPanel3.add(scrollPaneTiposItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 340));

        scrollPaneEquipoTipoItem.setViewportView(listaEquipoTipoItem);

        jPanel3.add(scrollPaneEquipoTipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 160, 290));

        scrollPaneEstadosTipoItem.setViewportView(listaEstadosTipoItem);

        jPanel3.add(scrollPaneEstadosTipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 150, 290));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel15.setText("Tipos de Item");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel16.setText("Información del Tipo de Item");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        botonAgregarTipoItem.setText("Agregar Tipo de Item");
        botonAgregarTipoItem.setEnabled(false);
        botonAgregarTipoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarTipoItemActionPerformed(evt);
            }
        });
        jPanel3.add(botonAgregarTipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 240, -1));

        botonEliminarTipoItem.setText("Eliminar Tipo de Item");
        botonEliminarTipoItem.setEnabled(false);
        jPanel3.add(botonEliminarTipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 240, -1));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Equipo");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Estados");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, -1));

        botonAgregarMiembroEquipo.setText("Agregar Miembro");
        botonAgregarMiembroEquipo.setEnabled(false);
        jPanel3.add(botonAgregarMiembroEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 160, -1));

        botonQuitarMiembroEquipo.setText("Quitar Miembro");
        botonQuitarMiembroEquipo.setEnabled(false);
        jPanel3.add(botonQuitarMiembroEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 160, -1));

        botonAgregarEstadoTipoItem.setText("Agregar Estado");
        botonAgregarEstadoTipoItem.setEnabled(false);
        jPanel3.add(botonAgregarEstadoTipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 150, -1));

        botonQuitarEstadoTipoItem.setText("Quitar Estado");
        botonQuitarEstadoTipoItem.setEnabled(false);
        jPanel3.add(botonQuitarEstadoTipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 150, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel2.setText("Nota: para ver los Tipos de Item de un determinado Proyecto,");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel3.setText("seleccione un proyecto dentro de la pestaña \"Proyectos\".");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, -1, -1));

        jLabel28.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel28.setText("Para ver todos los Tipos de Item, seleccione \"Todos\" en la");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, -1, -1));

        jLabel29.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel29.setText("pestaña \"Proyectos\".");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, -1, -1));

        jTabbedPane1.addTab("Tipos de Item", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneItems.setViewportView(listaItems);

        jPanel4.add(scrollPaneItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 340));
        jPanel4.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 780, 10));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 10, 570));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel19.setText("Items");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLabel20.setText("Nombre:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jLabel21.setText("Tipo:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jLabel22.setText("Prioridad:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jLabel23.setText("Usuario:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        jLabel24.setText("Responsable:");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        jLabel25.setText("Estado Actual:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, 20));

        estadoItem.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        estadoItem.setText("-");
        jPanel4.add(estadoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        nombreItem.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        nombreItem.setText("-");
        jPanel4.add(nombreItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        tipoItem.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tipoItem.setText("-");
        jPanel4.add(tipoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        prioridadItem.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        prioridadItem.setText("-");
        jPanel4.add(prioridadItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        duenoItem.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        duenoItem.setText("-");
        jPanel4.add(duenoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        responsableItem.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        responsableItem.setText("-");
        jPanel4.add(responsableItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setText("Secuencia de Estados");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, -1, -1));

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel45.setText("Información del Item");
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        botonCambiarResponsable.setText("Cambiar Responsable");
        botonCambiarResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarResponsableActionPerformed(evt);
            }
        });
        jPanel4.add(botonCambiarResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 160, -1));

        textAreaSecuenciaEstados.setEditable(false);
        textAreaSecuenciaEstados.setColumns(20);
        textAreaSecuenciaEstados.setRows(5);
        jScrollPane2.setViewportView(textAreaSecuenciaEstados);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 120, -1, 280));

        botonAvanzarEstado.setText("Avanzar Estado");
        botonAvanzarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAvanzarEstadoActionPerformed(evt);
            }
        });
        jPanel4.add(botonAvanzarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 160, -1));

        botonEnviarItem.setText("Enviar Nuevo Item");
        botonEnviarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarItemActionPerformed(evt);
            }
        });
        jPanel4.add(botonEnviarItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 160, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel4.setText("Nota: para ver todos los Items, seleccione");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, -1, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel12.setText("\"Todos\" en la pestaña de \"Tipos de Item\".");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel14.setText("Para ver los Items de un determinado Tipo");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, -1, -1));

        jLabel26.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel26.setText("de Item, seleccione el mismo en la pestaña");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 460, -1, -1));

        jLabel27.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel27.setText("\"Tipos de Item\".");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 470, -1, -1));

        jTabbedPane1.addTab("Items", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 780, 10));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 10, 570));

        scrollPaneMiembros.setViewportView(listaMiembros);

        jPanel5.add(scrollPaneMiembros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 340));

        scrollPaneItemsEnviadosMiembro.setViewportView(listaItemsEnviadosMiembro);

        jPanel5.add(scrollPaneItemsEnviadosMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 230, 130));

        botonNuevoMiembro.setText("Nuevo Miembro");
        botonNuevoMiembro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoMiembroActionPerformed(evt);
            }
        });
        jPanel5.add(botonNuevoMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 170, -1));

        botonEliminarMiembro.setText("Eliminar Miembro");
        botonEliminarMiembro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarMiembroActionPerformed(evt);
            }
        });
        jPanel5.add(botonEliminarMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 170, -1));

        botonEditarMiembro.setText("Editar Miembro");
        botonEditarMiembro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarMiembroActionPerformed(evt);
            }
        });
        jPanel5.add(botonEditarMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 170, -1));

        jLabel33.setText("Nombre:");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jLabel34.setText("DNI:");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        jLabel35.setText("Rol:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel36.setText("Usuario:");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        scrollPaneItemsACargoMiembro.setViewportView(listaItemsACargoMiembro);

        jPanel5.add(scrollPaneItemsACargoMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 230, 130));

        nombreMiembro.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        nombreMiembro.setText("-");
        jPanel5.add(nombreMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        dniMiembro.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        dniMiembro.setText("-");
        jPanel5.add(dniMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        rolMiembro.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rolMiembro.setText("-");
        jPanel5.add(rolMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        usuarioMiembro.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        usuarioMiembro.setText("-");
        jPanel5.add(usuarioMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, -1));

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel41.setText("Items enviados");
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, -1));

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel42.setText("Items a cargo");
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, -1, -1));

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel43.setText("Miembros");
        jPanel5.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel44.setText("Información del Miembro");
        jPanel5.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jTabbedPane1.addTab("Miembros", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarSesionActionPerformed
        LoginForm form = new LoginForm();
        form.setLocationRelativeTo(this);
        form.setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_botonCerrarSesionActionPerformed

    private void botonEditarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarInfoActionPerformed
        UserFormDialog dialogo = new UserFormDialog(this, true, usuarioLogueado);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
        if (!dialogo.isVisible()){
            usuarioAdmin.setText(usuarioLogueado.getUsuario());
            nombreAdmin.setText(usuarioLogueado.getNombre());
            rolAdmin.setText(usuarioLogueado.getRol() + "");
            nroDniAdmin.setText(usuarioLogueado.getDni() + "");            
        }
        
    }//GEN-LAST:event_botonEditarInfoActionPerformed

    private void botonAgregarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAgregarProyectoActionPerformed

    private void botonAgregarTipoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarTipoItemActionPerformed
        //System.out.println(listaTiposItem.getSelectedValue());
    }//GEN-LAST:event_botonAgregarTipoItemActionPerformed

    private void botonEliminarMiembroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarMiembroActionPerformed
        if(usuarioLogueado.getRol() == Rol.ADMIN){
            if(!listaMiembros.isSelectionEmpty()){
                Miembro miembro = Miembro.buscarMiembroPorNombre(listaMiembros.getSelectedValue());

                if(miembro.getRol() != Rol.LEADER && miembro.getRol() != Rol.ADMIN){
                    Miembro.eliminarMiembro(miembro);

                    DefaultListModel<String> lmMiembros = new DefaultListModel<>();
                    for (Miembro m : Miembro.getMiembros()){
                        lmMiembros.addElement(m.getNombre());
                    }
                    listaMiembros.setModel(lmMiembros);
                    listaMiembros.setVisibleRowCount(8);
                    listaMiembros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listaMiembros.setLayoutOrientation(JList.VERTICAL);                
                }
                else{
                    JOptionPane.showMessageDialog(this, 
                                                   "Los Administradores y Líderes de Proyecto no pueden ser eliminados.",
                                                   "Error",
                                                   JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, 
                                               "Por favor, seleccione un usuario para eliminar",
                                               "Error",
                                               JOptionPane.WARNING_MESSAGE);            
            }
        }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Sólo los Administradores del Sistema pueden eliminar miembros.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_botonEliminarMiembroActionPerformed

    private void botonNuevoMiembroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoMiembroActionPerformed
        if(usuarioLogueado.getRol() == Rol.ADMIN){
            UserFormDialog dialogo = new UserFormDialog(this, true);
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
            if (!dialogo.isVisible()){
                DefaultListModel<String> lmMiembros = new DefaultListModel<>();
                for (Miembro m : Miembro.getMiembros()){
                    lmMiembros.addElement(m.getNombre());
                }
                listaMiembros.setModel(lmMiembros);
                listaMiembros.setVisibleRowCount(8);
                listaMiembros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listaMiembros.setLayoutOrientation(JList.VERTICAL); 
            }            
        }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Sólo los Administradores del Sistema pueden agregar nuevos miembros.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);              
        }
    }//GEN-LAST:event_botonNuevoMiembroActionPerformed

    private void botonEditarMiembroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarMiembroActionPerformed
        if(usuarioLogueado.getRol() == Rol.ADMIN){
            if(!listaMiembros.isSelectionEmpty()){
                if(listaMiembros.getValueIsAdjusting() == false){
                    Miembro miembro = Miembro.buscarMiembroPorNombre(listaMiembros.getSelectedValue());
                    UserFormDialog dialogo = new UserFormDialog(this, true, miembro);
                    dialogo.setLocationRelativeTo(this);
                    dialogo.setVisible(true);
                    if (!dialogo.isVisible()){
                        DefaultListModel<String> lmMiembros = new DefaultListModel<>();
                        for (Miembro m : Miembro.getMiembros()){
                            lmMiembros.addElement(m.getNombre());
                        }
                        listaMiembros.setModel(lmMiembros);
                        listaMiembros.setVisibleRowCount(8);
                        listaMiembros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listaMiembros.setLayoutOrientation(JList.VERTICAL); 
                    }
                }            
            }
            else{
                JOptionPane.showMessageDialog(this, 
                                               "Por favor, seleccione un usuario para editar",
                                               "Error",
                                               JOptionPane.WARNING_MESSAGE);            
            }            
        }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Sólo los Administradores del Sistema pueden editar otros miembros.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);                        
        }
    }//GEN-LAST:event_botonEditarMiembroActionPerformed

    private void botonEnviarItemAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarItemAdminActionPerformed
        ItemFormDialog dialogo = new ItemFormDialog(this, true, usuarioLogueado);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
        if (!dialogo.isVisible()){
            DefaultListModel<String> lmItemsEnviadosAdmin = new DefaultListModel<>();
            for (Item item : usuarioLogueado.getItemsEnviados()){
                lmItemsEnviadosAdmin.addElement(item.getNombre());
            }
            listaItemsEnviadosAdmin.setModel(lmItemsEnviadosAdmin);
            listaItemsEnviadosAdmin.setVisibleRowCount(8);
            listaItemsEnviadosAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaItemsEnviadosAdmin.setLayoutOrientation(JList.VERTICAL);
            
            DefaultListModel<String> lmItems =  new DefaultListModel<>();
            for (Item item : Item.getItems()){
                lmItems.addElement(item.getNombre());
            }
            listaItems.setModel(lmItems);
            listaItems.setVisibleRowCount(10);
            listaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaItems.setLayoutOrientation(JList.VERTICAL);
        }
    }//GEN-LAST:event_botonEnviarItemAdminActionPerformed

    private void botonVerAvisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerAvisoActionPerformed
        if(usuarioLogueado.hayAviso()){
            String avisos = "";
            for (String aviso : usuarioLogueado.getAvisos()){
                avisos = avisos + aviso + "\n";
            }
            JOptionPane.showMessageDialog(this, 
                                           "Se registraron cambios en los siguientes items: \n" + avisos,
                                           "Aviso",
                                           JOptionPane.INFORMATION_MESSAGE);             
        }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "No se registraron cambios en sus Items",
                                           "Sin avisos",
                                           JOptionPane.INFORMATION_MESSAGE);             
        }
    }//GEN-LAST:event_botonVerAvisoActionPerformed

    private void botonAvanzarEstadoAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAvanzarEstadoAdminActionPerformed
        if(listaItemsACargoAdmin.isSelectionEmpty() == false){
            if(listaItemsACargoAdmin.getValueIsAdjusting() == false){
                CambioEstadoDialog dialogo = new CambioEstadoDialog(this, true, 
                        usuarioLogueado.getResponsabilidadItems().get(listaItemsACargoAdmin.getSelectedIndex()));
                dialogo.setLocationRelativeTo(this);
                dialogo.setVisible(true);
                
                if (!dialogo.isVisible()){
                    DefaultListModel<String> lmItemsACargoAdmin = new DefaultListModel<>();
                    for (Item item : usuarioLogueado.getResponsabilidadItems()){
                        lmItemsACargoAdmin.addElement(item.getNombre());
                    }
                    listaItemsACargoAdmin.setModel(lmItemsACargoAdmin);
                    listaItemsACargoAdmin.setVisibleRowCount(8);
                    listaItemsACargoAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listaItemsACargoAdmin.setLayoutOrientation(JList.VERTICAL); 
                }
            }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Por favor, seleccione un Item de la lista de Items a Cargo para continuar.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);              
            }
        }
    }//GEN-LAST:event_botonAvanzarEstadoAdminActionPerformed

    private void botonCambiarResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarResponsableActionPerformed
        if(usuarioLogueado.getRol() == Rol.LEADER || (usuarioLogueado.getEquipo() != null && usuarioLogueado.getEquipo().esLider(usuarioLogueado))){
            if(!listaItems.isSelectionEmpty()){
                if(!listaItems.getValueIsAdjusting()){
                    Item item = Item.buscarItem(listaItems.getSelectedValue());
                    Miembro ultimoResponsable = item.getResponsable();
                    
                    CambioResponsableDialog dialogo = new CambioResponsableDialog(this, true, item);                    
                    dialogo.setLocationRelativeTo(this);
                    dialogo.setVisible(true);
                    
                    if(!dialogo.isVisible() && ultimoResponsable != item.getResponsable()){
                        responsableItem.setText(item.getResponsable().getNombre());
                        SecuenciaEstados sec = item.getSecuenciaEstados().get(item.getSecuenciaEstados().size()-1);
                        textAreaSecuenciaEstados.append("Item: " + sec.getItem().getNombre() + "\n"
                                                + "Estado: " + sec.getEstado().getNombre() + "\n"
                                                + "Responsable: " + sec.getResponsable().getNombre() + "\n"
                                                + "Última Fecha: " + sec.getUltFecha() + "\n"
                                                + "------------------------------------------- \n");
                        
                        DefaultListModel<String> lmItemsACargoAdmin = new DefaultListModel<>();
                        for (Item i : usuarioLogueado.getResponsabilidadItems()){
                            lmItemsACargoAdmin.addElement(i.getNombre());
                        }
                        listaItemsACargoAdmin.setModel(lmItemsACargoAdmin);
                        listaItemsACargoAdmin.setVisibleRowCount(8);
                        listaItemsACargoAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listaItemsACargoAdmin.setLayoutOrientation(JList.VERTICAL);                             
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(this, 
                                               "Por favor, seleccione un Item de la lista de Items para continuar.",
                                               "Error",
                                               JOptionPane.WARNING_MESSAGE);                              
            }
        }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Sólo los líderes de proyecto o de equipo pueden cambiar de responsable.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);            
        }
    }//GEN-LAST:event_botonCambiarResponsableActionPerformed

    private void botonEnviarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarItemActionPerformed
        ItemFormDialog dialogo = new ItemFormDialog(this, true, usuarioLogueado);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
        if (!dialogo.isVisible()){
            DefaultListModel<String> lmItemsEnviadosAdmin = new DefaultListModel<>();
            for (Item item : usuarioLogueado.getItemsEnviados()){
                lmItemsEnviadosAdmin.addElement(item.getNombre());
            }
            listaItemsEnviadosAdmin.setModel(lmItemsEnviadosAdmin);
            listaItemsEnviadosAdmin.setVisibleRowCount(8);
            listaItemsEnviadosAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaItemsEnviadosAdmin.setLayoutOrientation(JList.VERTICAL);
            
            DefaultListModel<String> lmItems =  new DefaultListModel<>();
            for (Item item : Item.getItems()){
                lmItems.addElement(item.getNombre());
            }
            listaItems.setModel(lmItems);
            listaItems.setVisibleRowCount(10);
            listaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaItems.setLayoutOrientation(JList.VERTICAL);
        }
    }//GEN-LAST:event_botonEnviarItemActionPerformed

    private void botonAvanzarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAvanzarEstadoActionPerformed
        if(listaItems.isSelectionEmpty() == false){
            if(listaItems.getValueIsAdjusting() == false){
                Item itemAvanzar = Item.buscarItem(listaItems.getSelectedValue());
                if(itemAvanzar.getResponsable() == usuarioLogueado){
                    CambioEstadoDialog dialogo = new CambioEstadoDialog(this, true, itemAvanzar);
                    dialogo.setLocationRelativeTo(this);
                    dialogo.setVisible(true);

                    if (!dialogo.isVisible()){
                        DefaultListModel<String> lmItemsACargoAdmin = new DefaultListModel<>();
                        for (Item item : usuarioLogueado.getResponsabilidadItems()){
                            lmItemsACargoAdmin.addElement(item.getNombre());
                        }
                        listaItemsACargoAdmin.setModel(lmItemsACargoAdmin);
                        listaItemsACargoAdmin.setVisibleRowCount(8);
                        listaItemsACargoAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listaItemsACargoAdmin.setLayoutOrientation(JList.VERTICAL); 
                    }                    
                }
                else{
                    JOptionPane.showMessageDialog(this, 
                                                   "Sólo los responsables de un Item pueden hacer que avance de Estado",
                                                   "Error",
                                                   JOptionPane.WARNING_MESSAGE);                                  
                }
            }
        else{
            JOptionPane.showMessageDialog(this, 
                                           "Por favor, seleccione un Item de la lista de Items a Cargo para continuar.",
                                           "Error",
                                           JOptionPane.WARNING_MESSAGE);              
            }
        }
    }//GEN-LAST:event_botonAvanzarEstadoActionPerformed

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
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }

    private Miembro usuarioLogueado;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarEstadoTipoItem;
    private javax.swing.JButton botonAgregarMiembroEquipo;
    private javax.swing.JButton botonAgregarMiembroProyecto;
    private javax.swing.JButton botonAgregarProyecto;
    private javax.swing.JButton botonAgregarTipoItem;
    private javax.swing.JButton botonAvanzarEstado;
    private javax.swing.JButton botonAvanzarEstadoAdmin;
    private javax.swing.JButton botonCambiarResponsable;
    private javax.swing.JButton botonCerrarSesion;
    private javax.swing.JButton botonEditarInfo;
    private javax.swing.JButton botonEditarMiembro;
    private javax.swing.JButton botonEliminarMiembro;
    private javax.swing.JButton botonEliminarProyecto;
    private javax.swing.JButton botonEliminarTipoItem;
    private javax.swing.JButton botonEnviarItem;
    private javax.swing.JButton botonEnviarItemAdmin;
    private javax.swing.JButton botonNuevoMiembro;
    private javax.swing.JButton botonQuitarEstadoTipoItem;
    private javax.swing.JButton botonQuitarMiembroEquipo;
    private javax.swing.JButton botonQuitarMiembroProyecto;
    private javax.swing.JButton botonVerAviso;
    private javax.swing.JLabel dniMiembro;
    private javax.swing.JLabel duenoItem;
    private javax.swing.JLabel estadoItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel liderProyecto;
    private javax.swing.JList<String> listaEquipoTipoItem;
    private javax.swing.JList<String> listaEstadosTipoItem;
    private javax.swing.JList<String> listaItems;
    private javax.swing.JList<String> listaItemsACargoAdmin;
    private javax.swing.JList<String> listaItemsACargoMiembro;
    private javax.swing.JList<String> listaItemsEnviadosAdmin;
    private javax.swing.JList<String> listaItemsEnviadosMiembro;
    private javax.swing.JList<String> listaMiembros;
    private javax.swing.JList<String> listaMiembrosProyecto;
    private javax.swing.JList<String> listaProyectos;
    private javax.swing.JList<String> listaTiposItem;
    private javax.swing.JLabel nombreAdmin;
    private javax.swing.JLabel nombreItem;
    private javax.swing.JLabel nombreMiembro;
    private javax.swing.JLabel nombreProyecto;
    private javax.swing.JLabel nroDniAdmin;
    private javax.swing.JLabel prioridadItem;
    private javax.swing.JLabel responsableItem;
    private javax.swing.JLabel rolAdmin;
    private javax.swing.JLabel rolMiembro;
    private javax.swing.JScrollPane scrollPaneEquipoTipoItem;
    private javax.swing.JScrollPane scrollPaneEstadosTipoItem;
    private javax.swing.JScrollPane scrollPaneItems;
    private javax.swing.JScrollPane scrollPaneItemsACargoAdmin;
    private javax.swing.JScrollPane scrollPaneItemsACargoMiembro;
    private javax.swing.JScrollPane scrollPaneItemsEnviadosAdmin;
    private javax.swing.JScrollPane scrollPaneItemsEnviadosMiembro;
    private javax.swing.JScrollPane scrollPaneMiembros;
    private javax.swing.JScrollPane scrollPaneMiembrosProyecto;
    private javax.swing.JScrollPane scrollPaneProyectos;
    private javax.swing.JScrollPane scrollPaneTiposItem;
    private javax.swing.JTextArea textAreaSecuenciaEstados;
    private javax.swing.JLabel tipoItem;
    private javax.swing.JLabel usuarioAdmin;
    private javax.swing.JLabel usuarioMiembro;
    // End of variables declaration//GEN-END:variables
}
