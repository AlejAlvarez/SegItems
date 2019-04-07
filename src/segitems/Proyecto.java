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
public class Proyecto {
 
    private static ArrayList<Proyecto> proyectos = new ArrayList<>();
    private static ArrayList<String> nombresProyectos = new ArrayList<>();
    private String nombre;
    private Miembro lider;
//    private ArrayList<Miembro> miembros;
//    private ArrayList<TipoItem> tiposItem;
//    private ArrayList<Item> items; 
//    private ArrayList<Estado> estados;

    public Proyecto(String nombre){
        this.nombre = nombre;
//        miembros = new ArrayList<>();
//        tiposItem = new ArrayList<>();
//        items = new ArrayList<>();
//        estados = new ArrayList<>();
    }
    
    public Proyecto(String nombre, Miembro lider) {
        this.nombre = nombre;
        this.lider = lider;
//        miembros = new ArrayList<>();
//        tiposItem = new ArrayList<>();
//        items = new ArrayList<>();
//        estados = new ArrayList<>();
    }
    
    public static Proyecto buscarProyecto(String nombre){
        Iterator<Proyecto> iterator = proyectos.iterator();
        while (iterator.hasNext()){
            Proyecto proyecto = iterator.next();
            if (proyecto.getNombre().equals(nombre)){
                return proyecto;
            }
        }
        return null;
    }

    public static void addProyecto(Proyecto p){
        proyectos.add(p);
        nombresProyectos.add(p.nombre);
    }
    
    public static ArrayList<Proyecto> getProyectos(){
        return proyectos;
    }
    
    public static ArrayList<String> getNombresProyectos(){
        return nombresProyectos;
    }
    
    public static void cargarProyectosEjemplo(){
        Proyecto pAbby = new Proyecto("Abby");
        Proyecto.addProyecto(pAbby);
        Proyecto pBongo = new Proyecto("Bongo");
        Proyecto.addProyecto(pBongo);        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Miembro getLider() {
        return lider;
    }

    public void setLider(Miembro lider) {
        this.lider = lider;
    }

//    public ArrayList<TipoItem> getTiposItem() {
//        return tiposItem;
//    }
//
//    public void setTiposItem(ArrayList<TipoItem> tiposItem) {
//        this.tiposItem = tiposItem;
//    }
//
//    public ArrayList<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<Item> items) {
//        this.items = items;
//    }
//
//    public ArrayList<Estado> getEstados() {
//        return estados;
//    }
//
//    public void setEstados(ArrayList<Estado> estados) {
//        this.estados = estados;
//    }
//    
//    public void addMiembro(Miembro m){
//        miembros.add(m);
//    }
//    
//    public ArrayList<Miembro> getMiembros(){
//        return miembros;
//    }    
    
}
