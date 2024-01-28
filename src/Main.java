import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //Количество несовершеннолетних
        long peopleUnder18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        //Список фамилий призывников от 18 до 30 лет
        List<String> conscriptsList = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 30) //под новые реалии
                .map(Person::getFamily)
                .collect(Collectors.toList());

        //Список работоспособных людей с Высшим образованием (от 18 до 60 Женщин и от 18 до 65 Мужчин)
        List<Person> workablePeople = persons.stream()
                .filter(x ->
                        x.getEducation().equals(Education.HIGHER) && x.getAge() >= 18 &&
                                ((x.getSex().equals(Sex.MAN) && x.getAge() < 65) ||
                                (x.getSex().equals(Sex.WOMAN) && x.getAge() < 60)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}