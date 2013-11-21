package ar.fiuba.tecnicas.tetesteo.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//TestRunStore en archivo de texto plano con extension .txt

public class TxtFileTestRunStore implements TestRunStore {

	private File file;
	ArrayList<String> testNames;
	String filePath;;	


	public TxtFileTestRunStore(String reportPath) {
		filePath = reportPath;
		file = new File(filePath);
		testNames = new ArrayList<String>();
		if (existFile()){
			read();
		}
	}


	@Override
	public boolean hasStores() {
		return existFile();		
	}

	@Override
	public boolean isTestOk(String testName) {
		if (existFile()){
			return testNames.contains(testName);
		}
		return false;
	}

	@Override	
	public void addTestOk(String testName){
		testNames.add(testName);
		writeLine(testName);
	}

	@Override	
	public void deleteOldStores(){
		File file = new File(filePath);
		file.delete();	
		testNames.clear();
	}
	
	private boolean existFile (){
		return file.exists();
	}	

	private void read(){				
		if (hasStores()){
			FileReader fileReader = null;
			BufferedReader buffer = null;
			String line;
			try {

				fileReader = new FileReader (file);
				buffer = new BufferedReader(fileReader);			
				while((line=buffer.readLine())!=null){
					testNames.add(line);
				}				
			}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				try{                   
					if( null != fileReader ){  
						fileReader.close();    
					}                 
				}catch (Exception e2){
					e2.printStackTrace();
				}
			}

		}		
	}

	private void writeLine(String line){
		try {
			FileWriter outFile = new FileWriter(filePath,true);
			PrintWriter out = new PrintWriter(outFile);
			out.println(line);
			out.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}

