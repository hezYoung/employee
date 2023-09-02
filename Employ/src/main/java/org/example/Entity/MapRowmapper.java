package org.example.Entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/16/16:17
 * @Description:
 */
public class MapRowmapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Location(
                rs.getInt("locationid"),
                rs.getString("locationname"),
                rs.getString("latitude"),
                rs.getString("longitude")

        );
    }
}
