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
class Demo {
    @Id
    Long id;
    String name;
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

@RestController
@RequestMapping("/api")
class DemoController {
    DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/{id}")
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
        return demoRepository.findById(id);
    }
}

interface DemoRepository extends JpaRepository<Demo, Long> {

}