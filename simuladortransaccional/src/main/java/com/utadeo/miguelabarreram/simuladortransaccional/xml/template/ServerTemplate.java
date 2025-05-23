package com.utadeo.miguelabarreram.simuladortransaccional.xml.template;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;

@Component
public class ServerTemplate extends Template {

	@Override
	protected void generateStructureXML(Object entity) throws ParserConfigurationException {
		super.generateStructureXML(entity);

		IsoNode node = (IsoNode) entity;

		Element server = document.createElement("server");
		server.setAttribute("class", "org.jpos.q2.iso.QServer");
		server.setAttribute("logger", node.getNameToFiles() + "_log");
		server.setAttribute("name", node.getNameToFiles());
		{
			Element attr = document.createElement("attr");
			attr.setAttribute("name", "port");
			attr.setAttribute("type", "java.lang.Integer");
			attr.setTextContent(node.getIsoNodePort() + "");
			server.appendChild(attr);

			Element channel = document.createElement("channel");
			channel.setAttribute("name", node.getNameToFiles() + ".channel");
			channel.setAttribute("class", node.getIsoChannelId().getIsoChannelPackager());
			channel.setAttribute("packager", "org.jpos.iso.packager.GenericPackager");
			channel.setAttribute("logger", node.getNameToFiles() + "_log");
			{
				Element propPackagerConfig = document.createElement("property");
				propPackagerConfig.setAttribute("name", "packager-config");
				propPackagerConfig.setAttribute("value", "cfg/packager/" + node.getIsoTemplateId().getNameToFiles() + ".xml");
				channel.appendChild(propPackagerConfig);
				
				Element propTimeOut = document.createElement("property");
				propTimeOut.setAttribute("name", "timeout");
				propTimeOut.setAttribute("value", node.getIsoNodeTimeOut() + "");
				channel.appendChild(propTimeOut);
			}
			server.appendChild(channel);

//			Dejo este bloque en comentario porque el mux lo va a implementar
//			Element requestListener = document.createElement("request-listener");
//			requestListener.setAttribute("class", "org.jpos.bsh.BSHRequestListener");
//			requestListener.setAttribute("logger", node.getNameToFiles() + "-log");
//			server.appendChild(requestListener);

			Element in = document.createElement("in");
			in.setTextContent(node.getNameToFiles() + "_receive");
			server.appendChild(in);

			Element out = document.createElement("out");
			out.setTextContent(node.getNameToFiles() + "_send");
			server.appendChild(out);

			Element ready = document.createElement("ready");
			ready.setTextContent(node.getNameToFiles() + ".ready");
			server.appendChild(ready);
		}
		document.appendChild(server);

		fileName = "deploy/50_" + node.getNameToFiles() + ".xml";
	}

}
