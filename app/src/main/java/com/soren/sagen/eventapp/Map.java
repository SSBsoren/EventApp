package com.YourPackage.EventApp;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentActivity;

import android.os.Bundle;

public class Map extends FragmentActivity {

	float[] pincolor = new float[]{BitmapDescriptorFactory.HUE_AZURE,BitmapDescriptorFactory.HUE_BLUE,BitmapDescriptorFactory.HUE_CYAN,BitmapDescriptorFactory.HUE_GREEN,BitmapDescriptorFactory.HUE_MAGENTA,BitmapDescriptorFactory.HUE_ORANGE,BitmapDescriptorFactory.HUE_RED,BitmapDescriptorFactory.HUE_ROSE,BitmapDescriptorFactory.HUE_VIOLET,BitmapDescriptorFactory.HUE_YELLOW};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);


		//get data
		String[] map_title = getResources().getStringArray(R.array.map_pin_title);
		String[] map_description = getResources().getStringArray(R.array.map_pin_description);
		int[] map_pincolor = getResources().getIntArray(R.array.map_pin_color);
		String[] latitudedeg = getResources().getStringArray(R.array.map_pin_latitudeDegrees);
		String[] longitudedeg  = getResources().getStringArray(R.array.map_pin_longitudeDegrees);


		//make map overlays
		GoogleMap googleMap;
		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview)).getMap();
		for (int i = 0; i < map_title.length; i++) {
			LatLng latLng = new LatLng(Double.parseDouble(latitudedeg[i]), Double.parseDouble(longitudedeg[i]));
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.addMarker(new MarkerOptions()
					.position(latLng)
					.title(map_title[i])
					.snippet(map_description[i])
					.icon(BitmapDescriptorFactory.defaultMarker(pincolor[map_pincolor[i]])));
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
		}
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.getUiSettings().setAllGesturesEnabled(true);

	}

}