package beziehungen;

import java.util.*;

public class Hobby {

    private final String bezeichnung;
    private final Collection<Person> personen = new ArrayList<>();

    private Hobby(String bezeichnung, Person initialPerson) {
        this.bezeichnung = bezeichnung;
        this.personen.add(initialPerson);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Hobby withAddedPerson(Person person) {
        this.personen.add(person);
        return this;
    }

    public void removePerson(Person person) {
        if(personen.stream().allMatch(p -> p.equals(person))) {
            throw new IllegalStateException(this.bezeichnung + " hätte keine Person mehr");
        }
        personen.remove(person);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "bezeichnung='" + bezeichnung + "', " +
                personen.size() + " Personen" +
                '}';
    }

    // REPOSITORY

    private static final Map<String, Hobby> hobbyRepository = new HashMap<>();

    public static Hobby addPersonAndGet(String bezeichnung, Person owner) {
        if(hobbyRepository.containsKey(bezeichnung)) {
            return hobbyRepository.get(bezeichnung).withAddedPerson(owner);
        } else {
            return hobbyRepository.compute(bezeichnung, (bez, none) -> new Hobby(bez, owner));
        }
    }

    public static Hobby removePersonAndGet(String hobbyBez, Person person) {
        Hobby hobby = hobbyRepository.get(hobbyBez);
        if(hobby == null) {
            return null;
        }
        hobby.removePerson(person);
        return hobby;
    }
}
