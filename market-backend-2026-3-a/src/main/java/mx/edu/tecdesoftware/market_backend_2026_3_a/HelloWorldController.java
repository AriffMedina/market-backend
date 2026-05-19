package mx.edu.tecdesoftware.market_backend_2026_3_a;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hola")
    public String hola() {
        return "Hello World";
    }
}