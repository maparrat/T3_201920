package model.logic;

public class UBERTrip implements Comparable<UBERTrip>
{
	private double[] datosViaje;
	
	public UBERTrip(double sourceid, double dstid, double hod, double mtt, double sdtt, double gmtt, double gsdtt)
	{
	   datosViaje = new double[7];
		datosViaje[0] = sourceid;
		datosViaje[1] = dstid;
		datosViaje[2] = hod;
		datosViaje[3] = mtt;
		datosViaje[4] = sdtt;
		datosViaje[5] = gmtt;
		datosViaje[6] = gsdtt;
	}
	
	public double[] darDatosViaje()
	{
		return datosViaje;
	}

	public int compareTo(UBERTrip param)
	{
		if(datosViaje[3] > param.datosViaje[3])
		{
			return 1;
		}
		else if(datosViaje[3] <  param.datosViaje[3])
		{
			return -1;
		}
		else
		{
			if(datosViaje[4] > param.datosViaje[4])
			{
				return 1;
			}
			else if(datosViaje[4] < param.datosViaje[4])
			{
				return -1;
			}
		}		
		return 0;
	}
	public double darPrimerdatos()
	{
		return datosViaje[1];
	}
}