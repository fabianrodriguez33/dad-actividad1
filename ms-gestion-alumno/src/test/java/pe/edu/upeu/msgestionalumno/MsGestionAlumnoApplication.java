package pe.edu.upeu.msgestionalumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsGestionAlumnoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsGestionAlumnoApplication.class, args);
    }
}
