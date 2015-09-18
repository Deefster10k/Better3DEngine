package termin8or.base.engine;

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
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
	{
		if(material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		
		setUniform("colour", material.getColour());
		setUniform("transform", projectedMatrix);
	}
}