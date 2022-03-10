/** Decimal Class
 */

package eternity.math;

import java.math.BigDecimal;

import java.math.RoundingMode;

public class Decimal {
	private BigDecimal bigDecimal;
	private int decimal;

	public Decimal() {
		this.decimal = 5;
		this.bigDecimal = new BigDecimal(0).setScale(decimal, RoundingMode.HALF_UP);
	}

	public void setDecimal(int inputDecimal) {
		decimal = inputDecimal;
	}

	public double getResultDecimal(double rawResult) {
		String rawResultString = Double.toString(rawResult);
		BigDecimal bd = new BigDecimal(rawResultString).setScale(decimal, RoundingMode.HALF_UP);
		double resultWithDecimal = bd.doubleValue();
		return resultWithDecimal;
	}
}
