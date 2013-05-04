/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Keyma
 */
@Entity
@Table(name = "proceso")
public class Proceso implements java.io.Serializable {


 @Id
    @GeneratedValue
    private Long pid;
    @ManyToOne
    @JoinColumn(name = "fk_nodo")
    private Nodo nodo;
     private String valor;
     private Float porcentaje;

    public Proceso() {
    }

    public Proceso(Nodo nodo, String valor, Float porcentaje) {
       this.nodo = nodo;
       this.valor = valor;
       this.porcentaje = porcentaje;
    }
   
    public Long getPid() {
        return this.pid;
    }
    
    public void setPid(Long pid) {
        this.pid = pid;
    }
    public Nodo getNodo() {
        return this.nodo;
    }
    
    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    public String getValor() {
        return this.valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    public Float getPorcentaje() {
        return this.porcentaje;
    }
    
    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    
}
