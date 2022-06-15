package hello.world.controller;

import javax.inject.Inject;

import hello.world.service.GPSSearch;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/gps")
public class GPSController {

	@Inject
	private GPSSearch gpsSearch;

	@Get("/search/{lat}/{lng}")
	public String search(String lat, String lng) {
		return gpsSearch.search(lat, lng);
	}

}
