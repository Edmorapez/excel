
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
              System.out.println(nombreArrayList);
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
              pw.println("CNP|"+nombreArrayList.get(32)+"|||"+nombreArrayList.get(33)+"|"+nombreArrayList.get(34)+"||||||||||");
              
             String a1=String.valueOf(nombreArrayList.get(35));
             String a2=String.valueOf(nombreArrayList.get(36));                        
             if((!"0.0".equals(a1))&&("0.0".equals(a2))){              
                   pw.println("NPD||001|5111-11300001|Sueldos y Salarios|"+nombreArrayList.get(35)+"|0.0");                  
             }else{
                 if(("0.0".equals(a1))&&(!"0.0".equals(a2))){
                  pw.println("NPD||001|5111-11300001|Sueldos y Salarios|"+"0.0|"+nombreArrayList.get(36));
                 }else{
                     if(("0.0".equals(a1))&&("0.0".equals(a2))){                      
                     }
                     else{
                         if((!"0.0".equals(a1))&&(!"0.0".equals(a2))){
                              pw.println("NPD||001|5111-11300001|Sueldos y Salarios|"+nombreArrayList.get(35)+"|"+nombreArrayList.get(36));
                         }
                     }
                 }
             }
             
             String a3=String.valueOf(nombreArrayList.get(37));
             String a4=String.valueOf(nombreArrayList.get(38));                        
             if((!"0.0".equals(a3))&&("0.0".equals(a4))){              
                   pw.println("NPD||038|5113-13300001|Compensación por Servicios de Turno Vespertino|"+a3+"|0.0");                  
             }else{
                 if(("0.0".equals(a3))&&(!"0.0".equals(a4))){
                  pw.println("NPD||038|5113-13300001|Compensación por Servicios de Turno Vespertino|"+"0.0|"+a4);
                 }else{
                     if(("0.0".equals(a3))&&("0.0".equals(a4))){                      
                     }
                     else{
                         if((!"0.0".equals(a3))&&(!"0.0".equals(a4))){
                              pw.println("NPD||038|5113-13300001|Compensación por Servicios de Turno Vespertino|"+a3+"|"+a4);
                         }
                     }
                 }
             }
             String a5=String.valueOf(nombreArrayList.get(39));
             String a6=String.valueOf(nombreArrayList.get(40));                        
             if((!"0.0".equals(a5))&&("0.0".equals(a6))){              
                   pw.println("NPD||038|5115-15400001|Ayuda para Pasajes|"+a5+"|0.0");                  
             }else{
                 if(("0.0".equals(a5))&&(!"0.0".equals(a6))){
                  pw.println("NPD||038|5115-15400001|Ayuda para Pasajes|"+"0.0|"+a6);
                 }else{
                     if(("0.0".equals(a5))&&("0.0".equals(a6))){                      
                     }
                     else{
                         if((!"0.0".equals(a5))&&(!"0.0".equals(a6))){
                              pw.println("NPD||038|5115-15400001|Ayuda para Pasajes|"+a5+"|"+a6);
                         }
                     }
                 }
             }
             String a7=String.valueOf(nombreArrayList.get(41));
             String a8=String.valueOf(nombreArrayList.get(42));                        
             if((!"0.0".equals(a7))&&("0.0".equals(a8))){              
                   pw.println("NPD||001|5112-12200001|Sueldos al Personal Eventual|"+a7+"|0.0");                  
             }else{
                 if(("0.0".equals(a7))&&(!"0.0".equals(a8))){
                  pw.println("NPD||001|5112-12200001|Sueldos al Personal Eventual|"+"0.0|"+a8);
                 }else{
                     if(("0.0".equals(a7))&&("0.0".equals(a8))){                      
                     }
                     else{
                         if((!"0.0".equals(a7))&&(!"0.0".equals(a8))){
                              pw.println("NPD||001|5112-12200001|Sueldos al Personal Eventual|"+a7+"|"+a8);
                         }
                     }
                 }
             }
             String a9=String.valueOf(nombreArrayList.get(43));
             String a10=String.valueOf(nombreArrayList.get(44));                        
             if((!"0.0".equals(a9))&&("0.0".equals(a10))){              
                   pw.println("NPD||038|5116-17100001|Estímulos por Antigüedad|"+a9+"|0.0");                  
             }else{
                 if(("0.0".equals(a9))&&(!"0.0".equals(a10))){
                  pw.println("NPD||038|5116-17100001|Estímulos por Antigüedad|"+"0.0|"+a10);
                 }else{
                     if(("0.0".equals(a9))&&("0.0".equals(a10))){                      
                     }
                     else{
                         if((!"0.0".equals(a9))&&(!"0.0".equals(a10))){
                              pw.println("NPD||038|5116-17100001|Estímulos por Antigüedad|"+a9+"|"+a10);
                         }
                     }
                 }
             }
             String b1=String.valueOf(nombreArrayList.get(45));
             String b2=String.valueOf(nombreArrayList.get(46));                        
             if((!"0.0".equals(b1))&&("0.0".equals(b2))){              
                   pw.println("NPD||038|5113-13100001|Quinquenios|"+b1+"|0.0");                  
             }else{
                 if(("0.0".equals(b1))&&(!"0.0".equals(b2))){
                  pw.println("NPD||038|5113-13100001|Quinquenios|"+"0.0|"+b2);
                 }else{
                     if(("0.0".equals(b1))&&("0.0".equals(b2))){                      
                     }
                     else{
                         if((!"0.0".equals(b1))&&(!"0.0".equals(b2))){
                              pw.println("NPD||038|5113-13100001|Quinquenios|"+b1+"|"+b2);
                         }
                     }
                 }
             }
             String b3=String.valueOf(nombreArrayList.get(47));
             String b4=String.valueOf(nombreArrayList.get(48));                        
             if((!"0.0".equals(b3))&&("0.0".equals(b4))){              
                   pw.println("NPD||038|5115-15400004|Despensa|"+b3+"|0.0");                  
             }else{
                 if(("0.0".equals(b3))&&(!"0.0".equals(b4))){
                  pw.println("NPD||038|5115-15400004|Despensa|"+"0.0|"+b4);
                 }else{
                     if(("0.0".equals(b3))&&("0.0".equals(b4))){                      
                     }
                     else{
                         if((!"0.0".equals(b3))&&(!"0.0".equals(b4))){
                              pw.println("NPD||038|5115-15400004|Despensa|"+b3+"|"+b4);
                         }
                     }
                 }
             }
             String b5=String.valueOf(nombreArrayList.get(49));
             String b6=String.valueOf(nombreArrayList.get(50));                        
             if((!"0.0".equals(b5))&&("0.0".equals(b6))){              
                   pw.println("NPD||038|5113-13100002|Quinquenio Nueva Generación|"+b5+"|0.0");                  
             }else{
                 if(("0.0".equals(b5))&&(!"0.0".equals(b6))){
                  pw.println("NPD||038|5113-13100002|Quinquenio Nueva Generación|"+"0.0|"+b6);
                 }else{
                     if(("0.0".equals(b5))&&("0.0".equals(b6))){                      
                     }
                     else{
                         if((!"0.0".equals(b5))&&(!"0.0".equals(b6))){
                              pw.println("NPD||038|5113-13100002|Quinquenio Nueva Generación|"+b5+"|"+b6);
                         }
                     }
                 }
             }
            
              
             String b7=String.valueOf(nombreArrayList.get(51));
             String b8=String.valueOf(nombreArrayList.get(52));                        
             if((!"0.0".equals(b7))&&("0.0".equals(b8))){              
                   pw.println("NPD||038|5115-15400005|Previsión Social Múltiple|"+b7+"|0.0");                  
             }else{
                 if(("0.0".equals(b7))&&(!"0.0".equals(b8))){
                  pw.println("NPD||038|5115-15400005|Previsión Social Múltiple|"+"0.0|"+b8);
                 }else{
                     if(("0.0".equals(b7))&&("0.0".equals(b8))){                      
                     }
                     else{
                         if((!"0.0".equals(b7))&&(!"0.0".equals(b8))){
                              pw.println("NPD||038|5115-15400005|Previsión Social Múltiple|"+b7+"|"+b8);
                         }
                     }
                 }
             }
              /*Liquidaciones por Indemnizaciones y Sueldos y Salarios Caídos 51 y 52*/
             String x1=String.valueOf(nombreArrayList.get(53));
             String x2=String.valueOf(nombreArrayList.get(54));                        
             if((!"0.0".equals(x1))&&("0.0".equals(x2))){              
                   pw.println("NPD||025|5115-15300001|Liquidaciones por Indemnizaciones y Sueldos y Salarios Caídos|"+x1+"|0.0");                  
             }else{
                 if(("0.0".equals(x1))&&(!"0.0".equals(x2))){
                  pw.println("NPD||025|5115-15300001|Liquidaciones por Indemnizaciones y Sueldos y Salarios Caídos|"+"0.0|"+x2);
                 }else{
                     if(("0.0".equals(x1))&&("0.0".equals(x2))){                      
                     }
                     else{
                         if((!"0.0".equals(x1))&&(!"0.0".equals(x2))){
                              pw.println("NPD||025|5115-15300001|Liquidaciones por Indemnizaciones y Sueldos y Salarios Caídos|"+x1+"|"+x2);
                         }
                     }
                 }
             }
             String b9=String.valueOf(nombreArrayList.get(55));
             String b10=String.valueOf(nombreArrayList.get(56));                        
             if((!"0.0".equals(b9))&&("0.0".equals(b10))){              
                   pw.println("NPD||010|5116-17100004|Estímulo por Puntualidad y Asistencia|"+b9+"|0.0");                  
             }else{
                 if(("0.0".equals(b9))&&(!"0.0".equals(b10))){
                  pw.println("NPD||010|5116-17100004|Estímulo por Puntualidad y Asistencia|"+"0.0|"+b10);
                 }else{
                     if(("0.0".equals(b9))&&("0.0".equals(b10))){                      
                     }
                     else{
                         if((!"0.0".equals(b9))&&(!"0.0".equals(b10))){
                              pw.println("NPD||010|5116-17100004|Estímulo por Puntualidad y Asistencia|"+b9+"|"+b10);
                         }
                     }
                 }
             }
             String c1=String.valueOf(nombreArrayList.get(57));
             String c2=String.valueOf(nombreArrayList.get(58));                        
             if((!"0.0".equals(c1))&&("0.0".equals(c2))){              
                   pw.println("NPD||038|5115-15400007|Pago de Días Económicos No Disfrutados|"+c1+"|0.0");                  
             }else{
                 if(("0.0".equals(c1))&&(!"0.0".equals(c2))){
                  pw.println("NPD||038|5115-15400007|Pago de Días Económicos No Disfrutados|"+"0.0|"+c2);
                 }else{
                     if(("0.0".equals(c1))&&("0.0".equals(c2))){                      
                     }
                     else{
                         if((!"0.0".equals(c1))&&(!"0.0".equals(c2))){
                              pw.println("NPD||038|5115-15400007|Pago de Días Económicos No Disfrutados|"+c1+"|"+c2);
                         }
                     }
                 }
             }
             String c3=String.valueOf(nombreArrayList.get(59));
             String c4=String.valueOf(nombreArrayList.get(60));                        
             if((!"0.0".equals(c3))&&("0.0".equals(c4))){              
                   pw.println("NPD||038|5113-13200004|Bono Anual de Despensa|"+c3+"|0.0");                  
             }else{
                 if(("0.0".equals(c3))&&(!"0.0".equals(c4))){
                  pw.println("NPD||038|5113-13200004|Bono Anual de Despensa|"+"0.0|"+c4);
                 }else{
                     if(("0.0".equals(c3))&&("0.0".equals(c4))){                      
                     }
                     else{
                         if((!"0.0".equals(c3))&&(!"0.0".equals(c4))){
                              pw.println("NPD||038|5113-13200004|Bono Anual de Despensa|"+c3+"|"+c4);
                         }
                     }
                 }
             }
             String c5=String.valueOf(nombreArrayList.get(61));
             String c6=String.valueOf(nombreArrayList.get(62));                        
             if((!"0.0".equals(c5))&&("0.0".equals(c6))){              
                   pw.println("NPD||038|5116-17100006|Estímulo a Servidores Públicos|"+c5+"|0.0");                  
             }else{
                 if(("0.0".equals(c5))&&(!"0.0".equals(c6))){
                  pw.println("NPD||038|5116-17100006|Estímulo a Servidores Públicos|"+"0.0|"+c6);
                 }else{
                     if(("0.0".equals(c5))&&("0.0".equals(c6))){                      
                     }
                     else{
                         if((!"0.0".equals(c5))&&(!"0.0".equals(c6))){
                              pw.println("NPD||038|5116-17100006|Estímulo a Servidores Públicos|"+c5+"|"+c6);
                         }
                     }
                 }
             }
             String c7=String.valueOf(nombreArrayList.get(63));
             String c8=String.valueOf(nombreArrayList.get(64));                        
             if((!"0.0".equals(c7))&&("0.0".equals(c8))){              
                   pw.println("NPD||034|5115-15400014|Ayuda para la Adquisición de Utiles Escolares|"+c7+"|0.0");                  
             }else{
                 if(("0.0".equals(c7))&&(!"0.0".equals(c8))){
                  pw.println("NPD||034|5115-15400014|Ayuda para la Adquisición de Utiles Escolares|"+"0.0|"+c8);
                 }else{
                     if(("0.0".equals(c7))&&("0.0".equals(c8))){                      
                     }
                     else{
                         if((!"0.0".equals(c7))&&(!"0.0".equals(c8))){
                              pw.println("NPD||034|5115-15400014|Ayuda para la Adquisición de Utiles Escolares|"+c7+"|"+c8);
                         }
                     }
                 }
             }
             String c9=String.valueOf(nombreArrayList.get(65));
             String c10=String.valueOf(nombreArrayList.get(66));                        
             if((!"0.0".equals(c9))&&("0.0".equals(c10))){              
                   pw.println("NPD||038|5116-17100007|Premio Mensual por Buen Desempeño|"+c9+"|0.0");                  
             }else{
                 if(("0.0".equals(c9))&&(!"0.0".equals(c10))){
                  pw.println("NPD||038|5116-17100007|Premio Mensual por Buen Desempeño|"+"0.0|"+c10);
                 }else{
                     if(("0.0".equals(c9))&&("0.0".equals(c10))){                      
                     }
                     else{
                         if((!"0.0".equals(c9))&&(!"0.0".equals(c10))){
                              pw.println("NPD||038|5116-17100007|Premio Mensual por Buen Desempeño|"+c9+"|"+c10);
                         }
                     }
                 }
             }
             String d1=String.valueOf(nombreArrayList.get(67));
             String d2=String.valueOf(nombreArrayList.get(68));                        
             if((!"0.0".equals(d1))&&("0.0".equals(d2))){              
                   pw.println("NPD||038|5115-15400017|Pago del Día de las Madres y Padres|"+d1+"|0.0");                  
             }else{
                 if(("0.0".equals(d1))&&(!"0.0".equals(d2))){
                  pw.println("NPD||038|5115-15400017|Pago del Día de las Madres y Padres|"+"0.0|"+d2);
                 }else{
                     if(("0.0".equals(d1))&&("0.0".equals(d2))){                      
                     }
                     else{
                         if((!"0.0".equals(d1))&&(!"0.0".equals(d2))){
                              pw.println("NPD||038|5115-15400017|Pago del Día de las Madres y Padres|"+d1+"|"+d2);
                         }
                     }
                 }
             }
             String d3=String.valueOf(nombreArrayList.get(69));
             String d4=String.valueOf(nombreArrayList.get(70));                        
             if((!"0.0".equals(d3))&&("0.0".equals(d4))){              
                   pw.println("NPD||038|5115-15400023|Compensación Temporal Compactable|"+d3+"|0.0");                  
             }else{
                 if(("0.0".equals(d3))&&(!"0.0".equals(d4))){
                  pw.println("NPD||038|5115-15400023|Compensación Temporal Compactable|"+"0.0|"+d4);
                 }else{
                     if(("0.0".equals(d3))&&("0.0".equals(d4))){                      
                     }
                     else{
                         if((!"0.0".equals(d3))&&(!"0.0".equals(d4))){
                              pw.println("NPD||038|5115-15400023|Compensación Temporal Compactable|"+d3+"|"+d4);
                         }
                     }
                 }
             }
             String d5=String.valueOf(nombreArrayList.get(71));
             String d6=String.valueOf(nombreArrayList.get(72));                        
             if((!"0.0".equals(d5))&&("0.0".equals(d6))){              
                   pw.println("NPD||038|5113-13400007|Compensación Administrativa|"+d5+"|0.0");                  
             }else{
                 if(("0.0".equals(d5))&&(!"0.0".equals(d6))){
                  pw.println("NPD||038|5113-13400007|Compensación Administrativa|"+"0.0|"+d6);
                 }else{
                     if(("0.0".equals(d5))&&("0.0".equals(d6))){                      
                     }
                     else{
                         if((!"0.0".equals(d5))&&(!"0.0".equals(d6))){
                              pw.println("NPD||038|5113-13400007|Compensación Administrativa|"+d5+"|"+d6);
                         }
                     }
                 }
             }
             String d7=String.valueOf(nombreArrayList.get(73));
             String d8=String.valueOf(nombreArrayList.get(74));                        
             if((!"0.0".equals(d7))&&("0.0".equals(d8))){              
                   pw.println("NPD||038|5112-12300002|Ayuda por Servicios|"+d7+"|0.0");                  
             }else{
                 if(("0.0".equals(d7))&&(!"0.0".equals(d8))){
                  pw.println("NPD||038|5112-12300002|Ayuda por Servicios|"+"0.0|"+d8);
                 }else{
                     if(("0.0".equals(d7))&&("0.0".equals(d8))){                      
                     }
                     else{
                         if((!"0.0".equals(d7))&&(!"0.0".equals(d8))){
                              pw.println("NPD||038|5112-12300002|Ayuda por Servicios|"+d7+"|"+d8);
                         }
                     }
                 }
             }
             String d9=String.valueOf(nombreArrayList.get(75));
             String d10=String.valueOf(nombreArrayList.get(76));                        
             if((!"0.0".equals(d9))&&("0.0".equals(d10))){              
                   pw.println("NPD||038|5115-15400025|Asignación por Actividades Culturales|"+d9+"|0.0");                  
             }else{
                 if(("0.0".equals(d9))&&(!"0.0".equals(d10))){
                  pw.println("NPD||038|5115-15400025|Asignación por Actividades Culturales|"+"0.0|"+d10);
                 }else{
                     if(("0.0".equals(d9))&&("0.0".equals(d10))){                      
                     }
                     else{
                         if((!"0.0".equals(d9))&&(!"0.0".equals(d10))){
                              pw.println("NPD||038|5115-15400025|Asignación por Actividades Culturales|"+d9+"|"+d10);
                         }
                     }
                 }
             }
             String e1=String.valueOf(nombreArrayList.get(77));
             String e2=String.valueOf(nombreArrayList.get(78));                        
             if((!"0.0".equals(e1))&&("0.0".equals(e2))){              
                   pw.println("NPD||038|5115-15500002|Ayuda para Capacitación y Desarrollo|"+e1+"|0.0");                  
             }else{
                 if(("0.0".equals(e1))&&(!"0.0".equals(e2))){
                  pw.println("NPD||038|5115-15500002|Ayuda para Capacitación y Desarrollo|"+"0.0|"+e2);
                 }else{
                     if(("0.0".equals(e1))&&("0.0".equals(e2))){                      
                     }
                     else{
                         if((!"0.0".equals(e1))&&(!"0.0".equals(e2))){
                              pw.println("NPD||038|5115-15500002|Ayuda para Capacitación y Desarrollo|"+e1+"|"+e2);
                         }
                     }
                 }
             }
             String e3=String.valueOf(nombreArrayList.get(79));
             String e4=String.valueOf(nombreArrayList.get(80));                        
             if((!"0.0".equals(e3))&&("0.0".equals(e4))){              
                   pw.println("NPD||002|5113-13200002|Gratificación Anual|"+e3+"|0.0");                  
             }else{
                 if(("0.0".equals(e3))&&(!"0.0".equals(e4))){
                  pw.println("NPD||002|5113-13200002|Gratificación Anual|"+"0.0|"+e4);
                 }else{
                     if(("0.0".equals(e3))&&("0.0".equals(e4))){                      
                     }
                     else{
                         if((!"0.0".equals(e3))&&(!"0.0".equals(e4))){
                              pw.println("NPD||002|5113-13200002|Gratificación Anual|"+e3+"|"+e4);
                         }
                     }
                 }
             }
             String e5=String.valueOf(nombreArrayList.get(81));
             String e6=String.valueOf(nombreArrayList.get(82));                        
             if((!"0.0".equals(e5))&&("0.0".equals(e6))){              
                   pw.println("NPD||038|5115-15400030|Gratificación Extraordinaria|"+e5+"|0.0");                  
             }else{
                 if(("0.0".equals(e5))&&(!"0.0".equals(e6))){
                  pw.println("NPD||038|5115-15400030|Gratificación Extraordinaria|"+"0.0|"+e6);
                 }else{
                     if(("0.0".equals(e5))&&("0.0".equals(e4))){                      
                     }
                     else{
                         if((!"0.0".equals(e5))&&(!"0.0".equals(e6))){
                              pw.println("NPD||038|5115-15400030|Gratificación Extraordinaria|"+e5+"|"+e6);
                         }
                     }
                 }
             }
             String e7=String.valueOf(nombreArrayList.get(83));
             String e8=String.valueOf(nombreArrayList.get(84));                        
             if((!"0.0".equals(e7))&&("0.0".equals(e8))){              
                   pw.println("NPD||038|5115-15400031|Gratificación|"+e7+"|0.0");                  
             }else{
                 if(("0.0".equals(e7))&&(!"0.0".equals(e8))){
                  pw.println("NPD||038|5115-154000310|Gratificación|"+"0.0|"+e8);
                 }else{
                     if(("0.0".equals(e7))&&("0.0".equals(e8))){                      
                     }
                     else{
                         if((!"0.0".equals(e7))&&(!"0.0".equals(e8))){
                              pw.println("NPD||038|5115-15400031|Gratificación|"+e7+"|"+e8);
                         }
                     }
                 }
             }
             String e9=String.valueOf(nombreArrayList.get(85));
             String e10=String.valueOf(nombreArrayList.get(86));                        
             if((!"0.0".equals(e9))&&("0.0".equals(e10))){              
                   pw.println("NPD||038|5115-15400030|Compensación garantizada|"+e9+"|0.0");                  
             }else{
                 if(("0.0".equals(e9))&&(!"0.0".equals(e10))){
                  pw.println("NPD||038|5115-15400030|Compensación garantizada|"+"0.0|"+e10);
                 }else{
                     if(("0.0".equals(e9))&&("0.0".equals(e8))){                      
                     }
                     else{
                         if((!"0.0".equals(e9))&&(!"0.0".equals(e10))){
                              pw.println("NPD||038|5115-15400030|Compensación garantizada|"+e9+"|"+e10);
                         }
                     }
                 }
             }
             String f1=String.valueOf(nombreArrayList.get(87));
             String f2=String.valueOf(nombreArrayList.get(88));                        
             if((!"0.0".equals(f1))&&("0.0".equals(f2))){              
                   pw.println("NPD||038|5115-15400030|Compensación por Grado de Responsabilidad|"+f1+"|0.0");                  
             }else{
                 if(("0.0".equals(f1))&&(!"0.0".equals(f2))){
                  pw.println("NPD||038|5115-15400030|Compensación por Grado de Responsabilidad|"+"0.0|"+f2);
                 }else{
                     if(("0.0".equals(f1))&&("0.0".equals(f2))){                      
                     }
                     else{
                         if((!"0.0".equals(f1))&&(!"0.0".equals(f2))){
                              pw.println("NPD||038|5115-15400030|Compensación por Grado de Responsabilidad|"+f1+"|"+f2);
                         }
                     }
                 }
             }
             String f3=String.valueOf(nombreArrayList.get(89));
             String f4=String.valueOf(nombreArrayList.get(90));                        
             if((!"0.0".equals(f3))&&("0.0".equals(f4))){              
                   pw.println("NPD||038|5115-15400030|Bono Único Extraordinario|"+f3+"|0.0");                  
             }else{
                 if(("0.0".equals(f3))&&(!"0.0".equals(f4))){
                  pw.println("NPD||038|5115-15400030|Bono Único Extraordinario|"+"0.0|"+f4);
                 }else{
                     if(("0.0".equals(f3))&&("0.0".equals(f4))){                      
                     }
                     else{
                         if((!"0.0".equals(f3))&&(!"0.0".equals(f4))){
                              pw.println("NPD||038|5115-15400030|Bono Único Extraordinario|"+f3+"|"+f4);
                         }
                     }
                 }
             }
             String f5=String.valueOf(nombreArrayList.get(91));
             String f6=String.valueOf(nombreArrayList.get(92));                        
             if((!"0.0".equals(f5))&&("0.0".equals(f6))){              
                   pw.println("NPD||002|5115-15400030|Gratificación Anual de Compensación|"+f5+"|0.0");                  
             }else{
                 if(("0.0".equals(f5))&&(!"0.0".equals(f6))){
                  pw.println("NPD||002|5115-15400030|Gratificación Anual de Compensación|"+"0.0|"+f6);
                 }else{
                     if(("0.0".equals(f5))&&("0.0".equals(f6))){                      
                     }
                     else{
                         if((!"0.0".equals(f5))&&(!"0.0".equals(f6))){
                              pw.println("NPD||002|5115-15400030|Gratificación Anual de Compensación|"+f5+"|"+f6);
                         }
                     }
                 }
             }
             String f7=String.valueOf(nombreArrayList.get(93));
             String f8=String.valueOf(nombreArrayList.get(94));                        
             if((!"0.0".equals(f7))&&("0.0".equals(f8))){              
                   pw.println("NPD||021|5113-13200003|Prima Vacacional|"+f7+"|0.0");                  
             }else{
                 if(("0.0".equals(f7))&&(!"0.0".equals(f8))){
                  pw.println("NPD||021|5113-13200003|Prima Vacacional|"+"0.0|"+f8);
                 }else{
                     if(("0.0".equals(f7))&&("0.0".equals(f8))){                      
                     }
                     else{
                         if((!"0.0".equals(f7))&&(!"0.0".equals(f8))){
                              pw.println("NPD||021|5113-13200003|Prima Vacacional|"+f7+"|"+f8);
                         }
                     }
                 }
             }
             /**/
              ////OTROS DESCUENTOS E IMPUESTOS RETENIDOS
             /* */
             
             String f9=String.valueOf(nombreArrayList.get(95));
             String f10=String.valueOf(nombreArrayList.get(96));                        
             if((!"0.0".equals(f9))&&("0.0".equals(f10))){              
                     
                    pw.println("CND|"+f9+"|0.0");
             }else{
                 if(("0.0".equals(f9))&&(!"0.0".equals(f10))){
                
                   pw.println("CND|0.0|"+f10);
                 }else{
                     if(("0.0".equals(f9))&&("0.0".equals(f10))){                      
                     }
                     else{
                         if((!"0.0".equals(f9))&&(!"0.0".equals(f10))){
                              pw.println("CND|"+f9+"|"+f10);
                         }
                     }
                 }
             }
             //////////////////////////////////DEDUCCIONESSSSSS////////////////////////////////////////////////////////
             String g1=String.valueOf(nombreArrayList.get(97));                        
             if(!"0.0".equals(g1)){              
                     
                    pw.println("NDD|004|2117-60000002|Ciep procasa|"+g1);
             }
             String g2=String.valueOf(nombreArrayList.get(98));
             if(!"0.0".equals(g2)){              
                     
                    pw.println("NDD|019|2117-40000001|Sindicato del Poder Judicial|"+g2);
             }
              String g3=String.valueOf(nombreArrayList.get(99));
             if(!"0.0".equals(g3)){              
                     
                    pw.println("NDD|002|2117-10000001|Isr|"+g3);
             }
               String g4=String.valueOf(nombreArrayList.get(100));
             if(!"0.0".equals(g4)){              
                     
                    pw.println("NDD|071|5115-15400030|Ajuste en Subsidio para el empleo entregado al trabajador|"+g4);
             }
               String g5=String.valueOf(nombreArrayList.get(101));
             if(!"0.0".equals(g5)){              
                     
                    pw.println("NDD|001|2117-20000001|Cuota del Imss|"+g5);
             }
             String g6=String.valueOf(nombreArrayList.get(102));
             if(!"0.0".equals(g6)){              
                     
                    pw.println("NDD|003|2117-60000007|Seguro de Retiro|"+g6);
             }
             String g7=String.valueOf(nombreArrayList.get(103));
             if(!"0.0".equals(g7)){              
                     
                    pw.println("NDD|004|2117-60000006|Cruz Roja|"+g7);
             }
             String g8=String.valueOf(nombreArrayList.get(104));
             if(!"0.0".equals(g8)){              
                     
                    pw.println("NDD|004|2117-60000005|Colegio de Jueces|"+g8);
             }
           
             String h1=String.valueOf(nombreArrayList.get(105));
             if(!"0.0".equals(h1)){              
                     
                    pw.println("NDD|004|2117-50000001|Seguro Individual Metlife|"+h1);
             }
             String h2=String.valueOf(nombreArrayList.get(106));
             if(!"0.0".equals(h2)){              
                     
                    pw.println("NDD|004|2117-50000002|Seguro Adicional Metlife|"+h2);
             }
             String h3=String.valueOf(nombreArrayList.get(107));
             if(!"0.0".equals(h3)){              
                     
                    pw.println("NDD|001|2117-30000011|Cuota del Ipe 287|"+h3);
             }
             String h4=String.valueOf(nombreArrayList.get(108));
             if(!"0.0".equals(h4)){              
                     
                    pw.println("NDD|001|2117-30000001|Cuota del Ipe|"+h4);
             }
             String h5=String.valueOf(nombreArrayList.get(109));
             if(!"0.0".equals(h5)){              
                     
                    pw.println("NDD|004|2117-30000005|Préstamo Especial Emergente|"+h5);
             }
             String h6=String.valueOf(nombreArrayList.get(110));
             if(!"0.0".equals(h6)){              
                     
                    pw.println("NDD|004|2117-30000002|Préstamo Hipotecario Ipe|"+h6);
             }
             String h7=String.valueOf(nombreArrayList.get(111));
             if(!"0.0".equals(h7)){              
                     
                    pw.println("NDD|004|2117-30000003|Préstamo Corto Plazo Bis|"+h7);
             }
             String h8=String.valueOf(nombreArrayList.get(112));
             if(!"0.0".equals(h8)){              
                     
                    pw.println("NDD|001|2117-30000004|Cuota Ipe Nueva Generación|"+h8);
             }
             String h9=String.valueOf(nombreArrayList.get(113));
             if(!"0.0".equals(h9)){              
                     
                    pw.println("NDD|004|2117-60000001|Crédito Farmacias del Magisterio|"+h9);
             }
             String h10=String.valueOf(nombreArrayList.get(114));
             if(!"0.0".equals(h10)){              
                     
                    pw.println("NDD|004|4399-10000005|Sanciones Disciplinarias|"+h10);
             }
              String i0=String.valueOf(nombreArrayList.get(115));
             if(!"0.0".equals(i0)){              
                     
                    pw.println("NDD|020|4399-10000006|Sanciones por Retardos|"+i0);
             }
              String i1=String.valueOf(nombreArrayList.get(116));
             if(!"0.0".equals(i1)){              
                     
                    pw.println("NDD|020|4399-10000007|Faltas de Asistencia|"+i1);
             }
              String i2=String.valueOf(nombreArrayList.get(117));
             if(!"0.0".equals(i2)){              
                     
                    pw.println("NDD|007|2117-60000003|Descuentos Judiciales|"+i2);
             }
              String i3=String.valueOf(nombreArrayList.get(118));
             if(!"0.0".equals(i3)){              
                     
                    pw.println("NDD|013|1122-00000000|Reintegros|"+i3);
             }
              String i4=String.valueOf(nombreArrayList.get(119));
             if(!"0.0".equals(i4)){              
                     
                    pw.println("NDD|004|2117-30000006|Préstamos a Corto Plazo SI|"+i4);
             }
              String i5=String.valueOf(nombreArrayList.get(120));
             if(!"0.0".equals(i5)){              
                     
                    pw.println("NDD|004|4399-10000005|Préstamos a Mediano Plazo|"+i5);
             }
