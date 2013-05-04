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
@Table(name = "ram")
public class Ram implements java.io.Serializable {


    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_nodo")
    private Nodo nodo;
     private Float ram;

    public Ram() {
    }

    public Ram(Nodo nodo, Float ram) {
       this.nodo = nodo;
       this.ram = ram;
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
    public Float getRam() {
        return this.ram;
    }
    
    public void setRam(Float ram) {
        this.ram = ram;
    }

    
}
