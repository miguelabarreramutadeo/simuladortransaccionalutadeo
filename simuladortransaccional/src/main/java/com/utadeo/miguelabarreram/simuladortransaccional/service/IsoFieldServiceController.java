package com.utadeo.miguelabarreram.simuladortransaccional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoField;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoFieldController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;

@RestController
public class IsoFieldServiceController {

	@Autowired
	IsoFieldController isoFieldController;

	@GetMapping("/isofields/getTemplateFields")
	public GenericJson getTemplateFields(@RequestParam Integer isoTemplateId) {
		return new GenericJson(isoFieldController.getTemplateFields(isoTemplateId));
	}
	
	@PostMapping("/isofields/saveField")
	public IsoField saveField(@RequestBody IsoField isoField) {
		return isoFieldController.saveIsoField(isoField);
	}
	
	@DeleteMapping("/isofields/removeField")
	public void deleteField(@RequestBody IsoField isoField) {
		isoFieldController.deleteIsoField(isoField);
	}
}
