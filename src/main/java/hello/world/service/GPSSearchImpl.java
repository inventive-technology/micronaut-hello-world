package hello.world.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import io.micronaut.context.annotation.Primary;

@Primary
@Singleton
public class GPSSearchImpl implements GPSSearch {

	@Override
	public String search(String lat, String lng) {
		return printFileList(getFilesContaining(lat, lng));
	}

	private List<String> getFilesContaining(String lat, String lng) {
		List<String> files = new ArrayList<>();
		for (String path : getFileList()) {
			try {
				List<WayPoint> waypoints = GPX.read(Path.of(path)).tracks().flatMap(Track::segments)
						.flatMap(TrackSegment::points)
						.filter(w -> String.valueOf(((WayPoint) w).getLatitude()).contains(lat))
						.filter(w -> String.valueOf(((WayPoint) w).getLongitude()).contains(lng))
						.collect(Collectors.toList());
				if (waypoints.size() > 0) {
					files.add(path);
				}
			} catch (IOException e) {
				System.out.println(path);
				e.printStackTrace();
			}
		}
		return files;
	}

	private List<String> getFileList() {
		List<String> files = new ArrayList<>();
		try {
			files = Files.list(Paths.get("/home/rob/development/gps/sportypal-scrape"))
					.filter(f -> !Files.isDirectory(f))
					.map(Path::toString).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}

	private String printFileList(List<String> fileList) {
		String files = "";
		for (String s : fileList) {
			files += s + "\n";
		}
		return files;
	}

}
