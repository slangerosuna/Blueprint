package blueprint;

import org.lwjgl.glfw.GLFW;

import blueprint.engine.Math.Vector.*;
import blueprint.engine.graphics.Mesh;
import blueprint.engine.graphics.Renderer;
import blueprint.engine.graphics.Shader;
import blueprint.engine.io.Input;
import blueprint.engine.io.ModelLoader;
import blueprint.engine.io.Window;
import blueprint.engine.objects.Camera;
import blueprint.engine.objects.GameObject;
import blueprint.engine.objects.ObjectManager;

public class App implements Runnable {
	public Shader shader;
	public static Renderer renderer;
	public static Mesh mesh;
	public Thread game;
	public Window window;
	public final int WIDTH = 1280, HEIGHT = 760;
	public static GameObject object;
	public static Camera camera = new Camera(new Vector3(0, 0, 10), new Vector3(0, 0, 0));
	
	public void start() {	
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		ObjectManager.init();
		
		window = new Window(WIDTH, HEIGHT, "Game");
		window.setBackgroundColor(0.05f, 0.045f, 0.06f);
		
		shader = new Shader("/blueprint/shaders/MainVertex.glsl", "/blueprint/shaders/MainFrag.glsl");
		renderer = new Renderer(window, shader);
		
		window.create();
		
		mesh = ModelLoader.loadModel("C:/Users/Ball Fondlers/VSCode/Blueprint/Blueprint/src/main/java/blueprint/resources/cube.obj", "/blueprint/resources/randomAsset.png");
		
		mesh.create();
		
		object = new GameObject(new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(1, 1, 1));
		object.AddScript(mesh);

		shader.create();
	}
	
	public void run() {	
		init();
		while (!window.shouldClose()) {
			update();
			window.swapBuffers();

			if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());;
			if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) break;
		}
		close();
	}
	public static void renderObject(GameObject object, Mesh mesh, Camera camera){
		renderer.renderObject(object, mesh, camera);
	}
	private void close() {		
		mesh.destroy();
		window.destroy();
		shader.destroy();
	}
	
	
	private void update() {
		camera.update();

		window.update();
		ObjectManager.update();
	}
	
	
	public static void main(String[] args) {
		new App().start();
	}
}
