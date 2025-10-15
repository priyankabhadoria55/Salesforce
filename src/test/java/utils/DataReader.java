package utils;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataReader {

	static boolean DefectWithSheet=false;
	
	public static boolean isNumeric(String str) {
		  NumberFormat formatter = NumberFormat.getInstance();
		  ParsePosition pos = new ParsePosition(0);
		  formatter.parse(str, pos);
		  return str.length() == pos.getIndex();
		}

	
		
	//-------------------------------------------------------------------------------------------------------------
	public static void main(String []args) throws Exception {

	   // datareader1();
	 
	String value=	DataReader.readExcel(2, 2,"mom");
	System.out.println(value);
		}

	
	@DataProvider(name="TU_Main", parallel=true)
	public static Object[][] datareader1() throws IOException
	{                                    //src\test\resources\locators\TU.xml
		
		 FileInputStream file = new FileInputStream(new File("src\\test\\resources\\dataSource\\TU.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheetAt(0);
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equalsIgnoreCase("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
			
		 }		 
		 }
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		 Object[][]  data=null;
		 int firstrow=0;
		 int Lasttrow=0;
		 int rownumber=0;
		 int size=0;
		 int size1=0;
		 String Needtoinclude="No";
		 for(int i=1;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
		   
		     if(firstrow==0 && size==0 && Lasttrow==0 )
			 {
				 firstrow=i;
				 Lasttrow=i;
				 size=Lasttrow-firstrow+1;
				 size1=Lasttrow-firstrow+1;
                 //firstrow=i;
				 data=new Object[size][r1.getLastCellNum()];
//				System.out.println("First and last row without merge"+firstrow+"-"+Lasttrow+" with iteration"+i);
//				 System.out.println("First and last row  without merge"+size);
//				 System.out.println("First and last row  without merge"+r1.getLastCellNum());
//				 System.out.println("Rownumber Counter  without merge"+i);
				  Needtoinclude=sheet.getRow(i).getCell(1).toString();
			 } 

		if(Needtoinclude.equalsIgnoreCase("Yes"))
		{
		   for(int j=0;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()== CellType.NUMERIC)
				   {
					   if(DateUtil.isCellDateFormatted(r.getCell(j)))
					   {
						   SimpleDateFormat dt=new SimpleDateFormat("dd/MM/yyyy");
						   strCellValue=dt.format(r.getCell(j).getDateCellValue());
						   //Log.info("The 2Value is in data Format and Value is:"+strCellValue);
					   }
					   else{
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   System.out.println(strCellValue);

					   //Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   }
				   }
				   else if(r.getCell(j).getCellType()==CellType.BLANK)
				   {
					   strCellValue="";
					   System.out.println(strCellValue);

				   }
				   else {
					   strCellValue=r.getCell(j).toString();
					   System.out.println(strCellValue);
					   //Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(NullPointerException e)
				   {
					   strCellValue="";
					   //Log.info("The Value of this cell is: "+strCellValue);
				   }
			   
			   data[rownumber][j] = strCellValue.trim().trim();
			 
			 }
		  
				
//		   if(rownumber<size) {
//				 System.out.println("Data needs to Add still");
//			 }
//			else if(rownumber==size) {
				
				 listOfLists[rowindex][0]=data;
				 rowindex=rowindex+1;
				 firstrow=0;
				 Lasttrow=0;
				 rownumber=0;
				 size=0;
				 
//			 }
		   System.out.println("---------------------------------------------------------------");
		 }
		   ////Log.info(data.toString());
		
		 if(size1==Lasttrow) {
		 Lasttrow=0;
		 firstrow=0;
		 Lasttrow=0;
		 rownumber=0;
		 size=0;
		 size1=0;
		 }
		 if(size==0 || size==1)
		 {
			 Lasttrow=0;
			 firstrow=0;
			 Lasttrow=0;
			 rownumber=0;
			 size=0;
			 size1=0;
			 
		 }
		// System.out.println(" out Row number in "+i);
		 
		
		 size1=0;
			 }
		  
		 workbook.close();
		 return listOfLists;
		 
	}
	@DataProvider(name="RoobRik1", parallel=true)
	public static Object[][] datareader() throws IOException
	{
		
		 FileInputStream file = new FileInputStream(new File("src\\Data\\InputSheet.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheetAt(0);
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
			
		 }
		 
		 }
		 //Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		 Object[][]  data=null;
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 ////Log.info("Total number of Columns" +r.getLastCellNum());
		 ////Log.info("Total number of Columns" +sheet.getLastRowNum());
		 int firstrow=0;
		 int Lasttrow=0;
		 int rownumber=0;
		 int size=0;
		 int size1=0;
		 String Needtoinclude="No";
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 for(int resion=0;resion<=sheet.getNumMergedRegions()-1;resion++)
			 {
				System.out.println(sheet.getMergedRegion(resion).toString());
				 String numberofrow=sheet.getMergedRegion(resion).toString().substring(sheet.getMergedRegion(resion).toString().indexOf("[")+2, sheet.getMergedRegion(resion).toString().indexOf(":"));
				 if(sheet.getMergedRegion(resion).toString().contains("[A") && isNumeric(numberofrow)&& !sheet.getMergedRegion(resion).toString().contains("[A1:") && i>=sheet.getMergedRegion(resion).getFirstRow() && i<=sheet.getMergedRegion(resion).getLastRow()&&i>Lasttrow )
				 {//System.out.println(sheet.getMergedRegion(resion).toString());
					 firstrow=sheet.getMergedRegion(resion).getFirstRow();
					 Lasttrow=sheet.getMergedRegion(resion).getLastRow();
					 size=Lasttrow-firstrow+1;
					 //firstrow=i;
					 //size1=size;
					 data=new Object[size][r1.getLastCellNum()];
					 Needtoinclude=sheet.getRow(firstrow).getCell(1).toString();
					 System.out.println("Need to include-"+sheet.getRow(firstrow).getCell(1).toString());
					 if(Needtoinclude.equals("No"))
					 {
						 firstrow=0; 
						 //Lasttrow=0;
						 size=0;
						//size1=size1+1; 
						
						 
					 }
					 else
					 {
						 size1=i; 
					 }
				 }
				 
				
			 } 
			 if(firstrow==0 && size==0 && Lasttrow==0 )
			 {
				 firstrow=i;
				 Lasttrow=i;
				 size=Lasttrow-firstrow+1;
				 size1=Lasttrow-firstrow+1;
                 //firstrow=i;
				 data=new Object[size][r1.getLastCellNum()];
				 Needtoinclude=sheet.getRow(firstrow).getCell(1).toString();
			 }
		 
			 ////Log.info(r.getLastCellNum());
		if(Needtoinclude.equals("Yes"))
		{
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==CellType.NUMERIC)
				   {
					   if(DateUtil.isCellDateFormatted(r.getCell(j)))
					   {
						   SimpleDateFormat dt=new SimpleDateFormat("dd/MM/yyyy");
						   strCellValue=dt.format(r.getCell(j).getDateCellValue());
						   //Log.info("The 2Value is in data Format and Value is:"+strCellValue);
					   }
					   else{
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   //Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   }
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   //Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(NullPointerException e)
				   {
					   strCellValue="";
					   //Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[rownumber][j-3] = strCellValue.trim().trim();
			    //data[i-1][j] = ;
			 
			 }
		   data[rownumber][r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[rownumber][r1.getLastCellNum()-1]=r.getCell(1).toString();
		   
		   //Log.info("The Value of this cell is: "+data[rownumber][r1.getLastCellNum()-2]);
		   //Log.info("The Value of this cell is: "+data[rownumber][r1.getLastCellNum()-1]);
		   rownumber=rownumber+1;
		   
		   if(rownumber<size) {
			 }
			else if(rownumber==size) {
				
				 listOfLists[rowindex][0]=data;
				 rowindex=rowindex+1;
				 firstrow=0;
				 Lasttrow=0;
				 rownumber=0;
				 size=0;
				 
			 }
		   
		 }
		   ////Log.info(data.toString());
		
		 if(size1==Lasttrow) {
		 Lasttrow=0;
		 firstrow=0;
		 Lasttrow=0;
		 rownumber=0;
		 size=0;
		 size1=0;
		 }
		 if(size==0 || size==1)
		 {
			 Lasttrow=0;
			 firstrow=0;
			 Lasttrow=0;
			 rownumber=0;
			 size=0;
			 size1=0;
			 
		 }
		 System.out.println(" out Row number in "+i);
		 
		
		 size1=0;
			 }
			 //listOfLists[rowindex][0]=data;
			 //rowindex=rowindex+1;
		 
			 
		 workbook.close();
		 return listOfLists;
		 
	}



public static String readExcel(int rownum, int cellnum, String Role ) throws IOException
{
	 FileInputStream file = new FileInputStream(new File("src\\Data\\"+PropReader.readproperty("InputFileName")+".xlsx"));
	 XSSFWorkbook workbook = new XSSFWorkbook(file);
	 XSSFSheet sheet = workbook.getSheetAt(2);	

	 
	 String value;
	 
	 try {
	value = sheet.getRow(rownum).getCell(cellnum).getStringCellValue().trim();
	 }
	 catch(NullPointerException e)
	 {
		 value="";
	 }
	 
	 return value;
}
//public static String LoadConfig(int rownum, int cellnum, String Role ) throws IOException
//{
//	 FileInputStream file = new FileInputStream(new File("src\\Data\\InputSheet.xlsx"));
//	 XSSFWorkbook workbook = new XSSFWorkbook(file);
//	 XSSFSheet sheet = workbook.getSheetAt(2);	
//
//	 
//	 String value;
//	 
//	 try {
//	value = sheet.getRow(rownum).getCell(cellnum).getStringCellValue().toString().trim();
//	 }
//	 catch(java.lang.NullPointerException e)
//	 {
//		 value="";
//	 }
//	 
//	 return value;
//}

public static String ReadDefectID(String ClassName, String MethodName, String ExceptionName) throws IOException
{
	
	String Class;
	String Method ;
	String Exception1;
	String Defect=null;

    FileInputStream file = new FileInputStream(new File("src\\test\\resources\\dataSource\\Defect_Sheet.xlsx"));
	 XSSFWorkbook workbook = new XSSFWorkbook(file);
	 XSSFSheet sheet = workbook.getSheetAt(0);
	 
	 for(int i=0; i<=sheet.getLastRowNum();i++)
	 {
       try{
		 Class= sheet.getRow(i).getCell(0).getStringCellValue().trim();
//		   ExtentCucumberAdapter.addTestStepLog("Class Name"+Class);
		 if(Class.equalsIgnoreCase(ClassName))
		 {
             try {
				 if(sheet.getRow(i).getCell(1).getCellType()==CellType.NUMERIC)
				 {
					 Method=String.valueOf((int)sheet.getRow(i).getCell(1).getNumericCellValue());
				 }
				 else {
					 Method = sheet.getRow(i).getCell(1).getStringCellValue().trim();
				 }
//				 ExtentCucumberAdapter.addTestStepLog("Method Name"+Method);

				 if(Method.equalsIgnoreCase(MethodName))
				 {
						 if (sheet.getRow(i).getCell(2).getCellType() == CellType.NUMERIC) {
							 Exception1 = String.valueOf((int) sheet.getRow(i).getCell(2).getNumericCellValue());
						 } else {
							 Exception1 = sheet.getRow(i).getCell(2).getStringCellValue().trim();
						 }
//					 ExtentCucumberAdapter.addTestStepLog("Exception Name"+Exception1);

					 if(Exception1.equalsIgnoreCase(ExceptionName)) {
						 if (sheet.getRow(i).getCell(3).getCellType() == CellType.NUMERIC) {
							 Defect = String.valueOf((int) sheet.getRow(i).getCell(3).getNumericCellValue());
						 } else {
							 Defect = sheet.getRow(i).getCell(3).getStringCellValue().trim();
						 }
						 DefectWithSheet=true;
						 if(Defect.equalsIgnoreCase("New Defect"))
						 {
							 Defect = "Old Defect, But Defect ID not created";
						 }
//						 ExtentCucumberAdapter.addTestStepLog("Defect Name"+Defect);
						break;

					 }
				 }
			 }
             catch(NullPointerException e)
             {
				 Defect="New Defect";
             }
			 }
		 }
	   catch(NullPointerException e)
	   {
		   Defect="New Defect";
	   }

	 }

	 if(Defect==null||Defect.isEmpty()||Defect.isBlank())
	 {
		 Defect="New Defect";
	 }
	return Defect;
}

	public static void WriteExcelData(String ClassName, String MethodName, String ExceptionName) throws IOException {

//		ExtentCucumberAdapter.addTestStepLog(String.valueOf(DefectWithSheet));
		if (!DefectWithSheet) {
			FileInputStream file = new FileInputStream(new File("src\\test\\resources\\dataSource\\Defect_Sheet.xlsx"));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.createRow(sheet.getLastRowNum() + 1);

			// Create cells and write data
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(ClassName);

			Cell cell2 = row.createCell(1);
			cell2.setCellValue(MethodName);

			Cell cell3 = row.createCell(2);
			cell3.setCellValue(ExceptionName);

			Cell cell4 = row.createCell(3);
			cell4.setCellValue("New Defect");

			try (FileOutputStream fileOut = new FileOutputStream("src\\test\\resources\\dataSource\\Defect_Sheet.xlsx")) {
				workbook.write(fileOut);
				System.out.println("Excel file has been created successfully!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				// Close the workbook
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		DefectWithSheet=false;
	}

}
