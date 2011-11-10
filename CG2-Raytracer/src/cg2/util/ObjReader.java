package cg2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import math3D.ColumnVectorFloat;
import de.bht.fb6.cg1.math.IColumnVector;


public class ObjReader {
	/**
	 * Liest eine Obj Datei
	 * 
	 * @param path
	 *            Der Pfad zum Objekt.
	 * @return ObjData
	 */
	public static ObjData Read(final String path) {
		return Read(new File(path));
	}

	/**
	 * Liest eine Obj Datei
	 * 
	 * @param file
	 *            Das File Objekt zur Datei
	 * @return ObjData
	 */
	public static ObjData Read(final File file) {
		int lineNumber = -1;
		String line = "<KEINE>";
		final ArrayList<IColumnVector<Float>> vertices = new ArrayList<IColumnVector<Float>>();
		final ArrayList<IColumnVector<Float>> normals = new ArrayList<IColumnVector<Float>>();
		final ArrayList<Integer[]> faces = new ArrayList<Integer[]>();

		try {
			final FileReader fs = new FileReader(file);
			final BufferedReader bis = new BufferedReader(fs);

			lineNumber = 1;

			while ((line = bis.readLine()) != null) {
				if (line.startsWith("v "))
					addVector(line, vertices);
				else if (line.startsWith("vn "))
					addVector(line, normals);
				else if (line.startsWith("f "))
					addFace(line, faces);

				lineNumber++;
			}
		} catch (final Exception e) {
			final String message = "Fehler in Datei " + file + " in der " + lineNumber + " Zeile. (" + line + ")";
			throw new RuntimeException(message, e);
		}

		return new ObjData(vertices, normals, faces);
	}

	/**
	 * Fügt eine Fläche der Liste Hinzu
	 * 
	 * @param line
	 *            Die Info Zeile aus der Obj Datei.
	 * @param faces
	 *            Flächen
	 */
	private static void addFace(final String line, final List<Integer[]> faces) {
		final String[] data = line.split("\\s+");

		if (data.length < 4)
			throw new RuntimeException("Zeile hat nicht das richtige Format");

		final Integer[] ints = new Integer[data.length - 1];

		for (int i = 1; i < data.length; i++) {
			final String normal = data[i].split("//")[0];
			ints[i - 1] = Integer.parseInt(normal) - 1;
		}

		faces.add(ints);
	}

	/**
	 * Fügt einem Vektor der Liste hinzu.
	 * 
	 * @param line
	 *            Die Info Zeile aus der Obj Datei.
	 * @param vecs
	 *            Die Liste von Vektoren
	 */
	private static void addVector(final String line, final List<IColumnVector<Float>> vecs) {
		final String[] data = line.split("\\s+");

		if (data.length != 4 && data.length != 5)
			throw new RuntimeException("Zeile hat nicht das richtige Format");

		final float x = Float.parseFloat(data[1]);
		final float y = Float.parseFloat(data[2]);
		final float z = Float.parseFloat(data[3]);
		final float w = data.length == 4 ? 1f : Float.parseFloat(data[4]);

		final ColumnVectorFloat v = new ColumnVectorFloat(x, y, z, w);

		vecs.add(v);
	}
}


