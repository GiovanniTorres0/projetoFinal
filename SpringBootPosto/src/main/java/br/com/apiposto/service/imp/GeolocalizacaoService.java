package br.com.apiposto.service.imp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

import br.com.apiposto.modelo.Ubicacion;

@Service
public class GeolocalizacaoService {

	public List<Double> obterLatELong(Ubicacion ubicacion, String DatosEndereco)
			throws ApiException, InterruptedException, IOException, ArrayIndexOutOfBoundsException {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD9rUZ_MtqyuD2s3sOFNBNlVJabNLGgMUA");
		GeocodingApiRequest request = GeocodingApi.newRequest(context)
				.address(ubicacion.getEndereco() + " " + DatosEndereco);
		GeocodingResult[] result = request.await();
		GeocodingResult resultado = result[0];
		Geometry geometry = resultado.geometry;
		LatLng location = geometry.location;
		System.out.println(ubicacion.getEndereco() + " " + DatosEndereco);
		return Arrays.asList(location.lat, location.lng);
	}
}
