package doc;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;

import models.Adhesion;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;

public class DocAdhesion {

	public static JAXBContext context = org.docx4j.jaxb.Context.jc; 
	
	public static void generer(Adhesion adhesion) throws Exception {

		String inputfilepath = System.getProperty("user.dir")+"/app/doc/adhesionTemplate.docx";

		String outputfilepath = System.getProperty("user.dir")
				+ "/test-out.docx";

		// Open a document from the file system
		// 1. Load the Package
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
				.load(new java.io.File(inputfilepath));

		// 2. Fetch the document part
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

		org.docx4j.wml.Document wmlDocumentEl = (org.docx4j.wml.Document) documentPart
				.getJaxbElement();

		// xml --> string
		String xml = XmlUtils.marshaltoString(wmlDocumentEl, true);

		//On récupère les mappings
		HashMap<String, String> mappings = getMapping(adhesion);
		
		// valorize template
		Object obj = XmlUtils.unmarshallFromTemplate(xml, mappings);

		// change JaxbElement
		documentPart.setJaxbElement((Document) obj);

		// Save it
		SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
		saver.save(outputfilepath);
		System.out.println("Saved output to:" + outputfilepath);

	}
	
	private static HashMap<String,String> getMapping(Adhesion adhesion){
		HashMap<String, String> mappings = new HashMap<String, String>();
		
		mappings.put("dateEdition", adhesion.dateEdition.toLocaleString());
		mappings.put("intervenant", adhesion.intervenant.nom +" "+ adhesion.intervenant.prenom);
		mappings.put("mandat", adhesion.mandat +"");
		
		return mappings;
	}

}
