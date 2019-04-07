/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alejandro
 */
public class TipoItem {
    private static ArrayList<TipoItem> tiposItem = new ArrayList<>();
    
    private ArrayList<Estado> estados;
    private Equipo equipo;
    private String nombre;
    private Estado estadoInicial;
    private Proyecto proyecto;

    
    public TipoItem(String nombre, Proyecto proyecto) {
        this.nombre = nombre;
        this.proyecto = proyecto;
        equipo = new Equipo();
    }
    
    private static void addTipoItem(TipoItem tipoItem) {
        tiposItem.add(tipoItem);
    }
    
    public static ArrayList<TipoItem> buscarPorProyecto(Proyecto p){
        ArrayList<TipoItem> list = new ArrayList<>();
        
        Iterator<TipoItem> iterator = tiposItem.iterator();
        while (iterator.hasNext()){
            TipoItem tipo = iterator.next();
            if (tipo.getProyecto() != null){
                if (tipo.getProyecto().equals(p)){
                    list.add(tipo);
                }                
            }
        }
        return list;
    }
    
    public static ArrayList<TipoItem> getTiposItem(){
        return tiposItem;
    }
    
    public static TipoItem buscarTipoItem(String nombre){
        Iterator<TipoItem> iterator = tiposItem.iterator();
        while (iterator.hasNext()){
            TipoItem tipo = iterator.next();
            if (tipo.getNombre().equals(nombre)){
                return tipo;
            }
        }
        return null;
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
        ArrayList<Miembro> miembros = Miembro.buscarPorProyecto(pAbby);
        
        TipoItem reporte = new TipoItem("Reporte de Bug", pAbby);
        TipoItem.addTipoItem(reporte);
        reporte.setEstados(Estado.cargarEstadosEjemplo(reporte.getNombre()));      //la carga de estados devuelve un arrayList conteniendo todos los estados cargados
        reporte.setEstadoInicial(reporte.getEstados().get(0));  //se agrega el estado inicial llamado "inicial"
        for (int i=0;i<3;i++){
            reporte.getEquipo().addIntegrante(miembros.get(i));
            miembros.get(i).setEquipo(reporte.getEquipo());
        }
        reporte.getEquipo().setLider(miembros.get(0));
        
        TipoItem mejora = new TipoItem("Mejora", pAbby);
        TipoItem.addTipoItem(mejora);
        mejora.setEstados(Estado.cargarEstadosEjemplo(mejora.getNombre()));
        mejora.setEstadoInicial(mejora.getEstados().get(0));
        for (int i=3;i<6;i++){
            mejora.getEquipo().addIntegrante(miembros.get(i));
            miembros.get(i).setEquipo(mejora.getEquipo());
        }
        mejora.getEquipo().setLider(miembros.get(3));
    }
    
}
