package org.gichd.IMSMAweb.shared;

public class IMSMAThemaElement extends ThemaElement {
	

	public enum IMSMAType {HAZARD, ACCIDENT, VICTIM, QA, HAZARD_REDUCTION, MRE}
	private IMSMAType imsmaType = null;


	
	public IMSMAThemaElement(ThemaElement.ElementType gisType, IMSMAType imsmaType){
		
		super(gisType);
		this.imsmaType = imsmaType;
		
	}

	public IMSMAType getImsmaType() {
		return imsmaType;
	}

	public void setImsmaType(IMSMAType imsmaType) {
		this.imsmaType = imsmaType;
	}
	
	
	
}
