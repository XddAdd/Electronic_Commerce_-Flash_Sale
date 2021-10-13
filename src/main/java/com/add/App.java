package com.add;

import com.add.dao.UserDOMapper;
import com.add.databoject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.add"})
@MapperScan("com.add.dao")
@RestController
public class App 
{
    @Resource
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO == null)
            return "不存在";
        System.out.println(userDO);
        return "正常";
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
