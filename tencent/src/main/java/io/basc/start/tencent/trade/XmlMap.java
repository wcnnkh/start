package io.basc.start.tencent.trade;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.basc.framework.dom.DomUtils;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.xml.XmlUtils;

public final class XmlMap extends LinkedHashMap<String, String> {
	private static final long serialVersionUID = 1L;

	public XmlMap(ServerHttpRequest request) throws IOException {
		Document document = XmlUtils.getTemplate().getParser().parse(request.getReader());
		Element element = document.getDocumentElement();
		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (DomUtils.ignoreNode(n)) {
				continue;
			}

			put(n.getNodeName(), n.getTextContent());
		}
	}
}
