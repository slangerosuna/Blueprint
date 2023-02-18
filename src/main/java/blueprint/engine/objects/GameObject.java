package blueprint.engine.objects;

import java.util.ArrayList;

import blueprint.engine.Math.Vector.Vector3;

public class GameObject {
	public Vector3 position, rotation, scale;
	public ArrayList<ObjectScript> objectScripts;
	
	public GameObject(Vector3 position, Vector3 rotation, Vector3 scale) {
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
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
}
