package io.github.luan048;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
public class apiSpring {

    public static void main(String[] args) {
        SpringApplication.run(apiSpring.class,args);
    }
}

@Entity
class Demo { // Class com as informacoes do Demo
    @Id
    Long id;
    String name;
    String description;

    // Metodo para listar Id
    public Long getId() {
        return id;
    }

    // Metodo para criar Id
    public void setId(Long id) {
        this.id = id;
    }

    // Metodo para listar Name
    public String getName() {
        return name;
    }

    // Metodo para criar Name
    public void setName(String name) {
        this.name = name;
    }

    // Metodo para listar Description
    public String getDescription() {
        return description;
    }

    // Metodo para criar Description
    public void setDescription(String description) {
        this.description = description;
    }
}

@RestController
@RequestMapping("/api") // Definindo uma rota
class DemoController {
    DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/{id}") // Complementa a rota, para realizar uma operação de listagem pelo Id
    public Demo getDemo(@PathVariable("id") Long id) {
        return demoService.getDemo(id).orElse(null);
    }
}

@Service
class DemoService {
    DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public Optional<Demo> getDemo(Long id) {
        return demoRepository.findById(id);  // Retorna o Demo pelo Id, caso corresponda aos cadastrados
    }
}

interface DemoRepository extends JpaRepository<Demo, Long> {

}