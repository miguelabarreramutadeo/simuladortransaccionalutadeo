package com.utadeo.miguelabarreram.simuladortransaccional.xml.template;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

@Component
public class Template {

	DocumentBuilderFactory dBF;
	DocumentBuilder dB;
	Document document;
	DocumentType docType;
	String fileName;

	public void createXML(Object entity) {
		try {
			generateStructureXML(entity);
	
			TransformerFactory tF = TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();
	
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			
			if(null != docType) {
				t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, docType.getPublicId());
				t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, docType.getSystemId());
			}
	
			DOMSource dS = new DOMSource(document);
			StreamResult sR = new StreamResult(getFile());
			t.transform(dS, sR);
		} catch (IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile() throws IOException {
		File file = new File(fileName);
		if(!file.exists())
			file.createNewFile();
		return file;
	}

	protected void generateStructureXML(Object entity) throws ParserConfigurationException {
		dBF = DocumentBuilderFactory.newInstance();
		dB = dBF.newDocumentBuilder();
		document = dB.newDocument();
	}
}
