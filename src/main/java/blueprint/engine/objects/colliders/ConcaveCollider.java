package blueprint.engine.objects.colliders;

import blueprint.engine.Math.Matrix.Matrix4;
import blueprint.engine.Math.Vector.Vector3;
import blueprint.engine.objects.GameObject;
import blueprint.engine.objects.ObjectScript;

public class ConcaveCollider implements Collider, ObjectScript{
    Vector3[] vertices;
    int[] indices;
    Matrix4 prevRot;

    GameObject parent;
    public void setParent(GameObject object){
        parent = object;
    }
    public void start(){

    }
    public void update(){
        
    }
    public void setMesh(Vector3[] vertices, int[] indices){
        this.vertices = vertices;
        this.indices = indices;
    }
}
