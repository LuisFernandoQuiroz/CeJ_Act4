package act4_CeJ_FernandoQuiroz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class AddressBook {
	String path = "ContactList.txt";
	String line;
	String[] strContacts = new String[2];
	HashMap <String,String> hashContacts = new HashMap<String,String>();
	Scanner userInputs = new Scanner(System.in);
	
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
						
			while((line = br.readLine()) != null) {
				strContacts = line.split(",");
				hashContacts.put(strContacts[0],strContacts[1]);
			}
			
			br.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			}
		catch (IOException e){
			e.printStackTrace();
			}
	}
	
	public void save() {
		String sysProperty = System.getProperty("line.separator");

		try (Writer writer = new FileWriter(path)) {
		  for (HashMap.Entry<String, String> entry : hashContacts.entrySet()) {
		    writer.append(entry.getKey())
		    	  .append(',')
		          .append(entry.getValue())
		          .append(sysProperty);
		    
		  }
		} catch (IOException e) {
		  e.printStackTrace(System.err);
		}
	}
	
	public void list() {
		load();
		Iterator<HashMap.Entry<String,String>> itr = hashContacts.entrySet().iterator();
		
		while(itr.hasNext()) {
			HashMap.Entry<String,String> entry = itr.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		System.out.println("\n");
	}
	
	public void create() {
		System.out.println("Ingrese el numero telefonico, incluyendo espacios:");
		String inputKey = userInputs.nextLine();
		
		System.out.println("Ingrese el nombre asociado con el numero telefonico:");
		String inputName = userInputs.nextLine();
		
		hashContacts.put(inputKey, inputName);
		
		save();
		
		System.out.println("Ha introducido el numero telefonico " + inputKey + 
						   " asociado con el nombre " + inputName + 
						   " a la lista de contactos.");
	}
	
	public void delete() {
		int deletePosition = 0;
		String tempPath = "temp.txt";
		String currentLine;
		String hashPosition[];
		File oldFile = new File(path);
		File newFile = new File(tempPath);
		
		System.out.println("Ingrese el numero telefonico que desea borrar de la lista de contactos:");
		String inputDelete = userInputs.nextLine();
		
		try {
			FileWriter fw = new FileWriter(tempPath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null) {
				hashPosition = currentLine.split(",");
				
				if(!(hashPosition[deletePosition].equalsIgnoreCase(inputDelete))) {
					pw.println(currentLine);
				}
			}
			
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		oldFile.delete();
		File restoreName = new File(path);
		newFile.renameTo(restoreName);
		
		System.out.println("Contacto borrado" + "\n");
	}
}
