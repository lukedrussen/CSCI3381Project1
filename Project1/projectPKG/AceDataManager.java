package projectPKG;
//
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class AceDataManager<T> implements AceDataManagerADT {

	private ArrayList<PatientADT> Patients = new ArrayList<PatientADT>();
	private ArrayList<String> Risks = new ArrayList<String>();


	public void addPatient(PatientADT p) {
		Patients.add(p);
	}


	public PatientADT getPatient(String id) {
		for(PatientADT s:Patients)
			if(s.getId().equals(id))
				return s;


		return null;
	}


	public ArrayList<String> getRiskFactors(ArrayList<String> aces) {
		//All Stats Gathered From The Adverse Childhood Experiences (ACE) Study

		int RiskNum= aces.size();

		if(RiskNum == 0) {
			Risks.add("No Increased Risk Factors");
		}else if(RiskNum == 1) {
			Risks.add("1.5 X More likely to develop Depression");
			Risks.add("1.8 X More likely to attempt Suicide");
			Risks.add("2.0 X More likely to become an Alcoholic");
			Risks.add("1.7 X More likely to try Illicit Drugs");
			Risks.add("1.3 X More likely to try Injecting Drugs");
			Risks.add("1.7 X More likely to become Promiscuous (>50 Partners)");
			Risks.add("1.4 X More likely to contract an STD");
			Risks.add("1.6 X More likely to develop Emphysema");
		}else if(RiskNum == 2) {
			Risks.add("1.5 X More likely to become a Smoker");
			Risks.add("1.4 X More likely to become Obese");
			Risks.add("2.4 X More likely to develop Depression");
			Risks.add("3.0 X More likely to attempt Suicide");
			Risks.add("4.0 X More likely to become an Alcoholic");
			Risks.add("2.9 X More likely to try Illicit Drugs");
			Risks.add("3.8 X More likely to try Injecting Drugs");
			Risks.add("2.3 X More likely to become Promiscuous (>50 Partners)");
			Risks.add("1.6 X More likely to develop Emphysema");
		}else if(RiskNum == 3) {
			Risks.add("2.0 X More likely to become a Smoker");
			Risks.add("1.4 X More likely to become Obese");
			Risks.add("2.6 X More likely to develop Depression");
			Risks.add("6.6 X More likely to attempt Suicide");
			Risks.add("4.9 X More likely to become an Alcoholic");
			Risks.add("3.6 X More likely to try Illicit Drugs");
			Risks.add("7.1 X More likely to try Injecting Drugs");
			Risks.add("3.1 X More likely to become Promiscuous (>50 Partners)");
			Risks.add("1.9 X More likely to contract an STD");
			Risks.add("1.4 X More likely to develop Heart Disease");
			Risks.add("1.3 X More likely to have a Stroke");
			Risks.add("2.2 X More likely to develop Emphysema");
			Risks.add("1.2 X More likely to develop Diabetes");
		}else if(RiskNum == 4 || RiskNum > 4) {
			Risks.add("2.2 X More likely to become a Smoker");
			Risks.add("1.6 X More likely to become Obese");
			Risks.add("4.6 X More likely to develop Depression");
			Risks.add("12.2 X More likely to attempt Suicide");
			Risks.add("7.4 X More likely to become an Alcoholic");
			Risks.add("4.7 X More likely to try Illicit Drugs");
			Risks.add("10.3 X More likely to try Injecting Drugs");
			Risks.add("3.2 X More likely to become Promiscuous (>50 Partners)");
			Risks.add("2.5 X More likely to contract an STD");
			Risks.add("2.2 X More likely to develop Heart Disease");
			Risks.add("1.9 X More likely to be diagnosed with Cancer");
			Risks.add("2.4 X More likely to have a Stroke");
			Risks.add("3.9 X More likely to develop Emphysema");
			Risks.add("1.6 X More likely to develop Diabetes");
		}

		return Risks;
	}


	public void writeToFile() throws IOException {
		//Found on Stack Overflow
		FileWriter writer = new FileWriter("output.txt"); 

		for(PatientADT s: Patients) {
			writer.write(s + System.lineSeparator());
		}
		writer.close();

	}


	public void writeToFile(String fn) throws IOException {
		//Found On Stack Overflow
		FileWriter writer = new FileWriter(fn); 

		for(PatientADT s: Patients) {
			writer.write(s + System.lineSeparator());
		}
		writer.close();

	}

	public String toString() {
		String x = "";
		for(PatientADT s:Patients) {
			x += s + "\n"; 
		}
		return x;	
	}


	@Override
	public void readFile(String fn){
		BufferedReader lineReader = null;

		try {			

			lineReader = new BufferedReader(new InputStreamReader(new FileInputStream(fn), Charset.forName("UTF-8")));  

			String line = null;
			while ((line = lineReader.readLine())!=null) {

				String[] tokens = line.split(",");
				String id = tokens[0];
				String name = tokens[1];
				String temp =tokens[2];
				temp =temp.substring(1,temp.length());
				String temp2 = tokens[tokens.length-1];
				temp2 = temp2.substring(1,temp2.length()-1);
				
				PatientADT p = new Patient(name,id);

				for(int i = 2; i<tokens.length; i++) {
					if(i==2)
						p.addACE(temp);
					else if (i==tokens.length-1)
						p.addACE(temp2);
					else p.addACE(tokens[i]);
				}

				addPatient(p);


			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file.  either no such file or format error");
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
}
