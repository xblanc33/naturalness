package com.naturalness;

import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class NaturalnessModelTest {
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
        sequenceList.add(new Sequence(Arrays.asList(a, b, c, d, e)));
        sequenceList.add(new Sequence(Arrays.asList(a, b, c, d, c)));
        sequenceList.add(new Sequence(Arrays.asList(f, f, f, f, f, f)));        
    }

    @Test
    public void shouldReturnInfinityCrossEntropy() {
        NaturalnessModel model = new NaturalnessModel(3,0);
        double ce = model.crossEntropy(sequenceList.get(0));
        assertTrue(Double.isInfinite(ce));
    }

    @Test
    public void shouldDealWithEmptySequence() {
        NaturalnessModel model = new NaturalnessModel(3,0);
        Sequence emptySequence = new Sequence(new ArrayList<Event>());
        double ce = model.crossEntropy(emptySequence);
        assertTrue(ce == 0);
    }

    @Test
    public void shouldComputeAllRightCrossEntropy() {
        NaturalnessModel model = new NaturalnessModel(3,0);
        model.learn(sequenceList.get(0));
        double ce = model.crossEntropy(sequenceList.get(0));
        double expected = -(5 * Math.log(1)/Math.log(2)) / 5;
        assertTrue(ce == expected);
    }

    @Test
    public void shouldComputeAllRightButOneCrossEntropy() {
        NaturalnessModel model = new NaturalnessModel();
        model.learn(sequenceList.get(0));
        double ce = model.crossEntropy(sequenceList.get(1));
        double right = 1 - model.getProbaOfUnknown();
        double wrong = model.getProbaOfUnknown();
        double expected = -(4*Math.log(right)/Math.log(2) + Math.log(wrong)/Math.log(2)) / 5;
        assertTrue(ce == expected);
    }

    @Test
    public void shouldComputeAllWrongCrossEntropy() {
        NaturalnessModel model = new NaturalnessModel();
        model.learn(sequenceList.get(0));
        double ce = model.crossEntropy(sequenceList.get(2));
        double wrong = model.getProbaOfUnknown();
        double expected = -(5 * Math.log(wrong)/Math.log(2)) / 5;
        assertTrue(ce == expected);
    }
}