package kotlintraining.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonTestFromJava {

    @Test
    public void callPersonFromJava() {

        // Unfortunately named params are only in Kotlin :(
        Person person = new Person("Guy", "Mr");
        Person person2 = new Person("Guy", "Mr");

        // Auto generated getters, setters (only if mutable), hashcode and equals!
        assertEquals(person.getName(), "Guy");
        assertEquals(person.getTitle(), "Mr");

        assertEquals(person, person2);
        assertTrue(person.equals(person2));
        assertFalse(person == person2);
    }
}
