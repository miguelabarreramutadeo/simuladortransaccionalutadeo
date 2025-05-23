package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoField;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoFieldRepository;

@Controller
public class IsoFieldController {

	@Autowired
	IIsoFieldRepository iIsoFieldRepository;
	@Autowired
	IsoTemplateController isoTemplateController;
	
	public Iterable<IsoField> getTemplateFields(int isoTemplateId) {
		IsoTemplate isoTemplate = new IsoTemplate();
		isoTemplate.setIsoTemplateId(isoTemplateId);
		return iIsoFieldRepository.findByIsoTemplateIdOrderByIsoParentFieldAscIsoFieldNrAsc(isoTemplate);
	}
	
	public IsoField saveIsoField(IsoField isoField) {
		IsoField result = iIsoFieldRepository.save(isoField);
		if (null != result) {
			Optional<IsoTemplate> isoTemplate = isoTemplateController.getIsoTemplate(result.getIsoTemplateId());
			IsoTemplate template = isoTemplate.isPresent() ? isoTemplate.get() : null;
			if (null != template)
				isoTemplateController.saveIsoTemplate(template, "update");
		}
		return result;
	}
	
	public void deleteIsoField(IsoField isoField) {
		isoTemplateController.saveIsoTemplate(isoField.getIsoTemplateId(), "update");
		iIsoFieldRepository.delete(isoField);
	}
}
