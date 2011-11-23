package math3D;

import java.util.Arrays;

import de.bht.fb6.cg1.math.IMatrix;

/**
 * Eine IMatrix<T> Implementierung auf den Typ Float.
 * 
 * @author Rico
 * 
 */
public class MatrixFloat implements IMatrix<Float> {

	/**
	 * Die Elemente der Matrix.
	 */
	final protected float[] _member;
	/**
	 * Die Anzahl Zeilen
	 * */
	final protected int rows;
	/**
	 * Die Anzahl Spalten
	 * */
	final protected int columns;

	/**
	 * Die Fläche (n*m)
	 */
	final protected int _area;

	/**
	 * Hashcode. Weil Matrix immutable ist, kann der Hashcode im Konstruktor
	 * erzeugt werden.
	 */
	final protected int _hash;

	/**
	 * Erzeugt eine neue Matrix Entität
	 * 
	 * @param width
	 *            Breite (n)
	 * @param height
	 *            Höhe (m)
	 * @param e
	 *            die Member, geordent nach e11, e12, e13, ... , e1n, e21, e22,
	 *            ... , e2n, e31, ... , emn
	 */
	public MatrixFloat(final int m, final int n, final float... e) {
		this.rows = m;
		this.columns = n;
		this._area = n * m;

		this._member = e;

		this._hash = generateHash();

		if (e.length != _area || n <= 0 || m <= 0)
			throw new IllegalArgumentException("Übergebene Abmessungen stimmen nicht mit Elementen überein.");
	}

	public MatrixFloat(final MatrixFloat matrix) {
		this(matrix.rows, matrix.columns, matrix._member);
	}

	@Override
	public int getColumns() {
		return columns;
	}

	@Override
	public int getRows() {
		return rows;
	}

	@Override
	public Float get(final int row, final int col) {
		if (col < 0 || row < 0 || row >= this.rows || col >= this.columns)
			throw new IllegalArgumentException("row oder column sind außerhalb des Wertebereiches.");

		return _member[col + row * this.columns];
	}

	@Override
	public IMatrix<Float> add(final IMatrix<Float> matrix) {
		checkNull(matrix);

		if (this.rows != matrix.getRows() || this.columns != matrix.getColumns())
			throw new IllegalArgumentException("Es lassen sich nur Matritzen mit gleicher Abmessung addieren.");

		final float[] result = new float[_area];

		int i = 0;
		for (int row = 0; row != rows; row++) {
			for (int col = 0; col != columns; col++) {
				result[i] = this.get(row, col) + matrix.get(row, col);
				i++;
			}
		}

		return new MatrixFloat(rows, columns, result);
	}

	@Override
	public IMatrix<Float> mult(final IMatrix<Float> matrix) {
		checkNull(matrix);

		if (this.getRows() != matrix.getColumns()) {
			throw new IllegalArgumentException("Matrixmultiplikation geht nur mit Matritzen nach dem Schema m x p * p x n.");
		}

		int i = 0;
		int newRows = this.getColumns();
		int newColumns = this.getRows();
		float[] result = new float[newColumns * newRows];

		for (int row = 0; row != newRows; row++) {
			for (int column = 0; column != newColumns; column++) {

			}
		}
		throw new RuntimeException();
		/*
		 * if (this.getColu != matrix.getRows()) throw new
		 * IllegalArgumentException(
		 * "Matrixmultiplikation geht nur mit Matritzen nach dem Schema m x p * p x n."
		 * );
		 * 
		 * int resultCell = 0; final int newM = this.rows; final int newN =
		 * matrix.getColumns();
		 * 
		 * final float[] result = new float[newM * newN];
		 * 
		 * for (int row = 0; row != newM; row++) { for (int col = 0; col !=
		 * newN; col++) { float sum = 0f;
		 * 
		 * for (int i = 0; i != col; i++) { sum += this.get(row, i) *
		 * matrix.get(i, col); }
		 * 
		 * result[resultCell++] = sum; } }
		 * 
		 * return new MatrixFloat(newM, newN, result);
		 */
	}

	@Override
	public IMatrix<Float> mult(final Float value) {
		final float[] result = new float[_area];

		for (int i = 0; i != result.length; i++) {
			result[i] = _member[i] * value;
		}

		return new MatrixFloat(rows, columns, result);
	}

	@Override
	public IMatrix<Float> sub(final IMatrix<Float> matrix) {
		return this.add(matrix.mult(-1f));
	}

	@Override
	public IMatrix<Float> getTransposed() {
		final float[] result = new float[_area];

		int c = 0;
		for (int col = 0; col != this.getColumns(); col++)
			for (int row = 0; row != this.getRows(); row++)
				result[c++] = get(col, row);

		return new MatrixFloat(this.getColumns(), this.getRows(), result);
	}

