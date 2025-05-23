package com.utadeo.miguelabarreram.simuladortransaccional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.IsoChannelController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;

@RestController
public class IsoChannelServiceController {
	
	@Autowired
	IsoChannelController isoChannelController;
	
	@GetMapping("/isochannels/listChannels")
	public GenericJson isoChannels() {
		return new GenericJson(isoChannelController.isoChannels());
	}

}
