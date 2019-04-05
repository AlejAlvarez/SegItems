/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class TipoItem {
    private static ArrayList<TipoItem> tiposItem;
    
    private ArrayList<Estado> estados;
    private Equipo equipo;
    private String nombre;
    private Estado estadoInicial;
    private Proyecto proyecto;

    
    private static void addTipoItem(TipoItem tipoItem) {
        tiposItem.add(tipoItem);
    }
    
    public TipoItem(String nombre, Proyecto proyecto) {
        this.nombre = nombre;
        this.proyecto = proyecto;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setEstados(ArrayList<Estado> estados){
        this.estados = estados;
    }
    
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
        estados.add(estadoInicial);
    }
    
    public void addEstado(Estado e){
        estados.add(e);
    }
    
    public ArrayList<Estado> getEstados(){
        return estados;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
    public static void cargarTiposItemEjemplo(){
        Proyecto pAbby = Proyecto.buscarProyecto("Abby");
        
        TipoItem reporte = new TipoItem("Reporte de Bug", pAbby);
        
        TipoItem.addTipoItem(reporte);
        
        reporte.setEstados(Estado.cargarEstadosEjemplo());      //la carga de estados devuelve un arrayList conteniendo todos los estados cargados
        
        reporte.setEstadoInicial(reporte.getEstados().get(0));  //se agrega el estado inicial llamado "inicial"
    }
    
}
