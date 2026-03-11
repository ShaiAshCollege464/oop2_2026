package com.ashcollege.controllers;

import com.ashcollege.service.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;


@RestController
public class GeneralController {
    @Autowired
    private Persist persist;

    @PostConstruct
    public void init() {
    }


}
