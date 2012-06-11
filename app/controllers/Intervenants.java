package controllers;

import models.Intervenant;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

public class Intervenants extends Controller {

	final static Form<Intervenant> blankForm = form(Intervenant.class);

	public static Result liste() {
		return TODO;
	}
	
	public static Result page(Integer page){
		return TODO;
	}

	public static Result edit(Long id) {
		Intervenant in = Intervenant.find.byId(id);
		if(in == null) {
			//Si l'étudiant n'existe pas, on affiche une erreur
			return badRequest("Erreur : Cet intervenant n'existe pas");
		}
		//On affiche un formulaire pré-rempli
		return ok(IntervenantForm.render(blankForm.fill(in),true));
	}

	public static Result create() {
		return ok(IntervenantForm.render(blankForm,false));
	}

	public static Result submit() {
		//On récupère le formulaire depuis la requête
		Form<Intervenant> filledForm = blankForm.bindFromRequest();
		
		//Si le formulaire a des erreurs, on remet le formulaire avec les erreurs.
		if (filledForm.hasErrors()) {
			return badRequest(IntervenantForm.render(filledForm,false));
		}

		Intervenant intervenant = filledForm.get();
		Long id = intervenant.id;
		//Si l'intervenant existe déjà on l'update
		if(id != null){
			intervenant.update();
		}
		else{
			intervenant.save();
		}
		//On renvoie vers la liste des intervenants
		return liste();
	}

	public static Result delete(Long id) {
		return TODO;
	}
}
