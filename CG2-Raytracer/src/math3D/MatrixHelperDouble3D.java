package math3D;

public class MatrixHelperDouble3D {
	public static SquareMatrixDouble getRotationX(final double alpha) {
		final double sin = Math.sin(alpha);
		final double cos = Math.cos(alpha);

		return new SquareMatrixDouble(
				1.0, 0.0, 0.0, 0.0,
				0.0, cos, -sin, 0.0,
				0.0, sin, cos, 0.0,
				0.0, 0.0, 0.0, 1.0);
	}

	public static SquareMatrixDouble getRotationY(final double alpha) {
		final double sin = Math.sin(alpha);
		final double cos = Math.cos(alpha);

		return new SquareMatrixDouble(
				cos, 0.0, sin, 0.0,
				0.0, 1.0, 0.0, 0.0,
				-sin, 0.0, cos, 0.0,
				0.0, 0.0, 0.0, 1.0);
	}

	public static SquareMatrixDouble getRotationZ(final double alpha) {
		final double sin = Math.sin(alpha);
		final double cos = Math.cos(alpha);

		return new SquareMatrixDouble(
				cos, -sin, 0.0, 0.0,
				sin, cos, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 1.0);
	}

	public static SquareMatrixDouble getScalar(final double s) {
		return new SquareMatrixDouble(
				s, 0.0, 0.0, 0.0,
				0.0, s, 0.0, 0.0,
				0.0, 0.0, s, 0.0,
				0.0, 0.0, 0.0, 1.0);
	}

	public static SquareMatrixDouble getIdentity() {
		return new SquareMatrixDouble(
				1.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 1.0);
	}

	public static SquareMatrixDouble getTranslation(final double x, final double y, final double z) {
		return new SquareMatrixDouble(
				1.0, 0.0, 0.0, x,
				0.0, 1.0, 0.0, y,
				0.0, 0.0, 1.0, z,
				0.0, 0.0, 0.0, 1.0);
	}

	public static SquareMatrixDouble getShear(
			final double hxy, final double hxz,
			final double hyz, final double hyx,
			final double hzx, final double hzy) {
		return new SquareMatrixDouble(
				1.0, hxy, hxz, 0.0,
				hyx, 1.0, hyz, 0.0,
				hzx, hzy, 1.0, 0.0,
				0.0, 0.0, 0.0, 1.0);
	}
}

