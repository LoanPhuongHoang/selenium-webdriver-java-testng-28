package testng;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args) {
		String fullName = "Automation Testing";
		//Assert
		
//		Assert.assertTrue(fullName.contains("Auto"));
//		Assert.assertFalse(fullName.contains("Auto"));
		//2 conditions are equal
		Assert.assertEquals(fullName, "Manula");

	}

}
