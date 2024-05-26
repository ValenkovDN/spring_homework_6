package ru.gbhw.notes.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbhw.notes.model.Note;
import ru.gbhw.notes.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    //Репозиторий
    private final NoteRepository noteRepository;

    //Получим конкретную заметку
    public Note getNoteById(Long id) {
        return noteRepository.findNoteById(id).orElseThrow(null);
    }

    //Получим все заметки
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    //Создание заметки
    public Note createNote(Note note){

        return noteRepository.save(note);
    }

    //Редактирование заметки
    public Note updateNote(Note note){
        Note noteById = getNoteById(note.getId());
        noteById.setTitle(note.getTitle());
        noteById.setContent(note.getContent());

        return noteRepository.save(noteById);
    }

    //Удаление заметки
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }
}
