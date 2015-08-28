package termin8or.base.engine;

public class Game
{
	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;
	
	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f));
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));
	
	public Game()
	{
//		mesh = ResourceLoader.loadMesh("cube.obj");
		mesh = new Mesh();
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1,1,1), 1, 8);
		shader = new PhongShader().getInstance();
		camera = new Camera();
		transform = new Transform();
		
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
						
		mesh.addVertices(vertices, indices, true);
		
		transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		transform.setCamera(camera);
		
		PhongShader.setAmbientLight(new Vector3f(0.2f,0.2f,0.2f));
//		PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
	
		PhongShader.setPointLights(new PointLight[]{pLight1, pLight2});
	}

	public void input()
	{
		camera.input();
	}
	
	float temp = 0.0f;

	public void update()
	{
		temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		transform.setTranslation(0, -1, 5);
//		transform.setRotation(0, sinTemp * 180, 0);
		
		pLight1.setPosition(new Vector3f(3,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
		pLight2.setPosition(new Vector3f(7,0,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));
		
//		transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
	}

	public void render()
	{
		RenderUtil.setClearColour(Transform.getCamera().getPos().div(512f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}