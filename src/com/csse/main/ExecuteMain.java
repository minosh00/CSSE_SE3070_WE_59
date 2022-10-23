package com.csse.main;

import com.we59.common.XSLTransformUtil;
import com.we59.service.EmployeeService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			XSLTransformUtil.rEQUESTtRANSFORM();
			employeeService.setEmployeesToArrayList();
			employeeService.createEmployeeTable();
			employeeService.createEmployee();
			// employeeService.employeeGetById("EMP10004");
			// employeeService.employeeDelete("EMP10001");
			employeeService.getAllEmployees();
		} catch (Exception e) {
		}

	}

} 
