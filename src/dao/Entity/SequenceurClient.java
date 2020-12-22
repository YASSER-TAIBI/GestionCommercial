package dao.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="SequenceurClient.findAll", query="SELECT u FROM SequenceurClient u")
public class SequenceurClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
        
        @Column(name="CODE")
	private String code;
        
	@ManyToOne
        @JoinColumn(name="ID_DEPOT")
	private Depot depot;

        @Column(name="SEQ")
	private int seq;

        
        
        
	public SequenceurClient() {
	}

    public int getId() {
		return this.id;
    }

    public void setId(int id) {
		this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

}