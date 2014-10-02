package com.example.beyondto;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.android.gms.maps.model.UrlTileProvider;

public class myTileProvider extends UrlTileProvider {

	private static final String FORMAT;
	private String mMapIdentifier;

	public myTileProvider(String mapIdentifier) {
		super(256, 256);
		this.mMapIdentifier = mapIdentifier;
	}

	/*
	 * mapUri = "https://a.tiles.mapbox.com/v4/elviggio.jlac1733/" +
	 * "page.html?access_token=pk.eyJ1IjoiZWx2aWdnaW8iLCJhIjoiYVNEVkVrcyJ9.dD41RqV94iuVMkIrW0yCng#"
	 * + "16/45.0651/7.6578";
	 */

	static {
		FORMAT = "https://a.tiles.mapbox.com/v4/%s/%d/%d/%d";
	}

	@Override
	public URL getTileUrl(int x, int y, int z) {
		try {
			return new URL(String.format(FORMAT, this.mMapIdentifier, z, x, y));
		} catch (MalformedURLException e) {
			return null;
		}
	}

}
