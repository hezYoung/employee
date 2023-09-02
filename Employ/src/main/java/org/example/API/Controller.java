package org.example.API;

import org.example.Entity.Location;
import org.example.Service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/13/17:48
 * @Description:
 */

@RestController
@RequestMapping("/api")
public class Controller {
    private MapService mapService;
    @Autowired
    public Controller(MapService mapService) {
        this.mapService = mapService;
    }
    @GetMapping("findPlace/{locationname}/{region}")
    public Location findPlace(@PathVariable("locationname") String locationname,
                              @PathVariable("region") String region
                            ) {
        Location place = mapService.find(locationname, region);

        return place;
    }
    @GetMapping("distance/{latitude}/{longitude}")
    public int distance(@PathVariable("longitude") String longitude,
                              @PathVariable("latitude") String latitude) {
        int DIS = mapService.distance(longitude, latitude);

        return DIS;
    }
}