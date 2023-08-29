package com.alex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Logic {
    UI ui;

    DecimalFormat decimalFormat = new DecimalFormat("#.0");
    JButton searchBtn;
    String location, weather;
    int humidity, weatherID;
    Double temperature, wind;
    
    public Logic(UI ui) {
        this.ui = ui;
    }

    public void setupSearchButton() {
        searchBtn = new JButton();
        ui.configureButtonUI(searchBtn);
        searchBtn.addActionListener(new ButtonListener());
    }

    private String getApiKey() {
        Properties properties = new Properties();
        try (InputStream inputStream = Logic.class.getResourceAsStream("/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String apiKey = properties.getProperty("api_key");
        return apiKey;
    }
 
    private void getWeatherData() throws ClientProtocolException, IOException {
        String apiKey = getApiKey();
        String URL = "https://api.openweathermap.org/data/2.5/weather?"
               + "q=" + location
               + "&appid=" + apiKey
               + "&units=metric"; 
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(URL);
        CloseableHttpResponse response = client.execute(request);
        try {
            // Get response body
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(responseBody);
            // Retrieve relevant weather data
            JSONObject mainObject = jsonObject.getJSONObject("main");
            temperature = mainObject.getDouble("temp");
            humidity = mainObject.getInt("humidity");
            JSONObject windObject = jsonObject.getJSONObject("wind");
            wind = windObject.getDouble("speed");
            JSONArray weatherArray = jsonObject.getJSONArray("weather");

            for (int i = 0; i < weatherArray.length(); i++) {
                JSONObject weatherObject = weatherArray.getJSONObject(i);
                weather = weatherObject.getString("description");
                weatherID = weatherObject.getInt("id");
            }

            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
    }

    private String determineImageType(int weatherID) {
        if (weatherID == 800) {
            return "/clear.png";
        }

        int id = weatherID/100;
        switch (id) {
            case 2:
                return "/rain.png";
            case 3:
                return "/drizzle.png";
            case 5:
                return "/rain.png";
            case 6:
                return "/snow.png";
            case 7:
                return "/mist.png";
            case 8:
                return "/clouds.png";
        }
        return "";
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField searchField = ui.getSearchField();
            JTextField temperatureField = ui.getTemperatureField();
            JTextField weatherField = ui.getWeatherField();
            JTextField humidityField = ui.getHumidityField();
            JTextField windField = ui.getWindField();

            location = searchField.getText().trim().toLowerCase(Locale.US);
            if (location.length() < 1) {
                System.out.println("Invalid input");
                return;
            }
            
            try {
                getWeatherData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            String imgName = determineImageType(weatherID);
            ui.updateImageLabel(imgName);            

            String windStr = decimalFormat.format(wind);
            String temperatureStr = decimalFormat.format(temperature);

            temperatureField.setText(temperatureStr+"\u00B0C");
            humidityField.setText(humidity+"%");
            windField.setText(windStr+" Km/h");
            weatherField.setText(weather.substring(0, 1)
                                        .toUpperCase(Locale.US)
                                        + weather.substring(1));
        }
    }    
}