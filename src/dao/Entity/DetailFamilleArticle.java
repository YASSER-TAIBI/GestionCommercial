package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;





/**
 * The persistent class for the artiles database table.
 * 
 */

@Entity
@Table(name="Detail_Famille_Article")
@NamedQuery(name="DetailFamilleArticle.findAll", query="SELECT f FROM DetailFamilleArticle f")
public class DetailFamilleArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="CODE")
	private String code;
	
	@Column(name="LIBELLE")
	private String liblle ;
	
	
	@ManyToOne
	@JoinColumn(name="id_famille_article")
	private FamilleArticle familleArticle;

     

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

    public FamilleArticle getFamilleArticle() {
        return familleArticle;
    }

    public void setFamilleArticle(FamilleArticle familleArticle) {
        this.familleArticle = familleArticle;
    }

        
	
	

	

	
	
}