import static org.junit.Assert.assertTrue;
import math3D.MatrixFloat;
import math3D.MatrixHelperFloat3D;
import math3D.SquareMatrixFloat;

import org.junit.Before;
import org.junit.Test;

import de.bht.fb6.cg1.math.IMatrix;

public class MatrixTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void identityIsTransposed() {
		IMatrix<Float> i = MatrixHelperFloat3D.getIdentity();
		IMatrix<Float> t = i.getTransposed();

		assertTrue(i.equals(t));
	}

	@Test
	public void transposed() {
		MatrixFloat a = new MatrixFloat(4, 2, //
				1f, 2f, //
				3f, 4f, //
				5f, 6f, //
				7f, 8f);

		MatrixFloat b = new MatrixFloat(2, 4, //
				1f, 3f, 5f, 7f, //
				2f, 4f, 6f, 8f);

		a.print();
		b.print();
		((MatrixFloat) a.getTransposed()).print();

		assertTrue(a.getTransposed().equals(b));
	}

	@Test
	public void multiIdentity() {
		IMatrix<Float> a = new MatrixFloat(6, 4, //
				1f, 2f, 3f, 0f, //
				1f, 2f, 3f, 1f, //
				1f, 2f, 3f, 0f, //
				1f, 2f, 3f, 1f, //
				1f, 2f, 3f, 0f, //
				1f, 2f, 3f, 1f);

		IMatrix<Float> i = MatrixHelperFloat3D.getIdentity();

		assertTrue(a.mult(i).equals(a));
	}

	@Test
	public void multi() {
		IMatrix<Float> a = new MatrixFloat(2, 4, //
				1f, 1f, 1f, 0f,//
				1f, 1f, 1f, 1f);

		SquareMatrixFloat r = MatrixHelperFloat3D.getRotationX(Math.PI);

		IMatrix<Float> b = a.mult(r);

		IMatrix<Float> c = new MatrixFloat(2, 4, //
				1f, 1f, 1f, 0f,//
				1f, -1f, -1f, 1f);

		assertTrue(isNear(b, c));
	}

	private boolean isNear(IMatrix<Float> a, IMatrix<Float> b) {
		final float e = 0.001f;
		if (a.getColumns() != b.getColumns() || a.getRows() != b.getRows())
			return false;

		for (int r = 0; r != a.getRows(); r++) {
			for (int c = 0; c != a.getColumns(); c++) {
				if (Math.abs(a.get(r, c) - b.get(r, c)) > e)
					return false;
			}
		}

		return true;
	}
}
