package excelUtilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import commonUtilities.CommonUtility;
import commonUtilities.PrintUtility;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.poi.ss.util.SheetUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static commonUtilities.PrintUtility.*;
import static driverFactory.DriverManager.getDriver;

/**
 * @author Nikhil Singh
 *
 */
public class ExcelLibrary
{
	private Workbook workbook = null;
	private String workbookFilePath;
	private File inputFile;

	public boolean createWorkbook(String path) {
		Workbook wb = null;
		FileOutputStream fileOut = null;
		try {
			if (CommonUtility.checkIfFileExists(path)) {
				logFail("Failed to create workbook file. The file already exists. File= " + path);
				return false;
			} else {
				if (path.endsWith(".xls")) {
					wb = new HSSFWorkbook();
					fileOut = new FileOutputStream(path);
					wb.write(fileOut);
					fileOut.close();
				} else if (path.endsWith(".xlsx")) {
					wb = new XSSFWorkbook();
					fileOut = new FileOutputStream(path);
					wb.write(fileOut);
					fileOut.close();
				} else {
					logFail(
							"Failed to create workbook file. The filename must end with .xls or .xlsx File= " + path);
				}
				return true;
			}
		} catch (Exception exp1) {
			exp1.printStackTrace();
			logFail("Field to create workbook file. File = " + path + ". " + exp1.getMessage());
			return false;
		}
	}

	public Workbook openWorkbook(String path) {
		Workbook wb = null;

		try {
			if (CommonUtility.checkIfFileExists(path)) {
				this.workbookFilePath = path;
				this.inputFile = new File(path);
				InputStream inputStream = new FileInputStream(this.inputFile);
				wb = WorkbookFactory.create(inputStream);
			} else {
				logWarn("Failed to open workbook file. File= " + path);
			}
		} catch (Exception exp1) {
			exp1.printStackTrace();
		}
		this.workbook = wb;
		return wb;
	}

	public boolean closeWorkbook() {
		if (this.workbook == null) {
			logWarn("Failed The workbook is not opened!");
			return false;
		} else {
			try {
				this.workbook.close();
				this.workbook = null;
				return true;
			} catch (Exception exp1) {
				logWarn("Failed to close the workbook!");
				return false;
			}
		}
	}

	public boolean saveWorkbook() {
		try {
			FileOutputStream fileOut = new FileOutputStream(this.inputFile);
			this.workbook.write(fileOut);
			fileOut.close();
			return true;
		} catch (IOException exp6) {
			exp6.printStackTrace();
			logFail("Workbook save failure File = " + this.workbookFilePath + ". " + exp6.getMessage());
			return false;
		}
	}

	public boolean saveWorkbook(String path) {
		try {
			if (CommonUtility.checkIfFileExists(path)) {
				logFail("Workbook save failure. The file already exists. File = " + path);
				return false;
			} else {
				FileOutputStream fileOut = new FileOutputStream(path);
				this.workbook.write(fileOut);
				fileOut.close();
				return true;
			}
		} catch (IOException exp6) {
			exp6.printStackTrace();
			logFail("Workbook save failure File = " + this.workbookFilePath + ". " + exp6.getMessage());
			return false;
		}
	}

	public List<String> getRowTextValues(int rowIndex) {
		List<String> rowValues = new ArrayList<String>();
		Sheet sheet = this.getActiveSheet();
		DataFormatter formatter = new DataFormatter();
		if (sheet == null) {
			logWarn("Could not access the worksheet!");
			return null;
		} else if (rowIndex > sheet.getLastRowNum()) {
			logWarn("The rowIndex exceeds the number of rows on the worksheet!");
			return null;
		} else {
			Row row = sheet.getRow(rowIndex);
			@SuppressWarnings("rawtypes")
			Iterator var6 = row.iterator();
			while (var6.hasNext()) {
				Cell cell = (Cell) var6.next();
				rowValues.add(formatter.formatCellValue(cell));
			}
			return rowValues;
		}
	}

