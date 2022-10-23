package com.we59.common;

//import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
//import java.io.IOException;
//import java.text.MessageFormat;
import java.util.ArrayList;
//import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
//import org.xml.sax.SAXException;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpression;


/**
 * The class XSL transform until extends common properties
 */

public class XSLTransformUtil extends CommonProperties {

	private static final ArrayList<Map<String, String>> l = new ArrayList<Map<String, String>>();

	private static Map<String, String> map = null;

	
	/**
	 *
	 * Request transform
	 *
	 * @param Exception
	 *            the exception
	 * @throws Exception
	 */
	
	
	public static void rEQUESTtRANSFORM() throws Exception {

		Source request = new StreamSource(new File("src/com/csse/config/EmployeeRequest.xml"));
		Source modify = new StreamSource(new File("src/com/csse/config/Employee-modified.xsl"));
		Result result = new StreamResult(new File("src/com/csse/config/EmployeeResponse.xml"));
		TransformerFactory.newInstance().newTransformer(modify).transform(request, result);
	}

	/**
	 *
	 * XMLXP ATHS
	 *
	 * @param Exception
	 *            the exception
	 * @return String>>
	 * @throws Exception
	 */
	
	public static ArrayList<Map<String, String>> XMLXPATHS() throws Exception {

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse("src/com/csse/config/EmployeeResponse.xml");
		XPath request = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) request.compile("count(//Employees/Employee)").evaluate(doc, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			map = new HashMap<String, String>();
			map.put("XpathEmployeeIDKey", (String) request.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put("XpathEmployeeNameKey", (String) request.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put("XpathEmployeeAddressKey",
					(String) request.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(doc,
							XPathConstants.STRING));
			map.put("XpathFacultyNameKey", (String) request.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put("XpathDepartmentKey", (String) request.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put("XpathDesignationKey", (String) request.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(doc, XPathConstants.STRING));
			l.add(map);
		}
		return l;
	}
}
