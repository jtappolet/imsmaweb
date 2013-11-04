package org.gichd.IMSMAweb.shared;

public class ThemaElement {
	
	public enum ElementType {POINT, LINE, POLYLINE, POLYGON};
	
	private ElementType type = ElementType.POLYGON;
	private String name = "";
	private String symbolPath = "";
	private String elementID = "";
	
	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbolPath() {
		return symbolPath;
	}

	public void setSymbolPath(String symbolPath) {
		this.symbolPath = symbolPath;
	}

	public String getElementID() {
		return elementID;
	}

	public void setElementID(String elementID) {
		this.elementID = elementID;
	}

	public ThemaElement(ElementType type){
		
		this.type = type;
		
	}
	
	

}
