package org.example;

import example.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static example.services.DateService.getPeople;


public class LambdasTest {

    @Test
    public void test() throws IOException {
        List<Person> peoples = getPeople();
        Assertions.assertThat(peoples).hasSize(1000);
    }
}