//              String i6=String.valueOf(nombreArrayList.get(121));
//             if(!"0.0".equals(i6)){              
//                     
//                    pw.println("NDD|004|2117-30000007|Sanciones Disciplinarias|"+i6);
//             }
              String i7=String.valueOf(nombreArrayList.get(121));
             if(!"0.0".equals(i7)){              
                     
                    pw.println("NDD|004|2117-60000004|Ayuda Damnificados|"+i7);
             }
              String i8=String.valueOf(nombreArrayList.get(122));
             if(!"0.0".equals(i8)){              
                     
                    pw.println("NDD|004|2117-50000003|Metlife Gastos Médicos|"+i8);
             }
              String i9=String.valueOf(nombreArrayList.get(123));
             if(!"0.0".equals(i9)){              
                     
                    pw.println("NDD|004|2117-50000004|Seguro de Separación Individual|"+i9);
             }
             String j1=String.valueOf(nombreArrayList.get(124));
             if(!"0.0".equals(j1)){              
                     
                    pw.println("NDD|004|2117-50000006|Préstamo SSI Metlife|"+j1);
             }
             String j2=String.valueOf(nombreArrayList.get(125));
             if(!"0.0".equals(j2)){              
                     
                    pw.println("NDD|019|2117-40000002|Sindicato Democrático Empleados PJEV|"+j2);
             }
              String j3=String.valueOf(nombreArrayList.get(126));
             if(!"0.0".equals(j3)){              
                     
                    pw.println("NDD|004|2117-60000008|Libertad ser Fin SA de CV|"+j3);///bien////////////////////
             }
              String j4=String.valueOf(nombreArrayList.get(127));
             if(!"0.0".equals(j4)){              
                     
                    pw.println("NDD|004|2117-60000009|T Facilita Fin SA de CV|"+j4);
             }
              String j5=String.valueOf(nombreArrayList.get(128));
             if(!"0.0".equals(j5)){              
                     
                    pw.println("NDD|004|2117-60000010|Financiera Fortaleza|"+j5);
             }
              String j6=String.valueOf(nombreArrayList.get(129));
             if(!"0.0".equals(j6)){              
                     
                    pw.println("NDD|099|5111-11300001|Sueldos y Salarios|"+j6);
             }
              String j7=String.valueOf(nombreArrayList.get(130));
             if(!"0.0".equals(j7)){              
                     
                    pw.println("NDD|064|5113-13300001|Compensación por Servicios de Turno Vespertino|"+j7);
             }
              String j8=String.valueOf(nombreArrayList.get(131));
             if(!"0.0".equals(j8)){              
                     
                    pw.println("NDD|063|5115-15400001|Ayuda para Pasajes|"+j8);
             }
             String k0=String.valueOf(nombreArrayList.get(132));
             if(!"0.0".equals(k0)){              
                     
                    pw.println("NDD|099|5112-12200001|Sueldos al Personal Eventual|"+k0);
             }
             String k1=String.valueOf(nombreArrayList.get(133));
             if(!"0.0".equals(k1)){              
                     
                    pw.println("NDD|064|5116-17100001|Estímulos por Antigüedad|"+k1);
             }
             String k2=String.valueOf(nombreArrayList.get(134));
             if(!"0.0".equals(k2)){              
                     
                    pw.println("NDD|064|5113-13100001|Quinquenios|"+k2);
             }
             String k3=String.valueOf(nombreArrayList.get(135));
             if(!"0.0".equals(k3)){              
                     
                    pw.println("NDD|063|5115-15400004|Despensa|"+k3);
             }
             String k4=String.valueOf(nombreArrayList.get(136));
             if(!"0.0".equals(k4)){              
                     
                    pw.println("NDD|064|5113-13100002|Quinquenio Nueva Generación|"+k4);
             }
             String k5=String.valueOf(nombreArrayList.get(137));
             if(!"0.0".equals(k5)){              
                     
                    pw.println("NDD|063|5115-15400005|Previsión Social Múltiple|"+k5);
             }
             String k6=String.valueOf(nombreArrayList.get(138));
             if(!"0.0".equals(k6)){              
                     
                    pw.println("NDD|050|5115-15300001|Liquidaciones por Indemnizaciones y Sueldos y Salarios Caídos|"+k6);
             }
             String k7=String.valueOf(nombreArrayList.get(139));
             if(!"0.0".equals(k7)){              
                     
                    pw.println("NDD|049|5115-15300001|Liquidaciones por Indemnizaciones y Sueldos y Salarios Caídos|"+k7);
             }
             String k8=String.valueOf(nombreArrayList.get(140));
             if(!"0.0".equals(k8)){              
                     
                    pw.println("NDD|032|5116-17100004|Estímulo por Puntualidad y Asistencia|"+k8);
             }
             String k9=String.valueOf(nombreArrayList.get(141));
             if(!"0.0".equals(k9)){              
                     
                    pw.println("NDD|064|5115-15400007|Pago de Días Económicos No Disfrutados|"+k9);
             }
