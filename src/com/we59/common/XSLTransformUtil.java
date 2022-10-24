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

	//creating employeeArrayList
	
	private static final ArrayList<Map<String, String>> employeeArrayList = new ArrayList<Map<String, String>>();

	//creating maoDataSet variable
	
	private static Map<String, String> map = null;

	
	/**
	 *
	 * Request transform
	 *
	 * @param Exception
	 *            the exception
	 * @throws Exception
	 */
	
	
	public static void requestTransform() throws Exception {
		//Creating Instance of Source and Accessing the employeeRequest xml
		Source request = new StreamSource(new File(properties.getProperty("EmployeeRequestPath")));
		//Creating Instance of Source and Accessing the Employee-modified xml
		Source modify = new StreamSource(new File(properties.getProperty("Employee_modified_Path")));
		//Creating Instance of Result and Accessing the EmployeeResponse xml
		Result result = new StreamResult(new File(properties.getProperty("EmployeeResponsePath")));
		
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
		
		//creating new Document type variable to access the EmployeeResponse XML
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(properties.getProperty("EmployeeResponsePath"));
		
		// creating new XPath Instance
		XPath request = XPathFactory.newInstance().newXPath();
		
		//get the count of the xml data set to num variable
		int n = Integer.parseInt((String) request.compile(properties.getProperty("Count_Employee_Path")).evaluate(doc, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			
			//assinging new HashMap to the mapDataSet variable
			map = new HashMap<String, String>();
			
			//insert very single employee's data set by set in to the mapDataSet
			map.put(properties.getProperty("XpathEmployeeIDKey"), (String) request.compile("//Employees/Employee[" + i + "]/EmployeeID/text()").evaluate(doc, XPathConstants.STRING));
			map.put(properties.getProperty("XpathEmployeeNameKey"), (String) request.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()").evaluate(doc, XPathConstants.STRING));
			map.put(properties.getProperty("XpathEmployeeAddressKey"),(String) request.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(doc,XPathConstants.STRING));
			map.put(properties.getProperty("XpathFacultyNameKey"), (String) request.compile("//Employees/Employee[" + i + "]/FacultyName/text()").evaluate(doc, XPathConstants.STRING));
			map.put(properties.getProperty("XpathDepartmentKey"), (String) request.compile("//Employees/Employee[" + i + "]/Department/text()").evaluate(doc, XPathConstants.STRING));
			map.put(properties.getProperty("XpathDesignationKey"), (String) request.compile("//Employees/Employee[" + i + "]/Designation/text()").evaluate(doc, XPathConstants.STRING));
			
			//insert mapDataSet value to the main employee Array List
			employeeArrayList.add(map);
		}
		return employeeArrayList;
	}
}
