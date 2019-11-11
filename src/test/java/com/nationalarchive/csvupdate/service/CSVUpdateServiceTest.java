/**
 * 
 */
package com.nationalarchive.csvupdate.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.Test;

/**
 * @author Vaibhavi.Patel
 *
 */
public class CSVUpdateServiceTest {

	private static final String oldValue = "Londom";
    private static final String newValue = "London";
	/**
	 * Test method for
	 * {@link com.nationalarchive.csvupdate.service.CSVUpdateService#updateRowValues(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
    @Test
	public void shouldNotUpdateIfColumnNotFound() throws IOException {
		
		File file = new File("src/test/resources/file1.csv");
		String absolutePath = file.getAbsolutePath();
		List<String> linesBeforeUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);

		CSVUpdateService updateService = new CSVUpdateService();
		updateService.updateRowValues(absolutePath, "origin1", oldValue, newValue);

		//Column values should not change
		List<String> linesAfterUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);

		assertEquals(linesBeforeUpdate, linesAfterUpdate);
	}

	@Test
	public void shouldNotUpdateIfFileIsEmpty() throws IOException {
		File file = new File("src/test/resources/file2.csv");
		String absolutePath = file.getAbsolutePath();
		List<String> linesBeforeUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);

		CSVUpdateService updateService = new CSVUpdateService();
		updateService.updateRowValues(absolutePath, "origin", oldValue, newValue);

		List<String> linesAfterUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);
		
		assertEquals(linesBeforeUpdate, linesAfterUpdate);
	}
	
	@Test
	public void shouldLogErrorIfFileHasOnlyHeaders() throws IOException {
		File file = new File("src/test/resources/file3.csv");
		String absolutePath = file.getAbsolutePath();
		List<String> linesBeforeUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);

		CSVUpdateService updateService = new CSVUpdateService();
		updateService.updateRowValues(absolutePath, "origin", oldValue, newValue);

		List<String> linesAfterUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);
		assertEquals(linesBeforeUpdate, linesAfterUpdate);
	}
	
	@Test
	public void shouldUpdateRowValues() throws IOException {
		Path filePath = Paths.get("src/test/resources/file4.csv");
		Path inputFilePath = Paths.get("src/test/resources/file4_input.csv");
		Path outputFilePath = Paths.get("src/test/resources/file4_output.csv");
		
		Files.copy(inputFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
		
		File file = new File("src/test/resources/file4.csv");
		String absolutePath = file.getAbsolutePath();
		
		CSVUpdateService updateService = new CSVUpdateService();
		updateService.updateRowValues(absolutePath, "origin", oldValue, newValue);
		
		List<String> linesAfterUpdate = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);
		List<String> expectedLinesAfterUpdate = Files.readAllLines(outputFilePath, StandardCharsets.UTF_8);
		
		assertEquals(linesAfterUpdate, expectedLinesAfterUpdate);
	}
}
