package org.example.API;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IToken;
import org.example.Service.EmployeeService;
import org.example.Entity.Employee;
import org.example.Entity.Location;
import org.example.Entity.User;
import org.example.Service.MapService;
import org.example.Service.UserService;
import org.example.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/14/15:10
 * @Description:
 */
@Controller
@RequestMapping("/employ")
public class EmployeeContreller {
    private UserService userService;
    private EmployeeService employeeService;
    private MapService mapService;
    @Autowired
    public EmployeeContreller(UserService userService, EmployeeService employeeService, MapService mapService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.mapService = mapService;
    }


    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("singu")
    public String sing(@RequestParam("username") String username,
                       @RequestParam("password") String password, User user,
                       RedirectAttributes ra, Map<String, Object> map, HttpServletRequest request) {
//        String token1 = request.getHeader("token");
//        DecodedJWT verify = JwtUtils.verify(token1);

//        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(username)) {
            User byName = userService.selename(user.getUsername());
            if (byName == null) {
                //登录失败
                map.put("msg", "该用户名未注册！");
                return "login";
            } else {
                if (byName.getPassword().equals(password)) {
                    //获取token
//                    Map<String, String> payload = new HashMap<>();
//                    payload.put("name", byName.getUsername());
//                    payload.put("password", byName.getPassword());
//                   String token=   JwtUtils.getToken(payload);
//                    map.put("token", token);
                   // System.out.println(map);
                    //登录成功

                    System.out.println("用户名：" + username);

                    ra.addFlashAttribute("message", user.getUsername());
                    return "welcome";
                } else {
                    //密码错误
                    map.put("msg", "密码错误！");
                    return "login";
                }

            }

        } else {
            //登录失败
            map.put("msg", "请输入用户名和密码！");
            return "login";
        }
    }


    @GetMapping("employees")
    public String employees(Model model) {

        List<Employee> employeeList = employeeService.showEmployee();
        model.addAttribute("listemployees", employeeList);
        return "index";
    }

    @GetMapping("edit/{employeeid}")
    public String edit(@PathVariable("employeeid") String employeeid,
                       Model model
    ) {
        if (employeeService.selectEmployeeByid(employeeid).isPresent()) {
            Employee employee = employeeService.selectEmployeeByid(employeeid).get();
            model.addAttribute("employee", employee);

        }

        return "employeesEdit";
    }

    @GetMapping("new")
    public String show(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeesAdd";
    }

    @PostMapping("/save")
    public String update(Employee employee) {
        employeeService.updataEmployee(employee);
        return "redirect:/employ/employees";

    }

    @PostMapping("/add")
    public String add(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employ/employees";

    }

    @GetMapping("delete/{employeeid}")
    public String delete(@PathVariable("employeeid") String employeeid,
                         RedirectAttributes ra) {
        employeeService.deleteEmployeeByid(employeeid);

        return "redirect:/employ/employees";

    }

//    @GetMapping("search/{employeename}")
//    public String search(@PathVariable("employeename") String employeename,
//                         Model model
//    ) {
//
//        List<Employee> employeeList = employeeService.selectEmployeeByname(employeename);
//        model.addAttribute("listemployees", employeeList);
//        return "redirect:/employ/employees";
//    }

    @GetMapping("redit")
    public String redit() {
        return "redit";
    }

    @PostMapping("/adduser")
    public String adduser(@RequestParam("username") String username,
                          @RequestParam("password") String password,User user, Map<String, Object> map) {


        String regex = "^[a-zA-Z]{6,12}$";
        if (!StringUtils.isEmpty(username)) {
            User byName = userService.selename(user.getUsername());
            if (byName != null) {
                //登录失败
                map.put("msg", "该用户名已经注册！");
                return "redit";
            } else {
                if (password.matches(regex)) {
                    List<User> user1 = userService.insertUser(username, password);
                    map.put("msg", "注册成功！");
                    return "redit";
                } else {
                    //密码错误
                    map.put("msg", "密码必须包含大小写字母数字且长度在 6 位以上!");
                    return "redit";
                }

            }

        } else {
            //登录失败
            map.put("msg", "请输入用户名和密码！");
            return "redit";
        }

//        User byName = userService.selename(user.getUsername());
//        if (byName!=null){
//            map.put("msg", "用户名重复！");
//        }
//        if (password.matches(regex)){
//            List<User> user1 = userService.insertUser(username, password);
//            map.put("msg", "注册成功！");
//            return "redit";
//        }
//        map.put("msg", "密码必须包含大小写字母数字且长度在 6 位以上!");




    }

    @GetMapping("map/{locationid}")
    public String location(@PathVariable("locationid") String locationid, Model model)
     {
        if (mapService.selectLocationById(locationid).isPresent()) {
          Location location= mapService.selectLocationById(locationid).get();
            Employee employee = employeeService.selectEmployeeByid(locationid).get();
            model.addAttribute("location", location);
            model.addAttribute("employee1", employee);
        }


        return "map";
    }


}
