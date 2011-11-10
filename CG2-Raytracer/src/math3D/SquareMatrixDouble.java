package math3D;

import de.bht.fb6.cg1.math.ISquareMatrix;

public class SquareMatrixDouble extends MatrixDouble implements ISquareMatrix<Double> {
	private final int size;

	public SquareMatrixDouble(final Double... e) {
		this(getSize(e), e);
	}

	private SquareMatrixDouble(final int n, final Double... e) {
		super(n, n, e);
		size = n;
	}

	private static int getSize(final Double[] e) {
		final int n = (int) Math.sqrt(e.length);

		if (n * n != e.length)
			throw new IllegalArgumentException("Es muss eine Quadratische Anzahl an Elementen übergeben werden.");

		return n;
	}

	@Override
	public boolean isIdentityMatrix() {
		for (int i = 0; i != size; i++) {
			if (i / size == i % size) {
				//Diagonale
				if (this._member[i] != 1f)
					return false;
			} else {
				//Nicht Diagonale
				if (this._member[i] != 0f)
					return false;
			}
		}

		return true;
	}

}

