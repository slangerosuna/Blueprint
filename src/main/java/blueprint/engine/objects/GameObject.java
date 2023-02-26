package blueprint.engine.objects;

import java.util.ArrayList;

import blueprint.engine.Math.Matrix.Matrix4;
import blueprint.engine.Math.Vector.Vector3;

public class GameObject {
	public Vector3 position, scale;
	public Matrix4 rotation;
	public ArrayList<ObjectScript> objectScripts;
	
	public GameObject(Vector3 position, Vector3 rotation, Vector3 scale) {
		this.position = position;
		this.scale = scale;
		this.rotation = Matrix4.rotation(rotation);
		objectScripts = new ArrayList<ObjectScript>();
		ObjectManager.register(this);
	}
	
	public void update() {
		for(int i = 0; i < objectScripts.size(); i++){
			objectScripts.get(i).update();
		}
	}

	public void AddScript(ObjectScript script){
		objectScripts.add(script);
		script.start();
		script.setParent(this);
	}
	public <T extends ObjectScript> T GetScript(Class<T> T){
		for(int i = 0; i < objectScripts.size(); i++){
			if(objectScripts.get(i).getClass() == T){
				return (T) objectScripts.get(i);
			}
		}
		return null;
	}
	public void destroy(){
		ObjectManager.remove(this);
	}
	public void rotateObjectiveAxis(float angle, Vector3 axis){
		Vector3 relativeAxis = rotation.getRelativeAxis(axis);
		rotateRelativeAxis(angle, relativeAxis);
	}
	public void rotateRelativeAxis(float angle, Vector3 axis){
		rotation = Matrix4.Multiply(rotation, Matrix4.rotate(angle, axis));
	}
}
