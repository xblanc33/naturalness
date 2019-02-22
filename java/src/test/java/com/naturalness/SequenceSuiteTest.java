package com.naturalness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class SequenceSuiteTest {
    private static List<Sequence> sequenceList;

    @BeforeClass
    public static void buildSequence() {
        sequenceList = new ArrayList<Sequence>();
        Event a = new Event("a");
        Event b = new Event("b");
        Event c = new Event("c");
        Event d = new Event("d");
        Event e = new Event("e");
        Event f = new Event("f");
        sequenceList.add(new Sequence(Arrays.asList(a, b, c, d)));
        sequenceList.add(new Sequence(Arrays.asList(a, b, c, d, c)));
        sequenceList.add(new Sequence(Arrays.asList(f, f, f, f, f, f, f, f, f)));        
    }

    @Test
    public void shouldReturnSampleOneAsMoreNatural() {
        SequenceSuite sequenceSuite = new SequenceSuite(sequenceList, 2, 0.000001);
        Ranking r = sequenceSuite.getMoreNatural();
        assertEquals(r.getSequence(), sequenceList.get(0));
    }

    @Test
    public void shouldReturnOneTwoThree() {
        SequenceSuite sequenceSuite = new SequenceSuite(sequenceList, 2, 0.000001);
        List<Ranking> rankingList = sequenceSuite.rank();
        assertEquals(rankingList.get(0), sequenceList.get(0));
        assertEquals(rankingList.get(1), sequenceList.get(1));
        assertEquals(rankingList.get(2), sequenceList.get(2));

    }
}