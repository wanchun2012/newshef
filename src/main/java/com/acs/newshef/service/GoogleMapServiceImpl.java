package com.acs.newshef.service;

import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acs.newshef.dao.GoogleMapDAOImpl;
import com.acs.newshef.orm.GoogleMap;
 

@Service("mapService")
@Transactional
public class GoogleMapServiceImpl 
{
	@Autowired
  private GoogleMapDAOImpl mapDAO;
   
  public void addMarker(GoogleMap marker) throws HibernateException
  {
    mapDAO.addMarker(marker);;
  }
  
  public Set<GoogleMap> loadMarkers() throws HibernateException 
  {
      return mapDAO.loadMarkers();
  }
  
  public void updateMarker(GoogleMap marker) throws HibernateException 
  {
  	mapDAO.updateMarker(marker);
  }
  
  public GoogleMap getMarkerById(String id) throws HibernateException 
  {
    return mapDAO.getMarkerById(id);
  }
  
  public void remove(String id) throws HibernateException
  {
  	mapDAO.remove(id);
  }
}
