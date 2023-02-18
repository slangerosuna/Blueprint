package blueprint.engine.objects.colliders;

import blueprint.engine.objects.GameObject;
import blueprint.engine.objects.ObjectScript;

public class ConcaveCollider implements Collider, ObjectScript{
    GameObject parent;
    public void setParent(GameObject object){
        parent = object;
    }
    public void start(){

    }
    public void update(){
        
    }
    
}
