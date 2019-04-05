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
public class Estado {
    private static ArrayList<Estado> estados;
    private String nombre;
    private Miembro responsable;
    private ArrayList<Estado> estadosSiguientes;
    
    public Estado(String nombre){
        this.nombre = nombre;
    }
    
    public static ArrayList<Estado> cargarEstadosEjemplo(){
        Estado inicial = new Estado("Inicial");
        Estado analisis = new Estado("Analisis");
        Estado desarrollo = new Estado("Desarrollo");
        Estado validacion = new Estado("Validacion");
        Estado finalizado = new Estado("Finalizado");
        
        ArrayList<Estado> estadosCarga = new ArrayList<>();
        estadosCarga.add(inicial);
        estadosCarga.add(analisis);
        estadosCarga.add(desarrollo);
        estadosCarga.add(validacion);
        estadosCarga.add(finalizado);
        
        return estadosCarga;
    }
    
    public void addEstado(Estado e){
        estados.add(e);
    }
    
    public ArrayList<Estado> getEstados(){
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
    
}
