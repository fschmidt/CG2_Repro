package math3D;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.math.IMatrix;
import de.bht.fb6.cg1.math.IRowVector;

public class ColumnVectorFloat extends MatrixFloat implements IColumnVector<Float> {
	public ColumnVectorFloat(final float... t) {
		super(t.length, 1, t);
	}

	public ColumnVectorFloat(final IMatrix<Float> matrix) {
		//super(((MatrixFloat) matrix)._m, ((MatrixFloat) matrix)._n, ((MatrixFloat) matrix)._member);
		this(getSize(matrix), ((MatrixFloat) matrix)._member);
	}

	private ColumnVectorFloat(final int size, final float[] e) {
		super(size, 1, e);
	}

	private static int getSize(final IMatrix<Float> matrix) {
		if (matrix.getColumns() != 1)
			throw new RuntimeException("matrix ist im falschen Format");

		return matrix.getRows();
	}

	@Override
	public Float dotProduct(final IColumnVector<Float> vector) {
		if (vector.getRows() != this.getRows())
			throw new IllegalArgumentException("Vector muss gleiche Abmessungen haben.");

		if (this._area == 2 || this._area == 3)
			return this.getTransposed().mult(vector).get(0, 0);

		throw new IllegalArgumentException(
				"Skalarprodukt kann nur von zwei- oder dreidimensional)en Vektoren berechnet werden.");
	}

	@Override
	public IColumnVector<Float> crossProduct(final IColumnVector<Float>... vectors) {
		final IRowVector<Float> _this = new RowVectorFloat(this._member);

		final RowVectorFloat[] vecs = new RowVectorFloat[vectors.length];

		for (int i = 0; i != vectors.length; i++)
			vecs[i] = new RowVectorFloat(vectors[i].getTransposed());

		final IRowVector<Float> result = _this.crossProduct(vecs);

		return new ColumnVectorFloat(result.getTransposed());
	}
}
