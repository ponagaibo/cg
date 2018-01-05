#version 120

uniform float red = 55;
uniform float green = 55;
uniform float blue = 55;
uniform float alpha = 1;
uniform float ambK = 0.5;
uniform float diffK = 0.5;
uniform float specK = 0.5;

uniform vec3 col;
uniform vec3 light = vec3(0, 0, 1);
uniform vec3 Normal;

vec4 calc(){
    vec3 amb = ambK * col;
    vec3 n = normalize(Normal);
    float diff = max(dot(n, light), 0);
    vec3 diffuse = diffK * diff * col;
    float spec = pow(dot(n, light), 32);
    vec3 specular = specK * spec * vec3(1, 1, 1);
    return vec4(amb + diffuse + specular, abs(alpha));
}

void main() {
    gl_FragColor = calc();
}