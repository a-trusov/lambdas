package org.example;

import example.interfaces.MultiArgInterface;
import example.interfaces.MyFunctionalInterface;
import example.model.Person;
import example.services.DateService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static example.services.DateService.getPeople;

public class LambdasTest {
    private List<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = getPeople();
    }

    @Test
    public void test() throws IOException {
        Assertions.assertThat(persons).hasSize(1000);
    }

    @Test
    public void threadsLambdas() {
//        Runnable r1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Running thread r1");
//            }
//        };
//
//        Runnable r2 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Running thread r2");
//            }
//        };
//
//        new Thread(r1).start();
//        new Thread(r2).start();

        //functional interface

        Runnable r1 = () -> System.out.println("Running thread r1");
        Runnable r2 = () -> System.out.println("Running thread r2");
        new Thread(r1).start();
        new Thread(r2).start();

    }

    @Test
    public void multiCommand() {
        Runnable r1 = () -> {
            System.out.println("Line 1");
            System.out.println("Line 2");

        };

        new Thread(r1).start();
    }

    @Test
    public void testInterFace() {
        MyFunctionalInterface myFunctionalInterface = () -> System.out.println("Message");
        myFunctionalInterface.printMessage();
    }


    @Test
    public void multiArgInterface() {

        MultiArgInterface multiArgInterface = (x, y) -> {
            int sum = x + y;
            System.out.println("Sum:" + sum);
        };

        multiArgInterface.sum(10, 20);

    }

    @Test
    public void collectionsLambdas() throws IOException {
        Comparator<Person> personComparator = (o1, o2) -> o1.getLastName().compareToIgnoreCase(o2.getLastName());
//        List<Person> people1 = people.stream().sorted(personComparator).
//                collect(Collectors.toList());
        persons.sort(personComparator);
        persons.forEach(person -> System.out.println(person.getLastName()));
    }

    //predicate
    @Test
    public void testPredicate() throws IOException {
        Predicate<Person> personPredicate = person -> person.getGender().equalsIgnoreCase("Male");
        persons.forEach(person -> {
            if (personPredicate.test(person)) {
                System.out.println(person.getLastName() + "Gender: " + person.getGender());
            }
        });

    }

    //reference method
    @Test
    public void referenceMethod() throws IOException {
//        Thread thread = new Thread(DateService::printString);
//        thread.start();

        persons.sort(DateService::compareId);
        persons.forEach(person -> System.out.println(person.getLastName()));
    }

    @Test
    public void filterAndSortAndMap() {
        persons.stream().
                filter(person -> person.getAge() <= 18).
                sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName())).
                map(person -> person.getLastName()).
                forEach(System.out::println);
    }


    @Test
    public void testAverage() {
        double asDouble = persons.stream().
                filter(person -> person.getAge() >= 18).
                mapToInt(value -> value.getAge()).
                average().
                getAsDouble();
        System.out.println(asDouble);

    }
}