	public String getCellText(int rowIndex, int columnIndex) {
		Sheet sheet = this.getActiveSheet();
		DataFormatter formatter = new DataFormatter();
		if (sheet == null) {
			logWarn("Could not access the worksheet!");
			return null;
		} else if (rowIndex > sheet.getLastRowNum()) {
			logWarn("The rowIndex exceeds the number of rows on the worksheet!");
			return null;
		} else {
			Row row = sheet.getRow(rowIndex);
			if (columnIndex > row.getLastCellNum()) {
				logWarn("The columnIndex exceeds the number of columns in the row!");
				return null;
			} else {
				return formatter.formatCellValue(row.getCell(columnIndex));
			}
		}
	}

	public Sheet createSheet(String sheetName) {
		if (this.workbook == null) {
			logWarn("Failed The workbook is not opened!");
			return null;
		} else if (sheetName.length() > 31) {
			logFail("Sheet name cannot exceed 31 characters. Name = " + sheetName);
			return null;
		} else {
			String safeName = WorkbookUtil.createSafeSheetName(sheetName);
			Sheet sheet = this.workbook.createSheet(safeName);
			return sheet;
		}
	}

	public Workbook getWorkbook() {
		return this.workbook;
	}

	public Sheet getActiveSheet() {
		if (this.workbook == null) {
			logWarn("The workbook is not opened!");
			return null;
		} else {
			return this.workbook.getSheetAt(this.workbook.getActiveSheetIndex());
		}
	}

	public boolean setActiveSheet(int sheetIndex) {
		if (this.workbook == null) {
			logWarn("The workbook is not opened!");
			return false;
		} else if (sheetIndex > this.workbook.getNumberOfSheets()) {
			logWarn("The specified sheet index exceeds the number of sheets in the workbook!");
			return false;
		} else {
			this.workbook.setActiveSheet(sheetIndex);
			return true;
		}
	}

	/**************************
	 * Custom Excel Operations
	 ***********************************/
	private static XSSFSheet ExcelSheet;
	private static XSSFWorkbook ExcelBook;
	private static String sheetdata;
	private static XSSFRow RowId;
	private static XSSFCell Cell;
	private static Workbook Workbook;

	public static String readDataFromExcel(String Path, String SheetName, int rowNum, int colNum)
	{
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelBook = new XSSFWorkbook(ExcelFile);
			ExcelSheet = ExcelBook.getSheet(SheetName);
			RowId = ExcelSheet.getRow(rowNum);
			if (RowId.getCell(colNum).getCellType() == CellType.NUMERIC) {
				double cellNumericData = RowId.getCell(colNum).getNumericCellValue();
				sheetdata = Double.toString(cellNumericData);
			} else {
				sheetdata = RowId.getCell(colNum).getStringCellValue();
			}
			return sheetdata;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String getExcelValue(String excelFilePath, String sheetName, int rowNum, int cellNum)
			throws Exception {
		int cellNumericData;
		FileInputStream fs = new FileInputStream(excelFilePath);
		Workbook wb = WorkbookFactory.create(fs);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		DataFormatter df = new DataFormatter();
		String sheetdata = df.formatCellValue(cell);
		return sheetdata;
	}

	private int getColNo(Sheet sheet, String colName) {
		Row row = null;
		int total_rows = sheet.getLastRowNum();
		int colm_no = 16380;
		boolean flag = false;
		for (int i = 0; i <= total_rows; i++) {
			row = sheet.getRow(i);
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				if (row.getCell(j) != null) {
					if (row.getCell(j).toString().trim().equals(colName)) {
						flag = true;
						colm_no = j;
						break;
					}
				}
			}
			if (flag == true)
				break;
		}
		return colm_no;
	}

