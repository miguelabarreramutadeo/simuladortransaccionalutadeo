package com.utadeo.miguelabarreram.simuladortransaccional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoNodeController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;
import com.utadeo.miguelabarreram.simuladortransaccional.xml.template.ChannelAdaptorTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.xml.template.LoggerTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.xml.template.MuxTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.xml.template.ServerTemplate;

@RestController
public class IsoNodeServiceController {
	
	@Autowired
	IsoNodeController isoNodeController;
	@Autowired
	ServerTemplate serverTemplate;
	@Autowired
	MuxTemplate muxTemplate;
	@Autowired
	ChannelAdaptorTemplate channelAdaptorTemplate;
	@Autowired
	LoggerTemplate loggerTemplate;
	
	@GetMapping("/isonodes/listNodes")
	public GenericJson isoNodes() {
		return new GenericJson(isoNodeController.isoNodes());
	}

	@PostMapping("/isonodes/saveNode")
	public IsoNode saveIsoNode(@RequestBody IsoNode isoNode) {
		return isoNodeController.saveIsoNode(isoNode);
	}
	
	@PostMapping("/isonode/removeNode")
	public void removeNode(@RequestBody IsoNode isoNode) {
		isoNodeController.removeIsoNode(isoNode);
	}
	
	@PostMapping("/isonodes/resyncNode")
	public void resyncNode(@RequestBody IsoNode isoNode) {
		loggerTemplate.createXML(isoNode);
		if ("S".equalsIgnoreCase(isoNode.getIsoNodeType())) 
			serverTemplate.createXML(isoNode);
		else {
			channelAdaptorTemplate.createXML(isoNode);
			muxTemplate.createXML(isoNode);
		}
	}

	@PostMapping("/isonodes/startNode")
	public void startNode(@RequestBody IsoNode isoNode) {
		isoNodeController.startStopService(isoNode, true);
	}
	//(*probar*) del startstop queda pendiente cambiar el estado en la base de datos y en la pantalla de node hacer los botones y el js (probar)
	
	//al momento de crear los xml del channel o del server se van a levantar de una vez la conexión, contemplar hacer el 
	//llamado de bajar el servicio al terminar el resync, también evaluar si en la base de datos está marcado como started o closed
	@PostMapping("/isonodes/stopNode")
	public void stopNode(@RequestBody IsoNode isoNode) {
		isoNodeController.startStopService(isoNode, false);
	}
}

