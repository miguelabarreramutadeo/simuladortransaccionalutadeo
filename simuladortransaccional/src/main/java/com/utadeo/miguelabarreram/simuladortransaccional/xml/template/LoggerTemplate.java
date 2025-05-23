package com.utadeo.miguelabarreram.simuladortransaccional.xml.template;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;

@Component
public class LoggerTemplate extends Template {
	
	Logger log = LoggerFactory.getLogger(LoggerTemplate.class);

	@Override
	protected void generateStructureXML(Object entity) throws ParserConfigurationException {
		super.generateStructureXML(entity);
		
		IsoNode node = (IsoNode) entity;
		
		Element logger = document.createElement("logger");
		logger.setAttribute("name", node.getNameToFiles() + "_log");
		logger.setAttribute("class", "org.jpos.q2.qbean.LoggerAdaptor");
		{
			Element logListSimple = document.createElement("log-listener");
			logListSimple.setAttribute("class", "org.jpos.util.SimpleLogListener");
			logger.appendChild(logListSimple);
			
			Element logListProtected = document.createElement("log-listener");
			logListProtected.setAttribute("class", "org.jpos.util.ProtectedLogListener");
			{
				Element protect = document.createElement("property");
				protect.setAttribute("name", "protect");
				protect.setAttribute("value", "2 14 35 45");
				logListProtected.appendChild(protect);
				
				Element wipe = document.createElement("property");
				wipe.setAttribute("name", "wipe");
				wipe.setAttribute("value", "52 55 120");
				logListProtected.appendChild(wipe);
			}
			logger.appendChild(logListProtected);
			
			Element logListRotate = document.createElement("log-listener");
			logListRotate.setAttribute("class", "org.jpos.util.RotateLogListener");
			{
				Element file = document.createElement("property");
				file.setAttribute("name", "file");
				file.setAttribute("value", "log/" + node.getNameToFiles() + ".log");
				logListRotate.appendChild(file);
				
				Element window = document.createElement("property");
				window.setAttribute("name", "window");
				window.setAttribute("value", "86400");
				logListProtected.appendChild(window);
				
				Element copies = document.createElement("property");
				copies.setAttribute("name", "copies");
				copies.setAttribute("value", "90");
				logListRotate.appendChild(copies);
				
				Element maxSize = document.createElement("property");
				maxSize.setAttribute("name", "maxsize");
				maxSize.setAttribute("value", "100000000");
				logListRotate.appendChild(maxSize);
			}
			logger.appendChild(logListRotate);
			
		}
		document.appendChild(logger);
		
		fileName = "deploy/00_" + node.getNameToFiles() + ".xml";
		
		try {
			File fileLog = new File("log/" + node.getNameToFiles() + ".log");
			if (!fileLog.exists())
				fileLog.createNewFile();
		} catch(IOException ex) {
			log.error(ex.getMessage());
		}
	}
}
