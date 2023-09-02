package org.example.Dao;

import org.example.Entity.Location;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/16/16:14
 * @Description:
 */
public interface MapData {
    Optional<Location> selectLocationById(String locationid);
    List<Location> insertLocation(String longitude,String latitude);
}
