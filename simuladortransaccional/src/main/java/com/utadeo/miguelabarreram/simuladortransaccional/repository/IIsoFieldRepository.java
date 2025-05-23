package com.utadeo.miguelabarreram.simuladortransaccional.repository;

import org.springframework.data.repository.CrudRepository;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoField;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoTemplate;

public interface IIsoFieldRepository extends CrudRepository<IsoField, Integer>{

	public Iterable<IsoField> findByIsoTemplateIdOrderByIsoParentFieldAscIsoFieldNrAsc(IsoTemplate isoTemplateId);
	public void deleteByIsoTemplateId(IsoTemplate isoTemplateId);
	public void deleteAllByIsoTemplateId(IsoTemplate isoTemplateId);
}
