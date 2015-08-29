package termin8or.base.engine;

public class PhongShader extends Shader
{
	private static final int MAX_POINT_LIGHTS = 4;
	
	private static final PhongShader instance = new PhongShader();

	public PhongShader getInstance()
	{
		return instance;
	}
	
	private static Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
	private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(0,0,0), 0), new Vector3f(0,0,0));
	private static PointLight[] pointLights = new PointLight[] {};
	
	public PhongShader()
	{
		super();

		addVertexShader(ResourceLoader.loadShader("phong.vs"));
		addFragmentShader(ResourceLoader.loadShader("phong.fs"));
		compileShader();

		addUniform("transform");
		addUniform("transformProjected");
		addUniform("baseColour");
		addUniform("ambientLight");
		
		addUniform("specularIntensity");
		addUniform("specularExponent");
		addUniform("eyePos");
		
		
		addUniform("directionalLight.base.colour");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");
		
		for(int i = 0; i < MAX_POINT_LIGHTS; i++)
		{
			addUniform("pointLights[" + i + "].base.colour");
			addUniform("pointLights[" + i + "].base.intensity");
			addUniform("pointLights[" + i + "].atten.constant");
			addUniform("pointLights[" + i + "].atten.linear");
			addUniform("pointLights[" + i + "].atten.exponent");
			addUniform("pointLights[" + i + "].position");
		}
	}

	@Override
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
	{
		if (material.getTexture() != null) 
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();

		setUniform("transform", worldMatrix);
		setUniform("transformProjected", projectedMatrix);
		setUniform("baseColour", material.getColour());

		setUniform("ambientLight", ambientLight);
		setUniform("directionalLight", directionalLight);
		for(int i = 0; i < pointLights.length; i++)
			setUniform("pointLights[" + i + "]", pointLights[i]);
		
		setUniformf("specularIntensity", material.getSpecularIntensity());
		setUniformf("specularExponent", material.getSpecularExponent());
		
		setUniform("eyePos", Transform.getCamera().getPos());
	}

	public static Vector3f getAmbientLight()
	{
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight)
	{
		PhongShader.ambientLight = ambientLight;
	}
	
	public static void setDirectionalLight(DirectionalLight directionalLight)
	{
		PhongShader.directionalLight = directionalLight;
	}
	
	public static void setPointLights(PointLight[] pointLights)
	{
		if(pointLights.length > MAX_POINT_LIGHTS)
		{
			System.err.println("You passed in too many point lights. Max allowed is " + MAX_POINT_LIGHTS + ", you passed in " + pointLights.length);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		PhongShader.pointLights = pointLights;
	}
	
	public void setUniform(String uniformName, BaseLight baseLight)
	{
		setUniform(uniformName + ".colour", baseLight.getColor());
		setUniformf(uniformName + ".intensity", baseLight.getIntensity());
	}
	
	public void setUniform(String uniformName, DirectionalLight directionalLight)
	{
		setUniform(uniformName + ".base", directionalLight.getBase());
		setUniform(uniformName + ".direction", directionalLight.getDirection());
	}
	
	public void setUniform(String uniformName, PointLight pointLight)
	{
		setUniform(uniformName + ".base", pointLight.getBaseLight());
		setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getConstant());
		setUniformf(uniformName + ".atten.linear", pointLight.getAtten().getLinear());
		setUniformf(uniformName + ".atten.exponent", pointLight.getAtten().getExponent());
		setUniform(uniformName + ".position", pointLight.getPosition());
	}
}
