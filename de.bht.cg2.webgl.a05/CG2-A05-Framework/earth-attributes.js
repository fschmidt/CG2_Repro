EarthAttributes = function(drawLightsAtNight, drawClouds) {

	this.drawLightsAtNight = drawLightsAtNight;
	this.drawClouds = drawClouds;

	// set all uniforms required to render this material
	this.setUniforms = function(program) {

		program.setUniform("earthAttributes.drawLightsAtNight", "float", this.drawLightsAtNight);
		program.setUniform("earthAttributes.drawClouds", "float", this.drawClouds);

	}
}