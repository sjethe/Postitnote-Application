package com.backendexample.postitnotes.service;

import com.backendexample.postitnotes.dao.PostitnoteRepository;
import com.backendexample.postitnotes.dao.entity.Postit;
import com.backendexample.postitnotes.exception.PostitnoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PostitnoteServiceImpl implements PostitnoteService{

    @Autowired
    PostitnoteRepository postitnoteRepository;

    @Override
    public List getNotes(){
        try {
            List notes = postitnoteRepository.findAll();
            return notes;
        } catch(Exception e){
            throw new PostitnoteException("Could not fetch data");
        }
    }

    @Override
    public Postit getNoteById(long id) {
        try {
            Postit postit = postitnoteRepository.findById(id).orElse(null);
            if(postit == null) {
                throw new PostitnoteException("No Note exist for id");
            }
            return postit;
        } catch(Exception e){
            throw new PostitnoteException(e.getMessage());
        }
    }

    @Override
    public Postit createNote(Postit postit) {
        try {
            Postit newPostit = postitnoteRepository.save(postit);
            return newPostit;
        }catch(Exception e){
             throw new PostitnoteException("Internal Server Error");
        }
    }

    @Override
    public void updateNote(Postit postit) {
        try {
            Postit dbPostit = postitnoteRepository.findById(postit.getId()).orElse(null);
            if(dbPostit != null) {
                postitnoteRepository.save(postit);
            } else {
                throw new PostitnoteException("Cannot find Note with id "+postit.getId());
            }

        }catch(Exception e){
            throw new PostitnoteException("Internal Server Error");
        }
    }

    @Override
    public void deleteNote(long id) {
        try {
            Postit postit = postitnoteRepository.findById(id).orElse(null);
            if(postit != null) {
                postitnoteRepository.deleteById(id);
            } else {
                throw new PostitnoteException("Cannot find Note with id "+id);
            }
        } catch(Exception e){
            throw new PostitnoteException(e.getMessage());
        }
    }
}
