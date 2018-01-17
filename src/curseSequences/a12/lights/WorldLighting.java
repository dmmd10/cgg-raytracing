package curseSequences.a12.lights;

import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Vec3;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.sceneObjects.Shape;

public class WorldLighting {
	
	public final List<Light> lightList;
			
	public WorldLighting() {
		this.lightList = new ArrayList<Light>();
		createWorldLights();
	}

	protected void createWorldLights() {
		int[] xOffArr = {0, -500, -750, -1000, -1500, -3000, -4000, -4500, -5000, -5000, -6000, -6000, -7500, -6000, -7500, -7500};
		int[] zOffArr = {0, -750, -300, 500, -1000, -1500, 200, 1500, -4000, 2000, -2000, 1000, 2000, 3500, 5000, -4000};
		
		for (int i = 0; i < zOffArr.length; i++) {
			int xOff = xOffArr[i];
			int zOff = zOffArr[i];
			lightList.add(new PointLight(vec3(0 + xOff, 15, 0 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(43 + xOff, 28, 43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(43 + xOff, 15, 43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(43 + xOff, 28, -43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(43 + xOff, 15, -43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(-43 + xOff, 28, 43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(-43 + xOff, 15, 43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(-43 + xOff, 28, -43 + zOff), multiply(400, shiny_red)));
			lightList.add(new PointLight(vec3(-43 + xOff, 15, -43 + zOff), multiply(400, shiny_red)));
		}
		
//		lightList.add(new DirectionLight(vec3(1, -4, 4), shiny_red));
	}
	
	public Vec3 illumination(Hit hit, Shape scene) {
		Vec3 lightsSum = vec3(0);
		for (Light light: lightList) {
			Vec3 pointLight = light.sample(hit, scene);
			lightsSum = add(lightsSum, pointLight);
		}
		return lightsSum;
	}

}