	/**
	 * Generiert die Inverse
	 * 
	 * @return Inverse
	 */
	public IMatrix<Float> getInverse() {
		if (columns == 2 && rows == 2)
			return getInverse2();

		if (columns == 3 && rows == 3)
			return getInverse3();

		if (columns == 4 && rows == 4)
			return getInverse4();

		throw new RuntimeException("Inverse für diese Matrix nicht definiert.");
	}

	/**
	 * Generiert die Inverse für 4x4 Matritzen
	 * 
	 * @return Inverse
	 */
	private IMatrix<Float> getInverse4() {
		int c = 0;
		final float m00 = _member[c++];
		final float m01 = _member[c++];
		final float m02 = _member[c++];
		final float m03 = _member[c++];
		final float m10 = _member[c++];
		final float m11 = _member[c++];
		final float m12 = _member[c++];
		final float m13 = _member[c++];
		final float m20 = _member[c++];
		final float m21 = _member[c++];
		final float m22 = _member[c++];
		final float m23 = _member[c++];
		final float m30 = _member[c++];
		final float m31 = _member[c++];
		final float m32 = _member[c++];
		final float m33 = _member[c++];

		final float[] r = new float[] { m12 * m23 * m31 - m13 * m22 * m31 + m13 * m21 * m32 - m11 * m23 * m32 - m12 * m21 * m33 + m11 * m22 * m33, m03 * m22 * m31 - m02 * m23 * m31 - m03 * m21 * m32 + m01 * m23 * m32 + m02 * m21 * m33 - m01 * m22 * m33, m02 * m13 * m31 - m03 * m12 * m31 + m03 * m11 * m32 - m01 * m13 * m32 - m02 * m11 * m33 + m01 * m12 * m33, m03 * m12 * m21 - m02 * m13 * m21 - m03 * m11 * m22 + m01 * m13 * m22 + m02 * m11 * m23 - m01 * m12 * m23, m13 * m22 * m30 - m12 * m23 * m30 - m13 * m20 * m32 + m10 * m23 * m32 + m12 * m20 * m33 - m10 * m22 * m33, m02 * m23 * m30 - m03 * m22 * m30 + m03 * m20 * m32 - m00 * m23 * m32 - m02 * m20 * m33 + m00 * m22 * m33, m03 * m12 * m30 - m02 * m13 * m30 - m03 * m10 * m32 + m00 * m13 * m32 + m02 * m10 * m33 - m00 * m12 * m33, m02 * m13 * m20 - m03 * m12 * m20 + m03 * m10 * m22 - m00 * m13 * m22 - m02 * m10 * m23 + m00 * m12 * m23, m11 * m23 * m30 - m13 * m21 * m30 + m13 * m20 * m31 - m10 * m23 * m31 - m11 * m20 * m33 + m10 * m21 * m33, m03 * m21 * m30 - m01 * m23 * m30 - m03 * m20 * m31 + m00 * m23 * m31 + m01 * m20 * m33 - m00 * m21 * m33, m01 * m13 * m30 - m03 * m11 * m30 + m03 * m10 * m31 - m00 * m13 * m31 - m01 * m10 * m33 + m00 * m11 * m33, m03 * m11 * m20 - m01 * m13 * m20 - m03 * m10 * m21 + m00 * m13 * m21 + m01 * m10 * m23 - m00 * m11 * m23, m12 * m21 * m30 - m11 * m22 * m30 - m12 * m20 * m31 + m10 * m22 * m31 + m11 * m20 * m32 - m10 * m21 * m32, m01 * m22 * m30 - m02 * m21 * m30 + m02 * m20 * m31 - m00 * m22 * m31 - m01 * m20 * m32 + m00 * m21 * m32, m02 * m11 * m30 - m01 * m12 * m30 - m02 * m10 * m31 + m00 * m12 * m31 + m01 * m10 * m32 - m00 * m11 * m32, m01 * m12 * m20 - m02 * m11 * m20 + m02 * m10 * m21 - m00 * m12 * m21 - m01 * m10 * m22 + m00 * m11 * m22 };

		return new SquareMatrixFloat(r).mult(1 / getDeterminant4());
	}

	/**
	 * Generiert die Inverse für 3x3 Matritzen
	 * 
	 * @return Inverse
	 */
	private IMatrix<Float> getInverse3() {
		final float det = getDeterminant3();
		final float[] m = _member;
		final float a = m[0];
		final float b = m[1];
		final float c = m[2];
		final float d = m[3];
		final float e = m[4];
		final float f = m[5];
		final float g = m[6];
		final float h = m[7];
		final float i = m[8];

		return new SquareMatrixFloat(e * i - f * h, c * h - b * i, b * f - c * e, f * g - d * i, a * i - c * g, c * d - a * f, d * h - e * g, b * g - a * h, a * e - b * d).mult(1f / det);
	}

