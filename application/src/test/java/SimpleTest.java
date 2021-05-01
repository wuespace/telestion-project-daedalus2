package de.wuespace.telestion.extension.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {

	@Test
	public void test(){
		var a = 5;
		var b = 6;
		var expected = 11;
		var actual = a + b;
		Assertions.assertEquals(actual, expected);
	}
}
