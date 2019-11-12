package com.nationalarchive.csvupdate;

import java.io.File;
import java.io.IOException;

import com.nationalarchive.csvupdate.service.CSVUpdateService;

public class CSVUpdateApplication {

	public static void main(String[] args) throws IOException {
		
		if(args.length < 4) {
			System.err.println("Insufficient arguments");
			return;
		}
		
		// Read existing file
		File file = new File(args[0]);
		String absolutePath = file.getAbsolutePath();
		
		CSVUpdateService updateServive = new CSVUpdateService();
		updateServive.updateRowValues(absolutePath, args[1], args[2], args[3]);
	}
}
