package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.config.PolarProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    public HomeController(PolarProperties polarProperties) {
        this.polarProperties = polarProperties;
    }

    private final PolarProperties polarProperties;
    @GetMapping("/")
    public String getGreetings() {
           return polarProperties.getGreeting();
    }
}
