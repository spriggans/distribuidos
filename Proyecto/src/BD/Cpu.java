/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
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
@Table(name = "cpu")
public class Cpu implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_nodo")
    private Nodo nodo;
    private float cpu;
    
    public Cpu() {
    }

    public Cpu(Nodo nodo, float cpu) {
       this.nodo = nodo;
       this.cpu = cpu;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Nodo getNodo() {
        return this.nodo;
    }
    
    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    public Float getCpu() {
        return this.cpu;
    }
    
    public void setCpu(float cpu) {
        this.cpu = cpu;
    }

    
}
