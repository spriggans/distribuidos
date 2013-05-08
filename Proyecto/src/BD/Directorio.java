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
@Table(name = "directorio")
public class Directorio {
    
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_nodo")
    private Nodo nodo;
     
    private String nombre;
    
     private String valor;
  

    public Directorio() {
    }

	
    public Directorio(String nombre) {
        this.nombre = nombre;
    }
    public Directorio(String nombre, Nodo nodo, String valor) {
       this.nombre = nombre;
       this.nodo = nodo;
       this.valor = valor;
 
    }
   
        public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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


}
