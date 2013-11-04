package org.gichd.IMSMAweb.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.gichd.IMSMAweb.client.GISElementService;
import org.gichd.IMSMAweb.shared.GEOBoundingBox;
import org.gichd.IMSMAweb.shared.ThemaElement;
import org.gichd.IMSMAweb.shared.ThemaElement.ElementType;
import org.gichd.IMSMAweb.shared.IMSMAThemaElement;
import org.gichd.IMSMAweb.shared.IMSMAThemaElement.IMSMAType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GISElementServiceImpl extends RemoteServiceServlet implements
		GISElementService {

	public ArrayList<ThemaElement> getHazards(GEOBoundingBox bbox){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://192.168.1.13:3306/imsma","root", "password");
			String query = "SELECT " +
					"	hazard.hazard_localid AS hazard_localid, " +
					"	Avg(geopoint.latitude) AS marker_lat, " +
					"	Avg(geopoint.longitude) AS marker_lon " +
					"FROM " +
					"hazard " +
					"LEFT JOIN hazard_has_geospatialinfo ON hazard.hazard_guid = hazard_has_geospatialinfo.hazard_guid " +
					"LEFT JOIN geopoint ON hazard_has_geospatialinfo.geospatialinfo_guid = geopoint.geospatialinfo_guid " +
					"GROUP BY " +
					"hazard.hazard_localid";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				IMSMAThemaElement element = new IMSMAThemaElement(ElementType.POINT, IMSMAType.HAZARD);
				element.setElementID(rs.getString("hazard_localid"));
				double markerLat = rs.getDouble("marker_lat");
				double markerLon = rs.getDouble("marker_lon");
		
				
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return null;
		
	}

}
