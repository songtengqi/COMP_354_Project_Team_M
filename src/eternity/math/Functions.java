package eternity.math;

import java.util.ArrayList;

import com.expression.parser.exception.CalculatorException;

import eternity.exception.EmptyInputException;
import eternity.exception.MathErrorException;
import eternity.exception.OutOfRangeException;

public class Functions {

	/***
	 * @param x: Domain [-1, 1]
	 * @return: Range [0, PI], in Rad
	 */
	public static double arccosine(double x) throws CalculatorException {
		if (x < -1 || x > 1)
			throw new CalculatorException("MATH ERROR");

		if (x == 0)
			return (DegreeRadian.isRadian ? Math.PI / 2 : DegreeRadian.radianToDegree(Math.PI / 2));
		else if (x == 1)
			return 0;
		else if (x == -1)
			return (DegreeRadian.isRadian ? Math.PI : DegreeRadian.radianToDegree(Math.PI));

		// Taylor Series (14th approximation)
		double result = x;

		for (int n = 1; n < 14; n++) {
			result += (semifactorial(2 * n - 1) / semifactorial(2 * n)) * (exponential(1, x, 2 * n + 1) / (2 * n + 1));
		}

		return (DegreeRadian.isRadian ? Math.PI / 2 - result : DegreeRadian.radianToDegree((Math.PI / 2 - result)));
	}

	public static double semifactorial(int x) throws CalculatorException {
		if (x < 0)
			throw new CalculatorException("MATH ERROR");

		int result = 1;
		while (x > 0) {
			result *= x;
			x -= 2;
		}
		return result;
	}

	/**
	 * Taylor Series (100th approximation) e^x
	 *
	 * @param x real number
	 * @return real number
	 */
	private static double natural_exponential(double x) {
		double result = 1;
		int loop = 100;
		int start = 1;

		while (start <= loop) {
			result += power_intPositiveExponent(x, start) / factorial(start);
			start++;
		}
		return result;
	}

	public static double power_intPositiveExponent(double base, int x) {
		double result = base;

		if (x == 1)
			return result;
		else {
			for (int i = 2; i <= x; i++) {
				result = result * base;
			}
			return result;
		}
	}

	private static double factorial(int n) {
		if (n == 0)
			return 1;
		else {
			return n * factorial(n - 1);
		}
	}

	private static double ln_derivative(double x, int derivDegree) throws CalculatorException {
		if (derivDegree == 1)
			return exponential(1, x, -1);
		else {
			double prefix = 1;
			for (int i = 1; i < derivDegree; i++) {
				prefix *= (-1 * i);
			}
			return prefix * exponential(1, x, derivDegree * -1);
		}
	}

	/**
	 * ab^x
	 * 
	 * @param a real number
	 * @param b real number
	 * @param x real number
	 * @return real number Input restraint: no negative base with decimal exponent
	 */
	public static double exponential(double a, double b, double x) throws CalculatorException {
		Double result = 0.0;

		if (b >= 0)
			result = a * natural_exponential(x * ln(b));
		else
			result = a * Math.pow(b, x);

		if (result.isNaN())
			throw new CalculatorException("MATH ERROR");
		else
			return result;
	}

	public static double power(double x, int n) {
		if (n == 0)
			return 1;

		if (n < 0) {
			x = 1.0 / x;
			n = -n;
		}

		double ret = power(x, n / 2);
		ret = ret * ret;
		if (n % 2 != 0)
			ret = ret * x;
		return ret;
	}

	public static double std_dev(ArrayList<Double> dataSet) throws EmptyInputException, CalculatorException {
		if (dataSet.isEmpty())
			throw new EmptyInputException("Empty input detected.");
		double sd = 0;
		double sum = 0;
		for (double i : dataSet)
			sum += i;
		double mean = sum / dataSet.size();
		for (double num : dataSet)
			sd += Math.pow(num - mean, 2);
		sd = Math.sqrt(sd / dataSet.size());
		return sd;
	}

	/**
	 * Mean Absolute Deviation (MAD)
	 * 
	 * @param dataSet an arrayList of real numbers
	 * @return a real number
	 */
	public static double meanAbsoluteDeviation(ArrayList<Double> dataSet) throws EmptyInputException {
		if (dataSet.isEmpty())
			throw new EmptyInputException("Empty input detected.");

		double avg = 0;
		for (double x : dataSet) {
			avg += x;
		}
		avg /= dataSet.size(); // Calculate mean of dataSet

		double madSum = 0;
		for (double x : dataSet) {
			if (x < avg) // absolute value of difference from data point to calculated mean
				madSum += avg - x;
			else
				madSum += x - avg;
		}

		return madSum / dataSet.size(); // return mean absolute deviation
	}

	/**
	 * Natural logarithm (base e)
	 * 
	 * @param x - real number
	 * @return real number
	 */
	private static double ln(double x) {
		double old_sum = 0.0;
		double denominator = 1.0;
		double y = (x - 1) / (x + 1);
		double y2 = y * y;
		double sum = y;

		while (sum != old_sum) {
			old_sum = sum;
			denominator += 2.0;
			y *= y2;
			sum += y / denominator;
		}
		return 2.0 * sum;
	}

	/**
	 * Common logarithm (base 10)
	 * 
	 * @param x real number
	 * @return real number
	 */
	public static double log(double x) throws OutOfRangeException {
		return log(x, 10);
	}

	/**
	 * Logarithmic function (custom base)
	 * 
	 * @param x    real number
	 * @param base real number
	 * @return real number
	 */
	public static double log(double x, double base) throws OutOfRangeException {
		if (x <= 0 || base <= 0 || base == 1)
			throw new OutOfRangeException();
		return ln(x) / ln(base);
	}

	/**
	 * Hyperbolic Function (sinh(x))
	 * 
	 * @param x:a real variable
	 * @return :a real number with range (-infinity,infinity)
	 */
	public static double sinh(double x) {
		double e1 = natural_exponential(x); // calculate the e^x
		double e2 = natural_exponential(-x);// calculate the e^-x
		double sinh = (e1 - e2) / 2;
		return sinh;
	}
}
