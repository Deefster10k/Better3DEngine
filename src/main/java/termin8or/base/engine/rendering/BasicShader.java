package termin8or.base.engine.rendering;

import termin8or.base.engine.core.Matrix4f;
import termin8or.base.engine.core.Transform;

public class BasicShader extends Shader
{
	private static final BasicShader instance = new BasicShader();
	
	public static BasicShader getInstance()
	{
		return instance;
	}
	
	public BasicShader()
	{
		super();

		addVertexShaderFromFile("basic.vs");
		addFragmentShaderFromFile("basic.fs");
		compileShader();

		addUniform("colour");
		addUniform("transform");
	}

	@Override
	public void updateUniforms(Transform transform, Material material)
	{
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
		material.getTexture().bind();
		
		setUniform("colour", material.getColour());
		setUniform("transform", projectedMatrix);
	}
}