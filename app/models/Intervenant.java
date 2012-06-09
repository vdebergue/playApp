package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.avaje.ebean.Page;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Intervenant extends Model {

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String nom;
	
	@Required
	public String prenom;
	
	@Required
	@Email
	public String email;
	
	@OneToMany(mappedBy="intervenant", cascade=CascadeType.ALL)
	public List<Adhesion> adhesions;
	
	public static Finder<Long, Intervenant> find = new Finder<Long,Intervenant>(Long.class, Intervenant.class);
	
	public static Intervenant findById(Long id){
		return find.byId(id);
	}
	
	public static List<Intervenant> findByNom(String nom){
		return find.where().eq("nom", nom).findList();
	}
	
	/**
	 * Renvoie la page n°'page' avec 'size' intervenants dedans
	 * @param size : nombre d'intervenants
	 * @param page : numéro de la page
	 * @return 
	 */
	public static Page<Intervenant> last(int size, int page) {
		return find.where().orderBy("id desc").findPagingList(size).getPage(page);
	}
}
