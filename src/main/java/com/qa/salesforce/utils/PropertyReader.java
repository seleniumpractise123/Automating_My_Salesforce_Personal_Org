package com.qa.salesforce.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class PropertyReader {
	
	//private static final String FILE_PATH = "./src/main/resource/OutPutDataCapture/OutPut.txt"; 
	
	/*
	public static void write(String data) 
	{ 
		System.out.println("Driver calling this method");
		try(FileWriter writer = new FileWriter(FILE_PATH, true)){
			writer.write(data + System.lineSeparator());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	private static final String FILE_PATH = "output.properties"; 
	private static Properties props = new Properties();
	
	public static void writingDataIntoTextFile(String arg,String name) {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
			properties.load(fis);
		} catch (Exception ignored) {}

		try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
			properties.setProperty(arg, name);
			properties.store(fos, "Updated latest values");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	public static String readDataFromFile(String key){
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read property file", e);
		}

		return properties.getProperty(key);
	}

		    

}
