package online.zongzi.parcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("online.zongzi.parcel.dao")
public class ParcelApplication {

    //启动类 用于启动SpringBoot
    public static void main(String[] args) {
        SpringApplication.run(ParcelApplication.class, args);
    }

}
