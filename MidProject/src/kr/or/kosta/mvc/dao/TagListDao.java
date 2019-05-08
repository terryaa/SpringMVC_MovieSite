package kr.or.kosta.mvc.dao;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import kr.or.kosta.dto.InserttagVO;

public class TagListDao {
	
	public static String[] namelist;
        /*
         * �ѹ��� �ε��ؾ� �� ���  a
         */

		public void connectR(String movie_number) throws REXPMismatchException{	 
			InserttagVO vo = new InserttagVO();
			
			try {
            RConnection connection =  null;
            
            
            
        	connection = new RConnection();
        	connection.eval("library(rJava)");
        	connection.eval("library(DBI)");
        	connection.eval("library(RJDBC)");
        	connection.eval("library(rvest)");
        	connection.eval("library(httr)");
        	connection.eval("library(KoNLP)");
        	connection.eval("library(stringr)");
        	connection.eval("library(tm)");
        	connection.eval("library(qgraph)");
        	connection.eval("library(dplyr)");
        	connection.eval("library(xml2)");
        	// ���̺귯�� ȣ��
        	
        	
        	connection.eval("connect.db <- list(data.frame(dbname=c('DB1', 'DB2'), host=c('localhost', '172.188.1.2'),port=c(1521, 9999), sid=c('xe', 'ddbb'), username=c('midproject', 'BDUSER'), password=c('midproject', 'PPWW'),stringsAsFactors=FALSE))");
        	connection.eval("connect.db$connect.to <- 1");
        	connection.eval("options(java.parameters='-Xmx32g')");
        	connection.eval("connect.drv <- JDBC('oracle.jdbc.driver.OracleDriver', 'C:/bigdata/KOSTA/KOSTA/Oracle/File/lib/ojdbc6.jar')");
        	connection.eval("connect.info <- dbConnect(connect.drv, paste('jdbc:oracle:thin:@', connect.db[[1]]$host[connect.db$connect.to], ':', connect.db[[1]]$port[connect.db$connect.to], ':', connect.db[[1]]$sid[connect.db$connect.to], sep=''), connect.db[[1]]$username[connect.db$connect.to], connect.db[[1]]$password[connect.db$connect.to])");
        	// R  <-> DB ����
        	
        	connection.eval("gc(verbose=TRUE)");
        	//�޸� ������ �÷���
        	
        	
        	
        	 
        	
        	
        /*
         * �ѹ��� �ε��ؾ� �� ��� ��
         */
        	
        	connection.eval("query <- (\"select usedwords_contents as x from review_usedwords where movie_number='"+movie_number+"'\")");
//        	connection.eval("query <- (\"select usedwords_contents as x from ( select movie_title, rank() over (order by movie_number_of_audience desc) as rank from movie ), review_usedwords where  rank <= 10 " + ")");
        	
        	
        	
//        	connection.eval("query <- (\"select usedwords_contents as x from review_usedwords where movie_number='20129370'\")");
        	
        	connection.eval("result <- dbGetQuery(connect.info,query)");	
        	//����Ŭ�� ���� ���� ������
        	
        	
        	
//        	connection.eval(hochul);
        	// ��� / ����� ���� �Լ� ����
        	connection.eval("tdm <- TermDocumentMatrix(cps, control=list(tokenize=ko.words, removePunctuation=T,  stopwords=stopWord, removeNumbers=T,  wordLengths=c(4, 10), weighting=weightBin   )) ");
        	
        	connection.eval("cps <- Corpus(VectorSource(result[[\"X\"]]))  ");
        	//cps�� ���ͼҽ� ����
        	connection.eval("dim(tdm)");
        	connection.eval("tdm.matrix <- as.matrix(tdm)");
        	
        	connection.eval("Encoding(rownames(tdm.matrix)) <- 'UTF-8'");
        	connection.eval("word.count <- rowSums(tdm.matrix) ");
        	connection.eval("word.order <- order(word.count, decreasing=T)");
        	connection.eval("freq.words <- tdm.matrix[word.order[1:20], ]");
        	connection.eval("co.matrix <- freq.words %*% t(freq.words)");
        	connection.eval("wordcounttest <- sort(word.count,decreasing = TRUE)");

        	/*
        	 *   ## token �з��� Ȱ���� �Լ��� ����
        	 *   ## ������ ����
        	 *   ## ���ʿ� �ܾ� ����	
        	 *   ## ���� ����
        	 *   ## �ܾ� ���� ����, �ѱ��� 2��
        	 *   ## �ܾ �ؽ�Ʈ�� �����ϸ� 1, �ƴϸ� 0�� ��ȯ
        	 */
        	
        	
        	connection.eval("wordlist <- as.list(wordcounttest)");
        	connection.eval("nameslist <- names(wordlist)");
        	/*
        	 * ����� ������ List�� ��ȯ�ϴ� �۾�
        	 */
        	
        	namelist = connection.eval("nameslist").asStrings();
			
			for(int a=0; a<10; a++) {
			vo.setTag_name(namelist[a]);
        	}
			//�±��̸�vo������
        	
			
			
        	
        	
        	
        	
        	connection.close();
    		//��������
			}catch(RserveException e) {
				System.out.println("error RserveException");
			}
        			
		}
        
       
        
        
}

 


