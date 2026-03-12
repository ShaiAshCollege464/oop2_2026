package com.ashcollege.controllers;

import com.ashcollege.entities.Note;
import com.ashcollege.entities.User;
import com.ashcollege.entities.WorkPlace;
import com.ashcollege.service.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.List;


@RestController
public class GeneralController {
    @Autowired
    private Persist persist;

    @PostConstruct
    public void init() {
        Note note = persist.loadObject(Note.class, 1);
        System.out.println(note.getWriter().getUsername());
    }

    @RequestMapping ("/get-user-by-id")
    public User getUserById (long id) {
        return persist.loadObject(User.class, id);
    }

    @RequestMapping ("/get-wp-by-id")
    public WorkPlace getWpById (long id) {
        return persist.loadObject(WorkPlace.class, id);
    }




}
