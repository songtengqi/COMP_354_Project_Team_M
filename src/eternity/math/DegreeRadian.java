package eternity.math;

public class DegreeRadian {
	public static boolean isRadian = true;

	public static void setRadian(boolean radian) {
		isRadian = radian;
	}

	public static double radianToDegree(double radian) {
		return radian * 180 / Math.PI;
	}

	public static double degreeToRadian(double degree) {
		return degree * Math.PI / 180;
	}
}
