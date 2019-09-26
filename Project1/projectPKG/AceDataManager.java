package projectPKG;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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

		return Risks;
	}


	public void writeToFile() throws IOException {

		FileWriter writer = new FileWriter("output.txt"); 

		for(PatientADT s: Patients) {
			writer.write(s + System.lineSeparator());
		}
		writer.close();

	}


	public void writeToFile(String fn) throws IOException {
		
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

}
