package com.transposition.transposition.businessLogicManagers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transposition.transposition.model.Note;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.transposition.transposition.CONSTANTS.INPUT_FILE;
import static com.transposition.transposition.CONSTANTS.OUTPUT_FILE;


public class FileManager {

    private Map<String, Object> mapArgs;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public FileManager(Map<String, Object> mapArgs) {
        this.mapArgs = mapArgs;
    }


    public List<Note> getData() throws IOException {
        List<List<Integer>> list = objectMapper.readValue(new File((String) mapArgs.get(INPUT_FILE)), List.class);
        return list.stream().map(e-> new Note(e.get(0) , e.get(1))).collect(Collectors.toList());
    }

    public void createFile(final List<Note> modifiedNote) throws IOException {
        List<List<Integer>> output = modifiedNote.stream().map(e-> List.of(e.getX(), e.getY())).collect(Collectors.toList());
        objectMapper.writeValue(new File((String) mapArgs.get(OUTPUT_FILE)), output);
    }
}
