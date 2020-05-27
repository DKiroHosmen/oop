package oop.base_entities;

public final class Person {
    public static final Person NO_NAME = new Person("", "");
    private String firstName, lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() *
                lastName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Person object = (Person) obj;
        return object.getFirstName().equals(firstName) &&
                object.getLastName().equals(lastName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
