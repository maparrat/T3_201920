package view;

public class MVCView 
{
	/**
	 * Metodo constructor
	 */
	public MVCView()
	{}

	/**
	 * M�todo que imprime el men� por consola
	 */
	public void printMenu()
	{
		System.out.println("1. Cargar archivo");
		System.out.println("2. Consultar n�mero de viajes seg�n hora");
		System.out.println("3. Ordenar ascendentemente los viajes resultantes de la consulta del punto 3 con el algoritmo ShellSort");
		System.out.println("4. Ordenar ascendentemente los viajes resultantes de la consulta del punto 3 con el algoritmo MergeSort");
		System.out.println("5. Ordenar ascendentemente los viajes resultantes de la consulta del punto 3 con el algoritmo QuickSort");
		System.out.println("6. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}
}