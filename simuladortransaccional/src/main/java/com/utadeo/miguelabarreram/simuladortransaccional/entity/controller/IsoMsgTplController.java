package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgFldsTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoMsgTplRepository;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IUserRepository;

@Controller
public class IsoMsgTplController {

	@Autowired
	IIsoMsgTplRepository iIsoMsgTplRepository;
	@Autowired
	IsoMsgFldsTplController iIsoMsgFldsTplController;
	@Autowired
	IUserRepository iUserRepository;
	
	public IsoMsgTplController() {
	}
	
	public Iterable<IsoMsgTpl> isoMsgTpls() {
		return iIsoMsgTplRepository.findAll();
	}
	
	public IsoMsgTpl saveIsoMsgTpl(IsoMsgTpl isoMsgTpl) {
		Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());
		isoMsgTpl.setLastUpdated(lastUpdated);
		User user = iUserRepository.findById(1).get();
		if (null == isoMsgTpl.getIsoMsgTplOwner())
			isoMsgTpl.setIsoMsgTplOwner(user);
		isoMsgTpl.setUpdatedBy(user);
		boolean isACopy = (0 <= isoMsgTpl.getIsoMsgTplId());
		isoMsgTpl = iIsoMsgTplRepository.save(isoMsgTpl);
		if (isACopy && null != isoMsgTpl.getIsoMsgTplBsOn())
			copyIsoMsgFldsTpl(isoMsgTpl.getIsoMsgTplBsOn(), isoMsgTpl);
		return isoMsgTpl;
	}
	
	public Optional<IsoMsgTpl> getIsoMsgTpl(IsoMsgTpl isoMsgTpl) {
		return iIsoMsgTplRepository.findById(isoMsgTpl.getIsoMsgTplId());
	}
	
	public void removeIsoMsgTpl(IsoMsgTpl isoMsgTpl) {
		iIsoMsgTplRepository.delete(isoMsgTpl);
	}
	
	public void copyIsoMsgFldsTpl(IsoMsgTpl isoMsgTplFrom, IsoMsgTpl isoMsgTplTo) {
		iIsoMsgFldsTplController.deleteByIsoMsgTplId(isoMsgTplTo);
		iIsoMsgFldsTplController.getTplFields(isoMsgTplFrom.getIsoMsgTplId()).forEach(isoMsgFldsTpl -> {
			IsoMsgFldsTpl isoMsgFldTpl = new IsoMsgFldsTpl(isoMsgTplTo, isoMsgFldsTpl.getIsoFieldId(), isoMsgFldsTpl.isRequired(), isoMsgFldsTpl.isValAuto(), isoMsgFldsTpl.isValFixed(), isoMsgFldsTpl.getIsoMsgFldsTplVal());
			iIsoMsgFldsTplController.saveIsoMsgFldsTpl(isoMsgFldTpl);
		});
	}
	
	public Iterable<IsoMsgTpl> listMsgTplsByTpl(int isoTemplateId){
		IsoTemplate isoTemplate= new IsoTemplate();
		isoTemplate.setIsoTemplateId(isoTemplateId);
		return iIsoMsgTplRepository.findByIsoTemplateId(isoTemplate);
	}
}
