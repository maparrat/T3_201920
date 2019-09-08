package model.data_structures;

public interface IArregloDinamico<T extends Comparable<T>> {

	/**
	 * Retornar el numero de elementos maximo en el arreglo
	 * @return
	 */
	int darCapacidad();

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return
	 */
	int darTamano();
	
	/**
	 * Retornar el elemento en la posicion i
	 * @param i posicion de consulta
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	T darElemento(int i);
	
	/**
	 * Agregar un dato de forma compacta (en la primera casilla disponible) 
	 * Caso Especial: Si el arreglo esta lleno debe aumentarse su capacidad, agregar el nuevo dato y deben quedar multiples casillas disponibles para futuros nuevos datos.
	 * @param dato nuevo elemento
	 */
	public void agregar(T dato);
		
	/**
	 * Buscar un dato en el arreglo.
	 * @param dato Objeto de busqueda en el arreglo
	 * @return elemento encontrado en el arreglo (si existe). null si no se encontro el dato.
	 */
	T buscar(T dato);
	
	/**
	 * Eliminar un dato del arreglo.
	 * Los datos restantes deben quedar "compactos" desde la posicion 0.
	 * @param dato Objeto de eliminacion en el arreglo
	 * @return dato eliminado
	 */
	T eliminar( T dato );
	
	/**
	 * Retorna el primer elemento del arreglo
	 * @return el primer elemento del arreglo
	 */
	T darPrimerElemento();

	/**
	 * Retorna el último elemento del arreglo
	 * @return el último elemento del arreglo
	 */
	T darUltimoElemento();
	
	/**
	 * Cambia el elemento de la posición dada
	 * @param dato Nuevo dato
	 * @param index Posición del elemento a cambiar
	 */
	void cambiarElemento(T dato, int index);
	
	/**
	 * Retorna los primeros diez viajes del arreglo
	 * @return los primeros diez viajes del arreglo
	 */
	ArregloDinamico<T> darPrimeros10Viajes();
	
	/**
	 * Retorna los últimos diez viajes del arreglo
	 * @return los últimos diez viajes del arreglo
	 */
	ArregloDinamico<T> darUltimos10Viajes();
}