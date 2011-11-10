package cg2.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import math3D.ColumnVectorFloat;
import de.bht.fb6.cg1.math.IColumnVector;

public class ObjLoader {
	public static ObjData Load(final String path) {
		FileInputStream fstream;

		final ArrayList<IColumnVector<Float>> vertex = new ArrayList<IColumnVector<Float>>();
		final ArrayList<Integer[]> faces = new ArrayList<Integer[]>();
		final ArrayList<IColumnVector<Float>> normals = new ArrayList<IColumnVector<Float>>();
		int line = 1;

		try {
			fstream = new FileInputStream(path);
			final DataInputStream in = new DataInputStream(fstream);
			final BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				final String[] data = strLine.split(" ");
				if (data.length != 0) {
					if (data[0].equals("v")) {
						addVector(data, vertex);
					} else if (data[0].equals("f")) {
						addFace(data, faces);
					} else if (data[0].equals("vn")) {
						addVector(data, normals);
					}
				}
				line++;
			}
			in.close();
		} catch (final Exception e) {
			throw new RuntimeException("Fehler in Datei " + path + " in Zeile " + line + ".", e);
		}

		return new ObjData(vertex, normals, faces);
	}

	private static void addFace(final String[] data, final ArrayList<Integer[]> faces) {
		if (data.length != 2)
			throw new RuntimeException("Datei hat falsches Format");

		final Integer[] f = new Integer[3];
		f[0] = Integer.parseInt(data[1]);
		f[1] = Integer.parseInt(data[2]);
		f[2] = Integer.parseInt(data[3]);

		faces.add(f);
	}

	private static void addVector(final String[] data, final ArrayList<IColumnVector<Float>> vert) {
		if (data.length != 4 && data.length != 5)
			throw new RuntimeException("Datei hat falsches Format");

		final float x = Float.parseFloat(data[1]);
		final float y = Float.parseFloat(data[2]);
		final float z = Float.parseFloat(data[3]);
		final float w = data.length == 5 ? Float.parseFloat(data[4]) : 1f;

		final ColumnVectorFloat v = new ColumnVectorFloat(x, y, z, w);

		vert.add(v);
	}
}

