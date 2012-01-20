EarthAttributes = function(drawLightsAtNight, drawClouds) {

	this.drawLightsAtNight = drawLightsAtNight;
	this.drawClouds = drawClouds;
	this.time = 0; 
	this.drawAurora = 0; 

	// set all uniforms required to render this material
	this.setUniforms = function(program) {

		program.setUniform("earthAttributes.drawLightsAtNight", "float", this.drawLightsAtNight);
		program.setUniform("earthAttributes.drawClouds", "float", this.drawClouds);
		program.setUniform("earthAttributes.drawAurora", "float", this.drawAurora);
		program.setUniform("earthAttributes.time", "float", this.time);

	}
}
