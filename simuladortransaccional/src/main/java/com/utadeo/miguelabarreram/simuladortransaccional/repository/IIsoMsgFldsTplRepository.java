package com.utadeo.miguelabarreram.simuladortransaccional.repository;

import org.springframework.data.repository.CrudRepository;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgFldsTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgTpl;

public interface IIsoMsgFldsTplRepository extends CrudRepository<IsoMsgFldsTpl, Integer>{

	public Iterable<IsoMsgFldsTpl> findByIsoMsgTplId(IsoMsgTpl isoMsgTpl);
	public void deleteByIsoMsgTplId(IsoMsgTpl isoMsgTplId);
}
