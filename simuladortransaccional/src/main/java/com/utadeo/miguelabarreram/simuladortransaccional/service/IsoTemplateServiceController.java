package com.utadeo.miguelabarreram.simuladortransaccional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoTemplateController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;
import com.utadeo.miguelabarreram.simuladortransaccional.xml.template.PackagerTemplate;

@RestController
public class IsoTemplateServiceController {

	@Autowired
	IsoTemplateController isoTemplateController;
	@Autowired
	PackagerTemplate packagerTemplate;
	
	@GetMapping("/isotemplates/listTemplates")
	public GenericJson isoTemplates() {
		return new GenericJson(isoTemplateController.isoTemplates());
	}
	
	@PostMapping("/isotemplates/saveTemplate")
	public IsoTemplate saveTemplate(@RequestBody IsoTemplate isoTemplate) {
		return isoTemplateController.saveIsoTemplate(isoTemplate, (0 > isoTemplate.getIsoTemplateId() ? "Add" : "Updated"));
	}
	
	@PostMapping(value = "/isotemplates/resyncTemplate")
	public void resyncTemplate(@RequestBody IsoTemplate isoTemplate) {
		packagerTemplate.createXML(isoTemplate);
	}
}
