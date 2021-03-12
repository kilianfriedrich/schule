package beziehungen;

public class Main {

    public static void main(String[] args) {

        Person peter = new Person("Peter", "Zocken", "Fußball");
        Person hans = new Person("Hans", "Tischtennis");
        hans.addHobbies("Fußball", "Zocken"); // p2 hat Tischtennis, Fußball, Zocken
        peter.removeHobby("Fußball"); // p1 hat Zocken

        System.out.println(peter);
        System.out.println(hans);

        try {
            hans.removeHobby("Fußball");
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            peter.removeHobby("Zocken");
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

}
