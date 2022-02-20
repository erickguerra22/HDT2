public class Calculadora implements IPosfixCalc{
	private char[] operators = new char[]{'-','+','*','/'};
	StackArrayList<Integer> lista;

	@Override
	public int Evaluate(String expresion) {
		lista = new StackArrayList<Integer>(); //Se cre una nueva pila de tipo Double
		expresion = expresion.replace(" ", ""); //Remover espacios en blanco del texto
		for(int i = 0; i<expresion.length();i++) {
			if(!isANumber(expresion.charAt(i)) && !(new String(operators).contains(Character.toString(expresion.charAt(i))))) // Se comprueba que el caracter correspondiente es un número o un operador
				return -1;
			else if(isANumber(expresion.charAt(i))) {
				String numberText = Character.toString(expresion.charAt(i));
				int number = Integer.parseInt(numberText);
				lista.push(number); // En caso de ser un número, se guarda en la pila.
			}else {
				if(lista.count()<2)
					return -1; //Si la cantidad de numeros no es suficiente para operar, devuelve un error.
				else if(!lista.isEmpty())
					try {
						operate(expresion.charAt(i)); // Se ejecuta el operador encontrado
					}catch(ArithmeticException e){
						throw e; //Si existe algún error durante la ejecución, se envía un error aritmético.
					}
			}
		}
		return lista.peek();
	}
	
	//Evalúa si un caracter indicado es un número entero
	private boolean isANumber(char character) {
		try {
			Integer.parseInt(Character.toString(character));
		}catch(Exception e) {
			return false;
		}
		return true;
			
	}
	
	//Ejecuta la operación indicada, según el operador.
	private void operate(char operator) throws ArithmeticException{
		int num1 = 0;
		int num2 = 0;
		switch(operator) {
		case '+':
			num1 = lista.pull();
			num2 = lista.pull();
			lista.push(num2+num1);
			break;
		case '-':
			num1 = lista.pull();
			num2 = lista.pull();
			lista.push(num2-num1);
			break;
		case '/':
			int resultado = 0;
			num1 = lista.pull();
			num2 = lista.pull();
			resultado = num2/num1;
			lista.push(resultado);
			break;
		case '*':
			num1 = lista.pull();
			num2 = lista.pull();
			lista.push(num2*num1);
			break;
		}
	} 
}
