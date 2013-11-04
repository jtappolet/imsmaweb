package org.gichd.IMSMAweb.shared;

import java.io.Serializable;

public class GEOElement implements Serializable{

	private static final long serialVersionUID = -7875343373996387665L;
	private float lat = 0;
	private float lon = 0;
	
	public GEOElement(float lat, float lon){
		
		this.lat = lat;
		this.lon = lon;
		
	}
	
	public float getLon(){
		return lon;
	}
	
	public float getLat(){
		return lat;
	}
	
	public void setLat(float lat){
		this.lat = lat;
	}
	
	public void setLon(float lon){
		this.lon = lon;
	}
	
}
