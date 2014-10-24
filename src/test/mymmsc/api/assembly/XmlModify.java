/**
 * @(#)XmlModify.java	6.3.9 09/10/02
 *
 * Copyright 2000-2010 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MyMMSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package test.mymmsc.api.assembly;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.mymmsc.api.assembly.XmlParser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author WangFeng(wangfeng@yeah.net)
 * @version 6.3.9 09/10/02
 * @since mymmsc-test 6.3.9
 */
public class XmlModify {

	/**
	 * ModitifyXml
	 */
	public XmlModify() {
	}

	public static void main(String[] args) {
		String xmlFilename = "d:/yanyan/conf/test.xml";
		String xmlOut = "d:/yanyan/conf/test-out.xml";
		XmlParser xp = new XmlParser(xmlFilename);
		try {
			NodeList nl = xp.query("//SYSTEM/database[@vnet='MySQL']");
			if (nl != null && nl.getLength() > 0) {
				Node node = nl.item(0);
				NamedNodeMap atts = node.getAttributes();
				Node attnNode = atts.getNamedItem("vnet");
				attnNode.setNodeValue("oracle10g");
				node.setTextContent("abc");
			}
			xp.output(xmlOut, "utf-8");
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
