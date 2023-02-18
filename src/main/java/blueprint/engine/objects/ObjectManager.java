package blueprint.engine.objects;

import java.util.ArrayList;

public class ObjectManager {
    public static ArrayList<GameObject> objects;
    public static void init(){
        objects = new ArrayList<GameObject>();
    }
    public static void register(GameObject object){
        objects.add(object);
    }
    public static void remove(GameObject object){
        objects.remove(object);
    }
    public static void update(){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).update();
        }
    }
}
