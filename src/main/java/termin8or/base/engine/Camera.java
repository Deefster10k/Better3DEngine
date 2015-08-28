package termin8or.base.engine;

public class Camera
{
	public static final Vector3f yAxis = new Vector3f(0,1,0);
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	public Camera()
	{
		this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up)
	{
		this.pos = pos;
		this.forward = forward.normalise();
		this.up = up.normalise();
	}
	
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	
	public void input()
	{
		float sensitivity = 0.5f;
 		float movAmt = (float)(10 * Time.getDelta());
		
		if(Input.getKey(Input.KEY_ESCAPE))
		{
			Input.setCursor(true);
			mouseLocked = false;
		}
		
		if(Input.getMouseDown(0))
		{
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}
		
		if(Input.getKey(Input.KEY_W))
 			move(getForward(), movAmt);
 		if(Input.getKey(Input.KEY_S))
 			move(getForward(), -movAmt);
 		if(Input.getKey(Input.KEY_A))
 			move(getLeft(), movAmt);
 		if(Input.getKey(Input.KEY_D))
 			move(getRight(), movAmt);
 		
 		if(mouseLocked)
		{
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
			
			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;
			
			if(rotY)
				rotateY(deltaPos.getX() * sensitivity);
			if(rotX)
				rotateX(-deltaPos.getY() * sensitivity);			
				
			if(rotY || rotX)
				Input.setMousePosition(centerPosition);
		}
	}
	
	public void move(Vector3f dir, float amount)
	{
		pos = pos.add(dir.mul(amount));
	}
	
	public void rotateX(float angle)
	{
		Vector3f hAxis = yAxis.cross(forward).normalise();
		
		forward = forward.rotate(hAxis, angle).normalise();
		
		up = forward.cross(hAxis).normalise();
	}
	
	public void rotateY(float angle)
	{
		Vector3f hAxis = yAxis.cross(forward).normalise();
		
		forward = forward.rotate(yAxis, angle).normalise();
		
		up = forward.cross(hAxis).normalise();
	}

	public Vector3f getPos()
	{
		return pos;
	}

	public void setPos(Vector3f pos)
	{
		this.pos = pos;
	}

	public Vector3f getForward()
	{
		return forward;
	}

	public void setForward(Vector3f forward)
	{
		this.forward = forward;
	}

	public Vector3f getUp()
	{
		return up;
	}

	public void setUp(Vector3f up)
	{
		this.up = up;
	}
	
	public Vector3f getLeft()
	{
		Vector3f left = forward.cross(up).normalise();
		return left;
	}
	
	public Vector3f getRight()
	{
		Vector3f right = up.cross(forward).normalise();
		return right;
	}
}
