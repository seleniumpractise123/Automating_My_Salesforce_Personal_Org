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
		try{
			String finalOutPutData = arg+name;
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
			writer.write(finalOutPutData);
			writer.close();
			System.out.println("Data is stored in File successfully");
		} catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

	public static String readDataFromFile(){
		String outputData = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
			String line;
			System.out.println("Reading data from file:");
			line = reader.readLine();
			outputData = line;
			reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		return outputData;
	}

		    

}
