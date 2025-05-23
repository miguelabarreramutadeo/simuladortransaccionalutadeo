package com.utadeo.miguelabarreram.simuladortransaccional.xml.template;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;

@Component
public class ChannelAdaptorTemplate extends Template {

	@Override
	protected void generateStructureXML(Object entity) throws ParserConfigurationException {
		super.generateStructureXML(entity);
		
		IsoNode node = (IsoNode) entity;
		
		Element channelAdaptor = document.createElement("channel-adaptor");
		channelAdaptor.setAttribute("name", node.getNameToFiles());
		channelAdaptor.setAttribute("logger", node.getNameToFiles() + "_log");
		{
			Element channel = document.createElement("channel");
			channel.setAttribute("class", node.getIsoChannelId().getIsoChannelPackager());
			channel.setAttribute("packager", "org.jpos.iso.packager.GenericPackager");
			{
				Element propPackagerConfig = document.createElement("property");
				propPackagerConfig.setAttribute("name", "packager-config");
				propPackagerConfig.setAttribute("value", "cfg/packager/" + node.getIsoTemplateId().getNameToFiles());
				channel.appendChild(propPackagerConfig);
				
				Element propHost = document.createElement("property");
				propHost.setAttribute("name", "host");
				propHost.setAttribute("value", node.getIsoNodeHost());
				channel.appendChild(propHost);
				
				Element propPort = document.createElement("property");
				propPort.setAttribute("name", "port");
				propPort.setAttribute("value", node.getIsoNodePort() + "");
				channel.appendChild(propPort);
				
				Element propTimeOut = document.createElement("property");
				propTimeOut.setAttribute("name", "timeout");
				propTimeOut.setAttribute("value", node.getIsoNodeTimeOut() + "");
				channel.appendChild(propTimeOut);
			}
			channelAdaptor.appendChild(channel);
			
			Element in = document.createElement("in");
			in.setTextContent(node.getNameToFiles() + "_receive");
			channelAdaptor.appendChild(in);
			
			Element out = document.createElement("out");
			out.setTextContent(node.getNameToFiles() + "_send");
			channelAdaptor.appendChild(out);

			Element ready = document.createElement("ready");
			ready.setTextContent(node.getNameToFiles() + ".ready");
			channelAdaptor.appendChild(ready);
			
			Element reconnectDelay = document.createElement("reconnect-delay");
			reconnectDelay.setTextContent(node.getIsoNodeReconnectDelay() + "");
			channelAdaptor.appendChild(reconnectDelay);
		}
		document.appendChild(channelAdaptor);
		
		fileName = "deploy/10_" + node.getNameToFiles() + ".xml";
	}
}
