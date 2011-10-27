package cg2.raytracer;

import cg2.vecmath.Vector;

public class Camera {
  private Vector position;
  private Vector gazeDirection;
  private float oeffnungswinkel;

  public Camera(Vector position, Vector gazeDirection, float oeffnungswinkel) {
    super();
    this.position = position;
    this.gazeDirection = gazeDirection;
    if (oeffnungswinkel <= 0.0f || oeffnungswinkel >= 180.0f) {
      throw new IllegalArgumentException("oeffnungswinkel falsch");
    }
    this.oeffnungswinkel = oeffnungswinkel;
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Vector getGazeDirection() {
    return gazeDirection;
  }

  public void setGazeDirection(Vector gazeDirection) {
    this.gazeDirection = gazeDirection;
  }

  public float getOeffnungswinkel() {
    return oeffnungswinkel;
  }

  public void setOeffnungswinkel(float oeffnungswinkel) {
    this.oeffnungswinkel = oeffnungswinkel;
  }

}
