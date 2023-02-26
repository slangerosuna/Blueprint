package blueprint.engine.objects;

public class GeneralBehaviour implements ObjectScript {
    GameObject parent;
    public void setParent(GameObject object) { parent = object; }
    public void start() { }
    public void update() { }
}
