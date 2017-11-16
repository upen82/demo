package com.example;


import org.junit.*;

public class PayrollServiceTest{

	PayrollService	payrollService = null;

	@Before
	public void init(){	
		//System.out.println("Inside init()!!");
		payrollService = new PayrollService();
	}

	@After
	public void clean(){
		//System.out.println("Inside clean()!!");
		payrollService = null;
	}

	@Test(expected=InvalidSalaryException.class)
	public void daShouldYieldExceptionIfZeroOrNegativeSalary() throws InvalidSalaryException{
		//PayrollService	payrollService = new PayrollService();
		payrollService.da(-10000.00);
	}
	
	@Test
	public void daShouldBeTenPercentOfSalary(){
		try{
			//PayrollService	payrollService = new PayrollService();
			double result = payrollService.da(10000.00);
			Assert.assertEquals(1000.00,result,2);		
		}catch(InvalidSalaryException ex){}
	}

	@Test(expected=InvalidSalaryException.class)
	public void hraShouldYieldExceptionIfZeroOrNegativeSalary() throws InvalidSalaryException{
		//PayrollService	payrollService = new PayrollService();
		payrollService.hra(-10000.00);
	}
	
	//Every test case must be independent/isolated from other test cases
	@Test
	public void hraShouldBeSixtyPercentOfSalary(){
		try{
			//PayrollService	payrollService = new PayrollService();
			double result = payrollService.hra(10000.00);
			Assert.assertEquals(6000.00,result,2);		
		}catch(InvalidSalaryException ex){}
	}
}