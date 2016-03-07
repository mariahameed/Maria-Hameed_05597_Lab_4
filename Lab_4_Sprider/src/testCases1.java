import static org.junit.Assert.*;

import org.junit.Test;

public class testCases1 {

	@Test
	public void test() {
		
		//this test case tests tests if the exception handling is done correctly
		//if an exception is thrown, it fails. else pass
	  try {
		  Spider s = new Spider();
		} catch (Exception ex) {
			fail( "My method didn't throw when I expected it to" );
		}
	}
}
