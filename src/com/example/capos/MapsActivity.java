package com.example.capos;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity{

	private GoogleMap map;
	private LocationManager locationManager;
	private LatLng myLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitiy_maps);
      
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();       
        
        centerMapOnMyLocation();
    }
	
	private void centerMapOnMyLocation() {

	    map.setMyLocationEnabled(true);

	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    Criteria criteria = new Criteria();
	    Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
	    if (location != null) {
	        myLocation = new LatLng(location.getLatitude(), location.getLongitude());
	    }
	    
	    if (myLocation != null)
	    	map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
	    
	    createLocationSpotsInMap();
	}

	private void createLocationSpotsInMap() {
		DBAdapter adapter = DatabaseService.getInstance().getAdapter();
		if (adapter != null){
			List<MyEvent> cursor = adapter.getAllRecords();
			for (MyEvent e: cursor){
				if (e.getName().equals("capos")){
					map.addMarker(new MarkerOptions().position(new LatLng(48.400773,9.991045)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
				}
				else{
					map.addMarker(new MarkerOptions().position(new LatLng(48.396727,9.990422)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
				}
			}
		}
	}
	
//	@Override
//	public void onMapClick(LatLng position) {
//		map.addMarker(new MarkerOptions().position(position).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
//	}
}
