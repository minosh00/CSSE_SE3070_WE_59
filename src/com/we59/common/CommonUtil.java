package com.we59.common;


//import javax.xml.transform.TransformerFactoryConfigurationError;
//import javax.xml.transform.TransformerException;
import java.io.File;
//import org.xml.sax.SAXException;
//import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
//import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
//import javax.xml.transform.TransformerConfigurationException;


/**
 * The class Common until extends common properties
 */

public class CommonUtil extends CommonProperties {
	
	/**
	 *
	 * Gets the employee queries
	 *
	 * @param id
	 *            the id
	 * @return the employee queries
	 * @throws Exception
	 */
	public static String getEmployeeQueries(String id) throws Exception {
		NodeList nodelist; 
		Element element = null;
		
		nodelist = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(properties.getProperty("EmployeeQuery_path")).getElementsByTagName("query");
		for (int x = 0; x < nodelist.getLength(); x++) {
			element = (Element) nodelist.item(x);
			if (element.getAttribute("id").equals(id))
				break;
		}
		return element.getTextContent().trim();
	}
}
