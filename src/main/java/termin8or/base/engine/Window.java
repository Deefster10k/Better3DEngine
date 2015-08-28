package termin8or.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window
{
	public static void createWindow(int width, int height, String title)
	{
		PixelFormat pixelFormat = new PixelFormat();
        ContextAttribs contextAtrributes = new ContextAttribs(3, 1).withForwardCompatible(true);
		
		try
		{
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle(title);
			System.out.println("Pre: Display.create method call, crash happens at point of creation");
			Display.create(pixelFormat, contextAtrributes); // pixel format error.
			System.out.println("Post: Display.create method call, if this shows it's good!");
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