package io.github.mizinchik;

import java.util.Objects;

/**
 * Entry class for a simple database.
 * Represents a client of ATON.
 *
 * @author MIZINCHIK
 */
public class Person implements Comparable<Person> {
    private Long account;
    private String name;
    private Double value;

    /**
     * Constructs a client instance.
     * Arguments mustn't be null.
     *
     * @param account of the client
     * @param name of the client
     * @param value of the client
     */
    public Person(Long account, String name, Double value) {
        this.account = Objects.requireNonNull(account);
        this.name = Objects.requireNonNull(name);
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Returns the client's account.
     *
     * @return account
     */
    public long getAccount() {
        return account;
    }

    /**
     * Returns the client's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the client's account value.
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Changes the client's account.
     *
     * @param account to set
     */
    public void setAccount(Long account) {
        this.account = Objects.requireNonNull(account);
    }


    /**
     * Changes the client's name.
     *
     * @param name to set
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }


    /**
     * Changes the client's account value.
     *
     * @param value to set
     */
    public void setValue(Double value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!account.equals(person.account)) return false;
        if (!name.equals(person.name)) return false;
        return value.equals(person.value);
    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    /**
     * Creates a natural ordering of the entries
     * to put them into a TreeMap.
     *
     * @param o the object to be compared.
     * @return 1 as > 0 as == -1 as <
     */
    @Override
    public int compareTo(Person o) {
        int compare = Long.compare(getAccount(), o.getAccount());
        if (compare != 0) {
            return compare;
        }
        compare = getName().compareTo(o.getName());
        if (compare != 0) {
            return compare;
        }
        return Double.compare(getValue(), o.getValue());
    }
}
