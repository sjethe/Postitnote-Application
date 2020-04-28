package com.backendexample.postitnotes.service;

import com.backendexample.postitnotes.dao.entity.Postit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostitnoteService {

    List getNotes();
    Postit getNoteById(long id);
    Postit createNote(Postit postit);
    void updateNote(Postit postit);
    void deleteNote(long id);
}
