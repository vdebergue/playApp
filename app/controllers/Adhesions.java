package controllers;

import java.util.ArrayList;
import java.util.List;

import doc.DocAdhesion;

import models.Adhesion;
import models.Intervenant;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Adhesions extends Controller {

	public static Form<Adhesion> blankForm= form(Adhesion.class); 
	
	public static Result intervenant(Long id){
		Intervenant i = Intervenant.find.byId(id);
		List<Form<Adhesion>> adhesions = new ArrayList<>();
		if(i == null){
			return badRequest();
		}
		for (Adhesion a : i.adhesions) {
			adhesions.add(form(Adhesion.class).fill(a));
		}
		return ok(views.html.AdhesionByIntervenant.render(i,adhesions));
	}
	
	public static Result submit(){
		Form<Adhesion> form= blankForm.bindFromRequest();
		if(form.hasGlobalErrors() || form.hasErrors()){
			return badRequest(views.html.AdhesionForm.render(form));
		}
		Adhesion adhesion = form.get();
		Long id = adhesion.id;
		if(id != null){
			adhesion.update();
		}
		else{
			adhesion.save();
		}
		adhesion = Adhesion.find.byId(id);
		//On renvoie vers la liste des intervenants
		return intervenant(adhesion.intervenant.id);
	}
	
	public static Result add(Long id){
		Adhesion adhesion = new Adhesion();
		adhesion.intervenant = Intervenant.findById(id);
		adhesion.mandat = 2012;
		adhesion.save();
		return intervenant(id);
	}
	
	public static Result delete(Long id){
		Long idIntervenant = Adhesion.find.byId(id).intervenant.id;
		Adhesion.find.ref(id).delete();
		return intervenant(idIntervenant);
	}
	
	public static Result genererDoc(Long id){
		try{
			DocAdhesion.generer(Adhesion.find.byId(id));
			return ok("Document bien généré");
		}catch (Exception e) {
			return badRequest(views.html.defaultpages.badRequest.render(null, "Erreur: "+ e.getMessage()));
		}
	}
	
}