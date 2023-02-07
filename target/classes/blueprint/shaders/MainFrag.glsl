#version 460 core

in vec4 passColor;
in vec2 passUV;
in vec3 passNormal;

out vec4 color;

uniform sampler2D tex;

void main() {
	color = texture(tex, passUV);
}
