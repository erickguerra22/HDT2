public class Calculadora implements IPosfixCalc{
	private char[] operators = new char[]{'-','+','*','/'};
	StackArrayList<Double> lista;

	@Override
	public int Evaluate(String expresion) {
		lista = new StackArrayList<Double>(); //Se cre una nueva pila de tipo Double
		expresion = expresion.replace(" ", ""); //Remover espacios en blanco del texto
		for(int i = 0; i<expresion.length();i++) {
			if(!isANumber(expresion.charAt(i)) && !(new String(operators).contains(Character.toString(expresion.charAt(i))))) // Se comprueba que el caracter correspondiente es un n�mero o un operador
				return -1;
			else if(isANumber(expresion.charAt(i))) {
				String numberText = Character.toString(expresion.charAt(i));
				double number = Integer.parseInt(numberText);
				lista.push(number); // En caso de ser un n�mero, se guarda en la pila.
			}else {
				if(lista.count()<2)
					return -1; //Si la cantidad de numeros no es suficiente para operar, devuelve un error.
				else if(!lista.isEmpty())
					try {
						operate(expresion.charAt(i)); // Se ejecuta el operador encontrado
					}catch(ArithmeticException e){
						throw e; //Si existe alg�n error durante la ejecuci�n, se env�a un error aritm�tico.
					}
			}
		}
		return (int)Math.round(lista.peek()); //Se env�a el entero aproximado del resultado.
	}
	
	//Eval�a si un caracter indicado es un n�mero entero
	private boolean isANumber(char character) {
		try {
			Integer.parseInt(Character.toString(character));
		}catch(Exception e) {
			return false;
		}
		return true;
			
	}
	
	//Ejecuta la operaci�n indicada, seg�n el operador.
	private void operate(char operator) throws ArithmeticException{
		double num1 = 0;
		double num2 = 0;
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
			double resultado = 0;
			num1 = lista.pull();
			num2 = lista.pull();
			resultado = num2/num1;
			if(Double.isInfinite(resultado))
				throw new ArithmeticException("/ by 0"); //Si intenta dividir entre 0,se genera una excepci�n aritm�tica.
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
