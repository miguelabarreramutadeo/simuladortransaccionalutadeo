package com.utadeo.miguelabarreram.simuladortransaccional.xml.template;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.IsoNode;

@Component
public class MuxTemplate extends Template {

	@Override
	protected void generateStructureXML(Object entity) throws ParserConfigurationException {
		super.generateStructureXML(entity);
		IsoNode node = (IsoNode) entity;

		Element mux = document.createElement("mux");
		mux.setAttribute("class", "org.jpos.q2.iso.QMUX");
		mux.setAttribute("logger", node.getNameToFiles() + "_log");
		mux.setAttribute("name", node.getNameToFiles() + "_mux");
		{
			Element in = document.createElement("in");
			in.setTextContent(node.getNameToFiles() + "_receive");
			mux.appendChild(in);

			Element out = document.createElement("out");
			out.setTextContent(node.getNameToFiles() + "_send");
			mux.appendChild(out);

			Element ready = document.createElement("ready");
			ready.setTextContent(node.getNameToFiles() + ".ready");
			mux.appendChild(ready);

			Element unhandled = document.createElement("unhandled");
			unhandled.setTextContent(node.getNameToFiles() + "unhandledqueue");
			mux.appendChild(unhandled);

			Element requestListener = document.createElement("request-listener");
			requestListener.setAttribute("class", "org.jpos.bsh.BSHRequestListener");
			requestListener.setAttribute("logger", node.getNameToFiles() + "_log");
			mux.appendChild(requestListener);
		}
		document.adoptNode(mux);

		fileName = "deploy/20_" + node.getNameToFiles() + "_mux.xml";
	}
}
