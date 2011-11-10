package math3D;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.math.IMatrix;
import de.bht.fb6.cg1.math.IRowVector;

public class ColumnVectorDouble extends MatrixDouble implements IColumnVector<Double> {
	public ColumnVectorDouble(final Double... t) {
		super(t.length, 1, t);
	}

	public ColumnVectorDouble(final IMatrix<Double> matrix) {
		super(((MatrixDouble) matrix)._m, ((MatrixDouble) matrix)._n, ((MatrixDouble) matrix)._member);
	}

	@Override
	public Double dotProduct(final IColumnVector<Double> vector) {
		if (vector.getRows() != this.getRows())
			throw new IllegalArgumentException("Vector muss gleiche Abmessungen haben.");

		if (this._area == 2 || this._area == 3)
			return this.getTransposed().mult(vector).get(0, 0);

		throw new IllegalArgumentException(
				"Skalarprodukt kann nur von zwei- oder dreidimensional)en Vektoren berechnet werden.");
	}

	@Override
	public IColumnVector<Double> crossProduct(final IColumnVector<Double>... vectors) {
		final IRowVector<Double> _this = new RowVectorDouble(this._member);

		final RowVectorDouble[] vecs = new RowVectorDouble[vectors.length];

		for (int i = 0; i != vectors.length; i++)
			vecs[i] = new RowVectorDouble(vectors[i].getTransposed());

		final IRowVector<Double> result = _this.crossProduct(vecs);

		return new ColumnVectorDouble(result);
	}
}

