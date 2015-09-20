package termin8or.base.engine.core;

public abstract class Game
{
	public GameObject root;
	
	public void init() {}
	
	public void input(float delta)
	{
		getRootObject().input(delta);
	}
	
	public void update(float delta)
	{
		getRootObject().update(delta);
	}
	
	public GameObject getRootObject()
	{
		if (root == null)
			root = new GameObject();
		
		return root;
	}
}