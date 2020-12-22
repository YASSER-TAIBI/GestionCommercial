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
import javax.persistence.Table;

/**
 * The persistent class for the artiles database table.
 * 
 */

@Entity
@Table(name="Detail_Sous_Famille_Article")
@NamedQuery(name="DetailSousFamilleArticle.findAll", query="SELECT f FROM DetailSousFamilleArticle f")
public class DetailSousFamilleArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CODE")
	private String code;
	
	@Column(name="LIBELLE")
	private String liblle ;
	
	@ManyToOne
	@JoinColumn(name="id_Sous_Famile_Article")
	private SousFamilleArticle SousFamileArticle;


	public int getId() {
		return id;
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

	public String getLiblle() {
		return liblle;
	}

	public void setLiblle(String liblle) {
		this.liblle = liblle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public SousFamilleArticle getSousFamileArticle() {
        return SousFamileArticle;
    }

    public void setSousFamileArticle(SousFamilleArticle SousFamileArticle) {
        this.SousFamileArticle = SousFamileArticle;
    }

    

    

}