package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.logic.MVCModelo;
import model.logic.UBERTrip;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	/**
	 * Hilo de ejecuci�n del programa
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();

			String in;
			in = lector.next();
			
			ArregloDinamico<UBERTrip> primeros10Viajes;
			ArregloDinamico<UBERTrip> ultimos10Viajes;

			int option;
			try
			{
				option = Integer.parseInt(in);
			}
			catch(NumberFormatException e)
			{
				option = 0;
			}

			switch(option){
			case 1:

				try
				{
					modelo.cargarArchivoCSV();
					double[] datosPrimerViaje = modelo.darPrimerElemento().darDatosViaje();
					double[] datosUltimoViaje = modelo.darUltimoElemento().darDatosViaje();

					System.out.println("Archivo cargado");
					System.out.println("Total de viajes le�dos del archivo: " + modelo.darTamano() + "\n---------");
					System.out.println("Datos primer viaje: \nId zona origen: " + datosPrimerViaje[0] + "\nId zona destino: " + datosPrimerViaje[1] + "\nHora: " + datosPrimerViaje[2] + "\nTiempo promedio: " + datosPrimerViaje[3] + "\n---------");
					System.out.println("Datos �ltimo viaje: \nId zona origen: " + datosUltimoViaje[0] + "\nId zona destino: " + datosUltimoViaje[1] + "\nHora: " + datosUltimoViaje[2] + "\nTiempo promedio: " + datosUltimoViaje[3] + "\n---------");
				}
				catch (Exception e)
				{
					System.out.println("Se ha producido un error al cargar el archivo\n---------");
				}
				break;

			case 2:

				double hora;
				try
				{
					System.out.println("--------- \nDar hora a buscar: ");
					hora = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar valores num�ricos\n---------");
					break;
				}

				if(hora < 0 || hora > 23)
				{
					System.out.println("Debe ingresar un valor v�lido.\n---------");
					break;
				}
				
				ArregloDinamico<UBERTrip> lista = modelo.darViajesSegunHora(hora);

				if(lista.darTamano() == 0)
				{
					System.out.println("No hay viajes registrados con las condiciones dadas.\n---------");
				}
				else
				{
					for (int i = 0; i < lista.darTamano(); i++)
					{
						UBERTrip actual = lista.darElemento(i);
						double[] datos = actual.darDatosViaje();
						System.out.println("Id zona de origen: " + datos[0] + "\nId zona de destino: " + datos[1] + "\nHora: " + datos[2] +"\nTiempo promedio: " + datos[3] + "\nDesviaci�n est�ndar: " + datos[4] + "\nTiempo promedio geom�trico: " + datos[5] + "\nDesviaci�n est�ndar geom�trica: " + datos[6] + "\n---------");
					}
					System.out.println("N�mero de viajes: " + lista.darTamano() +  "\n---------");
				}
				break;

			case 3:

				if(modelo.darDatosSegunHora() == null)
				{
					System.out.println("---------\nPrimero debe consultar los viajes segun una hora dada. (Opcion 2) \n---------");
					break;
				}
				
				if(modelo.darDatosSegunHora().darTamano() == 0)
				{
					System.out.println("---------\nNo hay datos registrados seg�n la hora dada.\n---------");
					break;
				}
				
				long duracionShellSort = modelo.ordenamientoAscendenteShellSort(modelo.darDatosSegunHora());
				System.out.println("El tiempo que tom� el algoritmo realizando el ordenamiento fue de: " + duracionShellSort + " milisegundos\n---------");		

				primeros10Viajes = modelo.darDatosSegunHora().darPrimeros10Viajes();
				ultimos10Viajes = modelo.darDatosSegunHora().darUltimos10Viajes();
				
				System.out.println("Informaci�n 10 primeros viajes:\n");
				
				for (int i = 0; i < primeros10Viajes.darTamano(); i++)
				{
					System.out.println("La informaci�n del viaje n�mero " + (i + 1) + " es:\n");
					System.out.println("Id zona de origen: " + primeros10Viajes.darElemento(i).darDatosViaje()[0] + "\nId zona de destino: " + primeros10Viajes.darElemento(i).darDatosViaje()[1] + "\nHora: " + primeros10Viajes.darElemento(i).darDatosViaje()[2] + "\nTiempo promedio: " + primeros10Viajes.darElemento(i).darDatosViaje()[3] + "\nDesviaci�n est�ndar: " + primeros10Viajes.darElemento(i).darDatosViaje()[4] + "\nTiempo promedio geom�trico: " + primeros10Viajes.darElemento(i).darDatosViaje()[5] + "\nDesviaci�n est�ndar geom�trica: " + primeros10Viajes.darElemento(i).darDatosViaje()[6] + "\n---------");
				}
				
				System.out.println("Informaci�n 10 �ltimos viajes:\n");
				
				for (int i = ultimos10Viajes.darTamano() - 1; i >= 0; i--)
				{
					System.out.println("La informaci�n del viaje n�mero " + (modelo.darDatosSegunHora().darTamano() - i ) + " es:\n");
					System.out.println("Id zona de origen: " + ultimos10Viajes.darElemento(i).darDatosViaje()[0] + "\nId zona de destino: " + ultimos10Viajes.darElemento(i).darDatosViaje()[1] + "\nHora: " + ultimos10Viajes.darElemento(i).darDatosViaje()[2] + "\nTiempo promedio: " + ultimos10Viajes.darElemento(i).darDatosViaje()[3] + "\nDesviaci�n est�ndar: " + ultimos10Viajes.darElemento(i).darDatosViaje()[4] + "\nTiempo promedio geom�trico: " + ultimos10Viajes.darElemento(i).darDatosViaje()[5] + "\nDesviaci�n est�ndar geom�trica: " + ultimos10Viajes.darElemento(i).darDatosViaje()[6] + "\n---------");
				}

				break;

			case 4:
				
				if(modelo.darDatosSegunHora() == null)
				{
					System.out.println("---------\nPrimero debe consultar los viajes segun una hora dada. (Opcion 2) \n---------");
					break;
				}
				
				if(modelo.darDatosSegunHora().darTamano() == 0)
				{
					System.out.println("---------\nNo hay datos registrados seg�n la hora dada.\n---------");
					break;
				}
				
				long duracionMergeSort = modelo.ordenamientoAscendenteMergeSort(modelo.darDatosSegunHora());
				System.out.println("El tiempo que tom� el algoritmo realizando el ordenamiento fue de: " + duracionMergeSort + " milisegundos\n---------");

				primeros10Viajes = modelo.darDatosSegunHora().darPrimeros10Viajes();
				ultimos10Viajes = modelo.darDatosSegunHora().darUltimos10Viajes();
				
				System.out.println("Informaci�n 10 primeros viajes:\n");
				
				for (int i = 0; i < primeros10Viajes.darTamano(); i++)
				{
					System.out.println("La informaci�n del viaje n�mero " + (i + 1) + " es:\n");
					System.out.println("Id zona de origen: " + primeros10Viajes.darElemento(i).darDatosViaje()[0] + "\nId zona de destino: " + primeros10Viajes.darElemento(i).darDatosViaje()[1] + "\nHora: " + primeros10Viajes.darElemento(i).darDatosViaje()[2] + "\nTiempo promedio: " + primeros10Viajes.darElemento(i).darDatosViaje()[3] + "\nDesviaci�n est�ndar: " + primeros10Viajes.darElemento(i).darDatosViaje()[4] + "\nTiempo promedio geom�trico: " + primeros10Viajes.darElemento(i).darDatosViaje()[5] + "\nDesviaci�n est�ndar geom�trica: " + primeros10Viajes.darElemento(i).darDatosViaje()[6] + "\n---------");
				}
				
				System.out.println("Informaci�n 10 �ltimos viajes:\n");
				
				for (int i = ultimos10Viajes.darTamano() - 1; i >= 0; i--)
				{
					System.out.println("La informaci�n del viaje n�mero " + (modelo.darDatosSegunHora().darTamano() - i ) + " es:\n");
					System.out.println("Id zona de origen: " + ultimos10Viajes.darElemento(i).darDatosViaje()[0] + "\nId zona de destino: " + ultimos10Viajes.darElemento(i).darDatosViaje()[1] + "\nHora: " + ultimos10Viajes.darElemento(i).darDatosViaje()[2] + "\nTiempo promedio: " + ultimos10Viajes.darElemento(i).darDatosViaje()[3] + "\nDesviaci�n est�ndar: " + ultimos10Viajes.darElemento(i).darDatosViaje()[4] + "\nTiempo promedio geom�trico: " + ultimos10Viajes.darElemento(i).darDatosViaje()[5] + "\nDesviaci�n est�ndar geom�trica: " + ultimos10Viajes.darElemento(i).darDatosViaje()[6] + "\n---------");
				}

				break;

			case 5:

				if(modelo.darDatosSegunHora() == null)
				{
					System.out.println("---------\nPrimero debe consultar los viajes segun una hora dada. (Opcion 2) \n---------");
					break;
				}
				
				if(modelo.darDatosSegunHora().darTamano() == 0)
				{
					System.out.println("---------\nNo hay datos registrados seg�n la hora dada.\n---------");
					break;
				}
				
				long duracionQuickSort = modelo.ordenamientoAscendenteMergeSort(modelo.darDatosSegunHora());
				System.out.println("El tiempo que tom� el algoritmo realizando el ordenamiento fue de: " + duracionQuickSort + " milisegundos\n---------");

				primeros10Viajes = modelo.darDatosSegunHora().darPrimeros10Viajes();
				ultimos10Viajes = modelo.darDatosSegunHora().darUltimos10Viajes();
				
				System.out.println("Informaci�n 10 primeros viajes:\n");
				
				for (int i = 0; i < primeros10Viajes.darTamano(); i++)
				{
					System.out.println("La informaci�n del viaje n�mero " + (i + 1) + " es:\n");
					System.out.println("Id zona de origen: " + primeros10Viajes.darElemento(i).darDatosViaje()[0] + "\nId zona de destino: " + primeros10Viajes.darElemento(i).darDatosViaje()[1] + "\nHora: " + primeros10Viajes.darElemento(i).darDatosViaje()[2] + "\nTiempo promedio: " + primeros10Viajes.darElemento(i).darDatosViaje()[3] + "\nDesviaci�n est�ndar: " + primeros10Viajes.darElemento(i).darDatosViaje()[4] + "\nTiempo promedio geom�trico: " + primeros10Viajes.darElemento(i).darDatosViaje()[5] + "\nDesviaci�n est�ndar geom�trica: " + primeros10Viajes.darElemento(i).darDatosViaje()[6] + "\n---------");
				}
				
				System.out.println("Informaci�n 10 �ltimos viajes:\n");

				for (int i = ultimos10Viajes.darTamano() - 1; i >= 0; i--)
				{
					System.out.println("La informaci�n del viaje n�mero " + (modelo.darDatosSegunHora().darTamano() - i ) + " es:\n");
					System.out.println("Id zona de origen: " + ultimos10Viajes.darElemento(i).darDatosViaje()[0] + "\nId zona de destino: " + ultimos10Viajes.darElemento(i).darDatosViaje()[1] + "\nHora: " + ultimos10Viajes.darElemento(i).darDatosViaje()[2] + "\nTiempo promedio: " + ultimos10Viajes.darElemento(i).darDatosViaje()[3] + "\nDesviaci�n est�ndar: " + ultimos10Viajes.darElemento(i).darDatosViaje()[4] + "\nTiempo promedio geom�trico: " + ultimos10Viajes.darElemento(i).darDatosViaje()[5] + "\nDesviaci�n est�ndar geom�trica: " + ultimos10Viajes.darElemento(i).darDatosViaje()[6] + "\n---------");
				}

				break;

			case 6: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}