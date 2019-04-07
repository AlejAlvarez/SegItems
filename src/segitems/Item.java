/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Alejandro
 */
public class Item {
    
    private static ArrayList<Item> items = new ArrayList<>();
    private String nombre;
    private TipoItem tipoItem;
    private int prioridad;
    private Estado estado;
    private Miembro usuario;
    private ArrayList<SecuenciaEstados> secuenciaEstados;
    private Miembro responsable;

    public Item(String nombre, int prioridad, TipoItem tipoItem, Miembro usuario) {
        this.nombre = nombre;
        this.tipoItem = tipoItem;
        this.prioridad = prioridad;
        this.usuario = usuario;
        secuenciaEstados = new ArrayList<>();
    }
    
    public void inicializarItem(){
        usuario.addItemEnviado(this);
        estado = tipoItem.getEstadoInicial();
        responsable = tipoItem.getEquipo().getLider();
        responsable.addResponsabilidadItem(this);
        addItem(this);
    }    

    public static void addItem(Item item){
        items.add(item);
    }

    public static ArrayList<Item> getItems() {
        return items;
    }
    
    public static Item buscarItem(String nombre){
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()){
            Item item = iterator.next();
            if (item.getNombre().equals(nombre)){
                return item;
            }
        }
        return null;        
    }
    
    public static ArrayList<Item> buscarPorTipoItem(TipoItem tipo){
        ArrayList<Item> list = new ArrayList<>();
        
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()){
            Item item = iterator.next();
            if (item.getTipoItem()!= null){
                if (item.getTipoItem().equals(tipo)){
                    list.add(item);
                }                
            }
        }
        return list;
    }
    
    public void addSecuencia(){
        secuenciaEstados.add(new SecuenciaEstados(this, responsable, estado, new Date()));
    }

    public ArrayList<SecuenciaEstados> getSecuenciaEstados() {
        return secuenciaEstados;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Miembro getResponsable() {
        return responsable;
    }

    public void setResponsable(Miembro responsable) {
        this.responsable = responsable;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public Miembro getUsuario() {
        return usuario;
    }

    public static void cargarItemsEjemplo(){
        Miembro usuario = Miembro.buscarMiembroPorNombre("Alejandro Alvarez");
        
        Item bug1 = new Item("Bug #1", 2, TipoItem.getTiposItem().get(0), usuario);
        bug1.inicializarItem();
        
        Item mejora1 = new Item("Mejora #1", 1, TipoItem.getTiposItem().get(1), usuario);
        mejora1.inicializarItem();
    }
    
}
