package math3D;

import de.bht.fb6.cg1.math.IMatrix;
import de.bht.fb6.cg1.math.IRowVector;

public class RowVectorDouble extends MatrixDouble implements IRowVector<Double> {
	public RowVectorDouble(final Double... t) {
		super(1, t.length, t);
	}

	public RowVectorDouble(final IMatrix<Double> matrix) {
		super(((MatrixDouble) matrix)._m, ((MatrixDouble) matrix)._n, ((MatrixDouble) matrix)._member);
	}

	@Override
	public Double dotProduct(final IRowVector<Double> vector) {
		if (vector.getColumns() != this.getColumns())
			throw new IllegalArgumentException("Vector muss gleiche Abmessungen haben.");

		if (this._area == 2 || this._area == 3)
			return this.mult(vector.getTransposed()).get(0, 0);

		throw new IllegalArgumentException(
				"Skalarprodukt kann nur von zwei- oder dreidimensional)en Vektoren berechnet werden.");
	}

	@Override
	public IRowVector<Double> crossProduct(final IRowVector<Double>... vectors) {
		IRowVector<Double> last = this;

		for (int i = 0; i < vectors.length; i++)
			last = calcCrossProduct(last, vectors[i]);

		return last;
	}

	private IRowVector<Double> calcCrossProduct(final IRowVector<Double> a, final IRowVector<Double> b) {
		if (a.getColumns() != b.getColumns() || a.getRows() != b.getRows())
			throw new IllegalArgumentException("a und b müssen die gleichen Abmessungen haben.");

		if (a.getRows() != 3)
			throw new IllegalArgumentException("Kreuzprodukt ist nur für dreidimensionale Vektoren gedacht.");

		return new RowVectorDouble(
				a.get(0, 1) * b.get(0, 2) - a.get(0, 2) * b.get(0, 1),
				a.get(0, 2) * b.get(0, 0) - a.get(0, 0) * b.get(0, 2),
				a.get(0, 0) * b.get(0, 1) - a.get(0, 1) * b.get(0, 0));
	}
}

