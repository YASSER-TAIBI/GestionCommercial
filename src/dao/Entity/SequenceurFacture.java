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
@NamedQuery(name="SequenceurFacture.findAll", query="SELECT u FROM SequenceurFacture u")
public class SequenceurFacture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
        
        @Column(name="CODE_CLIENT")
	private String codeClient;

        @Column(name="SEQ")
	private int seq;

        
        
        
	public SequenceurFacture() {
	}

    public int getId() {
		return this.id;
    }

    public void setId(int id) {
		this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }



}