package com.nationalarchive.csvupdate;

import java.io.File;
import java.io.IOException;

import com.nationalarchive.csvupdate.service.CSVUpdateService;

public class CSVUpdateApplication {

	public static void main(String[] args) throws IOException {
		// Read existing file
		File file = new File("src/main/resources/file.csv");
		String absolutePath = file.getAbsolutePath();
		
		CSVUpdateService updateServive = new CSVUpdateService();
		updateServive.updateRowValues(absolutePath, "origin", "Londom", "London");
	}
}
