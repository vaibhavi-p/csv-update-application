package com.nationalarchive.csvupdate.service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVUpdateService {

	public void updateRowValues(String csvFileName, String column, String oldValue, String newValue)
			throws FileNotFoundException, IOException {
		
		try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
			List<String[]> fileContent = reader.readAll();

			if (fileContent.isEmpty()) {
				System.err.println("File is empty!");
				return;
			}
			String[] headerArray = fileContent.get(0);
			int columnIndex = -1;
			for (int i = 0; i < headerArray.length; i++) {
				if (headerArray[i].trim().equalsIgnoreCase(column)) {
					columnIndex = i;
					break;
				}
			}
			if (columnIndex == -1) {
				System.err.println("Column doesn't exist!");
				return;
			}
			for (int i = 1; i < fileContent.size(); i++) {
				String[] strArray = fileContent.get(i);
				if (strArray[columnIndex].trim().equalsIgnoreCase(oldValue)) {
					strArray[columnIndex] = newValue;
				}
			}
			
			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));
			writer.writeAll(fileContent);
			writer.flush();
			writer.close();
		}
	}
}
