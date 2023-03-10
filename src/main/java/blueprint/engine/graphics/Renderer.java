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

  public void renderObject(GameObject object, Mesh mesh, Camera camera) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0);
    GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getTextureID());

    GL30.glBindVertexArray(mesh.getVAO());

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

      mesh.bindVertexAttributes();

      GL30.glEnableVertexAttribArray(0);
      GL30.glEnableVertexAttribArray(1);
      GL30.glEnableVertexAttribArray(2);
      GL30.glEnableVertexAttribArray(3);
    } else {
      mesh.bindVertexAttributes();
    }

    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());

    GL13.glActiveTexture(GL13.GL_TEXTURE0);
    GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getTextureID());
    
    shader.setUniform("model", Matrix4.transform(object.position, object.rotation, object.scale), true);
    shader.setUniform("view", Matrix4.view(camera.position, camera.rotation), true);
    shader.setUniform("projection", window.getProjectionMatrix(), true);

    GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);

    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

    GL30.glBindVertexArray(0);
  }
}