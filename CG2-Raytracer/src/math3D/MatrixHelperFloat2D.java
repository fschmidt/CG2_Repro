package math3D;

import de.bht.fb6.cg1.math.IMatrix;

public class MatrixHelperFloat2D {
	public static IMatrix<Float> getRotation(final float alpha) {
		final float sin = (float) Math.sin(alpha);
		final float cos = (float) Math.cos(alpha);

		return new SquareMatrixFloat(
				cos, -sin, 0f,
				sin, cos, 0f,
				0f, 0f, 1f);
	}

	public static IMatrix<Float> getScalar(final float sx, final float sy) {
		return new SquareMatrixFloat(
				sx, 0f, 0f,
				0f, sy, 0f,
				0f, 0f, 1f);
	}

	public static IMatrix<Float> getIdentity() {
		return new SquareMatrixFloat(
				1f, 0f, 0f,
				0f, 1f, 0f,
				0f, 0f, 1f);
	}

	public static IMatrix<Float> getTranslation(final float x, final float y) {
		return new SquareMatrixFloat(
				1f, 0f, x,
				0f, 1f, y,
				0f, 0f, 1f);
	}

	public static IMatrix<Float> getShear(final float hx, final float hy) {
		return new SquareMatrixFloat(
				1f, hy, 0f,
				hx, 1f, 0f,
				0f, 0f, 1f);
	}
}
