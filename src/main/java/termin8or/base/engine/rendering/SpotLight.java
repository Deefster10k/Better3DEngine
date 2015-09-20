package termin8or.base.engine.rendering;

import termin8or.base.engine.core.Vector3f;

public class SpotLight
{
	private PointLight pointLight;
	private Vector3f direction;
	private float cutoff;

	public SpotLight(PointLight pointLight, Vector3f direction, float cutoff)
	{
		this.pointLight = pointLight;
		this.direction = direction.normalise();
		this.cutoff = cutoff;
	}

	public PointLight getPointLight()
	{
		return pointLight;
	}

	public void setPointLight(PointLight pointLight)
	{
		this.pointLight = pointLight;
	}

	public Vector3f getDirection()
	{
		return direction;
	}

	public void setDirection(Vector3f direction)
	{
		this.direction = direction.normalise();
	}

	public float getCutoff()
	{
		return cutoff;
	}

	public void setCutoff(float cutoff)
	{
		this.cutoff = cutoff;
	}

}
