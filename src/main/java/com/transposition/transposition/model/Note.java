package com.transposition.transposition.model;

public class Note {
    public Note(int octave, int semitone) {
        this.octave = octave;
        this.semitone = semitone;
    }

    private int octave = 0;
    private int semitone = 0;

    public int getOctave() {
        return octave;
    }

    public int getSemitone() {
        return semitone;
    }

    @Override
    public String toString() {
        return "{" + octave + "," + semitone + "}";
    }
}
