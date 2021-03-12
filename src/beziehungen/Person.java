package beziehungen;

import java.util.*;
import java.util.stream.Collectors;

public class Person {

    private final String name;
    private final Set<Hobby> hobbies = new HashSet<>();

    public Person(String name, String... hobbies) {
        this(name, Arrays.asList(hobbies));
    }

    public Person(String name, Collection<String> hobbies) {
        if(hobbies.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        addHobbies(hobbies);
    }

    public Collection<Hobby> getHobbies() {
        return Collections.unmodifiableSet(hobbies);
    }

    public void addHobbies(String... hobbies) {
        addHobbies(Arrays.asList(hobbies));
    }

    public void addHobbies(Collection<String> hobbies) {
        this.hobbies.addAll(
                hobbies.stream()
                .map(hobbyBez -> Hobby.addPersonAndGet(hobbyBez, this))
                .collect(Collectors.toSet())
        );
    }

    public void removeHobby(String hobbyBez) {
        if(hobbies.stream().allMatch(hobby -> hobby.getBezeichnung().equals(hobbyBez))) {
            throw new IllegalStateException(name + " hätte keine Hobbys mehr");
        }
        hobbies.remove(Hobby.removePersonAndGet(hobbyBez, this));
    }

    @Override
    public String toString() {
        return name + "{" +
                "hobbies=" + hobbies +
                '}';
    }
}
