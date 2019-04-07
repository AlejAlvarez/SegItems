/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

/**
 *
 * @author Alejandro
 */
public class Contexto {
    
    public static void cargarDatos(){
        Proyecto.cargarProyectosEjemplo();
        Miembro.cargarMiembrosEjemplo();
        Miembro.asignarLideresEjemplo();
        TipoItem.cargarTiposItemEjemplo();
        Item.cargarItemsEjemplo();
    }
    
}