	private int getRowNo(Sheet sheet, String colName) {
		Row row = null;
		int total_rows = sheet.getLastRowNum();
		int row_no = 16380;
		boolean flag = false;

		for (int i = 0; i <= total_rows; i++) {
			row = sheet.getRow(i);
			// System.out.println("total columns " + row.getLastCellNum());
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				if (row.getCell(j) != null) {
					if (row.getCell(j).toString().trim().equals(colName)) {
						flag = true;
						break;
					}
				}
			}
			if (flag == true) {
				row_no = i;
				break;
			}
		}
		return row_no;
	}

	private static Workbook getWorkbook(String WorkbookPath) throws IOException {
		if (WorkbookPath.contains(".xlsx")) {
			Workbook = new XSSFWorkbook(WorkbookPath);
		} else {
			FileInputStream inp = new FileInputStream(WorkbookPath);
			Workbook = new HSSFWorkbook(new POIFSFileSystem(inp));
		}
		return Workbook;
	}

	private static Sheet getSheet(String WorkbookPath, String SheetName)  {
		Sheet sheet = null;
		Workbook workbook = null;
		try {
			workbook = getWorkbook(WorkbookPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		sheet = workbook.getSheet(SheetName.trim());
		return sheet;
	}

	public static int getLastRowNum(String WorkbookPath, String SheetName) {
		int sheetRow;
		Sheet sheet = null;
		sheet = getSheet(WorkbookPath,SheetName);
		return sheetRow = sheet.getLastRowNum();
	}

	public String getCellContentRowNo(String WorkbookPath, String SheetName, int RowNo, String ColName) {
		String CellContent = null;
		Sheet sheet = null;
		sheet = getSheet(WorkbookPath, SheetName);
		System.out.println("getColNo : " + getColNo(sheet, ColName));
		Cell cell = sheet.getRow(RowNo).getCell(getColNo(sheet, ColName));
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			// System.err.println("OK-");
			CellContent = "";
		} else {
			CellContent = cell.toString().trim();
		}
		return CellContent;
	}

	public static void setCellData(String Result, int RowNum, int ColNum, String Path) throws Exception {
		try {
			RowId = ExcelSheet.getRow(RowNum);
			Cell = RowId.getCell(ColNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = RowId.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Path);
			ExcelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	public static void writeDataToExcel(String filePath, String sheetName, int rowNum, int colNum, String value)
	{
		try {
			File file = new File(filePath);

			// Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);

			// Getting workbook instance that refers to .xlsx file
			ExcelBook = new XSSFWorkbook(inputStream);

			// Getting a Sheet object
			ExcelSheet = ExcelBook.getSheet(sheetName);

			// Getting a Sheet object
			RowId = ExcelSheet.getRow(rowNum);

			if (RowId == null) {
				RowId = ExcelSheet.createRow(rowNum);
			}

			// Getting a Cell object and Setting Data
			try {
				Cell = RowId.getCell(colNum);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Cell == null) {
				Cell = RowId.createCell(colNum);
				Cell.setCellValue(value);
			} else {
				Cell.setCellValue(value);
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ExcelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			ExcelBook.close();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

	}
	
	public static void writeMultipleDataToExcel(String filePath, String sheetName, int rowNum, int colNum, List<String> value)
	{
		File file = new File(filePath);

		// Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = null;
        try
		{
            inputStream = new FileInputStream(file);
            ExcelBook = new XSSFWorkbook(inputStream);
        }
		catch (IOException e)
		{
            throw new RuntimeException(e);
        }

        // Getting a Sheet object
		ExcelSheet = ExcelBook.getSheet(sheetName);

		// Getting a Sheet object
		RowId = ExcelSheet.getRow(rowNum);

		// Getting a Cell object and Setting Data
		for(int i=0; i<value.size(); i++) {
			Cell = RowId.createCell(colNum);
			Cell.setCellValue(value.get(i));
			colNum++;		
		}

        FileOutputStream fileOut = null;
        try
		{
            fileOut = new FileOutputStream(filePath);
	        ExcelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}


	public static void appendValueToColumn(String filePath, String sheetName, int columnIndex, String appendValue)
	{
		long start = System.currentTimeMillis();
		String fileName = new File(filePath).getName();

		if (fileName.endsWith(".csv"))
		{
			appendValueToCSV(filePath, sheetName, columnIndex, appendValue);
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logPass(printWaitTime("appendValueToColumn():",totalTime,"Value appended successfully to CSV."));
		}
		else if (fileName.endsWith(".xlsx"))
		{
			appendValueToExcel(filePath, sheetName, columnIndex, appendValue);
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logPass(printWaitTime("appendValueToColumn():",totalTime,"Value appended successfully to CSV."));
		}
		else
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logFail(printWaitTime("appendValueToColumn():",totalTime,"Unsupported file format: "+fileName));
		}
	}

	public static void appendValueToCSV(String filePath, String sheetName, int columnIndex, String appendValue)
	{
		try
		{
			CSVReader reader = new CSVReader(new FileReader(filePath));
			List<String[]> records = reader.readAll();
			reader.close();

			boolean isFirstRow = true; // Flag to identify the header row
			for (String[] record : records)
			{
				if (isFirstRow)
				{
					isFirstRow = false; // Skip the header row
					continue;
				}

				if (columnIndex == 0)
				{ // Check if it's the name column
					record[0] = record[0] + "_" + appendValue; // Modify the name directly
				} else if (columnIndex < record.length)
				{ // Check if the column index is valid
					record[columnIndex] = record[columnIndex] + "_" + appendValue; // Modify the specified column
				}
			}
			CSVWriter writer = new CSVWriter(new FileWriter(filePath));
			writer.writeAll(records);
			writer.close();
		}
		catch (FileNotFoundException e)
		{
            throw new RuntimeException(e);
        }
		catch (IOException e)
		{
            throw new RuntimeException(e);
        }
		catch (CsvException e)
		{
            throw new RuntimeException(e);
        }
    }

	public static void appendValueToExcel(String filePath, String sheetName, int columnIndex, String appendValue)
	{
		long start = System.currentTimeMillis();
		try
		{
			// Open the Excel workbook
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(file);

			// Get the first sheet
			Sheet sheet = workbook.getSheet(sheetName);

			// Append the value to each row
			for (Row row : sheet)
			{
				Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellValue(cell.getStringCellValue() + "_"+appendValue);
			}

			// Write the updated workbook to a new file
			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}


	public static boolean verifyExcelHeaders(String filePath, List<String> expectedHeaders, String sheetName)
	{
		// Check if the file exists.
		File file = new File(filePath);
		if (!file.exists())
		{
            try {
                throw new IOException("File does not exist: " + filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

		// Get the file extension.
		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);

		// Check the file extension and verify the headers accordingly.
		switch (extension) {
			case "csv":
				return verifyCSVFileHeaders(filePath, expectedHeaders);
			case "xlsx":
			case "xls":
				return verifyExcelFileHeaders(filePath, expectedHeaders, sheetName);
			default:
				throw new IllegalArgumentException("Unsupported file extension: " + extension);
		}
	}

	private static boolean verifyCSVFileHeaders(String filePath, List<String> expectedHeaders)
	{
		long start = System.currentTimeMillis();
		boolean match=false;
		try (CSVReader reader = new CSVReader(new FileReader(filePath)))
		{
			String[] headers = reader.readNext();
			if (headers == null)
			{
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				logFail(printWaitTime("verifyCSVFileHeaders()",totalTime,"The CSV file does not have any headers."));
			}

			for (int i = 0; i < headers.length; i++)
			{
				if (!headers[i].trim().equals(expectedHeaders.get(i)))
				{
					match=false;
					long finish = System.currentTimeMillis();
					long totalTime = finish - start;
					logFail(printWaitTime("verifyCSVFileHeaders()",totalTime, "The header \"" + headers[i] + "\" at index " + i + " does not match the expected header \"" + expectedHeaders.get(i) + "\"."));
					return match;
				}
				else {
					match = true;
				}
			}
			if(match==true) {
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				logPass(printWaitTime("verifyCSVFileHeaders()",totalTime,"The headers in the CSV file match the expected headers"));
			}
		}
		catch (CsvValidationException | FileNotFoundException e)
		{
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return match;
    }

	private static boolean verifyExcelFileHeaders(String filePath, List<String> expectedHeaders, String sheetName)
	{
		long start = System.currentTimeMillis();
        Workbook workbook = null;
		Sheet sheet = null;
		boolean match=false;
        try
		{
            workbook = WorkbookFactory.create(new File(filePath));
			sheet = workbook.getSheet(sheetName);
			if (sheet == null)
			{
				throw new IllegalArgumentException("The sheet \"" + sheetName + "\" does not exist in the Excel file.");
			}
        }
		catch (IOException e)
		{
            throw new RuntimeException(e);
        }


		Row headerRow = sheet.getRow(0);
		if (headerRow == null)
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logFail(printWaitTime("verifyExcelFileHeaders()",totalTime,"The Excel file does not have any headers."));
		}

		int numHeaders = headerRow.getLastCellNum();
		for (int i = 0; i < numHeaders; i++)
		{
			Cell headerCell = headerRow.getCell(i);
			if (headerCell == null || !headerCell.getStringCellValue().trim().equals(expectedHeaders.get(i)))
			{
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				logFail(printWaitTime("verifyExcelFileHeaders()",totalTime,"The header \"" + headerCell.getStringCellValue() + "\" at index " + i + " does not match the expected header \"" + expectedHeaders.get(i) + "\"."));
				return match;
			}
			else
			{
				match=true;
			}
		}
		if(match==true)
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logFail(printWaitTime("verifyExcelFileHeaders()",totalTime,"The headers in the Excel file match the expected headers."));
		}
        return match;
    }

	public static List<Map<String, String>> readExcelTable(String excelFilePath, String sheetName)
	{
		// Read Excel file using Apache POI library
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(excelFilePath));
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
        Sheet sheet = workbook.getSheet(sheetName);
		DataFormatter dataFormatter = new DataFormatter();

		List<Map<String, String>> germplasmData = new ArrayList<>();
		for (Row row : sheet)
		{
			Map<String, String> germplasmRecord = new HashMap<>();
			for (Cell cell : row)
			{
				int columnIndex = cell.getColumnIndex();
				String cellValue = dataFormatter.formatCellValue(cell);
				if (columnIndex == 0)
				{
					germplasmRecord.put("Field", cellValue);
				} else if (columnIndex == 1)
				{
					germplasmRecord.put("Value", cellValue);
				}
			}
			germplasmData.add(germplasmRecord);
		}
		return germplasmData;
	}

	public static void compareExcelDataAndList(List<Map<String, String>> excelData, List<WebElement> fieldElement, List<WebElement> valueElement)
	{
		long start = System.currentTimeMillis();
		List<WebElement> dtElements = fieldElement;
		List<WebElement> ddElements = valueElement;
		// Initialize a counter for successful matches
		int successfulMatches = 0;

		// Iterate through dt and dd elements and match with Excel data
		for (int i = 0; i < dtElements.size(); i++)
		{
			String dtText = dtElements.get(i).getText().trim();
			String ddText = ddElements.get(i).getText().trim();

			Map<String, String> Record = excelData.stream()
					.filter(record -> record.get("Field").equalsIgnoreCase(dtText))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Record not found for field: " + dtText));

			if (ddText.equals(Record.get("Value")))
			{
				successfulMatches++;
			}
			else
			{
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				logFail(PrintUtility.printWaitTime("compareListInMap()",totalTime,"Mismatch found for field: " + dtText + ". Expected value: " + Record.get("Value") + ", Actual value: " + ddText));
			}
		}

		// Display success message if there were no mismatches
		if (successfulMatches == dtElements.size())
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logPass(PrintUtility.printWaitTime("compareListInMap()",totalTime,"Test Data matched with UI successfully"));
		}
	}

	public static Map<String, String> readExcelTable(String excelFilePath, String sheetName, boolean skipHeaders)
	{
		Map<String, String> data = new HashMap<>();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(excelFilePath);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet(sheetName);
			for (Row row : sheet)
			{
				if(skipHeaders==true)
				{
					if (row.getRowNum() == 0)
					{
						continue; // Skip header row
					}
				}
				String key,value=null;
				if (row.getCell(0).getCellType() == CellType.NUMERIC) {
					 key = String.valueOf(row.getCell(0).getNumericCellValue());
				} else {
					 key = row.getCell(0).getStringCellValue();
				}
				if (row.getCell(1).getCellType() == CellType.NUMERIC) {
					value = String.valueOf(row.getCell(1).getNumericCellValue());
				} else {
					 value = row.getCell(1).getStringCellValue();
				}
				String normalizedKey = key.toLowerCase().trim().replaceAll(" ", "_");
				String normalizedValue = value.toLowerCase().trim().replaceAll(" ", "_");

				data.put(normalizedKey, normalizedValue);
			}
			workbook.close();
			fileInputStream.close();
        } catch (FileNotFoundException e)
		{
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		return data;
	}

	public static Map<String, String> extractWebTableData(By locator, boolean skipheader) {

		WebElement webTableElement = getDriver().findElement(locator);
		List<WebElement> rows = webTableElement.findElements(By.tagName("tr"));
		Map<String, String> data = new HashMap<>();

		for (WebElement row : rows)
		{
			if(skipheader==true) {
				if (row.getAttribute("class").contains("header"))
				{
					continue; // Skip header row
				}
			}
			else
			{
				// Extract header row
				List<WebElement> headers = row.findElements(By.tagName("th"));
				for (int i = 0; i < headers.size(); i++) {
					String headerName = headers.get(i).getText();
					data.put(headerName, null); // Initialize with null values for header row
				}
			}
			// Extract data row
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String key = cells.get(0).getText();
			String value = cells.get(1).getText();
			data.put(key, value);
		}
		return data;
	}

	public static void compareExcelAndWebTable(Map<String, String> excelData, Map<String, String> webTableData)
	{
		long start = System.currentTimeMillis();
		boolean mismatchesFound = false;

		for (Map.Entry<String, String> entry : excelData.entrySet()) {
			String key = entry.getKey();
			String normalizedKey = key.toLowerCase().trim().replaceAll(" ", "_");

			String excelValue = entry.getValue();
			String normalizedExcelValue = excelValue.toLowerCase().trim().replaceAll(" ", "_");

			String webValue = webTableData.get(normalizedKey);
			if (!normalizedExcelValue.equals(webValue)) {
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				logFail(PrintUtility.printWaitTime("compareExcelAndWebTable()", totalTime, "Mismatch found for key: " + key));
				logFail(PrintUtility.printWaitTime("compareExcelAndWebTable()", totalTime, "Normalized key: " + normalizedKey));
				logFail(PrintUtility.printWaitTime("compareExcelAndWebTable()", totalTime, "Excel value: " + excelValue));
				logFail(PrintUtility.printWaitTime("compareExcelAndWebTable()", totalTime, "Web UI value: " + webValue));
				mismatchesFound = true;
			}
		}

		if (!mismatchesFound) {
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			logPass(PrintUtility.printWaitTime("compareExcelAndWebTable()", totalTime, "Test Data matched with UI Web Table successfully"));
		}
	}
}

