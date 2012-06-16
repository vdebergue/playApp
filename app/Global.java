import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			if (Ebean.find(Intervenant.class).findRowCount() == 0) {

				@SuppressWarnings("unchecked")
				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");

				Ebean.save(all.get("intervenants"));
				Ebean.save(all.get("adhesions"));

			}
		}

	}

}