package org.gichd.IMSMAweb.shared;

import java.io.Serializable;

public class GEOBoundingBox implements Serializable{

	private static final long serialVersionUID = -5184953826274163178L;
	private GEOElement upperLeft = null;
	private GEOElement lowerRight = null;
	
	public GEOBoundingBox(GEOElement upperLeft, GEOElement lowerRight){
		this.lowerRight = lowerRight;
		this.upperLeft = upperLeft;
	}
	
	public GEOElement getLowerRight(){
		return lowerRight;
	}
	public GEOElement getUpperLeft(){
		return upperLeft;
	}
	public GEOElement getLowerLeft(){
		return new GEOElement(upperLeft.getLat(),lowerRight.getLon());
	}
	public GEOElement getUpperRight(){
		return new GEOElement(lowerRight.getLat(), upperLeft.getLon());
	}
	public GEOElement getCenter(){
		return new GEOElement((upperLeft.getLat()-lowerRight.getLat())/2,(upperLeft.getLon()-lowerRight.getLon())/2);
	}
	
}