//             String l0=String.valueOf(nombreArrayList.get(142));
//             if(!"0.0".equals(l0)){              
//                     
//                    pw.println("NDD|064|5115-15400007|Pago de Días Económicos No Disfrutados|"+l0);
//             }
             String l1=String.valueOf(nombreArrayList.get(142));
             if(!"0.0".equals(l1)){              
                     
                    pw.println("NDD|063|5113-13200004|Bono Anual de Despensa|"+l1);
             }
             String l2=String.valueOf(nombreArrayList.get(143));
             if(!"0.0".equals(l2)){              
                     
                    pw.println("NDD|064|5116-17100006|Estímulo a Servidores Públicos|"+l2);
             }
             String l3=String.valueOf(nombreArrayList.get(144));
             if(!"0.0".equals(l3)){              
                     
                    pw.println("NDD|059|5115-15400014|Ayuda para la Adquisición de Utiles Escolares|"+l3);
             }
             String l4=String.valueOf(nombreArrayList.get(145));
             if(!"0.0".equals(l4)){              
                     
                    pw.println("NDD|064|5116-17100007|Premio Mensual por Buen Desempeño|"+l4);
             }
             String l5=String.valueOf(nombreArrayList.get(146));
             if(!"0.0".equals(l5)){              
                     
                    pw.println("NDD|064|5115-15400017|Pago del Día de las Madres y Padres|"+l5);
             }
             String l6=String.valueOf(nombreArrayList.get(147));
             if(!"0.0".equals(l6)){              
                     
                    pw.println("NDD|064|5115-15400023|Compensación Temporal Compactable|"+l6);
             }
             String l7=String.valueOf(nombreArrayList.get(148));
             if(!"0.0".equals(l7)){              
                     
                    pw.println("NDD|064|5113-13400007|Compensación Administrativa|"+l7);
             }
             String l8=String.valueOf(nombreArrayList.get(149));
             if(!"0.0".equals(l8)){              
                     
                    pw.println("NDD|064|5112-12300002|Ayuda por Servicios|"+l8);
             }
             String l9=String.valueOf(nombreArrayList.get(150));
             if(!"0.0".equals(l9)){              
                     
                    pw.println("NDD|063|5115-15400025|Asignación por Actividades Culturales|"+l9);
             }
             String l10=String.valueOf(nombreArrayList.get(151));
             if(!"0.0".equals(l10)){              
                     
                    pw.println("NDD|063|5115-15500002|Ayuda para Capacitación y Desarrollo|"+l10);
             }
             String m0=String.valueOf(nombreArrayList.get(152));
             if(!"0.0".equals(m0)){              
                     
                    pw.println("NDD|025|5113-13200002|Gratificación Anual|"+m0);
             }
             String m1=String.valueOf(nombreArrayList.get(153));
             if(!"0.0".equals(m1)){              
                     
                    pw.println("NDD|024|5113-13200002|Gratificación Anual|"+m1);
             }
              String m2=String.valueOf(nombreArrayList.get(154));
             if(!"0.0".equals(m2)){              
                     
                    pw.println("NDD|064|5115-15400030|Gratificación Extraordinaria|"+m2);
             }
             String m3=String.valueOf(nombreArrayList.get(155));
             if(!"0.0".equals(m3)){              
                     
                    pw.println("NDD|064|5115-15400031|Gratificación|"+m3);
             }
              String m5=String.valueOf(nombreArrayList.get(156));
             if(!"0.0".equals(m5)){              
                     
                    pw.println("NDD|064|5115-15400030|Compensación garantizada|"+m5);
             }
             String m6=String.valueOf(nombreArrayList.get(157));
             if(!"0.0".equals(m6)){              
                     
                    pw.println("NDD|064|5115-15400030|Compensación por Grado de Responsabilidada|"+m6);
             }
             String m7=String.valueOf(nombreArrayList.get(158));
             if(!"0.0".equals(m7)){              
                     
                    pw.println("NDD|064|5115-15400030|Bono Único Extraordinario|"+m7);
             }
             String m9=String.valueOf(nombreArrayList.get(159));
             if(!"0.0".equals(m9)){              
                     
                    pw.println("NDD|025|5115-15400030|Gratificación Anual de Compensación|"+m9);
             }
            String m10=String.valueOf(nombreArrayList.get(160));
             if(!"0.0".equals(m10)){              
                     
                    pw.println("NDD|024|5115-15400030|Gratificación Anual de Compensación|"+m10);
             }
             String m11=String.valueOf(nombreArrayList.get(161));
             if(!"0.0".equals(m11)){              
                     
                    pw.println("NDD|043|5113-13200003|Prima Vacacional|"+m11);
             }
             String m12=String.valueOf(nombreArrayList.get(162));
             if(!"0.0".equals(m12)){              
                     
                    pw.println("NDD|042|5113-13200003|Prima Vacacional|"+m12);
             }
            String m13=String.valueOf(nombreArrayList.get(163));
             if(!"0.0".equals(m13)){              
                     
                    pw.println("NDD|079|5115-15400032|Isr pagado por el Patrón|"+m13);
             }
             
             ////////************************/////////////////////////////////////////////////////////////
             /////////**********OTROS DESCUENTOS E IMPUESTO RETENIDO**************////////////////////////
             //////////************************//////////////////////////////////////////////////////////
             
             String p0=String.valueOf(nombreArrayList.get(164));
             if(!"0.0".equals(p0)){              
                     
                    pw.println("NOP|999|2117-40000001|Devolución clave 21|"+p0);
             }
              String p1=String.valueOf(nombreArrayList.get(165));
             if(!"0.0".equals(p1)){              
                     
                    pw.println("NOP|999|2117-10000001|Devolución clave 41|"+p1);
             }
              String p2=String.valueOf(nombreArrayList.get(166));
             if(!"0.0".equals(p2)){              
                     
                    pw.println("NOP|999|2117-20000001|Devolución clave 42|"+p2);
             }
              String p3=String.valueOf(nombreArrayList.get(167));
             if(!"0.0".equals(p3)){              
                     
                    pw.println("NOP|999|2117-60000006|Devolución clave 47|"+p3);
             }
              String p4=String.valueOf(nombreArrayList.get(168));
             if(!"0.0".equals(p4)){              
                     
                    pw.println("NOP|999|2117-40000002|Devolución clave  407|"+p4);
             }
              String p5=String.valueOf(nombreArrayList.get(169));
             if(!"0.0".equals(p5)){              
                     
                    pw.println("NOP|999|2117-20000001|Devolución clave 42|"+p5);
             }
              String p6=String.valueOf(nombreArrayList.get(170));
             if(!"0.0".equals(p6)){              
                     
                    pw.println("NOP|999|2117-60000001|Devolución farmacias del magisterio|"+p6);
             }
              String p7=String.valueOf(nombreArrayList.get(171));
             if(!"0.0".equals(p7)){              
                     
                    pw.println("NOP|999|4399-10000005|Devolución clave 90|"+p7);
             }
              String p8=String.valueOf(nombreArrayList.get(172));
             if(!"0.0".equals(p8)){              
                     
                    pw.println("NOP|999|4399-10000006|Devolución clave 91|"+p8);
             }
             String p9=String.valueOf(nombreArrayList.get(173));
             if(!"0.0".equals(p9)){              
                     
                    pw.println("NOP|999|4399-10000007|Devolución clave 92|"+p9);
             }
             String q1=String.valueOf(nombreArrayList.get(174));
             if(!"0.0".equals(q1)){              
                     
                    pw.println("NOP|999|2117-60000003|Devolución pensión alimenticia|"+q1);
             }
             String q2=String.valueOf(nombreArrayList.get(175));
             if(!"0.0".equals(q2)){              
                     
                    pw.println("NOP|999|1122-00000000|Devolución clave 99|"+q2);
             }
             String q3=String.valueOf(nombreArrayList.get(176));
             if(!"0.0".equals(q3)){              
                     
                    pw.println("NOP|001|2117-10000001|Isr|"+q3);
             }
             /////////*************************CONDICIONALES DE OTROS PAGOS 2042,1009,1001
             
             String r1=String.valueOf(nombreArrayList.get(177));
             String r2=String.valueOf(nombreArrayList.get(178));                        
             if((!"0.0".equals(r1))&&("0.0".equals(r2))){              
                     
                    pw.println("NOP|999|5114-14100001|Cuota del Imss pagada por el Patrón|"+r1+"||"+r2);
             }else{
                 if(("0.0".equals(r1))&&(!"0.0".equals(r2))){
                
                     pw.println("NOP|999|5114-14100001|Cuota del Imss pagada por el Patrón||"+r1+"||"+r2);
                 }else{
                     if(("0.0".equals(r1))&&("0.0".equals(r2))){                      
                     }
                     else{
                         if((!"0.0".equals(r1))&&(!"0.0".equals(r2))){
                      pw.println("NOP|999|5114-14100001|Cuota del Imss pagada por el Patrón||"+r1+"||"+r2);
                         }
                     }
                 }
             }
              String r3=String.valueOf(nombreArrayList.get(179));
             String r4=String.valueOf(nombreArrayList.get(180));                        
             if((!"0.0".equals(r3))&&("0.0".equals(r4))){              
                     
                    pw.println("NOP|999|5115-15400030|Isr pagado por el Patrón|"+r3+"||");
             }else{
                 if(("0.0".equals(r3))&&(!"0.0".equals(r4))){
                
                     pw.println("NOP|999|5115-15400030|Isr pagado por el Patrón||"+r4+"||");
                 }else{
                     if(("0.0".equals(r3))&&("0.0".equals(r4))){                      
                     }
                     else{
                         if((!"0.0".equals(r3))&&(!"0.0".equals(r4))){
                                 pw.println("NOP|999|5115-15400030|Isr pagado por el Patrón||"+r4+"||");
                         }
                     }
                 }
             }
              String r5=String.valueOf(nombreArrayList.get(181));
             String r6=String.valueOf(nombreArrayList.get(181));                        
             if((!"0.0".equals(r5))&&("0.0".equals(r6))){              
                     
                    pw.println("NOP|002|5115-15400030|Subsidio para el empleo entregado al trabajador|"+r5+"||");
             }else{
                 if(("0.0".equals(r5))&&(!"0.0".equals(r6))){
                
                     pw.println("NOP|002|5115-15400030|Subsidio para el empleo entregado al trabajador||"+r6+"||");
                 }else{
                     if(("0.0".equals(r5))&&("0.0".equals(r6))){                      
                     }
                     else{
                         if((!"0.0".equals(r5))&&(!"0.0".equals(r6))){
                             pw.println("NOP|002|5115-15400030|Subsidio para el empleo entregado al trabajador||"+r5+"||"+r6);
                         }
                     }
                 }
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
