package test.logic;




import model.data_structures.ArregloDinamico;
import model.logic.MVCModelo;
import model.logic.UBERTrip;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMVCModelo
{	
	private MVCModelo modelo;
	private ArregloDinamico<UBERTrip> ordenadoA, ordenadoD, desorden;
	private UBERTrip dato1, dato2, dato3;

	@Before
	public void setUp1()
	{
		modelo= new MVCModelo();
		try {
			modelo.cargarArchivoCSV();
		}
		catch (Exception e) {
			// TODO: handle exception
		}

		ordenadoA = new ArregloDinamico<UBERTrip>(3);
		ordenadoD = new ArregloDinamico<UBERTrip>(3);
		desorden = new ArregloDinamico<UBERTrip>(3);
		dato1 = new UBERTrip(1, 1, 1, 1, 1,1, 1);
		dato2 = new UBERTrip(2, 2, 2, 2, 2,2 ,2);
		dato3 = new UBERTrip(3, 3, 3, 3,3,3, 3);

		ordenadoA.agregar(dato1);
		ordenadoA.agregar(dato2);
		ordenadoA.agregar(dato3);

		ordenadoD.agregar(dato3);
		ordenadoD.agregar(dato2);
		ordenadoD.agregar(dato1);

		desorden.agregar(dato2);
		desorden.agregar(dato1);
		desorden.agregar(dato3);

	}	




	@Test
	public void testShelsort()
	{
		setUp1();
		modelo.ordenamientoAscendenteShellSort(ordenadoA);
		modelo.ordenamientoAscendenteShellSort(ordenadoD);
		modelo.ordenamientoAscendenteShellSort(desorden);
		assertEquals( 0, ordenadoA.darElemento(0).darPrimerdatos());
		assertEquals( 2, ordenadoD.darElemento(1).darPrimerdatos());
		assertEquals( 3, desorden.darElemento(2).darPrimerdatos());
		
	}
	@Test
	public void testMergeSort()
	{
		setUp1();
		modelo.ordenamientoAscendenteShellSort(ordenadoA);
		modelo.ordenamientoAscendenteShellSort(ordenadoD);
		modelo.ordenamientoAscendenteShellSort(desorden);
		assertEquals( 0, ordenadoA.darElemento(0).darPrimerdatos());
		assertEquals( 2, ordenadoD.darElemento(1).darPrimerdatos());
		assertEquals( 3, desorden.darElemento(2).darPrimerdatos());
		
	}
	@Test
	public void testQuicksort()
	{
		setUp1();
		modelo.ordenamientoAscendenteShellSort(ordenadoA);
		modelo.ordenamientoAscendenteShellSort(ordenadoD);
		modelo.ordenamientoAscendenteShellSort(desorden);
		assertEquals( 0, ordenadoA.darElemento(0).darPrimerdatos());
		assertEquals( 2, ordenadoD.darElemento(1).darPrimerdatos());
		assertEquals( 3, desorden.darElemento(2).darPrimerdatos());
		
	}

}