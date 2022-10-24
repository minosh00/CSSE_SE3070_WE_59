package com.csse.main;

import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactoryConfigurationError;

import com.we59.common.XSLTransformUtil;
import com.we59.service.EmployeeService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static final Logger log = Logger.getLogger(ExecuteMain.class.getName());
	
	public static void main(String[] args) {

		//	Get Singleton instance from employee service
		EmployeeService employeeService = EmployeeService.getInstance();
		
		try {
			XSLTransformUtil.requestTransform();
		  // Execute All methods according to template 
			employeeService.setEmployeesToArrayList();
			//employeeService.getEmployeeById("EMP10004");
			//employeeService.deleteEmployeeById("EMP10001");
		
			employeeService.executeEmployeeServiceMethods();
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

	}

} 
