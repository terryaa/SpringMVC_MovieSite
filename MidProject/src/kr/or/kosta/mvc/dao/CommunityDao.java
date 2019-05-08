package kr.or.kosta.mvc.dao;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import kr.or.kosta.dto.InserttagVO;

public class CommunityDao {
		
	public static int[] countlist;
	public static int[] meanlist;
        /*
         * �ѹ��� �ε��ؾ� �� ���  a
         */
	public void connectR() throws REXPMismatchException{	 
		
			try {
				System.out.println("�������");
			RConnection connection =  null;
            
            
            
        	connection = new RConnection();
        	//R�� ������ ����
        	connection.eval("library(caret)");
        	connection.eval("library('readxl')");
        	//R���� ���̺귯�� ȣ��
        	
        	connection.parseAndEval("amazon2<-read_xlsx('C:/RTest/amazon.xlsx')");
        	//�������� �ҷ�����
        	connection.eval("amazonkmeans <- kmeans(amazon2, centers=10, iter.max=10000)");
        	//k_means �˰��� ����
        	connection.eval("amazonkmeans$cluster");
        	//�����͸���Ʈ �޾ƿ���
        	connection.eval("kmeanscenters<-amazonkmeans$centers");
        	//kmeanscenters�� ������
        	
        	
        	countlist = connection.eval("amazonkmeans$cluster").asIntegers();
        	        	for(int i=1; i<=33; i++) {
        	System.out.println(countlist[i]);
        	        	}
        	for(int i = 1; i<= 33; i++) {
        		meanlist[i] = connection.eval("mean(kmeanscenters[," + i + "]").asInteger();
        		System.out.println(meanlist[i]);
        	}
        	
        	        	
        	connection.close();
        	System.out.println("����");
    		
			}catch(Exception e) {
				e.getStackTrace();
			}
		
//			return RCommunitylist;
			
		
       
}
}
        


 


