package com.qa.salesforce.utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH="./src/main/resource/testdata/OpenCartRegUserTestData.xlsx";
	private static Sheet ExcelWSheet;
	private static Workbook ExcelWBook;
	private static org.apache.poi.ss.usermodel.Cell Cell;

	//This method will retrieve the test data from Test file
	public static Object[][] getTestData(String testName)    throws Exception {
		String[][] testData = null;
		try {
			System.out.println("Inside getTestData try");
			FileInputStream excelFile = new FileInputStream(TEST_DATA_SHEET_PATH);
			ExcelWBook = new XSSFWorkbook(excelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");

			int ci = 0, cj = 0;

			//Figure out how many tests there are
			int numberOfTests = findNumberOfThisTest(testName);
			int fieldCount = getFieldCount(testName);
			System.out.println("numberOfTests: "+numberOfTests);
			System.out.println("numberOfParameters: "+fieldCount);
			testData = new String[numberOfTests][fieldCount];

			//Look into the first col of the current row to see if it matches the string passed in
			//If it does, load the data from the proceeding col into the tabArray
			//If it does not, increment the row and continue to check the col header
			int nextCase = 0;
			int caseCounter = 0;

			while(caseCounter < numberOfTests){
				//find next case row
				System.out.println("Inside getTestData While loop");
				nextCase = findNextCase(nextCase, testName);
				System.out.println("nextCaase: "+nextCase);
				if (nextCase != -1) {
					while (cj < fieldCount) {
						testData[caseCounter][cj] = getCellData(nextCase - 1, cj + 2);
						System.out.print("Test Data: ");
						System.out.println(testData[ci][cj]);
						cj++;
					}
					cj = 0;
					caseCounter++;
				} else{
					return testData;
				}


			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(testData);
	}


	/*This method will retrieve all of the test data based on the unique indentifier passed to it
    -The unique indentifier should directly correlate to the correct column labeled, "Unique Identifier"
    -The method will find any column with this identifier and return a two dimensional array filled with
    the proceeding data after the indentifier
    -In addition, this overload will take an integer value which denotes the requested number of test data
    with this testname that it will return.
    -If the number is greater then the number present of that test type in the excel sheet than it will
    return all of the values
    -If there is none, then the method will return null
    */
	public static Object[][] getTestData(String testName, int numRows) throws Exception {
		String[][] testData = null;
		try {
			FileInputStream excelFile = new FileInputStream(TEST_DATA_SHEET_PATH);
			ExcelWBook = new XSSFWorkbook(excelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");


			int ci = 0, cj = 0;

			//Figure out how many tests there are
			int numberOfTests = findNumberOfThisTest(testName);
			int fieldCount = getFieldCount(testName);
			if (numberOfTests > numRows)
				testData = new String[numRows][fieldCount];
			else
				testData = new String[numberOfTests][fieldCount];


			//Look into the first col of the current row to see if it matches the string passed in
			//If it does, load the data from the proceeding col into the tabArray
			//If it does not, increment the row and continue to check the col header
			int nextCase = 0;
			int caseCounter = 0;

			while (caseCounter < numberOfTests) {
				if (caseCounter < numRows) {
					//find next case row
					nextCase = findNextCase(nextCase, testName);
					if (nextCase != -1) {
						while (cj < fieldCount) {
							testData[caseCounter][cj] = getCellData(nextCase, cj + 2);
							cj++;
						}
						cj = 0;
						caseCounter++;
					} else
						return testData;

					//System.out.println(testData[ci][cj]);
				} else
					return testData;
			}

		} catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		} catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (testData);

	}

	/*This method will return all of the test data that is avilable in the excel document.
BEWARE, this method will return any data regardless of wether it will fit so be sure
to only call this when the only data that is present in the excel file is the same number
of columns and is cleaned properly
*/
	public static Object[][] getTestData() throws Exception {
		String[][] testData = null;
		try {
			FileInputStream excelFile = new FileInputStream(TEST_DATA_SHEET_PATH);
			ExcelWBook = new XSSFWorkbook(excelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");


			int ci = 0, cj = 0;

			//Figure out how many tests there are
			int numberOfTests = findNumberOfTests();
			int numberOfFields = Integer.parseInt(getCellData(2, 1));

			testData = new String[numberOfTests][numberOfFields];

			//Look into the first col of the current row to see if it matches the string passed in
			//If it does, load the data from the proceeding col into the tabArray
			//If it does not, increment the row and continue to check the col header
			int nextCase = 2;
			int caseCounter = 0;

			while (caseCounter < numberOfTests) {
				if (nextCase != -1) {
					while (cj < numberOfFields) {
						testData[caseCounter][cj] = getCellData(nextCase, cj + 2);
						cj++;
					}
					cj = 0;
					nextCase++;
					caseCounter++;
				} else
					return testData;

				//System.out.println(testData[ci][cj]);
			}

		} catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		} catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (testData);
	}

	private static int findNextCase(int currentCase, String testName) throws Exception {
		//We'll assume that nextCase was the last place that was checked for that case type
		int nextCase = -1;
		int curRow = currentCase;
		String contents = "";

		if (curRow < 1) {
			curRow = 1;
			while (!contents.equals("End")) {
				contents = getCellData(curRow, 0);
				if (contents.equals(testName)) {
					curRow++;
					return curRow;
				}
				curRow++;
			}

		}
		nextCase = -1;
		return nextCase;
	}

	public static int findNumberOfTests() throws Exception {
		int numOfTests = 0;
		int curRow = 1;
		String contents = "";
		contents = getCellData(curRow, 0);

		if (!contents.equals("End")) {
			while (!contents.equals("End")) {
				numOfTests++;
				curRow++;
				contents = getCellData(curRow, 0);
			}
			return numOfTests;
		} else
			return 0;
	}

	public static int findNumberOfThisTest(String testName) throws Exception {
		int numOfTests = 0;
		int curRow = 1;
		String contents = "";

		while (!contents.equals("End")) {
			contents = getCellData(curRow, 0);
			if (testName.equals(contents)) {
				numOfTests++;
			}
			curRow++;
		}
		return numOfTests;
	}

	public static int getFieldCount(String testName) throws Exception {
		int fieldCount = 0;
		int curRow = 1;
		String contents = "";

		while (contents != "End") {
			contents = getCellData(curRow, 0);
			if (contents.equals(testName)) {
				String mine = getCellData(curRow, 1);
				int other = Integer.parseInt(mine);
				return other;
			} else
				curRow++;
		}

		return fieldCount;
	}
	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DataFormatter df = new DataFormatter();
			if (Cell.getCellType() == CellType.NUMERIC) {
				return df.formatCellValue(Cell);
			} else if (Cell.getCellType() == CellType.STRING) {
				return Cell.getStringCellValue();

			} else
				return null;


		} catch (Exception e) {
			System.out.println(e);
			return "";

		}

	}


}
