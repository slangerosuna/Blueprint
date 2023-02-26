package blueprint.engine.objects;

import org.lwjgl.glfw.GLFW;

import blueprint.engine.io.Input;
import blueprint.engine.Math.Vector.Vector3;

public class Camera {
	public Vector3 position, rotation;
	private float moveSpeed = 0.05f, mouseSensitivity = 0.15f, distance = 2.0f, horizontalAngle = 0, verticalAngle = 0;
	private double oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

	public Camera(Vector3 position, Vector3 rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
	public void update() {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		float x = (float) Math.sin(Math.toRadians(rotation.y)) * moveSpeed;
		float z = (float) Math.cos(Math.toRadians(rotation.y)) * moveSpeed;
		
		if (Input.isKeyDown(GLFW.GLFW_KEY_A)) position = Vector3.add(position, new Vector3(-z, 0, x));
		if (Input.isKeyDown(GLFW.GLFW_KEY_D)) position = Vector3.add(position, new Vector3(z, 0, -x));
		if (Input.isKeyDown(GLFW.GLFW_KEY_W)) position = Vector3.add(position, new Vector3(-x, 0, -z));
		if (Input.isKeyDown(GLFW.GLFW_KEY_S)) position = Vector3.add(position, new Vector3(x, 0, z));
		if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) position = Vector3.add(position, new Vector3(0, moveSpeed, 0));
		if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) position = Vector3.add(position, new Vector3(0, -moveSpeed, 0));
		
		float dx = (float) (newMouseX - oldMouseX);
		float dy = (float) (newMouseY - oldMouseY);
		
		rotation = Vector3.add(rotation, new Vector3(-dy * mouseSensitivity, -dx * mouseSensitivity, 0));
		
		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
	}
	
	public void rotate(){
		rotation = new Vector3(0, (rotation.y + 2) % 360, 0);
		System.out.println(rotation.y);
	}

	public void update(GameObject object) {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		float dx = (float) (newMouseX - oldMouseX);
		float dy = (float) (newMouseY - oldMouseY);
		
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			verticalAngle -= dy * mouseSensitivity;
			horizontalAngle += dx * mouseSensitivity;
		}
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
			if (distance > 0) {
				distance += dy * mouseSensitivity / 4;
			} else {
				distance = 0.1f;
			}
		}
		
		float horizontalDistance = (float) (distance * Math.cos(Math.toRadians(verticalAngle)));
		float verticalDistance = (float) (distance * Math.sin(Math.toRadians(verticalAngle)));
		
		float xOffset = (float) (horizontalDistance * Math.sin(Math.toRadians(-horizontalAngle)));
		float zOffset = (float) (horizontalDistance * Math.cos(Math.toRadians(-horizontalAngle)));
		
		position.set(object.position.x + xOffset, object.position.y - verticalDistance, object.position.z + zOffset);
	    
		rotation.set(verticalAngle, -horizontalAngle, 0);
		
        //System.out.println(verticalAngle + "|" + horizontalAngle);

		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
	}
}
