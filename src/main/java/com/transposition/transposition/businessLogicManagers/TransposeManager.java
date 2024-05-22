package com.transposition.transposition.businessLogicManagers;

import com.transposition.transposition.model.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class TransposeManager {

    private Map<Integer, List<Integer>> pianoKeyBoard;

    public TransposeManager() {
        loadPianokeyboard();
    }

    public List<Note> transpose(List<Note> musicNote, int semitones) {
        List<Note> modifiedNote = new ArrayList<>();
        /*
         * loop musicNote
         *   on each note lookup key
         *          move semitones value ( check piano for next move)
         * */
        musicNote.stream().forEach(note-> {
            modifiedNote.add(calculate(note.getX(),note.getY() , semitones));
        });

        //System.out.println(modifiedNote);
        return modifiedNote;
    }

    private Note calculate(int key , int value, int semitones) {
        /*
         * Load key limit ( value range )
         * Compare the new value withing value range
         *   IF within range Okay subtract
         *   Else reduce/increase Key by 1 and subtract/add value to next key
         * */
        final Note note;
        List<Integer> allowedValues = pianoKeyBoard.get(key);
        int newValue = value + semitones;
        Optional<Integer> v = allowedValues.stream().filter(e-> e.equals(newValue)).findAny();
        if(v.isPresent()){
            note = new Note(key, newValue);
        }else {
            if(semitones>0) {
                if(null != pianoKeyBoard.get(key+1)) {
                    note = new Note(key + 1, newValue - pianoKeyBoard.get(key).getLast());
                }else{
                    note = new Note(key, pianoKeyBoard.get(key).getLast());
                }
            } else {
                if(null != pianoKeyBoard.get(key-1)) {
                    note = new Note(key - 1, pianoKeyBoard.get(key - 1).getLast() + newValue);
                }else{
                    note = new Note(key, pianoKeyBoard.get(key).getFirst());
                }
            }
        }
        return note;
    }

    private void loadPianokeyboard() {
        pianoKeyBoard = new HashMap<>();
        pianoKeyBoard.put( -3 , createList(10,12));
        pianoKeyBoard.put( -2 , createList(1,12));
        pianoKeyBoard.put( -1 , createList(1,12));
        pianoKeyBoard.put( 0 , createList(1,12));
        pianoKeyBoard.put( 1 , createList(1,12));
        pianoKeyBoard.put( 2 , createList(1,12));
        pianoKeyBoard.put( 3 , createList(1,12));
        pianoKeyBoard.put( 4 , createList(1,12));
        pianoKeyBoard.put( 5 , createList(1,1));
    }

    private static List<Integer> createList(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().toList();
    }
}
