#version 330

in vec2 texCoord0;

out vec4 fragColour;

uniform vec3 colour;
uniform sampler2D sampler;

void main()
{
	vec4 textureColour = texture(sampler, texCoord0.xy);
	
	if(textureColour == vec4(0,0,0,1))
		fragColour = vec4(colour, 1);
	else
		fragColour = textureColour * vec4(colour, 1);
}