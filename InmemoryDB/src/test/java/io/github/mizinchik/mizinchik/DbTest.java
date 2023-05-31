package io.github.mizinchik.mizinchik;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mizinchik.InMemoryDb;
import io.github.mizinchik.InMemoryDbImpl;
import io.github.mizinchik.Person;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DbTest {
    private InMemoryDb db;

    /**
     * Sets up a database before each test.
     */
    @BeforeEach
    public void setUp() {
        db = new InMemoryDbImpl();
        db.addEntry(new Person(1212L, "Pyotr", 23.14));
        db.addEntry(new Person(233432432453L, "Maxim", 150.0));
        db.addEntry(new Person(12321L, "Elena", 78.0));
        db.addEntry(new Person(45654676587657L, "Kirill", (double) 0));
        db.addEntry(new Person(234L, "Elena", (double) 0));
        db.addEntry(new Person(0L, "Dmitry", (double) 0));
        db.addEntry(new Person(233432432453L, "Alexey", 12.0));
        db.addEntry(new Person(1L, "X Æ A-12", 78.0));
    }

    /**
     * Tests correctness of the initial db.
     */
    @Test
    @DisplayName("Entry getting 1")
    void testGet1() {
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(234L, "Elena", (double) 0));
        expected.add(new Person(12321L, "Elena", 78.0));
        assertEquals(expected, db.getEntries("Elena"));
    }

    /**
     * Tests correctness of the initial db.
     */
    @Test
    @DisplayName("Entry getting 2")
    void testGet2() {
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(233432432453L, "Maxim", 150.0));
        expected.add(new Person(233432432453L, "Alexey", 12.0));
        assertEquals(expected, db.getEntries(233432432453L));
    }

    /**
     * Tests correctness of the initial db.
     */
    @Test
    @DisplayName("Entry getting 3")
    void testGet3() {
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(45654676587657L, "Kirill", (double) 0));
        expected.add(new Person(234L, "Elena", (double) 0));
        expected.add(new Person(0L, "Dmitry", (double) 0));
        assertEquals(expected, db.getEntries((double) 0));
    }

    /**
     * Tests correctness of the changed db.
     */
    @Test
    @DisplayName("Entry change 1")
    void testChange1() {
        db.changeEntry(new Person(1212L, "Pyotr", 23.14),
                new Person(233432432453L, "Maxim", 150.0));
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(233432432453L, "Maxim", 150.0));
        expected.add(new Person(233432432453L, "Alexey", 12.0));
        assertEquals(expected, db.getEntries(233432432453L));
    }

    /**
     * Tests correctness of the changed db.
     */
    @Test
    @DisplayName("Entry change 2")
    void testChange2() {
        db.changeEntry(new Person(233432432453L, "Maxim", 150.0),
                "Default");
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(233432432453L, "Default", 150.0));
        expected.add(new Person(233432432453L, "Alexey", 12.0));
        assertEquals(expected, db.getEntries(233432432453L));
    }

    /**
     * Tests correctness of the changed db.
     */
    @Test
    @DisplayName("Entry change 3")
    void testChange3() {
        db.changeEntry(new Person(233432432453L, "Maxim", 150.0),
                0L);
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(0L, "Maxim", 150.0));
        expected.add(new Person(0L, "Dmitry", (double) 0));
        assertEquals(expected, db.getEntries(0L));
    }

    /**
     * Tests correctness of the changed db.
     */
    @Test
    @DisplayName("Entry change 4")
    void testChange4() {
        db.changeEntry(new Person(233432432453L, "Maxim", 150.0),
                12323213.0);
        Set<Person> expected = new HashSet<>();
        expected.add(new Person(233432432453L, "Maxim", 12323213.0));
        assertEquals(expected, db.getEntries(12323213.0));
    }
}