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
public class Equipo {
    private ArrayList<Miembro> integrantes;
    private Miembro lider;
    
    public Equipo(){
        integrantes = new ArrayList<>();
    }
    
    public void addIntegrante(Miembro m){
        integrantes.add(m);
    }
    
    public void eliminarIntegrante(Miembro m){
        integrantes.remove(m);
    }
    
    public void setLider(Miembro m){
        lider = m;
    }
    
    public ArrayList<Miembro> getIntegrantes(){
        return integrantes;
    }
    
    public void setIntegrantes(ArrayList<Miembro> integrantes){
        this.integrantes = integrantes;
    }
    
    public Miembro getLider(){
        return lider;
    }
    
    public boolean esLider(Miembro m){
        return m==lider;
    }
}
