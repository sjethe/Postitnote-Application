package com.backendexample.postitnotes.service;

import com.backendexample.postitnotes.dao.PostitnoteRepository;
import com.backendexample.postitnotes.dao.entity.Postit;
import com.backendexample.postitnotes.exception.PostitnoteException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostitnoteServiceImplTest {

    @InjectMocks
    PostitnoteService postitnoteService = new PostitnoteServiceImpl();

    @Mock
    PostitnoteRepository postitnoteRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNotes_shouldReturnAllNotes(){
        Postit postit = new Postit();
        postit.setId(1);
        postit.setText("first note");
        List Notes = new ArrayList();
        Notes.add(postit);
        when(postitnoteRepository.findAll()).thenReturn(Notes);
        List actualNotes = postitnoteService.getNotes();
        long actualId = ((Postit)actualNotes.get(0)).getId();
        String actaulText = ((Postit)actualNotes.get(0)).getText();
        assertEquals(1,actualId);
        assertEquals("first note", actaulText);
    }

    @Test(expected = PostitnoteException.class)
    public void getNotes_shouldThrowPostitnoteException(){
        Postit postit = new Postit();
        postit.setId(1);
        postit.setText("first note");
        List Notes = new ArrayList();
        Notes.add(postit);
        when(postitnoteRepository.findAll()).thenThrow(PostitnoteException.class);
        List actualNotes = postitnoteService.getNotes();
    }

    @Test
    public void getNoteById_shouldReturnNote(){
        Postit postit = new Postit();
        long id = 1;
        postit.setId(1);
        postit.setText("first note");
        when(postitnoteRepository.findById(id)).thenReturn(Optional.of(postit));
        Postit actualNote = postitnoteService.getNoteById(id);
        long actualId = actualNote.getId();
        String actaulText = actualNote.getText();
        assertEquals(1,actualId);
        assertEquals("first note", actaulText);
    }

    @Test(expected = PostitnoteException.class)
    public void getNoteById_shouldThrowPostitnoteException(){
        long id = 1;
        when(postitnoteRepository.findById(id)).thenThrow(PostitnoteException.class);
        Postit actualNote = postitnoteService.getNoteById(id);
    }

    @Test(expected = PostitnoteException.class)
    public void getNoteById_shouldThrowPostitnoteExceptionWhenNoRecordFound(){
        long id = 1;
        when(postitnoteRepository.findById(id)).thenReturn(Optional.empty());
        Postit actualNote = postitnoteService.getNoteById(id);
    }

    @Test
    public void createNote_shouldReturnNewNote(){
        Postit postit = new Postit();
        postit.setId(1);
        postit.setText("first note");
        when(postitnoteRepository.save(postit)).thenReturn(postit);
        Postit actualNewNote = postitnoteService.createNote(postit);
        long actualId = actualNewNote.getId();
        String actaulText = actualNewNote.getText();
        assertEquals(1,actualId);
        assertEquals("first note", actaulText);
    }

    @Test(expected = PostitnoteException.class)
    public void createNote_shouldThrowPostitnoteException(){
        Postit postit = new Postit();
        postit.setId(1);
        postit.setText("first note");
        when(postitnoteRepository.save(postit)).thenThrow(PostitnoteException.class);
        Postit actualNote = postitnoteService.createNote(postit);
    }

    //TODO
    @Test
    public void updateNote_shouldUpdateNoteSuccessfully(){
        Postit postit = new Postit();
        long id = 1;
        postit.setId(1);
        postit.setText("first note");
        when(postitnoteRepository.findById(id)).thenReturn(Optional.of(postit));
        postitnoteService.updateNote(postit);
        verify(postitnoteRepository, times(1)).save(postit);
    }

    @Test(expected = PostitnoteException.class)
    public void updateNote_shouldThrowPostitnoteException(){
        long id = 1;
        Postit postit = new Postit();
        postit.setId(1);
        postit.setText("first note");
        when(postitnoteRepository.save(postit)).thenThrow(PostitnoteException.class);
        postitnoteService.updateNote(postit);
    }

    @Test
    public void deleteNote_shouldReturnNote(){
        Postit postit = new Postit();
        long id = 1;
        postit.setId(1);
        postit.setText("first note");
        when(postitnoteRepository.findById(id)).thenReturn(Optional.of(postit));
        doNothing().when(postitnoteRepository).deleteById(id);
        postitnoteService.deleteNote(id);
        verify(postitnoteRepository, times(1)).deleteById(id);
    }

    @Test(expected = PostitnoteException.class)
    public void deleteNote_shouldThrowPostitnoteException(){
        long id = 1;
        doThrow(PostitnoteException.class).when(postitnoteRepository).deleteById(id);
        Postit actualNote = postitnoteService.getNoteById(id);
    }

    @Test(expected = PostitnoteException.class)
    public void deleteNote_shouldThrowPostitnoteExceptionWhenNoRecordFound(){
        long id = 1;
        when(postitnoteRepository.findById(id)).thenReturn(Optional.empty());
        doNothing().when(postitnoteRepository).deleteById(id);
        Postit actualNote = postitnoteService.getNoteById(id);
    }
}
