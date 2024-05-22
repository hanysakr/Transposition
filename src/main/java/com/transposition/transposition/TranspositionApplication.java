package com.transposition.transposition;

import com.transposition.transposition.businessLogicManagers.FileManager;
import com.transposition.transposition.businessLogicManagers.TransposeManager;
import com.transposition.transposition.model.Note;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TranspositionApplication implements ApplicationRunner, CONSTANTS {


    public static void main(String[] args) {
       SpringApplication.run(TranspositionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Map mapArgs = readArgs(args);
        FileManager fileManager = new FileManager(mapArgs);

        List musicNote = fileManager.getData();

        List<Note> modifiedNote = new TransposeManager().transpose(musicNote, Integer.valueOf((String)mapArgs.get(SEMITONE)));

        fileManager.createFile(modifiedNote);
        //System.out.println(modifiedNote);
    }


    private static Map<String, Object> readArgs(ApplicationArguments args) {
        List<String> list = Arrays.stream(args.getSourceArgs()).toList();
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(INPUT_FILE, list.get(0));
            map.put(SEMITONE, list.get(1));
            map.put(OUTPUT_FILE, list.get(2));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Missing arguments, format should be 'in/a.json 5 out/b.json'");
        }
        return map;
    }


}
