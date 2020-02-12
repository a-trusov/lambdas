package example.services;

import com.google.common.io.Resources;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import example.model.Person;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

public class DateService {

    public static List<Person> getPeople() throws IOException {
        InputStream inputStream = Resources.getResource("people.json").openStream();
        String json = IOUtils.toString(inputStream);

        List<Person> people = new Gson().fromJson(json,
                new TypeToken<List<Person>>() {}.getType());
        return people;
    }

    public static void printString() {
        System.out.println("Message reference Method");
    }

    public static int compareId(Person p1, Person p2) {
        Integer id = p2.getId();
        return id.compareTo(p1.getId());
    }

}
