package eternity.math;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;

import eternity.exception.MathErrorException;
import org.junit.jupiter.api.Test;

import com.expression.parser.exception.CalculatorException;

import org.junit.jupiter.api.DisplayName;

import eternity.exception.EmptyInputException;
import eternity.exception.OutOfRangeException;

class FunctionsTest {

	@Test
	void testArccosine() {

		class Case {
			String name;
			double input;
			double expectedResult;
			Exception exception;

			public Case(String name, double input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] { new Case("Input = 0", 0, Math.PI / 2, null), new Case("Input = 1", 1, 0, null),
			new Case("Input = -1", -1, Math.PI, null), new Case("Input = 10", 10, 0, new CalculatorException()),
			new Case("Input = -25", 10, 0, new CalculatorException()), new Case("Input = 0.5", 0.5, 1.04719, null),
			new Case("Input = -0.5", -0.5, 2.09439, null) 
		};

		System.out.println("\nTest: Arccosine");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(new BigDecimal(scenario.expectedResult).setScale(5, RoundingMode.DOWN).doubleValue(),
								 new BigDecimal(Functions.arccosine(scenario.input)).setScale(5, RoundingMode.DOWN).doubleValue());
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.arccosine(scenario.input));
			}
		}
	}

	@Test
	void testSemifactorial() {
		class Case {
			String name;
			int input;
			double expectedResult;
			Exception exception;

			public Case(String name, int input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] { new Case("Input = -4", -4, 0, new CalculatorException()),
			new Case("Input = 0", 0, 1, null), new Case("Input = 5", 5, 15, null),
			new Case("Input = 10", 10, 3840, null), new Case("Input = 15", 15, 2027025, null), 
		};

		System.out.println("\nTest: Double Factorial");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(scenario.expectedResult, (double) Functions.semifactorial(scenario.input));
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.arccosine(scenario.input));
			}
		}
	}

	@Test
	void testExponential() {
		class Case {
			String name;
			double a;
			double b;
			double x;
			double expectedResult;
			Exception exception;

			public Case(String name, double a, double b, double x, double expectedResult, Exception exception) {
				this.name = name;
				this.a = a;
				this.b = b;
				this.x = x;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] { new Case("Input = 0.5*(2^-3)", 0.5, 2, -3, 0.0625, null),
			new Case("Input = 7*(-3^3)", 7, -3, 3, -189, null),
			new Case("Input = 10*(2.5^2.5)", 10, 2.5, 2.5, 98.82118, null),
			new Case("Input = 3.5*(3.5^-2)", 3.5, 3.5, -2, 0.285714, null),
			new Case("Input = -5*(-2^-0.5)", -5, -2, -0.5, 0, new CalculatorException("MATH ERROR")), 
		};

		System.out.println("\nTest: Exponential function");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(
							new BigDecimal(scenario.expectedResult).setScale(5, RoundingMode.HALF_UP).doubleValue(),
							new BigDecimal(Functions.exponential(scenario.a, scenario.b, scenario.x)).setScale(5, RoundingMode.HALF_UP).doubleValue());
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.exponential(scenario.a, scenario.b, scenario.x));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}
	}

	@Test
	void testStd_dev() {
		class Case {
			String name;
			ArrayList<Double> input;
			double expectedResult;
			Exception exception;

			public Case(String name, ArrayList<Double> input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] {
			new Case("Input = (3.0, 4.0, 8.6, 9.4, 49.3)",
				new ArrayList<Double>(Arrays.asList(3.0, 4.0, 8.6, 9.4, 49.3)), 17.399494245523343, null),
			new Case("Input = (1.0, 1.0, 1.0, 1.0)", 
				new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0)), 0.0, null),
			new Case("Input = empty", new ArrayList<Double>(), 0, new EmptyInputException("Empty input detected.")) 
		};

		System.out.println("\nTest standard deviation");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(scenario.expectedResult, (double) Functions.std_dev(scenario.input));
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.std_dev(scenario.input));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}

	}

	@Test
	void testMeanAbsoluteDeviation() {
		class Case {
			String name;
			ArrayList<Double> input;
			double expectedResult;
			Exception exception;

			public Case(String name, ArrayList<Double> input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}
		Case cases[] = new Case[] {
			new Case("Input = 0.0, 1.4", new ArrayList<Double>(Arrays.asList(0.0, 1.4)), 0.7, null),
			new Case("Input = -5.2, 3.6, 122.235, 12.0, -6.0, -1000000.9", 
				new ArrayList<Double>(Arrays.asList(-5.2, 3.2, 122.235, 12.0, -6.0, -1000000.9)), 277785.04083333333, null),
			new Case("Input = 0.0", new ArrayList<Double>(Arrays.asList(0.0)), 0, null),
			new Case("Input = null", null, 0, new Exception()),
			new Case("Input = ", new ArrayList<Double>(), 0, new EmptyInputException("Empty input detected.")) 
		};

		System.out.println("\nTest: Mean Absolute Deviation");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(scenario.expectedResult, (double) Functions.meanAbsoluteDeviation(scenario.input));
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(),() -> Functions.meanAbsoluteDeviation(scenario.input));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}
	}

	@Test
	void testLogDouble() {
		class Case {
			String name;
			double input;
			double expectedResult;
			Exception exception;

			public Case(String name, double input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] { 
			new Case("Input = 0.5", 0.5, -0.30102, null),
			new Case("Input = 7", 7, 0.84509, null), new Case("Input = 10", 10, 1.0, null),
			new Case("Input = 0", 0, 0, new OutOfRangeException()),
			new Case("Input = -5", -5, 0, new OutOfRangeException()), 
		};

		System.out.println("\nTest: Common Logarithm (Base 10)");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(new BigDecimal(scenario.expectedResult).setScale(5, RoundingMode.DOWN).doubleValue(),
								 new BigDecimal(Functions.log(scenario.input)).setScale(5, RoundingMode.DOWN).doubleValue());
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.log(scenario.input));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}
	}

	@Test
	void testLogDoubleDouble() {
		class Case {
			String name;
			double input;
			double base;
			double expectedResult;
			Exception exception;

			public Case(String name, double input, double base, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.base = base;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] { 
			new Case("Input = 0.5, Base = 2", 0.5, 2, -1.0, null),
			new Case("Input = 7, Base = 5", 7, 5, 1.20906, null),
			new Case("Input = 50, Base = 78", 50, 78, 0.89793, null),
			new Case("Input = -1, Base = 5", -1, 5, 0, new OutOfRangeException()),
			new Case("Input = 3, Base = 1", 3, 1, 0, new OutOfRangeException()),
			new Case("Input = 3, Base = 0", 3, 0, 0, new OutOfRangeException()),
			new Case("Input = 3, Base = -5", 3, -5, 0, new OutOfRangeException()), 
		};
		
		System.out.println("\nTest: Log (custom base)");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(new BigDecimal(scenario.expectedResult).setScale(5, RoundingMode.DOWN).doubleValue(),
								 new BigDecimal(Functions.log(scenario.input, scenario.base)).setScale(5, RoundingMode.DOWN).doubleValue());
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.log(scenario.input, scenario.base));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}
	}

	@Test
	void testsinh() {
		class Case {
			String name;
			double input;
			double expectedResult;
			Exception exception;

			public Case(String name, double input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}

		Case cases[] = new Case[] { 
			new Case("Input = 0", 0, 0, null), 
			new Case("Input = 1", 1, 1.1752, null),
			new Case("Input = -1", -1, -1.1752, null),
			new Case("Input = 10", 10, 11013.23287, new CalculatorException()),
			new Case("Input = -25", 10, -3.6002, new CalculatorException()),
			new Case("Input = 0.5", 0.5, 0.52109, null), new Case("Input = -0.5", -0.5, -0.52109, null), 
		};

		System.out.println("\nTest: sinh");
		for (Case scenario : cases) {
			System.out.println(scenario.name);

			if (scenario.exception == null) {
				try {
					assertEquals(new BigDecimal(scenario.expectedResult).setScale(5, RoundingMode.DOWN).doubleValue(),
							     new BigDecimal(Functions.sinh(scenario.input)).setScale(5, RoundingMode.DOWN).doubleValue());
				} catch (Exception e) {
					fail("Unexpected exception thrown");
				}
			} else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.arccosine(scenario.input));
			}
		}
	}
}
