package controllers;

import models.Intervenant;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Intervenants extends Controller {

	final static Form<Intervenant> blankForm = form(Intervenant.class);

	public static Result liste() {
		return TODO;
	}
	
	public static Result page(Integer page){
		return TODO;
	}

	public static Result edit(Long id) {
		return TODO;
	}

	public static Result create() {
		return TODO;
	}

	public static Result submit() {
		return TODO;
	}

	public static Result delete(Long id) {
		return TODO;
	}
}
