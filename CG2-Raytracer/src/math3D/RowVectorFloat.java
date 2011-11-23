package math3D;

import de.bht.fb6.cg1.math.IMatrix;
import de.bht.fb6.cg1.math.IRowVector;

public class RowVectorFloat extends MatrixFloat implements IRowVector<Float> {
	public RowVectorFloat(final float... t) {
		super(1, t.length, t);
	}

	public RowVectorFloat(final IMatrix<Float> matrix) {
		super(((MatrixFloat) matrix).rows, ((MatrixFloat) matrix).columns, ((MatrixFloat) matrix)._member);
	}

	@Override
	public Float dotProduct(final IRowVector<Float> vector) {
		if (vector.getColumns() != this.getColumns())
			throw new IllegalArgumentException("Vector muss gleiche Abmessungen haben.");

		if (this._area == 2 || this._area == 3)
			return this.mult(vector.getTransposed()).get(0, 0);

		throw new IllegalArgumentException("Skalarprodukt kann nur von zwei- oder dreidimensional)en Vektoren berechnet werden.");
	}

	@Override
	public IRowVector<Float> crossProduct(final IRowVector<Float>... vectors) {
		IRowVector<Float> last = this;

		for (int i = 0; i < vectors.length; i++)
			last = calcCrossProduct(last, vectors[i]);

		return last;
	}

	private IRowVector<Float> calcCrossProduct(final IRowVector<Float> a, final IRowVector<Float> b) {
		if (a.getColumns() != b.getColumns() || a.getRows() != b.getRows())
			throw new IllegalArgumentException("a und b müssen die gleichen Abmessungen haben.");

		if (a.getColumns() == 3)
			return calcCrossProduct3(a, b);
		else if (a.getColumns() == 4)
			return calcCrossProduct4(a, b);

		throw new IllegalArgumentException("Kreuzprodukt ist nur für dreidimensionale Vektoren gedacht.");
	}

	private IRowVector<Float> calcCrossProduct4(final IRowVector<Float> a, final IRowVector<Float> b) {
		// Wir gehen davon aus, dass die vierte Komponente W ist.
		final float w = a.get(0, 3);
		if (w != b.get(0, 3))
			throw new IllegalArgumentException("Beide Vektoren braucehn die gleiche W Komponente");

		return new RowVectorFloat(a.get(0, 1) * b.get(0, 2) - a.get(0, 2) * b.get(0, 1), a.get(0, 2) * b.get(0, 0) - a.get(0, 0) * b.get(0, 2), a.get(0, 0) * b.get(0, 1) - a.get(0, 1) * b.get(0, 0), w);
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 * @throws IllegalArgumentException
	 */
	private IRowVector<Float> calcCrossProduct3(final IRowVector<Float> a, final IRowVector<Float> b) throws IllegalArgumentException {
		return new RowVectorFloat(a.get(0, 1) * b.get(0, 2) - a.get(0, 2) * b.get(0, 1), a.get(0, 2) * b.get(0, 0) - a.get(0, 0) * b.get(0, 2), a.get(0, 0) * b.get(0, 1) - a.get(0, 1) * b.get(0, 0));
	}
}
