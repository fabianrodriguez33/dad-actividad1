package pe.edu.upeu.msgestioninstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsGestionInstructorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsGestionInstructorApplication.class, args);
    }
}
