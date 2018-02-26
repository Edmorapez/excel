
package getexcelv1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
    static String fileDictName = "";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
    // FileInputStream file = new FileInputStream(new File("C:\\Users\\Propietario\\\\Downloads\\prueba.xlsx"));
	       XSSFWorkbook workbook;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the file"); //name for chooser
       // FileFilter filter = new FileNameExtensionFilter("excel", ".xlsx"); //filter to show only that
       		FileNameExtensionFilter filter = new FileNameExtensionFilter("xls and GIF images", "xls", "xlsx");

        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }

        File file = new File(fileDictName);
        if (file.exists() == false) {
            workbook = new XSSFWorkbook();
            XSSFSheet exampleSheet = workbook.createSheet("1");
            XSSFRow firstRow = exampleSheet.createRow(1);
            XSSFCell cell = firstRow.createCell(0);
            cell.setCellValue("value");

            try (
                    //Write the workbook in file system
                    FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }
        } else {
            // Sheet already exists
            System.out.println("File already exist");
        }
       
    }
    
}
