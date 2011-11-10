package math3D;

import de.bht.fb6.cg1.math.IMatrix;

public class MatrixHelperDouble2D {
	public static IMatrix<Double> getRotation(final double alpha) {
		final double sin = (double) Math.sin(alpha);
		final double cos = (double) Math.cos(alpha);

		return new SquareMatrixDouble(
				cos, -sin, 0.0,
				sin, cos, 0.0,
				0.0, 0.0, 1.0);
	}

	public static IMatrix<Double> getScalar(final double s) {
		return new SquareMatrixDouble(
				s, 0.0, 0.0,
				0.0, s, 0.0,
				0.0, 0.0, 1.0);
	}

	public static IMatrix<Double> getIdentity() {
		return new SquareMatrixDouble(
				1.0, 0.0, 0.0,
				0.0, 1.0, 0.0,
				0.0, 0.0, 1.0);
	}

	public static IMatrix<Double> getTranslation(final double x, final double y) {
		return new SquareMatrixDouble(
				1.0, 0.0, x,
				0.0, 1.0, y,
				0.0, 0.0, 1.0);
	}

	public static IMatrix<Double> getShear(final double hx, final double hy) {
		return new SquareMatrixDouble(
				1.0, hy, 0.0,
				hx, 1.0, 0.0,
				0.0, 0.0, 1.0);
	}
}

