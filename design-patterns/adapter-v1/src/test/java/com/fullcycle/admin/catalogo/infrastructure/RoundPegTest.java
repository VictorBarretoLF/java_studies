package com.fullcycle.admin.catalogo.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RoundPegTest {

    @Test
    public void shouldReturnRadius_withRadiusFive() {
        RoundPeg peg = new RoundPeg(5);

        Assertions.assertEquals(peg.getRadius(), 5);
    }

    @Test
    public void shouldReturnRadius_withRadiusTen() {
        RoundPeg peg = new RoundPeg(10);

        Assertions.assertEquals(peg.getRadius(), 10);
    }

    @Test
    public void shouldReturnRadius_withRadiusFive_withMockito() {
        RoundPeg spyPeg = BDDMockito.spy(RoundPeg.class);

        BDDMockito.given(spyPeg.getRadius()).willReturn(5.0);

        Assertions.assertEquals(spyPeg.getRadius(), 5.0);

        Mockito.verify(spyPeg).getRadius();
    }

    @Test
    public void shouldReturnRadius_withRadiusTen_withMockito() {
        RoundPeg spyPeg = BDDMockito.spy(RoundPeg.class);

        BDDMockito.given(spyPeg.getRadius()).willReturn(10.0);

        Assertions.assertEquals(spyPeg.getRadius(), 10.0);

        Mockito.verify(spyPeg).getRadius();
    }

}