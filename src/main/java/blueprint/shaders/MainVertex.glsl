#version 460 core

layout(location = 0) attribute vec3 position;
layout(location = 1) attribute vec4 color;
layout(location = 2) attribute vec2 UV;
layout(location = 3) attribute vec3 normal;

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
