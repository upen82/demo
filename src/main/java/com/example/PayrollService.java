package com.example;


public class PayrollService{

	public double da(double salary) throws InvalidSalaryException{
		if(salary <= 0){
			throw new InvalidSalaryException("Salary should not be zero or negative!!!!!");
		}
		return salary*.10;
	}
	
	public double hra(double salary) throws InvalidSalaryException{
		if(salary <= 0){
			throw new InvalidSalaryException("Salary should not be zero or negative!!!!!");
		}
		return salary*.60;
	}

}