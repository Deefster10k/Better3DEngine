package termin8or.base.engine;

public class Vector2f
{
	private float x;
	private float y;
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vector2f v)
	{
		return x * v.getX() + y * v.getY();
	}
	
	public Vector2f normalise()
	{
		float length = length();
		
		return new Vector2f(x / length, y / length);
	}
	
	public Vector2f rotate(float angle)
	{
		double rad = Math.toRadians(angle);
 		double cos = Math.cos(rad);
 		double sin = Math.sin(rad);
 		
 		return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
	}
	
	public Vector2f add(Vector2f v)
	{
		return new Vector2f(x + v.getX(), y + v.getY());
	}
	
	public Vector2f add(float f)
	{
		return new Vector2f(x + f, y + f);
	}
	
	public Vector2f sub(Vector2f v)
	{
		return new Vector2f(x - v.getX(), y - v.getY());
	}
	
	public Vector2f sub(float f)
	{
		return new Vector2f(x - f, y - f);
	}
	
	public Vector2f mul(Vector2f v)
	{
		return new Vector2f(x * v.getX(), y * v.getY());
	}
	
	public Vector2f mul(float f)
	{
		return new Vector2f(x * f, y * f);
	}
	
	public Vector2f div(Vector2f v)
	{
		return new Vector2f(x / v.getX(), y / v.getY());
	}
	
	public Vector2f div(float f)
	{
		return new Vector2f(x / f, y / f);
	}
	
	public Vector2f abs()
	{
		return new Vector2f(Math.abs(x), Math.abs(y));
	}
	
	public String toString()
	{
		return "(" + x + " " + y + ")";
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
}
