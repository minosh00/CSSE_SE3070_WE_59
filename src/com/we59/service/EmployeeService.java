package com.we59.service;

import com.we59.common.CommonProperties;
import com.we59.common.CommonUtil;
import com.we59.common.XSLTransformUtil;
import com.we59.model.Employee;

import java.io.IOException;
import java.sql.Connection;
//import java.util.logging.Logger;
import java.sql.DriverManager;
//import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
//import javax.xml.xpath.XPathExpressionException;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLException;
//import java.util.logging.Level;
import java.sql.Statement;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * The class Employee service extends common properties
 */


public class EmployeeService extends CommonProperties {

	private final ArrayList<Employee> employeelist = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;
	private static EmployeeService uniqueInstance;
	
	private PreparedStatement preparedStatement;

	public static final Logger log = Logger.getLogger(EmployeeService.class.getName());

	/**
	 *
	 * Employee service
	 *
	 * @return
	 */
	
	public EmployeeService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
			log.log(Level.INFO, "Database Connection Success");
			
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	
	// Get Singleton Object

		public static EmployeeService getInstance() {
			if (uniqueInstance == null) {
				synchronized (EmployeeService.class) {
					if (uniqueInstance == null) {
						uniqueInstance = new EmployeeService();
					}
				}
			}
			return uniqueInstance;
		}

		/**
		 * 
		 * Execute Employee Service methods according to template pattern
		 *
		 */
		public final void executeEmployeeServiceMethods() {
			setEmployeesToArrayList();
			createEmployeeTable();
			createEmployee();
			getAllEmployees();
		}

		/**
		 *
		 * Sets the employees to array list
		 *
		 */
		
	/**
	 *
	 * Sets the employees to array list
	 *
	 */
	
