package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/13/18:18
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    private int locationid;
    private String locationname;
    private String longitude;
    private String latitude;

}
