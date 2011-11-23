package cg2.raytracerApp;

import math3D.MatrixFloat;
import math3D.MatrixHelperFloat2D;
import de.bht.fb6.cg1.math.IMatrix;

public class MathMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MatrixFloat a = new MatrixFloat(8, 3, //
				0f, 1f, 1f, // v1
				0f, 1f, 0f, // n1
				-1f, 0f, 1f, // v2
				-1f, 0f, 0f, // n2
				0f, -1f, 1f, // v3
				0f, -1f, 0f, // n3
				1f, 0f, 1f, // v4
				1f, 0f, 0f); // n4

		IMatrix<Float> rotat = MatrixHelperFloat2D.getRotation((float) Math.PI / 2f);
		IMatrix<Float> translation = MatrixHelperFloat2D.getTranslation(1, 0);
		IMatrix<Float> t = translation.mult(rotat);

		print(a);
		print(rotat);
		print(translation);
		print(t);
		print(a.mult(t));

	}

	private static void print(IMatrix<Float> a) {
		for (int row = 0; row < a.getRows(); row++) {
			for (int col = 0; col != a.getColumns(); col++) {
				System.out.print(a.get(row, col) + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

}
