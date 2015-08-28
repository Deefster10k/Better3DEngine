package termin8or.base.engine;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;

public class Shader
{
	private int program;
	private Map<String, Integer> uniforms;

	public Shader()
	{
		program = glCreateProgram();
		uniforms = Maps.newHashMap();
		
		if(program == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location in constructor");
			System.exit(1);
		}
	}
	
	public void bind()
	{
		glUseProgram(program);
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
	{
		
	}
	
	public void addUniform(String uniform)
	{
		int uniformLocation = glGetUniformLocation(program, uniform);
		
		if(uniformLocation == 0xFFFFFF)
		{
			System.err.println("Could not find uniform: " + uniform);
			new Exception().getStackTrace();
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLocation);
	}
	
	//OpenGL version 2.0+ only
	public void addVertexShader(String text)
	{
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	//OpenGL version 3.2+ only
	public void addGeometryShader(String text)
	{
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	//OpenGL version 2.0+ only
	public void addFragmentShader(String text)
	{
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader()
	{
		glLinkProgram(program);
		
		if(glGetProgrami(program, GL_LINK_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
	}
	
	private void addProgram(String text, int type)
	{
		int shader = glCreateShader(type);
		
		if(shader == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
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
