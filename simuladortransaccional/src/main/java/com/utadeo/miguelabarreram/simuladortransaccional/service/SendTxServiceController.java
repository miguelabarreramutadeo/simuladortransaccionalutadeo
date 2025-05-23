package com.utadeo.miguelabarreram.simuladortransaccional.service;

import java.io.InputStream;
import java.util.Iterator;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.util.NameRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoField;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgFldsTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoMsgTplController;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoNodeController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;

@RestController
public class SendTxServiceController {

	@Autowired
	private IsoNodeController isoNodeController;
	@Autowired
	private IsoMsgTplController isoMsgTplController;
	
	Logger log = LoggerFactory.getLogger(SendTxServiceController.class);
	
	@PostMapping("/sendtx/sendRequest")
	public GenericJson sendRequest(@RequestBody GenericJson request) {
		IsoNode isoNode = (IsoNode) request.getObj();
//		sendTxRequest(isoNode, isoMsgTpl, isoMsgFldsTpl);
		return new GenericJson();
	}
	
	private String sendTxRequest(IsoNode isoNode, IsoMsgTpl isoMsgTpl, Iterable<IsoMsgFldsTpl> isoMsgFldsTpl) {
		String rspCode = "96 - System Mal Function (JTransactional Simulator)";
		try {
			isoNode = isoNodeController.getIsoNode(isoNode).get();
			
			isoMsgTpl = isoMsgTplController.getIsoMsgTpl(isoMsgTpl).get();
			
			IsoTemplate isoTemplate = isoMsgTpl.getIsoTemplateId();
			InputStream iS = SendTxServiceController.class.getResourceAsStream("cfg/packager/" + isoTemplate.getNameToFiles() + ".xml");
			GenericPackager packager = new GenericPackager(iS);
			ISOMsg msg = new ISOMsg(isoMsgTpl.getIsoMsgType());
			msg.setPackager(packager);
			
			isoMsgFldsTpl.forEach(isoMsgFldTpl -> {
				String fieldNr = getFieldNr(isoMsgFldTpl.getIsoFieldId());
				String value = isoMsgFldTpl.getIsoMsgFldsTplVal();
				msg.set(fieldNr, value);
			});
				
				//info que puedo sacar del objeto isoMsgFldsTpl:
				//-isoMsgFldsTpl
				//-isoField
			//recuperar primero la conexion y evaluar que este activa
			//traer y setear el packager
			//recorrer los campos 
			//mandar la tx
			//devolver la respuesta
		} catch(Exception e) {
			log.error("sendTxRequest error", e);
		}
		return rspCode;
	}
	
	private String getFieldNr(IsoField isoField) {
		String fieldNr = "";
		if (null != isoField.getIsoParentField()) {
			fieldNr = getFieldNr(isoField.getIsoParentField()) + "." + isoField.getIsoFieldNr(); 
		} else {
			fieldNr = String.valueOf(isoField.getIsoFieldNr());
		}
		return fieldNr;
	}
}
