package termin8or.base.game;

import termin8or.base.engine.components.MeshRenderer;
import termin8or.base.engine.core.Game;
import termin8or.base.engine.core.GameObject;
import termin8or.base.engine.core.Vector2f;
import termin8or.base.engine.core.Vector3f;
import termin8or.base.engine.rendering.Camera;
import termin8or.base.engine.rendering.Material;
import termin8or.base.engine.rendering.Mesh;
import termin8or.base.engine.rendering.Texture;
import termin8or.base.engine.rendering.Vertex;

public class TestGame extends Game
{
	private Camera camera;

	public void init()
	{
		// @formatter:off
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
											new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indices[] = { 0, 1, 2,
					      2, 1, 3};
		// @formatter:on

		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().setPos(0, -1, 5);

		getRootObject().addChild(planeObject);
	}
}
