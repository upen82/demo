package com.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@SuiteClasses({
	PayrollServiceTest.class,
	ShoppingServiceTest.class
})
@RunWith(Suite.class)
public class AllTests {

}
