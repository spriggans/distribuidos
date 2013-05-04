/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Keyma
 */
@Entity
@Table(name = "nodo")
public class Nodo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "ip")
    private String ip;
    @OneToMany()
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "fk_nodo")
    private Set<Cpu> cpus;
    @OneToMany()
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "fk_nodo")
    private Set<Directorio> directorios;
    @OneToMany()
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "fk_nodo")
    private Set<Ram> rams;
    @OneToMany()
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "fk_nodo")
    private Set<Filesystem> filesystems;
    @OneToMany()
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "fk_nodo")
    private Set<Proceso> procesos;

    public Nodo() {
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public Set getRams() {
        return this.rams;
    }
    
    public void setRams(Set<Ram> rams) {
        this.rams = rams;
    }
    public Set getProcesos() {
        return this.procesos;
    }
    
    public void setProcesos(Set<Proceso> procesos) {
        this.procesos = procesos;
    }
    public Set getDirectorios() {
        return this.directorios;
    }
    
    public void setDirectorios(Set<Directorio> directorios) {
        this.directorios = directorios;
    }
    public Set getCpus() {
        return this.cpus;
    }
    
    public void setCpus(Set<Cpu> cpus) {
        this.cpus = cpus;
    }
    public Set getFilesystems() {
        return this.filesystems;
    }
    
    public void setFilesystems(Set<Filesystem> filesystems) {
        this.filesystems = filesystems;
    }

}
