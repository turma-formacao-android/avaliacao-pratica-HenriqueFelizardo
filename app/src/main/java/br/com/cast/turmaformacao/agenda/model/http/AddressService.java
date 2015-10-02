package br.com.cast.turmaformacao.agenda.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.cast.turmaformacao.agenda.model.entities.Address;

public class AddressService {
    private static final String URL = "http://api.postmon.com.br/v1/cep/";

    private AddressService() {
        super();
    }

    public static Address getAddresByZip(String zipCode) {
        Address address = null;

        try {
            java.net.URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.e("getAddressByZip", "Codigo de retorno: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(inputStream, Address.class);
            }

        } catch (Exception e) {
            Log.e(AddressService.class.getName(), e.getMessage());
        }


        return address;
    }

}
