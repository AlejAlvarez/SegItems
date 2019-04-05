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
public class Item {
    
    private static ArrayList<Item> items;
    private String nombre;
    private TipoItem tipoItem;
    private int prioridad;
    private Estado estado;
    private Miembro usuario;
    private SecuenciaEstados secuenciaEstados;
    private Miembro responsable;
    
    
}
