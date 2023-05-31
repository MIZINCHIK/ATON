package io.github.mizinchik;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class InMemoryDbImpl implements InMemoryDb {
    private final Map<Long, Set<Person>> accountToPerson;
    private final Map<String, Set<Person>> nameToPerson;
    private final Map<Double, Set<Person>> valueToPerson;

    public InMemoryDbImpl() {
        accountToPerson = new TreeMap<>();
        nameToPerson = new TreeMap<>();
        valueToPerson = new TreeMap<>();
    }

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
        boolean exists = deleteEntry(person);
        if (exists) {
            addEntry(newPerson);
        }
    }

    @Override
    public void changeEntry(Person person, Long newAccount) {
        Person newPerson = new Person(newAccount, person.getName(), person.getValue());
        changeEntry(person, newPerson);
    }

    @Override
    public void changeEntry(Person person, String newName) {
        Person newPerson = new Person(person.getAccount(), newName, person.getValue());
        changeEntry(person, newPerson);
    }

    @Override
    public void changeEntry(Person person, Double newValue) {
        Person newPerson = new Person(person.getAccount(), person.getName(), newValue);
        changeEntry(person, newPerson);
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