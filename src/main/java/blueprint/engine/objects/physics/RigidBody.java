package blueprint.engine.objects.physics;

import blueprint.engine.objects.GameObject;
import blueprint.engine.objects.ObjectScript;
import blueprint.engine.objects.colliders.Collider;

public class RigidBody implements ObjectScript {
    
    Collider coll;
    public void start(){

    }
    public void update(){

    }
    GameObject parent;
    public void setParent(GameObject object){
        parent = object;
    }
}
