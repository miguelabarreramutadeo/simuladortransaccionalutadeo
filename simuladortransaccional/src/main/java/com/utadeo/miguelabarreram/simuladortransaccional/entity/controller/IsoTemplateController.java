package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoField;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoFieldRepository;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoTemplateRepository;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IUserRepository;

@Controller
public class IsoTemplateController {

	@Autowired
	IIsoTemplateRepository iIsoTemplateRepository;
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	IIsoFieldRepository iIsoFieldRepository;
	
	public IsoTemplateController() {}
	
	public Iterable<IsoTemplate> isoTemplates() {
		return iIsoTemplateRepository.findAll();
	}
	
	public IsoTemplate saveIsoTemplate(IsoTemplate isoTemplate, String action) {
		Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());
		isoTemplate.setLastUpdated(lastUpdated);
		isoTemplate.setUserId(iUserRepository.findById(1).get());
		isoTemplate = iIsoTemplateRepository.save(isoTemplate);
		if ("Add".equals(action) && null != isoTemplate.getBaseOn())
			copyFieldsFromBaseTemplate(isoTemplate);
		return isoTemplate;
	}
	
	public Optional<IsoTemplate> getIsoTemplate(IsoTemplate isoTemplate) {
		return iIsoTemplateRepository.findById(isoTemplate.getIsoTemplateId());
	}
	
	public void copyFieldsFromBaseTemplate(IsoTemplate isoTemplate) {
		iIsoFieldRepository.deleteByIsoTemplateId(isoTemplate);
		Iterable<IsoField> isoFields = iIsoFieldRepository.findByIsoTemplateIdOrderByIsoParentFieldAscIsoFieldNrAsc(isoTemplate.getBaseOn());
		Hashtable<Integer, IsoField> isoFieldsParent = new Hashtable<>();
		for (IsoField isoField : isoFields) {
			int orgIsoFieldId = isoField.getIsoFieldId();
			IsoField isoFieldParent = null;
			
			if (null != isoField.getIsoParentField())
				isoFieldParent = isoFieldsParent.get(isoField.getIsoParentField().getIsoFieldId());
			
			IsoField newIsoField = new IsoField(isoField, isoTemplate, isoFieldParent);
			newIsoField = iIsoFieldRepository.save(newIsoField);
			if (newIsoField.isIsoHasSubFields())
				isoFieldsParent.put(orgIsoFieldId, newIsoField);
		}
	}
}