	/**
	 * Generiert die Inverse für 2x2 Matritzen
	 * 
	 * @return Inverse
	 */
	private IMatrix<Float> getInverse2() {
		final float det = getDeterminant2();

		final float[] m = _member;
		return new SquareMatrixFloat(m[3], -m[1], -m[2], m[0]).mult(1f / det);
	}

	/**
	 * Gibt die Determinate zurück.
	 * 
	 * @return Determinante
	 */
	public float getDeterminant() {
		if (columns == 2 && rows == 2)
			return getDeterminant2();

		if (columns == 3 && rows == 3)
			return getDeterminant3();

		if (columns == 4 && rows == 4)
			return getDeterminant4();

		throw new RuntimeException("Determinante für diese Matrix nicht definiert.");
	}

	/**
	 * Gibt die Determinante für 4x4 Matritzen zurück.
	 * 
	 * @return Determinante
	 */
	private float getDeterminant4() {
		int c = 0;
		final float m00 = _member[c++];
		final float m01 = _member[c++];
		final float m02 = _member[c++];
		final float m03 = _member[c++];
		final float m10 = _member[c++];
		final float m11 = _member[c++];
		final float m12 = _member[c++];
		final float m13 = _member[c++];
		final float m20 = _member[c++];
		final float m21 = _member[c++];
		final float m22 = _member[c++];
		final float m23 = _member[c++];
		final float m30 = _member[c++];
		final float m31 = _member[c++];
		final float m32 = _member[c++];
		final float m33 = _member[c++];

		return m03 * m12 * m21 * m30 - m02 * m13 * m21 * m30 - m03 * m11 * m22 * m30 + m01 * m13 * m22 * m30 + m02 * m11 * m23 * m30 - m01 * m12 * m23 * m30 - m03 * m12 * m20 * m31 + m02 * m13 * m20 * m31 + m03 * m10 * m22 * m31 - m00 * m13 * m22 * m31 - m02 * m10 * m23 * m31 + m00 * m12 * m23 * m31 + m03 * m11 * m20 * m32 - m01 * m13 * m20 * m32 - m03 * m10 * m21 * m32 + m00 * m13 * m21 * m32 + m01 * m10 * m23 * m32 - m00 * m11 * m23 * m32 - m02 * m11 * m20 * m33 + m01 * m12 * m20 * m33 + m02 * m10 * m21 * m33 - m00 * m12 * m21 * m33 - m01 * m10 * m22 * m33 + m00 * m11 * m22 * m33;
	}

	/**
	 * Gibt die Determinante für 3x3 Matritzen zurück.
	 * 
	 * @return Determinante
	 */
	private float getDeterminant3() {
		final float[] m = _member;
		final float a = m[0] * m[4] * m[8];
		final float b = m[1] * m[5] * m[6];
		final float c = m[2] * m[3] * m[7];
		final float d = m[2] * m[4] * m[6];
		final float e = m[1] * m[3] * m[8];
		final float f = m[0] * m[5] * m[7];

		return a + b + c - d - e - f;
	}

	/**
	 * Gibt die Determinante für 2x2 Matritzen zurück.
	 * 
	 * @return Determinante
	 */
	private float getDeterminant2() {
		final float[] m = _member;
		return m[0] * m[3] - m[1] * m[2];
	}

	/**
	 * Generiert ein Hashcode.
	 * 
	 * @return Hashcode
	 */
	private int generateHash() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rows;
		result = prime * result + columns;
		result = prime * result + Arrays.hashCode(_member);
		return result;
	}

	@Override
	public String toString() {
		return "[member=" + Arrays.toString(_member) + ", _m=" + rows + ", _n=" + columns + ", area=" + _area + "]";
	}

	@Override
	public int hashCode() {
		return _hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MatrixFloat))
			return false;
		final MatrixFloat other = (MatrixFloat) obj;
		if (rows != other.rows)
			return false;
		if (columns != other.columns)
			return false;
		if (!Arrays.equals(_member, other._member))
			return false;
		return true;
	}

	/**
	 * Gibt die Matrix in menschen lesbare Form wieder.
	 */
	public void print() {
		for (int m = 0; m != rows; m++) {
			for (int n = 0; n != columns; n++) {
				System.out.print(get(m, n));
				if (n != columns - 1)
					System.out.print(", ");
			}

			System.out.printf("%n");
		}

		System.out.printf("%n");
	}

	/**
	 * prüft auf Null und schmeißt ggf. eine Exception.
	 * 
	 * @param matrix
	 *            Argument
	 * @throws IllegalArgumentException
	 */
	private void checkNull(final IMatrix<Float> matrix) throws IllegalArgumentException {
		if (matrix == null)
			throw new IllegalArgumentException("Argument darf nicht null sein.");
	}
}
