
package getexcelv1;
import com.mysql.jdbc.Connection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Propietario
 */
public class GetExcelv1 {

   public static ArrayList nombreArrayList = new ArrayList<String>();
   public static  Date date = new Date();
   public static   DateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");
   public static    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
   
   public static void readXLSFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream("C:\\Users\\Propietario\\Downloads\\prueba2.xls");
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		HSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();
		
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}
	
	}
	
	public static void writeXLSFile() throws IOException {
		
		String excelFileName = "C:\\Users\\Propietario\\Downloads\\Test.xls";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			HSSFRow row = sheet.createRow(r);
	
			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				HSSFCell cell = row.createCell(c);
				
				cell.setCellValue("Cell "+r+" "+c);
			}
		}
		
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}


	public static void readXLSXFile() throws IOException
	{        
		InputStream ExcelFileToRead = new FileInputStream("C:\\Users\\Propietario\\Downloads\\prueba.xlsx");
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);		
		XSSFWorkbook test = new XSSFWorkbook(); 		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();
                int i=0;
                DataFormatter formatter = new DataFormatter(Locale.US);

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
                   
                   String temporal="";
			while (cells.hasNext())//next fila
			{
				cell=(XSSFCell) cells.next();
		
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
				//	System.out.print(cell.getStringCellValue()+" "+i);
                                         nombreArrayList.add(cell.getStringCellValue());
                                        
                                }
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
				//	System.out.print(cell.getNumericCellValue()+" "+i);
                                          nombreArrayList.add(cell.getNumericCellValue());
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
                                
                                  
                                 
			}//END ROWS
                         i++;
                         escribir(nombreArrayList);
                       nombreArrayList.clear();
			System.out.println();
		}//END FILA
	
	}
	
	public static void writeXLSXFile() throws IOException {
		
		String excelFileName = "C:\\Users\\Propietario\\Downloads\\Test.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			XSSFRow row = sheet.createRow(r);

			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				XSSFCell cell = row.createCell(c);
	
				cell.setCellValue("Cell "+r+" "+c);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public static void main(String[] args) throws IOException
        {
		
        	//writeXLSFile();
         	//readXLSFile();		
		//writeXLSXFile();nombreArrayList.size()
		readXLSXFile();
                
        // for(int i = 0;i<4;i++)
        // {
       
         
       
 // System.out.println("DC|"+"3.3|"+"PJVERACRUZ|"+nombreArrayList.get(0)+"|"+hourFormat.format(date)+"T"+dateFormat.format(date)+"|99"+"|"+nombreArrayList.get(1)+"|"+nombreArrayList.get(2)+"|MXN||"+nombreArrayList.get(3)+"|N|");
	}public static int contador=1;
        ///modicar el archivo creado 
        public static void escribir(ArrayList nombreArrayList){
            
              System.out.println("tamano "+nombreArrayList.size()+"entro "+contador);
             Iterator it = nombreArrayList.iterator();
                 FileWriter fichero = null;
                 PrintWriter pw = null;
                 BufferedWriter pw2 = null;
                 try{
                      fichero = new FileWriter("prueba2.txt",true);
                       pw2 = new BufferedWriter(fichero);
                      pw = new PrintWriter(fichero);
                 }catch (Exception e) {
                 
                 e.printStackTrace();
                 }finally {
           try { 
                 
              pw.println("DC|"+"3.3|"+"PJVERACRUZ|"+nombreArrayList.get(0)+"|"+dateFormat.format(date)+"T"+hourFormat.format(date)+"|99"+"|"+nombreArrayList.get(1)+"|"+nombreArrayList.get(2)+"|MXN||"+nombreArrayList.get(3)+"|N|");
              pw.println("PUE|91170||||");
              pw.println("EM|PJE970419FZ2|PODER JUDICIAL DEL ESTADO DE VERACRUZ");
              pw.println("CNE|603||F5434322360||IP||");
              pw.println("RC|"+nombreArrayList.get(4)+"|"+nombreArrayList.get(5)+"|P01");
              pw.println("CNR|"+nombreArrayList.get(6)+"|"+nombreArrayList.get(7)+"|"+nombreArrayList.get(8)+"|"+nombreArrayList.get(9)+"|"+nombreArrayList.get(10)
              +"|"+nombreArrayList.get(11)+"|"+nombreArrayList.get(12)+"|"+nombreArrayList.get(13)+"|"+nombreArrayList.get(14)+"|"+nombreArrayList.get(15)+"|"+nombreArrayList.get(16)
              +"|"+nombreArrayList.get(17)+"|"+nombreArrayList.get(18)+"|"+nombreArrayList.get(19)+"|01|02|"+nombreArrayList.get(20)+"|"+nombreArrayList.get(21)
              +"|"+nombreArrayList.get(22)+"|1|"+nombreArrayList.get(23)+"|"+nombreArrayList.get(24)+"|"+nombreArrayList.get(25)+"|"+nombreArrayList.get(26)+"|"+nombreArrayList.get(27)+"|VER");
              pw.println("CN|84111505|1|ACT|Pago de nómina|"+nombreArrayList.get(28)+"|"+nombreArrayList.get(29)+"|"+nombreArrayList.get(30));
              pw.println("MI|"+nombreArrayList.get(31)+"||");
              pw.println("CNP|"+nombreArrayList.get(32)+"|"+nombreArrayList.get(33)+"|"+nombreArrayList.get(34)+"||||||||||");
              String val=String.valueOf(nombreArrayList.get(35));
              String val2=String.valueOf(nombreArrayList.get(36));
              System.out.println("valor 11111:  "+val+"  valor 2:   "+val2);
             if((!"0".equals(val))&&("0.0".equals(val2))){              
                   pw.println("NPD||001|5111-11300001|Sueldos y Salariossi|"+nombreArrayList.get(35)+"|0.0");
                  
             }else{
                  pw.println("NPD||001|5111-11300001|Sueldos y Salariosno|"+"0.0|"+nombreArrayList.get(36));
             }
              
              pw.println("NVOEMP");
        
        
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
       contador++;
        }
}
