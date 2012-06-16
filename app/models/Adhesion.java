package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats.DateTime;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Adhesion extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@ManyToOne
	public Intervenant intervenant;

	@Required
	public Integer mandat;

	@DateTime(pattern = "dd/MM/yyyy")
	public Date dateEdition;

	public static Finder<Long, Adhesion> find = new Finder<Long, Adhesion>(Long.class, Adhesion.class);

	public static List<Adhesion> findByIntervenant(Intervenant intervenant) {
		return find.where().eq("intervenant", intervenant).orderBy("id desc").findList();
	}
}
