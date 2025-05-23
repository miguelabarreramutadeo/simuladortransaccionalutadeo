package com.utadeo.miguelabarreram.simuladortransaccional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoMsgTplController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;

@RestController
public class IsoMsgTplServiceController {

	@Autowired
	IsoMsgTplController isoMsgTplController;
	
	@GetMapping("/isomsgtpls/listMsgTpls")
	public GenericJson listMsgTpls() {
		return new GenericJson(isoMsgTplController.isoMsgTpls());
	}
	
	@PostMapping("/isomsgtpls/saveMsgTpl")
	public IsoMsgTpl saveMsgTpl(@RequestBody IsoMsgTpl isoMsgTpl) {
		return isoMsgTplController.saveIsoMsgTpl(isoMsgTpl);
	}
	
	@DeleteMapping("/isomsgtpls/removeMsgTpl")
	public void deleteMsgTpl(@RequestBody IsoMsgTpl isoMsgTpl) {
		isoMsgTplController.removeIsoMsgTpl(isoMsgTpl);
	}
	
	@GetMapping("/isomsgtpls/listMsgTplsByTpl")
	public GenericJson listMsgTplsByTpl(@RequestParam Integer isoTemplateId) {
		return new GenericJson(isoMsgTplController.listMsgTplsByTpl(isoTemplateId));
	}
	
	@GetMapping("/isomsgtpls/getMsgTplFldsToSendTx")
	public GenericJson getMsgTplFldsToSendTx(@RequestParam Integer isoTemplateId) {
		return new GenericJson(isoMsgTplController.listMsgTplsByTpl(isoTemplateId));
	}
}
