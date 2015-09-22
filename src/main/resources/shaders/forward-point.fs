#version 120

varying vec2 texCoord0;
varying vec3 normal0;
varying vec3 worldPos0;

struct BaseLight
{
	vec3 colour;
	float intensity;
};

struct Attenuation
{
	float constant;
	float linear;
	float exponent;
};

struct PointLight
{
	BaseLight base;
	Attenuation atten;
	vec3 position;
	float range;
};

uniform vec3 eyePos;
uniform sampler2D diffuse;

uniform float specularIntensity;
uniform float specularExponent;

uniform PointLight pointLight;

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal)
{
	float diffuseFactor = dot(normal, -direction);
	
	vec4 diffuseColour = vec4(0,0,0,0);
	vec4 specularColour = vec4(0,0,0,0);
	
	if(diffuseFactor > 0)
	{
		diffuseColour = vec4(base.colour, 1.0) * base.intensity * diffuseFactor;
	
		vec3 directionToEye = normalize(eyePos - worldPos0);
		vec3 reflectDirection = normalize(reflect(direction, normal));
		
		float specularFactor = dot(directionToEye, reflectDirection);
		specularFactor = pow(specularFactor, specularExponent);
		
		if(specularFactor > 0)
		{
			specularColour = vec4(base.colour, 1.0) * specularIntensity * specularFactor;
		}
	}
	
	return diffuseColour + specularColour;
}

vec4 calcPointLight(PointLight pointLight, vec3 normal)
{
	vec3 lightDirection = worldPos0 - pointLight.position;
	float distanceToPoint = length(lightDirection);
	
	if(distanceToPoint > pointLight.range)
		return vec4(0,0,0,0);
	
	lightDirection = normalize(lightDirection);
	
	vec4 colour = calcLight(pointLight.base, lightDirection, normal);
	
	float attenuation = pointLight.atten.constant =
						pointLight.atten.linear * distanceToPoint +
						pointLight.atten.exponent * distanceToPoint * distanceToPoint +
						0.00001;

	return colour / attenuation;
}

void main()
{		
	gl_FragColor = texture2D(diffuse, texCoord0.xy) * calcPointLight(pointLight, normalize(normal0));
}