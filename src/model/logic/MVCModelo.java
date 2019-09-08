package model.logic;

import java.io.FileReader;

import com.opencsv.CSVReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;

import java.util.Random;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<UBERTrip> datos;

	private ArregloDinamico<UBERTrip> datosSegunHora;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		datos = new ArregloDinamico<UBERTrip>(8);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	/**
	 * Retorna los viajes según la hora ingresada
	 * @return los viajes según la hora ingresada
	 */
	public ArregloDinamico<UBERTrip> darDatosSegunHora()
	{
		return datosSegunHora;
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(UBERTrip dato)
	{	
		datos.agregar(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public UBERTrip buscar(UBERTrip dato)
	{
		return (UBERTrip) datos.buscar(dato);
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public UBERTrip eliminar(UBERTrip dato)
	{
		return (UBERTrip) datos.eliminar(dato);
	}

	public UBERTrip darPrimerElemento()
	{		
		return (UBERTrip) datos.darPrimerElemento();
	}

	public UBERTrip darUltimoElemento()
	{		
		return (UBERTrip) datos.darUltimoElemento();
	}

	/**
	 * Metodo que carga los archivos
	 */
	public void cargarArchivoCSV() throws Exception
	{	
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])); 
				datos.agregar(nuevo);
			}
			primeraLectura = false;
		}
		reader.close();
	}

	/**
	 * Indica el número de viajes en el mes indicado
	 * @param mes Mes a buscar
	 * @return el número de viajes en el mes indicado
	 */
	public ArregloDinamico<UBERTrip> darViajesSegunHora(double hora)
	{
		ArregloDinamico<UBERTrip> respuesta = new ArregloDinamico<UBERTrip>(1);

		for (int i = 0; i < darTamano(); i++)
		{
			UBERTrip actual = (UBERTrip) datos.darElemento(i);
			if(actual.darDatosViaje()[2] == hora)
			{
				respuesta.agregar(actual);
			}			
		}

		datosSegunHora = respuesta;

		return respuesta;
	}

	/** 
	 * Algoritmo tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
	 * Consultado el 06/09/19
	 * Disponible en http://www.albertstam.com/Algorithms.pdf
	 */
	public long ordenamientoAscendenteShellSort(ArregloDinamico<UBERTrip> x)
	{
		long startTime = System.currentTimeMillis(); // Medición tiempo actual

		// Sort a[] into increasing order.
		int N = x.darTamano();
		int h = 1;
		while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		while (h >= 1)
		{ // h-sort the array.
			for (int i = h; i < N; i++)
			{ // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= h && x.darElemento(j).compareTo(x.darElemento(j-h)) < 0; j -= h)
				{					
					UBERTrip t = x.darElemento(j); 
					x.cambiarElemento(x.darElemento(j-h), j);
					x.cambiarElemento(t, j-h);
				}
			}
			h = h/3;
		}

		long endTime = System.currentTimeMillis(); // Medición tiempo actual
		long duration = endTime - startTime; // Duracion de ejecucion del algoritmo
		return duration;
	}

	/** 
	 * Algoritmo tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
	 * Consultado el 06/09/19
	 * Disponible en http://www.albertstam.com/Algorithms.pdf
	 */
	public long ordenamientoAscendenteMergeSort(ArregloDinamico<UBERTrip> x)
	{
		long startTime = System.currentTimeMillis(); // Medición tiempo actual

		ArregloDinamico<UBERTrip> aux = new ArregloDinamico<UBERTrip>(x.darTamano()); // Allocate space just once.
		sortMerge(x, 0, x.darTamano() - 1, aux);

		long endTime = System.currentTimeMillis(); // Medición tiempo actual
		long duration = endTime - startTime; // Duracion de ejecucion del algoritmo
		return duration;
	}

	/** 
	 * Algoritmo tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
	 * Consultado el 06/09/19
	 * Disponible en http://www.albertstam.com/Algorithms.pdf
	 */
	private void sortMerge(ArregloDinamico<UBERTrip> a, int lo, int hi, ArregloDinamico<UBERTrip> aux)
	{ // Sort a[lo..hi].
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sortMerge(a, lo, mid, aux); // Sort left half.
		sortMerge(a, mid+1, hi, aux); // Sort right half.

		// Merge a[lo..mid] with a[mid+1..hi].
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
		{
			aux.cambiarElemento(a.darElemento(k), k);
		}
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid) 		
				aux.cambiarElemento(aux.darElemento(j++), k);

			else if (j > hi ) 		
				a.cambiarElemento(aux.darElemento(i++), k);

			else if (aux.darElemento(j).compareTo(aux.darElemento(i)) < 0) 
				a.cambiarElemento(aux.darElemento(j++), k);

			else 
				a.cambiarElemento(aux.darElemento(i++), k); 
	}

	/** 
	 * Algoritmo tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
	 * Consultado el 06/09/19
	 * Disponible en http://www.albertstam.com/Algorithms.pdf
	 */
	public long ordenamientoAscendenteQuickSort(ArregloDinamico<UBERTrip> x)
	{
		long startTime = System.currentTimeMillis(); // Medición tiempo actual
		Random random = new Random(System.currentTimeMillis());

		int n = x.darTamano();
		for (int i = 0; i < n; i++)
		{	// Exchange a[i] with random element in a[i..N-1]
			int r = i + random.nextInt(n-i);
			UBERTrip temp = x.darElemento(i);
			x.cambiarElemento(x.darElemento(r), i);
			x.cambiarElemento(temp, r);
		}

		sortQuick(x, 0, x.darTamano() - 1);

		long endTime = System.currentTimeMillis(); // medición tiempo actual
		long duration = endTime - startTime; // duracion de ejecucion del algoritmo
		return duration;
	}

	/** 
	 * Algoritmo tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
	 * Consultado el 06/09/19
	 * Disponible en http://www.albertstam.com/Algorithms.pdf
	 */
	private void sortQuick(ArregloDinamico<UBERTrip> a, int lo, int hi)
	{
		if (hi <= lo) return;		

		// Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi+1; // left and right scan indices
		UBERTrip v = a.darElemento(lo); // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (a.darElemento(++i).compareTo(v) < 0) 
			{
				if (i == hi) 
				{
					break;
				}
			}
			while (v.compareTo(a.darElemento(--j)) <0) 
			{
				if (j == lo)
				{
					break;
				}
			}

			if (i >= j) 
			{
				break;
			}

			UBERTrip t = a.darElemento(i); a.cambiarElemento(a.darElemento(j), i); a.cambiarElemento(t, j);
		}

		UBERTrip t = a.darElemento(lo); a.cambiarElemento(a.darElemento(j), lo); a.cambiarElemento(t, j);
		
		sortQuick(a, lo, j-1); // Sort left part a[lo .. j-1].
		sortQuick(a, j+1, hi); // Sort right part a[j+1 .. hi].
	}
}