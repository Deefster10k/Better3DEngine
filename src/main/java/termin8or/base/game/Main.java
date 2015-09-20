package termin8or.base.game;

import termin8or.base.engine.core.CoreEngine;

public class Main
{
	public static void main(String[] args)
	{
		CoreEngine engine = new CoreEngine(800, 600, 200, new TestGame());
		engine.createWindow("Test Engine");
		engine.start();
	}
}
