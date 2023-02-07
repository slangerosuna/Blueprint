package blueprint.engine.graphics;

import blueprint.engine.Math.Vector.*;

public class Vertex {
	public Vector3 position;
	public Vector3 normal;
	public Vector2 UV;
	public Vector4 rgba;
	
	public Vertex(Vector3 position, Vector3 normal, Vector2 UV) {
		this.position = position;
		this.UV = UV;
		this.normal = normal;
		this.rgba = new Vector4(1, 1, 1, 1);
	}
	
	public Vertex(Vector3 position, Vector2 UV, Vector3 rgb) {
		this.position = position;
		this.UV = UV;
		this.normal = new Vector3(0, 0, 1);
		this.rgba = new Vector4(rgb.x, rgb.y, rgb.z, 1);
	}
}
