package org.example.Dao;

import org.example.Entity.Location;
import org.example.Entity.MapRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/16/16:14
 * @Description:
 */
@Repository("mapMySQL")
public class MapDao  implements MapData{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public MapDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public Optional<Location> selectLocationById(String locationid) {
        final String sql = "select * from location where locationid=?";
        List<Location> locationList = jdbcTemplate.query(sql, new MapRowmapper(), locationid);
        return locationList.stream().findFirst();
    }

    @Override
    public List<Location> insertLocation(String longitude,String latitude) {
        final String sql = "insert into location(longitude,latitude) value (?,?)";
        int result=jdbcTemplate.update(sql,
                longitude,
                latitude
        );
        if (result > 0) {
            System.out.println("信息添加成功");
        }else {
            System.out.println("插入失败");
        }
        return null;
    }
    }

