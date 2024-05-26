package ru.gbhw.notes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbhw.notes.model.Note;
import ru.gbhw.notes.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {


    private final NoteService noteService;

    //Добавление заметки
    @PostMapping("/create")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    //Получение всех заметок
    @GetMapping("/getAll")
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }

    //Получение конкретной заметки
    @GetMapping("/get/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.getNoteById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }

        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    //Изменение конкретной заметки
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable("id") Long id) {

            return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
        }

    //Удаление заметки
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id) {
            noteService.deleteNote(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
