package blueprint.engine.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import blueprint.engine.Math.Matrix.Matrix4;
import blueprint.engine.Math.Vector.Vector2;
import blueprint.engine.Math.Vector.Vector3;
import blueprint.engine.UTILS.FileUtils;

public class Shader {
	private String vertexFile, fragmentFile;
	private int vertexID, fragmentID, programID;
	
	public Shader(String vertexPath, String fragmentPath) {
		vertexFile = FileUtils.loadAsString(vertexPath);
		fragmentFile = FileUtils.loadAsString(fragmentPath);
	}
	
	public void create() {
		programID = GL20.glCreateProgram();
		vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		
		GL20.glShaderSource(vertexID, vertexFile);
		GL20.glCompileShader(vertexID);
		
		if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID));
			return;
		}
		
		fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		
		GL20.glShaderSource(fragmentID, fragmentFile);
		GL20.glCompileShader(fragmentID);
		
		if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID));
			return;
		}
		
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		
		GL20.glLinkProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			System.err.println("Program Linking: " + GL20.glGetProgramInfoLog(programID));
			return;
		}
		
		GL20.glValidateProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Program Validation: " + GL20.glGetProgramInfoLog(programID));
			return;
		}
	}
	
	public int getUniformLocation(String name) {
		return GL20.glGetUniformLocation(programID, name);
	}
	
	public void setUniform(String name, float value) {
		GL20.glUniform1f(getUniformLocation(name), value);
	}
	
	public void setUniform(String name, int value) {
		GL20.glUniform1i(getUniformLocation(name), value);
	}
	
	public void setUniform(String name, boolean value) {
		GL20.glUniform1i(getUniformLocation(name), value ? 1 : 0);
	}
	
	public void setUniform(String name, Vector2 value) {
		GL20.glUniform2f(getUniformLocation(name), value.x, value.y);
	}
	
	public void setUniform(String name, Vector3 value) {
		GL20.glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
	}
	
	public void setUniform(String name, Matrix4 value, Boolean transpose) {
		FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4.SIZE * Matrix4.SIZE);
		matrix.put(value.getAll()).flip();
		GL20.glUniformMatrix4fv(getUniformLocation(name), transpose, matrix);
	}
	
	public void bind() {
		GL20.glUseProgram(programID);
	}
	
	public void unBind() {
		GL20.glUseProgram(0);
	}
	
	public void destroy() {
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDetachShader(programID, vertexID);
		GL20.glDeleteProgram(programID);
		
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
	}
}