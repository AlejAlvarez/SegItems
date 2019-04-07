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
public class Miembro {
    
    private static ArrayList<Miembro> miembros = new ArrayList<Miembro>();
    private String nombre;
    private long dni;
    private Rol rol;
    private String usuario;
    private String pass;
    private ArrayList<Item> itemsEnviados;
    private ArrayList<Item> itemsACargo;
    private ArrayList<String> avisos;
    private Proyecto proyecto;
    private Equipo equipo;
    
    public Miembro(){
        
    }
    
    public Miembro(String nombre, long dni, String usuario, String pass){
        this.nombre = nombre;
        this.dni = dni;
        this.usuario = usuario;
        this.pass = pass;
        this.rol = Rol.USER;
        itemsEnviados = new ArrayList<>();
        itemsACargo = new ArrayList<>();
        avisos = new ArrayList<>();
    }

    public Miembro(String nombre, long dni, String usuario, String pass, Proyecto proyecto) {
        this.nombre = nombre;
        this.dni = dni;
        this.usuario = usuario;
        this.pass = pass;
        this.proyecto = proyecto;
        this.rol = Rol.USER;
        itemsEnviados = new ArrayList<>();
        itemsACargo = new ArrayList<>();
        avisos = new ArrayList<>();
    }

    public Miembro(String nombre, long dni, String usuario, String pass, Rol rol, Proyecto proyecto) {
        this.nombre = nombre;
        this.dni = dni;
        this.rol = rol;
        this.usuario = usuario;
        this.pass = pass;
        this.proyecto = proyecto;
        itemsEnviados = new ArrayList<>();
        itemsACargo = new ArrayList<>();
        avisos = new ArrayList<>();
    }

    public Miembro(String nombre, long dni, String usuario, String pass, Rol rol) {
        this.nombre = nombre;
        this.dni = dni;
        this.rol = rol;
        this.usuario = usuario;
        this.pass = pass;
        itemsEnviados = new ArrayList<>();
        itemsACargo = new ArrayList<>();
        avisos = new ArrayList<>();
    }
    
    public static  void addMiembro(Miembro m){
        miembros.add(m);
    }
    
    public static ArrayList<Miembro> getMiembros(){
        return miembros;
    }
    
    public static Miembro buscarMiembro(String usuario){
        Iterator<Miembro> iterator = miembros.iterator();
        while (iterator.hasNext()){
            Miembro miembro = iterator.next();
            if (miembro.getUsuario().equals(usuario)){
                return miembro;
            }
        }
        return null;
    }

    public static Miembro buscarMiembroPorNombre(String nombre){
        Iterator<Miembro> iterator = miembros.iterator();
        while (iterator.hasNext()){
            Miembro miembro = iterator.next();
            if (miembro.getNombre().equals(nombre)){
                return miembro;
            }
        }
        return null;
    }
    
    public static ArrayList<Miembro> buscarPorProyecto(Proyecto p){
        ArrayList<Miembro> list = new ArrayList<>();
        
        Iterator<Miembro> iterator = miembros.iterator();
        while (iterator.hasNext()){
            Miembro miembro = iterator.next();
            if (miembro.getProyecto() != null){
                if (miembro.getProyecto().equals(p)){
                    list.add(miembro);
                }                
            }
        }
        return list;
    }
    
    public static void eliminarMiembro(String nombre){
        Miembro m = buscarMiembroPorNombre(nombre);
        m.equipo.getIntegrantes().remove(m);
        if(m.equipo.esLider(m)){
            if(m.equipo.getIntegrantes() != null){
                m.equipo.setLider(m.equipo.getIntegrantes().get(0));
            }
            else{
                m.equipo.setLider(null);
            }
        }
        miembros.remove(m);
    }
    
    public static void eliminarMiembro(Miembro m){
        m.equipo.getIntegrantes().remove(m);
        if(m.equipo.esLider(m)){
            if(m.equipo.getIntegrantes() != null){
                m.equipo.setLider(m.equipo.getIntegrantes().get(0));
            }
            else{
                m.equipo.setLider(null);
            }
        }
        miembros.remove(m);
    }
    
    public void addAviso(String nombre){
        avisos.add(nombre);
    }
    
    public void eliminarItemEnviado(Item item){
        itemsEnviados.remove(item);
    }
    
