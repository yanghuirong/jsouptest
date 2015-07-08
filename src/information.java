import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class information {
	public String companyNameSave ="";
	public String projectName = "";
	public String settlementPrice = "";
	public String completionDate = "";
	public String quality = "";
	public String mainJob = "";
	public String status = "";
	
	public information( String companyNameSave, String projectName , String settlementPrice,String completionDate, String quality,String mainJob, String status){
		this.companyNameSave = companyNameSave;
		this.projectName = projectName;
		this.settlementPrice = settlementPrice;
		this.completionDate = completionDate;
		this.quality = quality;
		this.mainJob = mainJob;
		this.status = status;
	}
	public String getCompanyNameSave() {
		return companyNameSave;
	}
	public void setCompanyNameSave(String companyNameSave) {
		this.companyNameSave = companyNameSave;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSettlementPrice() {
		return settlementPrice;
	}
	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getMainJob() {
		return mainJob;
	}
	public void setMainJob(String mainJob) {
		this.mainJob = mainJob;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



	
	public static void main(String[] args) throws IOException {
		  File file = new File("test1.xls");
		  if(!file.exists()){
		   file.createNewFile();
		  }
		  List<information> piecesOfInformation = new ArrayList<information>();
		  HTMLParser a = new HTMLParser();
		  a.getData();
		  System.out.println(a.totalNumOfProject);
		  int s = 0;
		  for( s = 0;s<a.totalNumOfProject; s++)
		  {
		  piecesOfInformation.add(new information(a.companyNameSave[s],a.projectName[s],a.settlementPrice[s],a.completionDate[s], a.quality[s], a.mainJob[s],a.status[s]));
		  }
		  write2excel(piecesOfInformation, file);
		 }
	
	public static void write2excel(List<information> piecesOfInformation,File file){
		HSSFWorkbook excel= new HSSFWorkbook();
		HSSFSheet sheet = excel.createSheet("information");
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cells[] = new HSSFCell[7]; 
		String[] titles = new String[]{"companyNameSave","projectName","settlementPrice","completionDate","quality","mainJob","status"};
		for (int i = 0; i < 7; i++) {
			   cells[0] = firstRow.createCell(i);
			   cells[0].setCellValue(titles[i]);
			  }
		for (int i = 0; i < piecesOfInformation.size(); i++) {
			   HSSFRow row = sheet.createRow(i + 1);
			   information information = piecesOfInformation.get(i);
			   HSSFCell cell = row.createCell(0);
			   cell.setCellValue(information.getCompanyNameSave());
			   cell = row.createCell(1);
			   cell.setCellValue(information.getProjectName());
			   cell = row.createCell(2);
			   cell.setCellValue(information.getSettlementPrice());
			   cell = row.createCell(3);
			   cell.setCellValue(information.getCompletionDate());
			   cell = row.createCell(4);
			   cell.setCellValue(information.getQuality());
			   cell = row.createCell(5);
			   cell.setCellValue(information.getMainJob());
			   cell = row.createCell(6);
			   cell.setCellValue(information.getStatus());
			  }
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            excel.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("数据已经写入excel"); //温馨提示
	}
	
}