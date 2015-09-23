package termin8or.base.engine.components;

import termin8or.base.engine.core.Transform;
import termin8or.base.engine.rendering.Material;
import termin8or.base.engine.rendering.Mesh;
import termin8or.base.engine.rendering.Shader;

public class MeshRenderer extends GameComponent
{
	private Mesh mesh;
	private Material material;

	public MeshRenderer(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}

	@Override
	public void render(Transform transform, Shader shader)
	{
		shader.bind();
		shader.updateUniforms(transform, material);
		mesh.draw();
	}
}
