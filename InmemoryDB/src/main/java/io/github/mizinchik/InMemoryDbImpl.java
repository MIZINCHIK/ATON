package io.github.mizinchik;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * InMemoryDb with the lowest memory usage and retrieving
 * in log(n) time.
 *
 * @author MIZINCHIK
 */
public class InMemoryDbImpl implements InMemoryDb {
    private final Map<Long, Set<Person>> accountToPerson;
    private final Map<String, Set<Person>> nameToPerson;
    private final Map<Double, Set<Person>> valueToPerson;

    /**
     * Constructs an empty db.
     */
    public InMemoryDbImpl() {
        accountToPerson = new TreeMap<>();
        nameToPerson = new TreeMap<>();
        valueToPerson = new TreeMap<>();
    }

    /**
     * @inheritDoc
     * Sets are TreeSets for less memory usage.
     * Entry is stored in 3 sets in 3 respective maps.
     *
     * @param person to add to the db
     */
    @Override
    public void addEntry(Person person) {
        Long account = person.getAccount();
        if (accountToPerson.containsKey(account)) {
            accountToPerson.get(account).add(person);
        } else {
            Set<Person> newSet = new TreeSet<>();
            newSet.add(person);
            accountToPerson.put(account, newSet);
        }
        String name = person.getName();
        if (nameToPerson.containsKey(name)) {
            nameToPerson.get(name).add(person);
        } else {
            Set<Person> newSet = new TreeSet<>();
            newSet.add(person);
            nameToPerson.put(name, newSet);
        }
        Double value = person.getValue();
        if (valueToPerson.containsKey(value)) {
            valueToPerson.get(value).add(person);
        } else {
            Set<Person> newSet = new TreeSet<>();
            newSet.add(person);
            valueToPerson.put(value, newSet);
        }
    }

    @Override
    public boolean deleteEntry(Person person) {
        if (!accountToPerson.containsKey(person.getAccount())) {
            return false;
        }
        Set<Person> check = accountToPerson.get(person.getAccount());
        if (!check.contains(person)) {
            return false;
        }
        check.remove(person);
        nameToPerson.get(person.getName()).remove(person);
        valueToPerson.get(person.getValue()).remove(person);
        return true;
    }

    @Override
    public void changeEntry(Person person, Person newPerson) {
        if (deleteEntry(person)) {
            addEntry(Objects.requireNonNull(newPerson));
        }
    }

    @Override
    public void changeEntry(Person person, Long newAccount) {
        if (deleteEntry(person)) {
            person.setAccount(newAccount);
            addEntry(person);
        }
    }

    @Override
    public void changeEntry(Person person, String newName) {
        if (deleteEntry(person)) {
            person.setName(newName);
            addEntry(person);
        }
    }

    @Override
    public void changeEntry(Person person, Double newValue) {
        if (deleteEntry(person)) {
            person.setValue(newValue);
            addEntry(person);
        }
    }

    @Override
    public Set<Person> getEntries(Long account) {
        return accountToPerson.get(account);
    }

    @Override
    public Set<Person> getEntries(String name) {
        return nameToPerson.get(name);
    }

    @Override
    public Set<Person> getEntries(Double value) {
        return valueToPerson.get(value);
    }
}