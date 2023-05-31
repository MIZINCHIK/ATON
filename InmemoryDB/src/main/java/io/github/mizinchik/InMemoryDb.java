package io.github.mizinchik;

import java.util.Set;

/**
 * Simple in-memory database for storing
 * Person entries.
 *
 * @author MIZINCHIK
 */
public interface InMemoryDb {
    /**
     * Adds a new entry person (if not present already)
     * to the db.
     *
     * @param person to add to the db
     */
    void addEntry(Person person);

    /**
     * Deletes an entry if present.
     *
     * @param person to delete from the db
     * @return true if the entry was there initially
     */
    boolean deleteEntry(Person person);

    /**
     * Changes one entry to another.
     *
     * @param person to change
     * @param newPerson what to change to
     */
    void changeEntry(Person person, Person newPerson);

    /**
     * Changes account in an entry.
     *
     * @param person to change
     * @param newAccount to assign
     */
    void changeEntry(Person person, Long newAccount);

    /**
     * Changes name in an entry.
     *
     * @param person to change
     * @param newName to assign
     */
    void changeEntry(Person person, String newName);

    /**
     * Changes value in an entry.
     *
     * @param person to change
     * @param newValue to assign
     */
    void changeEntry(Person person, Double newValue);

    /**
     * Retrieves a set of all the entries with the
     * provided key in the account field.
     *
     * @param account key
     * @return set of all entries by the key
     */
    Set<Person> getEntries(Long account);

    /**
     * Retrieves a set of all the entries with the
     * provided key in the name field.
     *
     * @param name key
     * @return set of all entries by the key
     */
    Set<Person> getEntries(String name);

    /**
     * Retrieves a set of all the entries with the
     * provided key in the value field.
     *
     * @param value key
     * @return set of all entries by the key
     */
    Set<Person> getEntries(Double value);
}
