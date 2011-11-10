package math3D;

public class MatrixHelperFloat3D {
	public static SquareMatrixFloat getRotationX(final double alpha) {
		final float sin = (float) Math.sin(alpha);
		final float cos = (float) Math.cos(alpha);

		return new SquareMatrixFloat(
				1f, 0f, 0f, 0f,
				0f, cos, -sin, 0f,
				0f, sin, cos, 0f,
				0f, 0f, 0f, 1f);
	}

	public static SquareMatrixFloat getRotationY(final double alpha) {
		final float sin = (float) Math.sin(alpha);
		final float cos = (float) Math.cos(alpha);

		return new SquareMatrixFloat(
				cos, 0f, sin, 0f,
				0f, 1f, 0f, 0f,
				-sin, 0f, cos, 0f,
				0f, 0f, 0f, 1f);
	}

	public static SquareMatrixFloat getRotationZ(final double alpha) {
		final float sin = (float) Math.sin(alpha);
		final float cos = (float) Math.cos(alpha);

		return new SquareMatrixFloat(
				cos, -sin, 0f, 0f,
				sin, cos, 0f, 0f,
				0f, 0f, 1f, 0f,
				0f, 0f, 0f, 1f);
	}
	
	public static SquareMatrixFloat getScalar(final float s) {
		return new SquareMatrixFloat(
				s, 0f, 0f, 0f,
				0f, s, 0f, 0f,
				0f, 0f, s, 0f,
				0f, 0f, 0f, 1f);
	}

	public static SquareMatrixFloat getIdentity() {
		return new SquareMatrixFloat(
				1f, 0f, 0f, 0f,
				0f, 1f, 0f, 0f,
				0f, 0f, 1f, 0f,
				0f, 0f, 0f, 1f);
	}

	public static SquareMatrixFloat getTranslation(final float x, final float y, final float z) {
		return new SquareMatrixFloat(
				1f, 0f, 0f, x,
				0f, 1f, 0f, y,
				0f, 0f, 1f, z,
				0f, 0f, 0f, 1f);
	}

	public static SquareMatrixFloat getShear(
			final float hxy, final float hxz,
			final float hyz, final float hyx,
			final float hzx, final float hzy) {
		return new SquareMatrixFloat(
				1f, hxy, hxz, 0f,
				hyx, 1f, hyz, 0f,
				hzx, hzy, 1f, 0f,
				0f, 0f, 0f, 1f);
	}
}
