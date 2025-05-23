package com.utadeo.miguelabarreram.simuladortransaccional.repository;

import org.springframework.data.repository.CrudRepository;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoMsgTpl;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;

public interface IIsoMsgTplRepository extends CrudRepository<IsoMsgTpl, Integer> {

	public Iterable<IsoMsgTpl> findByIsoTemplateId(IsoTemplate isoTemplate);
}
