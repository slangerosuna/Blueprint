package blueprint.engine.objects;

import java.lang.reflect.Type;
import java.util.ArrayList;

import blueprint.engine.Math.Vector.Vector3;
import blueprint.engine.graphics.Mesh;

public class GameObject {
	public Vector3 position, rotation, scale;
	public Mesh mesh;
	private double temp;
	public ArrayList<ObjectScript> objectScripts;
	
	public GameObject(Mesh mesh, Vector3 position, Vector3 rotation, Vector3 scale) {
		this.mesh = mesh;
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
		objectScripts = new ArrayList<ObjectScript>();
	}
	
	public void update() {
		for(int i = 0; i < objectScripts.size(); i++){
			objectScripts.get(i).update();
		}
	}

	public void AddScript(ObjectScript script){
		objectScripts.add(script);
		script.start();
	}
	public <T extends ObjectScript> T GetScript(Class<T> T){
		for(int i = 0; i < objectScripts.size(); i++){
			if(objectScripts.get(i).getClass() == T){
				return (T) objectScripts.get(i);
			}
		}
		return null;
	}
}
