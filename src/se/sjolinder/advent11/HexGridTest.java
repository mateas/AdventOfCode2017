package se.sjolinder.advent11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexGridTest {

    @Test
    void part1() {
        assertEquals(3, HexGrid.part1("ne,ne,ne"));
        assertEquals(0, HexGrid.part1("ne,ne,sw,sw"));
        assertEquals(2, HexGrid.part1("ne,ne,s,s"));
        assertEquals(3, HexGrid.part1("se,sw,se,sw,sw"));
    }
}