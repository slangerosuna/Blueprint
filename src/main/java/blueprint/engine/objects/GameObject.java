package blueprint.engine.objects;

import blueprint.engine.Math.Vector.Vector3;
import blueprint.engine.graphics.Mesh;

public class GameObject {
	public Vector3 position, rotation, scale;
	public Mesh mesh;
	private double temp;
	
	public GameObject(Mesh mesh, Vector3 position, Vector3 rotation, Vector3 scale) {
		this.mesh = mesh;
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
	}
	
	public void update() {
		temp += 0.02;
		position.x = (float) Math.sin(temp);
		rotation.set((float) Math.sin(temp) * 360, (float) Math.sin(temp) * 360, (float) Math.sin(temp) * 360);
		scale.set((float) Math.sin(temp), (float) Math.sin(temp), (float) Math.sin(temp));
	}
}
