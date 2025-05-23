package com.utadeo.miguelabarreram.simuladortransaccional.xml.template;

import java.util.Collection;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoField;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoFieldController;

@Component
public class PackagerTemplate extends Template {
	
	final String FILLZEROS = "%05d";
	TreeMap<String, IsoField> sortedFields;
	TreeMap<String, Integer> maxValidFields;
	
	@Autowired
	IsoFieldController isoFieldController;
	
	@Override
	protected void generateStructureXML(Object entity) throws ParserConfigurationException {
		super.generateStructureXML(entity);
		
		IsoTemplate isoTemplate = (IsoTemplate) entity;
		Collection<IsoField> isoTemplateFields = (Collection<IsoField>) isoFieldController.getTemplateFields(isoTemplate.getIsoTemplateId());
		
		DOMImplementation dI = document.getImplementation();
		docType = dI.createDocumentType("isopackager", "-//jPOS/jPOS Generic Packager DTD 1.0//EN", "http://jpos.org/dtd/generic-packager-1.0.dtd");
		
		Element packager = document.createElement("isopackager");
		
		if (-1 != isoTemplate.getBitmapField()) {
			packager.setAttribute("bitmapField", isoTemplate.getBitmapField() + "");
			packager.setAttribute("emitBitmap", isoTemplate.isEmitBitmap() + "");
		}
		
		if (-1 != isoTemplate.getFirstField())
			packager.setAttribute("firstField", isoTemplate.getFirstField() + "");
		
		if (-1 != isoTemplate.getHeaderLength())
			packager.setAttribute("headerLength", isoTemplate.getHeaderLength() + "");
		
		if (-1 != isoTemplate.getMaxValidField())
			packager.setAttribute("maxValidField", isoTemplate.getMaxValidField() + "");
		
		{
			sortFields(isoTemplateFields);
			int maxValidField = getMaxValidField("", isoTemplate.getMaxValidField());
			addIsoFields(packager, maxValidField, "");
		}
		document.appendChild(packager);

		fileName = "cfg/packager/" + isoTemplate.getNameToFiles() + ".xml";
	}
	
	private void addIsoFields(Element packager, int maxField, String prefix) {
		for (int i = 0; i <= maxField; i++) {
			if (sortedFields.containsKey(prefix + String.format(FILLZEROS, i))) 
				createElement(packager, i, sortedFields.get(prefix + String.format(FILLZEROS, i)));
			else
				createElement(packager, i, null);
		}
	}
	
	private void createElement(Element parent, int isoFieldNr, IsoField isoField) {
		Element field = null;
		if (null == isoField) {
			field = document.createElement("isofield");
			field.setAttribute("id", isoFieldNr + "");
			field.setAttribute("length", "999");
			field.setAttribute("name", "Reserved Field");
			field.setAttribute("class", "org.jpos.iso.IFA_LLLCHAR");
		} else {
			if(!isoField.isIsoHasSubFields())
				field = document.createElement("isofield");
			else {
				field = document.createElement("isofieldpackager");
				field.setAttribute("packager", "org.jpos.iso.packager.GenericSubFieldPackager");
				
				if (isoField.isIsoEmitBitmap()) {
					field.setAttribute("emitBitmap", isoField.isIsoEmitBitmap() + "");
					if (-1 != isoField.getIsoBitmapField())
						field.setAttribute("bitmapField", isoField.getIsoBitmapField() + "");
				}
				
				if (-1 != isoField.getIsoMaxValidField())
					field.setAttribute("maxValidField", isoField.getIsoMaxValidField() + "");
				
				if (-1 != isoField.getIsoFirstField())
					field.setAttribute("firstField", isoField.getIsoFirstField() + "");
				
				if (-1 != isoField.getIsoHeaderLength())
					field.setAttribute("headerLength", isoField.getIsoHeaderLength() + "");
				
				String parentNr = getParentNr(isoField);
				int maxValidField = getMaxValidField(parentNr, isoField.getIsoMaxValidField());
				addIsoFields(field, maxValidField, parentNr + ".");
			}
			
			field.setAttribute("id", isoField.getIsoFieldNr() + "");
			field.setAttribute("name", isoField.getIsoFieldName());
			field.setAttribute("length", isoField.getIsoFieldLength() + "");
			field.setAttribute("class", isoField.getIsoFieldClass());
			
			if (isoField.isIsoFieldPad())
				field.setAttribute("pad", isoField.isIsoFieldPad() + "");
		}
		parent.appendChild(field);
	}
	
	private int getMaxValidField(String parentNr, int defaultMaxValid) {
		int tMaxValidField = -1;
		if (maxValidFields.containsKey(parentNr))
			tMaxValidField = maxValidFields.get(parentNr);
		
		if (defaultMaxValid > tMaxValidField) 
			return defaultMaxValid;
		else 
			return tMaxValidField;
	}
	
	private void sortFields(Iterable<IsoField> isoFields) {
		sortedFields = new TreeMap<String, IsoField>();
		for (IsoField isoField : isoFields)
			sortedFields.put(getParentNr(isoField), isoField);
	}
	
	private String getParentNr(IsoField isoField) {
		String parentNr = "";
		if (null != isoField.getIsoParentField())
			parentNr = getParentNr(isoField.getIsoParentField());

		putMaxValidField(parentNr, isoField.getIsoFieldNr());
		parentNr = (!parentNr.isBlank() ? parentNr + "." : "") + String.format(FILLZEROS, isoField.getIsoFieldNr());
		return parentNr;
	}
	
	private void putMaxValidField(String parentNr, Integer isoFieldNr) {
		if (null == maxValidFields)
			maxValidFields = new TreeMap<String, Integer>();
		
		if (maxValidFields.containsKey(parentNr)) {
			Integer currentMaxValidField = maxValidFields.get(parentNr);
			if (currentMaxValidField > isoFieldNr)
				isoFieldNr = currentMaxValidField;
		}
		
		maxValidFields.put(parentNr, isoFieldNr);
	}
}
