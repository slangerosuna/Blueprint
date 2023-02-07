package blueprint.engine.Math.Vector;

public class Vector2 {
	public float x, y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2 vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	static public Vector2 zero() {
		return new Vector2(0, 0);
	}
}
