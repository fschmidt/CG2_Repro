package cg2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import math3D.MatrixFloat;
import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.math.IMatrix;
import de.bht.fb6.cg1.mesh.TriangleMesh;

 
public class ObjData {

	/**
	 * Die Vektoren des Körpers.
	 */
	private final List<IColumnVector<Float>> vectors;
	/**
	 * Die Normalen
	 */
	private final List<IColumnVector<Float>> normals;
	/**
	 * Die Flächen
	 */
	private final Integer[][] faces;

	/**
	 * Konstruktor
	 * 
	 * @param vectors
	 *            die Vektoren
	 * @param normals
	 *            Die Normalen
	 * @param faces
	 *            die Flächen
	 */
	public ObjData(final ArrayList<IColumnVector<Float>> vectors, final ArrayList<IColumnVector<Float>> normals,
			final ArrayList<Integer[]> faces) {
		this.vectors = vectors;
		this.normals = normals;
		this.faces = faces.toArray(new Integer[][] { {} });
	}

	/**
	 * verwandelt das Objekt in eine 4xn Matrix auf die Transformationen
	 * angewand werden können.
	 * 
	 * @return Matrix
	 */
	public IMatrix<Float> getMatrixMap() {
		final int n = vectors.size();
		final float[] field = new float[4 * n];

		int i = 0;
		for (final IColumnVector<Float> v : vectors) {
			field[i] = v.get(0, 0);
			field[i + n] = v.get(1, 0);
			field[i + 2 * n] = v.get(2, 0);
			field[i + 3 * n] = v.get(3, 0);

			i++;
		}

		return new MatrixFloat(4, n, field);
	}

	/**
	 * Gibt die Flächen nach dem Schema int[n][3] zurück
	 * 
	 * @return Flächen
	 */
	public int[][] getFaces() {
		final int[][] field = new int[faces.length][];
		for (int i = 0; i != faces.length; i++) {
			field[i] = new int[3];
			field[i][0] = this.faces[i][0];
			field[i][1] = this.faces[i][1];
			field[i][2] = this.faces[i][2];
		}

		return field;
	}

	/**
	 * Gibt Objekt als TriangleMesh zurück.
	 * 
	 * @return Triangle Mesh
	 */
	public TriangleMesh<Float> getMesh() {
		final List<Integer> indicies = new ArrayList<Integer>();
		for (int i = 0; i != faces.length; i++) {
			indicies.add(faces[i][0]);
			indicies.add(faces[i][1]);
			indicies.add(faces[i][2]);
		}
		final List<Map<String, Object>> att = new ArrayList<Map<String, Object>>();
		final HashMap<String, Object> hsh = new HashMap<String, Object>();
		final String key = "Text";
		final String val = "Bla";
		hsh.put(key, val);
		hsh.put(key, val);
		hsh.put(key, val);
		att.add(hsh);
		att.add(hsh);
		att.add(hsh);

		return new TriangleMesh<Float>(this.vectors, att, indicies);
	}
}
