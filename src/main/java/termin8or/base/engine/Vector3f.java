package termin8or.base.engine;

public class Vector3f
{
	private float x;
	private float y;
	private float z;
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	public float dot(Vector3f v)
	{
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public Vector3f cross(Vector3f v)
	{
		float a = y * v.getZ() - z * v.getY();
		float b = z * v.getX() - x * v.getZ();
		float c = x * v.getY() - y * v.getX();
		
		return new Vector3f(a, b, c);
	}
	
	public Vector3f normalise()
	{
		float length = length();
		
		return new Vector3f(x / length, y / length, z / length);
	}
	
	public Vector3f rotate(Vector3f axis, float angle)
	{
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
		
		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();
		
		Quaternion w = rotation.mul(this).mul(conjugate);
		
		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}
	
	public Vector3f add(Vector3f v)
	{
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	public Vector3f add(float f)
	{
		return new Vector3f(x + f, y + f, z + f);
	}
	
	public Vector3f sub(Vector3f v)
	{
		return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}
	
	public Vector3f sub(float f)
	{
		return new Vector3f(x - f, y - f, z - f);
	}
	
	public Vector3f mul(Vector3f v)
	{
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	
	public Vector3f mul(float f)
	{
		return new Vector3f(x * f, y * f, z * f);
	}
	
	public Vector3f div(Vector3f v)
	{
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}
	
	public Vector3f div(float f)
	{
		return new Vector3f(x / f, y / f, z / f);
	}
	
	public Vector3f abs()
	{
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public String toString()
	{
		return "(" + x + " " + y + " " + z + ")";
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

	public float getZ()
	{
		return z;
	}

	public void setZ(float z)
	{
		this.z = z;
	}
}
