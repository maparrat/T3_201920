package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.Node;
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
	 * Hilo de ejecución del programa
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
					System.out.println("Numero actual de elementos " + modelo.darTamano());
					System.out.println("Datos primer viaje: \nId zona origen: " + datosPrimerViaje[0] + "\nId zona destino: " + datosPrimerViaje[1] + "\nHora: " + datosPrimerViaje[2] + "\nTiempo promedio: " + datosPrimerViaje[3] + "\n");
					System.out.println("Datos último viaje: \nId zona origen: " + datosUltimoViaje[0] + "\nId zona destino: " + datosUltimoViaje[1] + "\nHora: " + datosUltimoViaje[2] + "\nTiempo promedio: " + datosUltimoViaje[3] + "\n---------");
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
					System.out.println("Debe ingresar valores numéricos\n---------");
					break;
				}

				ArregloDinamico<UBERTrip> lista = modelo.darViajesSegunHora(hora);
				if (lista.darTamano() == 0)
				{
					System.out.println("No hay viajes registrados con las condiciones dadas.\n---------");
				}
				else
				{
					for (int i = 0; i < lista.darTamano(); i++)
					{
						UBERTrip actual = lista.darElemento(i);
						double[] datos = actual.darDatosViaje();
						System.out.println("Id zona de origen: " + datos[0] + "\nId zona de destino: " + datos[1] + "\nTiempo promedio: " + datos[3] + "\nDesviación estándar: " + datos[4] + "\nTiempo promedio geométrico: " + datos[5] + "\nDesviación estándar geométrica: " + datos[6] + "\n---------");
					}
					System.out.println("Número de viajes: " + lista.darTamano() +  "\n---------");
				}
				break;

			case 3:
				System.out.println("El número de viajes reportados en el semestre es: " + modelo.darTamano() + "\n---------");						
				break;

			case 4:
				if(modelo.darTamano() == 0)
				{
					System.out.println("No hay viajes registrados en ningún mes\n---------");
				}
				else
				{
					double mesABuscar;
					try
					{
						System.out.println("--------- \nDar mes a buscar: ");
						mesABuscar = lector.nextInt();
					}
					catch(InputMismatchException e)
					{
						System.out.println("Debe ingresar un valor numérico\n---------");
						break;
					}					

					double cantidad = modelo.numeroViajesSegunMes(mesABuscar);
					System.out.println("El número de viajes reportados en el mes es: " + cantidad + "\nEl porcentaje respecto al total de viajes del semestre es: "+ (cantidad/modelo.darTamano())*100 + "\n---------");
				}
				break;

			case 5:
				if(modelo.darTamano() == 0)
				{
					System.out.println("No hay viajes registrados en ningún mes\n---------");
				}
				else
				{
					double mesABuscar;
					double zonaDeOrigen;
					try
					{
						System.out.println("--------- \nDar mes a buscar: ");
						mesABuscar = lector.nextInt();
						System.out.println("--------- \nDar id zona de origen a buscar: ");
						zonaDeOrigen = lector.nextInt();
					}
					catch(InputMismatchException e)
					{
						System.out.println("Debe ingresar valores numéricos\n---------");
						break;
					}

					double cantidad = modelo.numeroViajesSegunMesYZonaOrigen(mesABuscar, zonaDeOrigen);
					System.out.println("El número de viajes reportados en el mes es para esa zona es: " + cantidad + "\nEl porcentaje respecto al total de viajes del mes es: "+ (cantidad/modelo.numeroViajesSegunMes(mesABuscar))*100 + "\n---------");
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