	public void setEmployeesToArrayList() {

		try {
			int statement = XSLTransformUtil.XMLXPATHS().size();
			for (int i = 0; i < statement; i++) {
				Map<String, String> employeeOutPutMap  = XSLTransformUtil.XMLXPATHS().get(i);
				Employee employee = new Employee();
				employee.setEmployeeID(employeeOutPutMap .get("XpathEmployeeIDKey"));
				employee.setFullName(employeeOutPutMap .get("XpathEmployeeNameKey"));
				employee.setAddress(employeeOutPutMap .get("XpathEmployeeAddressKey"));
				employee.setFacultyName(employeeOutPutMap .get("XpathFacultyNameKey"));
				employee.setDepartment(employeeOutPutMap .get("XpathDepartmentKey"));
				employee.setDesignation(employeeOutPutMap .get("XpathDesignationKey"));
				employeelist.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 *
	 * Create employee table
	 *
	 *** @throws NumberFormatException
     *     - Thrown to indicate that the application has attempted to
     * *          convert a string to one of the numeric types
     *
     *@throws ClassNotFoundException
     *    - Thrown when an application tries to load in a class through
     *     its string name using
     *     
     * @throws SAXException 
     *     - Encapsulate a general SAX error or warning
     * 
     * @throws I0Exception
     *    - Exception produced by failed or interrupted I/O operations.
     *    
     * @throws ParserConfigurationException
     *    - Indicates a serious configuration error.
     *    
     * @throws NullPointerException
     *    - Service is not available
     *
     * @throws SQLException
     *     - Thrown when database access error occurs or this method is called on a closed connection
     */
	
	public void createEmployeeTable() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(CommonUtil.getEmployeeQueries("q2"));
			statement.executeUpdate(CommonUtil.getEmployeeQueries("q1"));
		
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	
	/**
	 *
	 * Create employee
	 *
	 */
	
	public void createEmployee() {
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q3"));
			connection.setAutoCommit(false);
			for (int i = 0; i < employeelist.size(); i++) {
				Employee employee = employeelist.get(i);
				preparedStatement.setString(1, employee.getEmployeeID());
				preparedStatement.setString(2, employee.getFullName());
				preparedStatement.setString(3, employee.getAddress());
				preparedStatement.setString(4, employee.getFacultyName());
				preparedStatement.setString(5, employee.getDepartment());
				preparedStatement.setString(6, employee.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	/**
	 *
	 * Gets the employee by identifier
	 *
	 * @param eid
	 *            the employee ID
	 * 
	 ** @throws NumberFormatException
     *     - Thrown to indicate that the application has attempted to
     * *          convert a string to one of the numeric types
     *
     *@throws ClassNotFoundException
     *    - Thrown when an application tries to load in a class through
     *     its string name using
     *     
     * @throws SAXException 
     *     - Encapsulate a general SAX error or warning
     * 
     * @throws I0Exception
     *    - Exception produced by failed or interrupted I/O operations.
     *    
     * @throws ParserConfigurationException
     *    - Indicates a serious configuration error.
     *    
     * @throws NullPointerException
     *    - Service is not available
     *
     * @throws SQLException
     *     - Thrown when database access error occurs or this method is called on a closed connection
     */
	
	public void getEmployeeById(String eid) {

		Employee emp = new Employee();
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q4"));
			preparedStatement.setString(1, eid);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				emp.setEmployeeID(resultset.getString(1));
				emp.setFullName(resultset.getString(2));
				emp.setAddress(resultset.getString(3));
				emp.setFacultyName(resultset.getString(4));
				emp.setDepartment(resultset.getString(5));
				emp.setDesignation(resultset.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(emp);
			printEmployeeDetails(l);
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	
	/**
	 *
	 * Delete employee by identifier
	 *
	 * @param eid
	 *            the employee ID
	 *** @throws NumberFormatException
     *     - Thrown to indicate that the application has attempted to
     * *          convert a string to one of the numeric types
     *
     *@throws ClassNotFoundException
     *    - Thrown when an application tries to load in a class through
     *     its string name using
     *     
     * @throws SAXException 
     *     - Encapsulate a general SAX error or warning
     * 
     * @throws I0Exception
     *    - Exception produced by failed or interrupted I/O operations.
     *    
     * @throws ParserConfigurationException
     *    - Indicates a serious configuration error.
     *    
     * @throws NullPointerException
     *    - Service is not available
     *
     * @throws SQLException
     *     - Thrown when database access error occurs or this method is called on a closed connection
     */
	
	public void deleteEmployeeById(String eid) {

		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q6"));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 *
	 * Gets the all employees
	 *
	 *** @throws NumberFormatException
     *     - Thrown to indicate that the application has attempted to
     * *          convert a string to one of the numeric types
     *
     *@throws ClassNotFoundException
     *    - Thrown when an application tries to load in a class through
     *     its string name using
     *     
     * @throws SAXException 
     *     - Encapsulate a general SAX error or warning
     * 
     * @throws I0Exception
     *    - Exception produced by failed or interrupted I/O operations.
     *    
     * @throws ParserConfigurationException
     *    - Indicates a serious configuration error.
     *    
     * @throws NullPointerException
     *    - Service is not available
     *
     * @throws SQLException
     *     - Thrown when database access error occurs or this method is called on a closed connection
     */
	
	public void getAllEmployees() {

		ArrayList<Employee> l = new ArrayList<Employee>();

		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q5"));

			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Employee emp = new Employee();
				emp.setEmployeeID(result.getString(1));
				emp.setFullName(result.getString(2));
				emp.setAddress(result.getString(3));
				emp.setFacultyName(result.getString(4));
				emp.setDepartment(result.getString(5));
				emp.setDesignation(result.getString(6));
				l.add(emp);
			}

		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		printEmployeeDetails(l);
	}
	/**
	 *
	 * Print employee details
	 *
	 * @param employeeList
	 *            the employee list
	 */
	
	public void printEmployeeDetails(ArrayList<Employee> l) {

		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out.println(
				"================================================================================================================");
		for (int i = 0; i < l.size(); i++) {
			Employee e = l.get(i);
			System.out.println(e.getEmployeeID() + "\t" + e.getFullName() + "\t\t" + e.getAddress() + "\t"
					+ e.getFacultyName() + "\t" + e.getDepartment() + "\t" + e.getDesignation() + "\n");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------");
		}

	}
}
