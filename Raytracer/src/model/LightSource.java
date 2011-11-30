package model;

import vecmath.Color;
import vecmath.Vector;

public class LightSource {
	private Vector position;
	private Color color;

	public LightSource(Vector position, Color color) {
		this.position = position;
		this.color = color;
	}

	public Vector getOrigin() {
		return position;
	}

	public Color getColor() {
		return color;
	}

}
