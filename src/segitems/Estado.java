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
public class Estado {
    private static ArrayList<Estado> estados = new ArrayList<>();
    private String nombre;
    private Miembro responsable;
    private ArrayList<Estado> estadosSiguientes;
    
    public Estado(String nombre){
        this.nombre = nombre;
        estadosSiguientes = new ArrayList<>();
    }
    
    public static Estado buscarEstado(String nombre){
        Iterator<Estado> iterator = estados.iterator();
        while (iterator.hasNext()){
            Estado estado = iterator.next();
            if (estado.getNombre().equals(nombre)){
                return estado;
            }
        }
        return null;
    }
    
    public static void addEstado(Estado e){
        estados.add(e);
    }
    
    public static ArrayList<Estado> getEstados(){
        return estados;
    }
    
    public void addEstadoSiguiente(Estado e){
        estadosSiguientes.add(e);
    }
    
    public ArrayList<Estado> getEstadosSiguientes(){
        return estadosSiguientes;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setResponsable(Miembro m){
        responsable = m;
    }
    
    public Miembro getResponsable(){
        return responsable;
    }
    
    public static ArrayList<Estado> cargarEstadosEjemplo(String tipoItem){
        
        ArrayList<Estado> estadosCarga = new ArrayList<>();
        
        Estado inicial = new Estado("Inicial"); 
        Estado.addEstado(inicial);
        Estado analisis = new Estado("Analisis");
        Estado.addEstado(analisis);
        Estado desarrollo = new Estado("Desarrollo");
        Estado.addEstado(desarrollo);
        Estado validacion = new Estado("Validacion");
        Estado.addEstado(validacion);
        Estado finalizado = new Estado("Finalizado");
        Estado.addEstado(finalizado);
        Estado diseno = new Estado("Diseño");
        Estado.addEstado(diseno);
        Estado testing = new Estado("Testing");
        Estado.addEstado(testing);
        Estado comprobacion = new Estado("Comprobacion");
        Estado.addEstado(comprobacion);
        
        //CARGA ESTADOS SIGUIENTES DE EJEMPLOS
        inicial.addEstadoSiguiente(analisis);
        analisis.addEstadoSiguiente(diseno);
        analisis.addEstadoSiguiente(desarrollo);
        desarrollo.addEstadoSiguiente(testing);
        desarrollo.addEstadoSiguiente(comprobacion);
        desarrollo.addEstadoSiguiente(validacion);
        validacion.addEstadoSiguiente(finalizado);
        diseno.addEstadoSiguiente(desarrollo);
        comprobacion.addEstadoSiguiente(finalizado);
        testing.addEstadoSiguiente(finalizado);
        
        if (tipoItem=="Reporte de Bug"){
            estadosCarga.add(inicial);
            estadosCarga.add(analisis);
            estadosCarga.add(desarrollo);
            estadosCarga.add(validacion);
            estadosCarga.add(finalizado);    
        }
        
        else if (tipoItem=="Mejora"){
            estadosCarga.add(inicial);
            estadosCarga.add(analisis);
            estadosCarga.add(diseno);
            estadosCarga.add(desarrollo);
            estadosCarga.add(testing);
            estadosCarga.add(finalizado);    
        }
        
        else{
            estadosCarga.add(inicial);
            estadosCarga.add(diseno);
            estadosCarga.add(desarrollo);
            estadosCarga.add(comprobacion);
            estadosCarga.add(finalizado);    
        } 
        
        return estadosCarga;
    }
    
}
