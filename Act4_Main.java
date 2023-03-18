package act4_CeJ_FernandoQuiroz;

import java.util.Scanner;

public class Act4_Main {
	public static void main(String[] args) {
		AddressBook addressBook = new AddressBook();
		Scanner userInput = new Scanner(System.in);
		boolean end = false;
		
		addressBook.load();
		
		while(end == false) {
			System.out.println("Seleccione el numero de la accion que desee realizar:" + 
						   	   "\n1. Mostrar la lista de contactos" + 
						   	   "\n2. Introducir un nuevo contacto" + 
						   	   "\n3. Borrar un contacto" + 
						   	   "\n4. Fin");
			int action = userInput.nextInt();
			
			switch (action) {
			case 1:
				addressBook.list();
				break;
			case 2:
				System.out.println("2" + "\n");
				addressBook.create();
				break;
			case 3:
				System.out.println("3" + "\n");
				addressBook.delete();
				break;
			case 4:
				System.out.println("Fin del programa" + "\n");
				end = true;
				break;
			default:
				System.out.println("Ingrese una accion valida" + "\n");
			}
		}
	}
}