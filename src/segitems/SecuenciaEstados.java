/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segitems;

import java.util.Date;

/**
 *
 * @author Alejandro
 */
public class SecuenciaEstados {
    
    private Item item;
    private Miembro responsable;
    private Estado estado;
    private Date ultFecha;

    public SecuenciaEstados(Item item, Miembro responsable, Estado estado, Date ultFecha) {
        this.item = item;
        this.responsable = responsable;
        this.estado = estado;
        this.ultFecha = ultFecha;
    }

    public Item getItem() {
        return item;
    }

    public Miembro getResponsable() {
        return responsable;
    }

    public Estado getEstado() {
        return estado;
    }

    public Date getUltFecha() {
        return ultFecha;
    }
    
    
    
}
