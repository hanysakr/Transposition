package com.transposition.transposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transposition.transposition.manager.TransposeManager;
import com.transposition.transposition.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class TranspositionApplicationTests {

    private int semitone = 0;
    private Map<Integer, List<Integer>> piano;

    private TransposeManager transposeManager = new TransposeManager();

    private List<Note> musicNote = List.of(
            new Note(2,1),
            new Note(2,6),
            new Note(2,1),
            new Note(2,8),
            new Note(2,1),
            new Note(1,1),
            new Note(0,1),
            new Note(-1,1),
            new Note(-2,1),
            new Note(-3,12),
            new Note(4,12)
    );

    @Test
    public void contextLoads() {
        System.out.println(musicNote);
        List<Note> modifiedNote = transposeManager.transpose(musicNote, 3);
        System.out.println(modifiedNote);
        Assertions.assertEquals(musicNote.size(), modifiedNote.size());
    }

    @Test
    public void testParsing() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String test = "[[1,2],[1,1]]";
        List data = objectMapper.readValue(test,List.class);
        Assertions.assertEquals(2, data.size());
        System.out.println(data);
    }

}
