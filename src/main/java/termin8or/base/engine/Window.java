package termin8or.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window
{
	public static void createWindow(int width, int height, String title)
	{
		try
		{
			System.out.println("Pre: Display.create method call, crash happens at point of creation");
			Display.create(); // pixel format error.
			System.out.println("Post: Display.create method call, if this shows it's good!");
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Keyboard.create();
			Mouse.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}

	public static void render()
	{
		Display.update();
	}

	public static void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}

	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}

	public static int getWidth()
	{
		return Display.getDisplayMode().getWidth();
	}

	public static int getHeight()
	{
		return Display.getDisplayMode().getHeight();
	}

	public static String getTitle()
	{
		return Display.getTitle();
	}
}