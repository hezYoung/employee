package org.example.Service;

import org.example.Entity.Location;
import org.example.Dao.MapDao;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class MapService {
    private MapDao mapDao;

    public MapService(MapDao mapDao) {
        this.mapDao = mapDao;
    }

    public Location find(String locationName, String region){
        Location place = new Location();
        place.setLocationname(locationName);
        String ak = "Ysff3W8G3OoAo9In0oSEebAXHuGpTdTX";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)).build();
        HttpResponse response=null;
        String endPoint = "https://api.map.baidu.com/place/v2/search?";
        URI uri = URI.create(endPoint + "query=" + locationName + "&region=" + region +
                "&output=json" + "&ak=" + ak);


        try {
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
            response= client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject placeJson=null;
        try {
            placeJson = new JSONObject((response.body().toString()));
        } catch (Exception e) {
            System.out.println("parsing to json error");
        }

        try {
            String latitude = placeJson.getJSONArray("results").getJSONObject(0).getJSONObject("location").getString("lat");
            String longitude = placeJson.getJSONArray("results").getJSONObject(0).getJSONObject("location").getString("lng");
            place.setLongitude(longitude);
            place.setLatitude(latitude);
//            mapDao.insertLocation(longitude, latitude);

        } catch (Exception e) {
            System.out.println("getting value from json error");
        }

        return place;
    }
    public int distance(String longitude,String latitude){
        int distance1=0;
        Location place = new Location();
        place.setLongitude(longitude);
        place.setLatitude(latitude);
        String  endlon="103.738152";
        String endlat="29.56574";
        String ak = "Ysff3W8G3OoAo9In0oSEebAXHuGpTdTX";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)).build();
        HttpResponse response=null;
        String endPoint = "https://api.map.baidu.com/direction/v2/driving?";
        URI uri = URI.create(endPoint + "origin=" + latitude+","+longitude+"&destination=" + endlat+","+endlon + "&ak=" + ak);
        System.out.println(uri);

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
            response= client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject placeJson=null;
        try {
            placeJson = new JSONObject((response.body().toString()));
        } catch (Exception e) {
            System.out.println("parsing to json error");
        }

        try {
             distance1 = placeJson.getJSONObject("result").getJSONArray("routes").getJSONObject(0).getInt("distance");


        } catch (Exception e) {
            System.out.println("getting value from json error");
        }

        return distance1;
    }
    public Optional<Location> selectLocationById(String locationid) {

        return mapDao.selectLocationById(locationid);
    }


}
