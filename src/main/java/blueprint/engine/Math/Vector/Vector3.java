package blueprint.engine.Math.Vector;

public class Vector3 {
	public float x, y, z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Vector3 other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}
	
	public void set(Vector3 vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	static public Vector3 zero() {
		return new Vector3(0, 0, 0);
	}

	public static Vector3 add(Vector3 a, Vector3 b){
		return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
	}
}
