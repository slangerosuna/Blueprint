#version 460 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec4 color;
layout(location = 2) in vec2 UV;
layout(location = 3) in vec3 normal;

out vec4 passColor;
out vec2 passUV;
out vec3 passNormal;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main() {
	gl_Position = vec4(position, 1.0) * model * view * projection;
	
	passColor = color;
	passUV = UV;
	passNormal = normal;
}
