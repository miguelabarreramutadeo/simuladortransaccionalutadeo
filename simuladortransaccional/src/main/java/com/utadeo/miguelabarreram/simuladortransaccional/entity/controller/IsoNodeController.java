package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.jpos.q2.QBean;
import org.jpos.q2.iso.QMUX;
import org.jpos.q2.iso.QServer;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IIsoNodeRepository;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IUserRepository;

@Controller
public class IsoNodeController {

	@Autowired
	IIsoNodeRepository iIsoNodeRepository;
	@Autowired
	IUserRepository iUserRepository;
	
	Logger log = LoggerFactory.getLogger(IsoNodeController.class);
	
	public IsoNodeController() {}
	
	public Iterable<IsoNode> isoNodes() {
		return iIsoNodeRepository.findAll(); 	
	}
	
	public IsoNode saveIsoNode(IsoNode isoNode) {
		User user = iUserRepository.findById(1).get();
		isoNode.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		isoNode.setUpdatedBy(user);
		if (null == isoNode.getIsoNodeOwner())
			isoNode.setIsoNodeOwner(user);
		return iIsoNodeRepository.save(isoNode);
	}
	
	public Optional<IsoNode> getIsoNode(IsoNode isoNode) {
		return iIsoNodeRepository.findById(isoNode.getIsoNodeId());
	}
	
	public void removeIsoNode(IsoNode isoNode) {
		iIsoNodeRepository.delete(isoNode);
	}
	
	public void startStopServices() {
		isoNodes().forEach(isoNode -> {
			startStopService(isoNode, isoNode.isIsoNodeIsStarted(), false);
		});
	}
	
	public void startStopService(IsoNode isoNode, boolean toOpen) {
		startStopService(isoNode, toOpen, true);
	}
	
	private void startStopService(IsoNode isoNode, boolean toOpen, boolean updatedDb) {
		try {
			log.info(isoNode.getNameToFiles() + " changing status to: " + (toOpen ? "Start" : "Stop"));
			if ("S".equalsIgnoreCase(isoNode.getIsoNodeType()))
				startStopServer(isoNode, toOpen);
			else 
				startStopClient(isoNode, toOpen);
		} catch (NotFoundException e) {
			log.error(e.getLocalizedMessage());
		}
		if (updatedDb) {
			isoNode.setIsoNodeIsStarted(toOpen);
			saveIsoNode(isoNode);
		}
	}
	
	private void startStopServer(IsoNode isoNode, boolean toOpen) throws NotFoundException {
		QServer qS = (QServer) NameRegistrar.get(isoNode.getNameToFiles());
		if (!checkIfStartedStopedService(qS, null, toOpen)) {
			if (toOpen) 
				qS.startService();
			else
				qS.stopService();
		}
		log.info(isoNode.getNameToFiles() + " - Status: " + qS.getStateAsString());
	}
	
	private void startStopClient(IsoNode isoNode, boolean toOpen) throws NotFoundException {
		QMUX qM = (QMUX) NameRegistrar.get(isoNode.getNameToFiles() + "_mux");
		if (!checkIfStartedStopedService(null, qM, toOpen)) {
			if (toOpen) 
				qM.startService();
			else
				qM.stopService();
		}
		log.info(isoNode.getNameToFiles() + " - Status: " + qM.getStateAsString());
	}
	
	private boolean checkIfStartedStopedService(QServer qS, QMUX qM, boolean toOpen) {
		boolean isStartedStoped = false;
		
		int currentState = -1;
		if (null != qS) {
			currentState = qS.getState();
		} else if (null != qM) {
			currentState = qM.getState();
		}
		log.info("currentState: " + currentState);
		if(toOpen) {
			isStartedStoped = (QBean.STARTED == currentState || QBean.STARTING == currentState);
		} else {
			isStartedStoped = (QBean.STOPPED == currentState || QBean.STOPPING == currentState);
		}
		return isStartedStoped;
	}
}
