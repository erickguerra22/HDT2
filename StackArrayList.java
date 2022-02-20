/**
 * 
 */

import java.util.ArrayList;
/**
 * @author erick
 *
 */
public class StackArrayList<T> implements IStack<T> {

	private ArrayList<T> mainList;
	
	public StackArrayList() {
		mainList = new ArrayList<T>();
	}
	
	@Override
	public void push(T value) {
		//Agrega el elemento al principio de la lista
		mainList.add(0, value);
	}

	@Override
	public T pull() {
		// Devuelve el último elemento de la lista, y lo elimina de ella.
		T element = mainList.get(0);
		mainList.remove(0);
		return element;
	}

	@Override
	public T peek() {
		// Devuelve el último elemento de la lista
		return mainList.get(0);
	}

	@Override
	public int count() {
		// Devuelve la cantidad de elementos en la lista.
		return mainList.size();
	}

	@Override
	public boolean isEmpty() {
		// true: La lista está vacía, false: La lista contiene elementos.
		return mainList.isEmpty();
	}
}