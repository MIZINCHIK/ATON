package io.github.mizinchik;

import java.util.Set;

public interface InMemoryDb {
    void addEntry(Person person);
    boolean deleteEntry(Person person);
    void changeEntry(Person person, Person newPerson);
    void changeEntry(Person person, Long newAccount);
    void changeEntry(Person person, String newName);
    void changeEntry(Person person, Double newValue);
    Set<Person> getEntries(Long account);
    Set<Person> getEntries(String name);
    Set<Person> getEntries(Double value);
}
