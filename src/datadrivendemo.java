/**
 * 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author kapilnegi
 *
 */
public class datadrivendemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "/Users/kapilnegi/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Import excel sheet.
		File dataFile = new File("/Volumes/DataVolume/eclipse-workspace/JavaSeleniumDataDrivenFramework/src/resources/testData.xlsx");

		// Load the file.
		FileInputStream finput = new FileInputStream(dataFile);

		// Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (finput);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        int my_rows = mySheet.getPhysicalNumberOfRows();
        for (int i=1; i<my_rows; i++)
        {
        	driver.get("https://studentstutorial.com/project/signuser/");
        	XSSFRow my_row = mySheet.getRow(i);
        	driver.findElement(By.id("email_id")).sendKeys(my_row.getCell(1).getStringCellValue());
 			driver.findElement(By.id("pass")).sendKeys(my_row.getCell(2).getStringCellValue());
 			//driver.findElement(By.id("signin")).click();
 			Cell cell2Update = my_row.createCell(3);
 			cell2Update.setCellValue("Pass");
 			
        }
        finput.close();
        FileOutputStream outFile =new FileOutputStream(dataFile);
        myWorkBook.write(outFile);
		outFile.close();
        driver.quit();



	}

}