    public void eliminarResponsabilidadItem(Item item){
        itemsACargo.remove(item);
    }

    public void addItemEnviado(Item item){
        this.itemsEnviados.add(item);
    }
    
    public void addResponsabilidadItem(Item item){
        this.itemsACargo.add(item);
    }
    
    public ArrayList<Item> getItemsEnviados(){
        return itemsEnviados;
    }
    
    public ArrayList<Item> getResponsabilidadItems(){
        return itemsACargo;
    }
    
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
    public void setAdmin(){
        rol = Rol.ADMIN;
    }
        
    public void setUser(){
        rol = Rol.USER;
    }
    
    public void setLeader(){
        rol = Rol.LEADER;
    }
    
    public Rol getRol(){
        return rol;
    }
    
    public void setRol(Rol rol){
        this.rol = rol;
    }
    
    public boolean hayAviso() {
        return !avisos.isEmpty();
    }
        
    public void setAviso(String aviso){
        this.avisos.add(aviso);
    }
    
    public ArrayList<String> getAvisos(){
        return avisos;
    }
    
    public void borrarAvisos(){
        avisos.clear();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    public static void cargarMiembrosEjemplo(){
        Proyecto pAbby = Proyecto.buscarProyecto("Abby");
        Proyecto pBongo = Proyecto.buscarProyecto("Bongo");
        
        Miembro admin1 = new Miembro("Alejandro Alvarez", 40828836, "aalvarez", "1234");
        admin1.setAdmin();
        Miembro.addMiembro(admin1);
        Miembro leader1 = new Miembro("Eugenia Luque", 39659451, "eluque", "1234", pAbby);
        leader1.setLeader();
        pAbby.setLider(leader1);
//        pAbby.addMiembro(leader1);
        Miembro.addMiembro(leader1);
        Miembro leader2 = new Miembro("Brian Rigoni", 40939947, "brigoni", "1234", pBongo);
        leader2.setLeader();        
        pBongo.setLider(leader2);
//        pBongo.addMiembro(leader2);
        Miembro.addMiembro(leader2);
        Miembro user1 = new Miembro("Federico Palacio", 40535784, "fpalacio", "1234", pAbby);
        Miembro.addMiembro(user1);
//        pAbby.addMiembro(user1);
        Miembro user2 = new Miembro("Federico Liborio", 39456154, "fliborio", "1234", pAbby);
        Miembro.addMiembro(user2);
//        pAbby.addMiembro(user2);
        Miembro user3 = new Miembro("Leonel Antonaz", 39478123, "lantonaz", "1234", pAbby);
        Miembro.addMiembro(user3);
//        pAbby.addMiembro(user3);
        Miembro user4 = new Miembro("Cristian Garcia", 39789423, "cgarcia", "1234", pAbby);
        Miembro.addMiembro(user4);
//        pAbby.addMiembro(user4);
        Miembro user5 = new Miembro("Facundo Tenuta", 39489345, "ftenuta", "1234", pAbby);
        Miembro.addMiembro(user5);
//        pAbby.addMiembro(user5);
        Miembro user6 = new Miembro("Ezequiel Obreque", 39865149, "eobreque", "1234", pAbby);
        Miembro.addMiembro(user6);
//        pAbby.addMiembro(user6);
        Miembro user7 = new Miembro("Franco Piloni", 41878457, "fpiloni", "1234");        
        Miembro.addMiembro(user7);
        Miembro user8 = new Miembro("Sergio Miron", 41128457, "smiron", "1234");        
        Miembro.addMiembro(user8);
        Miembro user9 = new Miembro("Ariel Villarreal", 40564127, "avillarreal", "1234");        
        Miembro.addMiembro(user9);
        Miembro user10 = new Miembro("Guillermo Ackerman", 40223124, "gackerman", "1234");
        Miembro.addMiembro(user10);
    }
    
    public static void asignarLideresEjemplo(){
        Proyecto pAbby = Proyecto.buscarProyecto("Abby");
        pAbby.setLider(Miembro.buscarMiembro("eluque"));
        Proyecto pBongo = Proyecto.buscarProyecto("Bongo");
        pBongo.setLider(Miembro.buscarMiembro("brigoni"));
    }

    
}
