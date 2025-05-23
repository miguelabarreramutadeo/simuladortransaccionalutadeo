package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoChannel;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoChannelRepository;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IUserRepository;

@Controller
public class IsoChannelController {
	
	@Autowired
	IIsoChannelRepository iIsoChannelRepository;
	@Autowired
	IUserRepository iUserRepository;
	
	public IsoChannelController() {}

	public Iterable<IsoChannel> isoChannels() {
		return iIsoChannelRepository.findAll();
	}
	
	public Optional<IsoChannel> getIsoChannel(IsoChannel isoChannel) {
		return iIsoChannelRepository.findById(isoChannel.getIsoChannelId());
	}
}
