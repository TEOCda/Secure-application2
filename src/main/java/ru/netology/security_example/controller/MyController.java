package ru.netology.security_example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class MyController {
    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @PreAuthorize("hasRole('ROLE_WRITE')or hasRole('ROLE_DELETE')")
    @GetMapping("/delete")
    public String delete() {
        return "Deleted";
    }

    @GetMapping("/read")
    @Secured("ROLE_READ")
    public String read() {
        return "Read";
    }

    @GetMapping("/write")
    @RolesAllowed("ROLE_WRITE")
    public String write() {
        return "Write";
    }

    @GetMapping("/username")
    @PostAuthorize("#username == authentication.principal.username")
    public String named(String username) {
        return username + " welcome";
    }

}
