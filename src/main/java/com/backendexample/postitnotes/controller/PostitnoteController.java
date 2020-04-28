package com.backendexample.postitnotes.controller;

import com.backendexample.postitnotes.dao.entity.Postit;
import com.backendexample.postitnotes.exception.PostitnoteException;
import com.backendexample.postitnotes.service.PostitnoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping(value = "/postitnote/V1")
public class PostitnoteController {

    @Autowired
    PostitnoteService postitnoteService;

    @GetMapping("/")
    public String getNotes(Model model){
        try {
            List notes = postitnoteService.getNotes();
            model.addAttribute("notes", notes);
            return "index";
        } catch(PostitnoteException px){
            model.addAttribute("errorMessage",px.getMessage());
            return "error";
        }
    }

    @GetMapping("/addnote")
    public String addNotes(Model model){
        model.addAttribute("postit", new Postit());
        return "add-note";

    }

    @PostMapping(value = "/")
    public String createNote(@ModelAttribute("postit") Postit postit, Model model){
        try {
            this.postitnoteService.createNote(postit);
            return "redirect:/postitnote/V1/";
        } catch(PostitnoteException px){
            model.addAttribute("errorMessage", px.getMessage());
            return "error";
        }
    }

    @GetMapping("/update/{id}")
    public String updateNote(@PathVariable("id") long id, Model model){
        try {
            Postit postit = this.postitnoteService.getNoteById(id);
            model.addAttribute("postit", postit);
            return "update-note";
        } catch(PostitnoteException px){
            model.addAttribute("errorMessage", px.getMessage());
            return "error";
        }

    }

    @PostMapping(value = "/update")
    public String updateNote(@ModelAttribute("postit") Postit postit, Model model){
        try {
            this.postitnoteService.updateNote(postit);
            return "redirect:/postitnote/V1/";
        } catch(PostitnoteException px){
            model.addAttribute("errorMessage", px.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String deleteNote(@PathVariable("id") long id, Model model){
        try {
            this.postitnoteService.deleteNote(id);
            return "redirect:/postitnote/V1/";
        } catch(PostitnoteException px){
            model.addAttribute("errorMessage", px.getMessage());
            return "error";
        }
    }
}
