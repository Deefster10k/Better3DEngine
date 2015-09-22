package termin8or.base.engine.components;

import termin8or.base.engine.core.Transform;
import termin8or.base.engine.rendering.Shader;

public abstract class GameComponent
{
	public void input(Transform transform, float delta) {};	
	public void update(Transform transform, float delta) {};	
	public void render(Transform transform, Shader shader) {};
}
