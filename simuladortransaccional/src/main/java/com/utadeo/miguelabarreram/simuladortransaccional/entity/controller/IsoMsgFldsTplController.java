package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgFldsTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoMsgFldsTplRepository;

@Controller
public class IsoMsgFldsTplController {

	@Autowired
	private IIsoMsgFldsTplRepository iIsoMsgFldsTplRepository;
	
	public Iterable<IsoMsgFldsTpl> getTplFields(int isoMsgTplId) {
		IsoMsgTpl isoMsgTpl = new IsoMsgTpl();
		isoMsgTpl.setIsoMsgTplId(isoMsgTplId);
		return iIsoMsgFldsTplRepository.findByIsoMsgTplId(isoMsgTpl);
	}
	
	public IsoMsgFldsTpl saveIsoMsgFldsTpl(IsoMsgFldsTpl isoMsgFldsTpl) {
		return iIsoMsgFldsTplRepository.save(isoMsgFldsTpl);
	}
	
	public void deleteIsoMsgFldTpl(IsoMsgFldsTpl isoMsgFldsTpl) {
		iIsoMsgFldsTplRepository.delete(isoMsgFldsTpl);
	}
	
	public void deleteByIsoMsgTplId(IsoMsgTpl isoMsgTpl) {
		iIsoMsgFldsTplRepository.deleteByIsoMsgTplId(isoMsgTpl);
	}
}
