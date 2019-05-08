package kr.or.kosta.mvc.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import kr.or.kosta.dto.DistanceVO;

public class PoiTest {
	
//	public static void main(String[] args) {
//		PoiTest poi = new PoiTest();
//		Scanner sc = new Scanner(System.in);
//		int mynum = sc.nextInt();
//		try {
//			List<DistanceVO> list = poi.Distance();
//			poi.distence(list,mynum);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public List<DistanceVO> Distance() throws IOException {
        String path = "C:\\bigdataStudy\\SpringProject\\SpringProject\\MidProject\\WebContent\\resources\\data\\utwl_list2019-04-02.csv";
        List<DistanceVO> dlist= new ArrayList<DistanceVO>();
        List<Double> list = null;
        FileInputStream fis = new FileInputStream(path);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        int rowindex = 0;
        int columnindex = 0;
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        test:for (rowindex = 1; rowindex < rows; rowindex++) {
        	DistanceVO vo = new DistanceVO();
            // ���� �д´�
            HSSFRow row = sheet.getRow(rowindex);
            if (row != null) {
                // ���� ��
                int cells = row.getPhysicalNumberOfCells();
                for (columnindex = 0; columnindex <= cells; columnindex++) {
                    // ������ �д´�
                    HSSFCell cell = row.getCell(columnindex);
                    String value = "";
                    // ���� ���ϰ�츦 ���� ��üũ
                    if (cell == null) {
                        continue;
                    } else {
                        // Ÿ�Ժ��� ���� �б�
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_FORMULA:
                            value = cell.getCellFormula();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            value = String.valueOf(cell.getNumericCellValue() + "");
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            value = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = cell.getErrorCellValue() + "";
                            break;
                        }
                    }
                    if (rowindex != 0) {
                        if (columnindex == 0) {
                        	vo.setID(value);
                        	list=new ArrayList<>();
                        }else if(columnindex == 35){
                        	break;
                        }else {
                        	double val = Double.parseDouble(value);
                        	list.add(val);
                        }
                    }
                }
                vo.setList(list);
                dlist.add(vo);
            }else {
            	break test;
            }
        }
        System.out.println(dlist.get(1).getID());
        return dlist;
        }
	
	
	public double distence(List<DistanceVO> arr,int myindex) {
		List<Double> a = new ArrayList<>();
		List<Double> b = new ArrayList<>();
		double x=0;
		for(int i=0; i<arr.size();i++) {
			for(int j=0; j<arr.get(myindex).getList().size();j++) {	
				if(i == 0) {
					//�� ���̵�� ������ȣ �����ͼ� �� ��ȣ ����Ʈ�� ������
					a = arr.get(myindex).getList();
				}else if(i == j) {
					//�������� ���ʴ�� ������ �Ÿ�����
					b = arr.get(i).getList();
					if(a != null) {
						break;
					}
				}
			}
		}
		for(int k=0; k<arr.get(0).getList().size();k++) {
			double z = b.get(k) - a.get(k);
			 z *= z;
			 x += z;
			}
		System.out.println("===================");	
		double aa = Math.sqrt(x);
		System.out.println(aa);
		return aa;
	}
	
	
}
