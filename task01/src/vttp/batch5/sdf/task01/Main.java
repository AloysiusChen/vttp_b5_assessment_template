package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) {

		File inputFile = new File("day.csv");

		ArrayList<BikeEntry> list = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String line;
			// Skip header
			String header = reader.readLine();

			// Read all rows
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				list.add(BikeEntry.toBikeEntry(split));
			}
		} catch (IOException e) {
			System.err.println("Error during file operation: " + e.getMessage());
		}

		Collections.sort(list, (a, b) -> {
			int totalA = a.getRegistered() + a.getCasual();
			int totalB = b.getRegistered() + b.getCasual();

			return Integer.compare(totalB, totalA);
		});

		// Print message
		String[] position = { "highest", "second highest", "third highest", "fourth highest", "fifth highest" };

		for (int i = 0; i < 5; i++) {
			// Calculate total
			int total = list.get(i).getCasual() + list.get(i).getRegistered();
			// Holiday string
			String holiday = "";
			if (list.get(i).isHoliday()) {
				holiday = "a holiday";
			} else {
				holiday = "not a holiday";
			}
			// Weather string
			String weather = "";
			if (list.get(i).getWeather() == 1) {
				weather = "Clear, Few clouds, Partly cloudy, Partly cloudy";
			} else if (list.get(i).getWeather() == 2) {
				weather = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
			} else if (list.get(i).getWeather() == 3) {
				weather = "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
			} else if (list.get(i).getWeather() == 4) {
				weather = "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
			}

			System.out.printf(
					"The %s recorded number of cyclists was in %s, on a %s in the month of %s. There were a total of %d cyclists. The weather was %s. %s was %s.\n",
					position[i], Utilities.toSeason(list.get(i).getSeason()),
					Utilities.toWeekday(list.get(i).getWeekday()),
					Utilities.toMonth(list.get(i).getMonth()), total, weather,
					Utilities.toWeekday(list.get(i).getWeekday()),
					holiday);

			System.out.println();
		}
	}
}