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
import blueprint.engine.objects.colliders.ConcaveCollider;
import blueprint.engine.objects.colliders.*;

public class App implements Runnable {
	public Shader shader;
	public Renderer renderer;
	public Mesh mesh;
	public Thread game;
	public Window window;
	public final int WIDTH = 1280, HEIGHT = 760;
	public GameObject object;
	public Camera camera = new Camera(new Vector3(0, 0, 10), new Vector3(0, 180, 0));
	
	public void start() {	
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		window = new Window(WIDTH, HEIGHT, "Game");
		window.setBackgroundColor(0.05f, 0.045f, 0.06f);
		
		shader = new Shader("/blueprint/shaders/MainVertex.glsl", "/blueprint/shaders/MainFrag.glsl");
		renderer = new Renderer(window, shader);
		load();
		
		window.create();
		
		mesh = ModelLoader.loadModel("C:/Users/Ball Fondlers/VSCode/Blueprint/Blueprint/src/main/java/blueprint/resources/cube.obj", "/blueprint/resources/randomAsset.png");
		
		mesh.create();
		
		object = new GameObject(mesh, new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(1, 1, 1));
		
		shader.create();

		System.out.println(object.<ConcaveCollider>GetScript(ConcaveCollider.class) == null);
	}
	
	public void run() {	
		init();
		while (!window.shouldClose()) {
			update();
			render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());;
		}
		close();
	}
	
	private void close() {
		save();
		
		mesh.destroy();
		window.destroy();
		shader.destroy();
	}
	
	private void save() {
		
	}
	
	private void load() {
		
	}
	
	private void update() {
		window.update();
		//if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
		camera.update();
		//object.update();
	}
	
	private void render() {
		renderer.renderObject(object, camera);
		window.swapBuffers();
	}
	
	public static void main(String[] args) {
		new App().start();
	}
}
