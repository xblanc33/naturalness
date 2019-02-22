package com.naturalness;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SequenceTest {

    @Test
    public void shouldCreateEmptySequence() {
        Sequence seq = new Sequence();
        assertTrue(seq.getEventList().size() == 0);
    }

    @Test
    public void shouldCreateSequence() {
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(new Event("hey"));
        Sequence seq = new Sequence(eventList);
        assertTrue(seq.getEventList().size() == 1);
        eventList.add(new Event("ho"));
        assertTrue(seq.getEventList().size() == 1);
        seq.getEventList().clear();
        assertTrue(seq.getEventList().size() == 1);
    }

    @Test
    public void shouldAppendEvent() {
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(new Event("hey"));
        Sequence seq = new Sequence(eventList);
        assertTrue(seq.getEventList().size() == 1);
        seq.append(new Event("ho"));
        assertTrue(seq.getEventList().size() == 2);  
    }

    @Test
    public void shouldCreateNGram() {
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(new Event("a"));
        eventList.add(new Event("b"));
        eventList.add(new Event("c"));
        Sequence seq = new Sequence(eventList);
        NGram observed = seq.getNgram(2,1);
        List<Event> ngramEventList = new ArrayList<Event>();
        ngramEventList.add(new Event("b"));
        NGram expected = new NGram(ngramEventList); 
        assertEquals(observed, expected);
    }
}