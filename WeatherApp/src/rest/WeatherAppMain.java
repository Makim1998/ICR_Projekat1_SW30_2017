package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Grad;
import beans.Gradovi;
import beans.Grafikon;
import beans.Grafikoni;

public class WeatherAppMain {

	private static Gson g = new Gson();
	private static String KEY = "7477c646372341ada7a159eb42f22ea1";
	
	private static Grafikon zaGrafikon(String imeGrada,String podatak, int interval) throws JSONException  {
		try {
			StringBuilder result = new StringBuilder();
			String urlPath = "https://api.openweathermap.org/data/2.5/forecast?q=" + imeGrada +"&appid=" + KEY;
			URL url = new URL(urlPath);
			URLConnection conn;
			conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine())!= null) {
				result.append(line);
			}
			System.out.println(result.toString());
			
			JSONObject jo = new JSONObject(result.toString()); 
			System.out.println(jo.toString());
			JSONArray arr = jo.getJSONArray("list");
			Grafikon graf= new Grafikon(imeGrada);
			for (int i = 0; i < arr.length() && i <= interval; i+=3)
			{
			    int j = i/3;
			    Double vrednost = arr.getJSONObject(j).getJSONObject("main").getDouble(podatak);
			    System.out.println(vrednost);
			    String datum = arr.getJSONObject(j).getString("dt_txt");
			    System.out.println(datum);
			    graf.getDatumi().add(datum);
			    graf.getMerenja().add(String.valueOf(vrednost));
			}
			return graf;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static Grad zaTabelu(String imeGrada) {
		try {
			StringBuilder result = new StringBuilder();
			String urlPath = "https://api.openweathermap.org/data/2.5/weather?q=" + imeGrada +"&appid=" + KEY;
			URL url = new URL(urlPath);
			URLConnection conn;
			conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine())!= null) {
				result.append(line);
			}
			Map<String, Object> respMap = jsonToMap(result.toString());
			Map<String, Object> main = jsonToMap(respMap.get("main").toString());
			String datum = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
			String tempMin = main.get("temp_min").toString();
			String vlaznost = main.get("humidity").toString();
			String tempMax = main.get("temp_max").toString();
			String temp = main.get("temp").toString();
			String pritisak = main.get("pressure").toString();
			String vidljivost = respMap.get("visibility").toString();
			System.out.println(datum+" " +vlaznost +" "+temp+" " + tempMin + " " +tempMax +" "+pritisak+ " " + vlaznost + " " + vidljivost );
			Grad g = new Grad(imeGrada, datum, Double.parseDouble(temp),Double.parseDouble(tempMin), Double.parseDouble(tempMax), Double.parseDouble(pritisak), Double.parseDouble(pritisak), Double.parseDouble(vlaznost));
			return g;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	private static Map<String,Object> jsonToMap(String s){
		Map<String,Object> map = g.fromJson(s, new TypeToken<HashMap<String,Object>>() {}.getType());
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		//zaGrafikon("London", "temp", 9);
		port(8080);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
		get("rest/tabela/:grad", (req, res) -> {
			res.type("application/json");
			String nazivi = req.params(":grad");
			System.out.println(nazivi);
			String[] split = nazivi.split(";");
			System.out.println("Duzina tabele");
			System.out.println(split.length);
			Gradovi gradovi = new Gradovi();
			for(int i=0;i < split.length;i++) {
				System.out.println(split[i]);
				Grad grad = zaTabelu(split[i]);
				
				gradovi.getGradovi().add(grad);
			}
			
			return g.toJson(gradovi.getGradovi());
		});
		
		get("rest/grafikon/:grad/:podatak/:interval", (req, res) -> {
			res.type("application/json");
			
			String nazivi = req.params(":grad");
			String podatak = req.params(":podatak");
			System.out.println(nazivi);
			System.out.println(podatak);
			int interval = Integer.parseInt(req.params(":interval"));
			System.out.println(interval);
			String[] split = nazivi.split(";");
			Grafikoni grafikoni = new Grafikoni(podatak);
			for(int i=0;i < split.length;i++) {
				System.out.println(split[i]);
				Grafikon graf = zaGrafikon(split[i], podatak, interval);
				grafikoni.getGradovi().add(graf);
			}
			
			return g.toJson(grafikoni);
		});
	}

}
