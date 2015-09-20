package termin8or.base.engine.core;

public class Quaternion
{
	private float x;
	private float y;
	private float z;
	private float w;

	public Quaternion(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public float length()
	{
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public Quaternion nomalise()
	{
		float length = length();
		
		return new Quaternion(x / length, y / length, z / length, w / length);
	}
	
	public Quaternion conjugate()
	{
		return new Quaternion(-x, -y, -z, w);
	}
	
	public Quaternion mul(Quaternion q)
	{
		float a = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();
		float b = x * q.getW() + w * q.getX() + y * q.getY() - z * q.getY();
		float c = y * q.getW() + w * q.getY() + z * q.getX() - x * q.getZ();
		float d = z * q.getW() + w * q.getZ() + x * q.getY() - y * q.getX();
		
		return new Quaternion(b, c, d, a);
	}
	
	public Quaternion mul(Vector3f v)
	{
		float a = -x * v.getX() - y * v.getY() - z * v.getZ();
		float b =  w * v.getX() + y * v.getZ() - z * v.getY();
		float c =  w * v.getY() + z * v.getX() - x * v.getZ();
		float d =  w * v.getZ() + x * v.getY() - y * v.getX();
		
		return new Quaternion(b, c, d, a);
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

	public float getW()
	{
		return w;
	}

	public void setW(float w)
	{
		this.w = w;
	}
}
