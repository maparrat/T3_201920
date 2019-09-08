package test.logic;

import model.data_structures.ArregloDinamico;
import model.logic.MVCModelo;
import model.logic.UBERTrip;

import static org.junit.Assert.*;

import java.io.File;

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

		ordenadoA = new ArregloDinamico<UBERTrip>(3);
		ordenadoD = new ArregloDinamico<UBERTrip>(3);
		desorden = new ArregloDinamico<UBERTrip>(3);
		dato1 = new UBERTrip(1, 1, 1, 1, 1, 1, 1);
		dato2 = new UBERTrip(2, 2, 2, 2, 2, 2 ,2);
		dato3 = new UBERTrip(3, 3, 3, 3, 3, 3, 3);

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
	public void testShellsort()
	{
		modelo.ordenamientoAscendenteShellSort(ordenadoA);
		modelo.ordenamientoAscendenteShellSort(ordenadoD);
		modelo.ordenamientoAscendenteShellSort(desorden);
		assertEquals( 1, ordenadoA.darElemento(0).darPrimerdato(), 0);
		
		System.out.println(ordenadoD.darElemento(0).darPrimerdato());
		System.out.println(ordenadoD.darElemento(1).darPrimerdato());
		System.out.println(ordenadoD.darElemento(2).darPrimerdato());
		
		assertEquals( 2, ordenadoD.darElemento(1).darPrimerdato(), 0);
		assertEquals( 3, desorden.darElemento(2).darPrimerdato(), 0);
	}
	
	@Test
	public void testMergeSort()
	{
		modelo.ordenamientoAscendenteMergeSort(ordenadoA);
		modelo.ordenamientoAscendenteMergeSort(ordenadoD);
		modelo.ordenamientoAscendenteMergeSort(desorden);
		assertEquals( 1, ordenadoA.darElemento(0).darPrimerdato(), 0);
		assertEquals( 2, ordenadoD.darElemento(1).darPrimerdato(), 0);
		assertEquals( 3, desorden.darElemento(2).darPrimerdato(), 0);
	}
	
	@Test
	public void testQuicksort()
	{
		modelo.ordenamientoAscendenteQuickSort(ordenadoA);
		modelo.ordenamientoAscendenteQuickSort(ordenadoD);
		modelo.ordenamientoAscendenteQuickSort(desorden);
		assertEquals(1, ordenadoA.darElemento(0).darPrimerdato(), 0);
		assertEquals(2, ordenadoD.darElemento(1).darPrimerdato(), 0);
		assertEquals(3, desorden.darElemento(2).darPrimerdato(), 0);
	}
}