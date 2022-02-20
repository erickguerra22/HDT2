/**
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author erick
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calculadora calculadora = new Calculadora();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String path = "";
		
		System.out.println("Bienvenido a la calculadora Postfix");
		System.out.println("Por favor, ingrese la ruta completa de la carpeta en donde se encuentra el archivo.");
		path = scan.nextLine();
		try {
			File file = new File(path + "\\datos.txt"); //Se busca el archivo datos.txt en la ruta indicada.
			Scanner reader = new Scanner(file);
			int linea = 1;
			while(reader.hasNextLine()) {
				try {
					int resultado = calculadora.Evaluate(reader.nextLine()); //Se obtiene el resultado de la línea.
					System.out.println("El resultado entero de la linea " + linea + " es: " + resultado+".");
				}catch(Exception e) {
					System.out.println("La operación indicada en la línea "+linea+" no es válida.");
				}
				linea++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al leer el archivo indicado");
		}	
	}
}
