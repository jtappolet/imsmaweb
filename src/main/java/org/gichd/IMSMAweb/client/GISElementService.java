package org.gichd.IMSMAweb.client;

import java.util.ArrayList;

import org.gichd.IMSMAweb.shared.GEOBoundingBox;
import org.gichd.IMSMAweb.shared.ThemaElement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GISElementService extends RemoteService {
	public ArrayList<ThemaElement> getHazards(GEOBoundingBox bbox);
	
}
