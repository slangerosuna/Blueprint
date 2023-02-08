package blueprint.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import blueprint.engine.Math.Matrix.Matrix4;
import blueprint.engine.io.Window;
import blueprint.engine.objects.Camera;
import blueprint.engine.objects.GameObject;

public class Renderer {
  private Shader shader;
  private Window window;
  private Shader bound;

  public Renderer(Window window, Shader shader) {
    this.shader = shader;
    this.window = window;
    bound = null;
  }

  public void renderObject(GameObject object, Camera camera) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0);
    GL13.glBindTexture(GL11.GL_TEXTURE_2D, object.mesh.getTextureID());

    GL30.glBindVertexArray(object.mesh.getVAO());

    if (bound != shader) {
      if (bound != null) {
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glDisableVertexAttribArray(3);
        
        shader.unBind();
      }
      shader.bind();
      bound = shader;

      object.mesh.bindVertexAttributes();

      GL30.glEnableVertexAttribArray(0);
      GL30.glEnableVertexAttribArray(1);
      GL30.glEnableVertexAttribArray(2);
      GL30.glEnableVertexAttribArray(3);
    } else {
      object.mesh.bindVertexAttributes();
    }

    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.mesh.getIBO());
    GL13.glActiveTexture(GL13.GL_TEXTURE0);
    GL13.glBindTexture(GL11.GL_TEXTURE_2D, object.mesh.getTextureID());
    shader.setUniform("model", Matrix4.transform(object.position, object.rotation, object.scale));
    shader.setUniform("view", Matrix4.view(camera.position, camera.rotation));
    shader.setUniform("projection", window.getProjectionMatrix());

    System.out.println("Model:");
    Matrix4.transform(object.position, object.rotation, object.scale).print();
    System.out.println("\n-------------------------\n");

    System.out.println("View:");
    Matrix4.view(camera.position, camera.rotation).print();
    System.out.println("\n-------------------------\n");

    System.out.println("Projection:");
    window.getProjectionMatrix().print();
    System.out.println("\n-------------------------\n");

    GL11.glDrawElements(GL11.GL_TRIANGLES, object.mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);

    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

    GL30.glBindVertexArray(0);
  }
}