package termin8or.base.engine.rendering;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import com.google.common.collect.Maps;

import termin8or.base.engine.core.Matrix4f;
import termin8or.base.engine.core.RenderingEngine;
import termin8or.base.engine.core.Transform;
import termin8or.base.engine.core.Util;
import termin8or.base.engine.core.Vector3f;

public class Shader
{
	private RenderingEngine renderingEngine;
	private int program;
	private Map<String, Integer> uniforms;

	public Shader()
	{
		program = glCreateProgram();
		uniforms = Maps.newHashMap();

		if (program == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location in constructor");
			System.exit(1);
		}
	}

	public void bind()
	{
		glUseProgram(program);
	}
	
	public void setRenderingEngine(RenderingEngine renderingEngine)
	{
		this.renderingEngine = renderingEngine;
	}
	
	public RenderingEngine getRenderingEngine()
	{
		return renderingEngine;
	}

	public void updateUniforms(Transform transform, Material material)
	{
		
	}

	public void addUniform(String uniform)
	{
		int uniformLocation = glGetUniformLocation(program, uniform);

		if (uniformLocation == 0xFFFFFF)
		{
			System.err.println("Could not find uniform: " + uniform);
			new Exception().getStackTrace();
			System.exit(1);
		}

		uniforms.put(uniform, uniformLocation);
	}

	// OpenGL version 2.0+ only
	public void addVertexShaderFromFile(String text)
	{
		addProgram(loadShader(text), GL_VERTEX_SHADER);
	}

	// OpenGL version 3.2+ only
	public void addGeometryShaderFromFile(String text)
	{
		addProgram(loadShader(text), GL_GEOMETRY_SHADER);
	}

	// OpenGL version 2.0+ only
	public void addFragmentShaderFromFile(String text)
	{
		addProgram(loadShader(text), GL_FRAGMENT_SHADER);
	}

	// OpenGL version 2.0+ only
	public void addVertexShader(String text)
	{
		addProgram(text, GL_VERTEX_SHADER);
	}

	// OpenGL version 3.2+ only
	public void addGeometryShader(String text)
	{
		addProgram(text, GL_GEOMETRY_SHADER);
	}

	// OpenGL version 2.0+ only
	public void addFragmentShader(String text)
	{
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void setAttribLocation(String attributeName, int location)
	{
		glBindAttribLocation(program, location, attributeName);
	}

	public void compileShader()
	{
		glLinkProgram(program);

		if (glGetProgrami(program, GL_LINK_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}

		glValidateProgram(program);

		if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
	}

	private void addProgram(String text, int type)
	{
		int shader = glCreateShader(type);

		if (shader == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
			System.exit(1);
		}

		glShaderSource(shader, text);
		glCompileShader(shader);

		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}

		glAttachShader(program, shader);
	}

	private static String loadShader(String fileName)
	{
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;

		try
		{
			shaderReader = new BufferedReader(new FileReader("./src/main/resources/shaders/" + fileName));
			String line;
			while ((line = shaderReader.readLine()) != null)
			{
				shaderSource.append(line).append("\n");
			}
			shaderReader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return shaderSource.toString();
	}

	public void setUniformi(String uniformName, int i)
	{
		glUniform1i(uniforms.get(uniformName), i);
	}

	public void setUniformf(String uniformName, float f)
	{
		glUniform1f(uniforms.get(uniformName), f);
	}

	public void setUniform(String uniformName, Vector3f v)
	{
		glUniform3f(uniforms.get(uniformName), v.getX(), v.getY(), v.getZ());
	}

	public void setUniform(String uniformName, Matrix4f m)
	{
		glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(m));
	}
}
