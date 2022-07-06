package basic;

public class Main {


    public static void main(String[] args) {


        ServiceOperation service = new ServiceOperation();
        try {

            // A - Add person
//            service.AddPerson(new Person("1003", "Donavan", 23));
//            service.AddPerson(new Person("1002", "Martin", 25));
//            service.AddPerson(new Person("1001", "Philip", 33));
//            service.AddPerson(new Person("1004", "Giovanny", 29));
//            service.AddPerson(new Person("1006", "Ayaan", 36));
//            service.AddPerson(new Person("1005", "Bruce", 42));
//            service.AddPerson(new Person("1007", "Margaret", 27));
//            service.AddPerson(new Person("1009", "Valerie", 20));
//            service.AddPerson(new Person("1010", "Karla", 19));
//            service.AddPerson(new Person("1008", "Mylee", 38));

//            service.writeFile(service.getPersonMap(), "file.txt");

            service.readFile("file.txt");

            // B - Find by ID
            System.out.println("Find Person 1001 : " + service.findPerson("1001"));

            // C - Find by name
//            System.out.println("Find Person 'Bruce' : " + service.findPersonByName("Bruce"));

            // D - Delete by ID
//            String id = "1001";
//            if(service.deleteById(id))
//                System.out.println("Person with ID(" + id + ") has been removed.\n");

            // E - Update person
//            Person updatePerson = new Person("1005", "Kelvin", 25);
//            System.out.println("Before update : " + service.findPerson(updatePerson.getId()));
//            System.out.println("After  update : " + service.updateById(updatePerson));

            // Sort
            // 1 - sort by id
            // 2 - sort by name
            // 3 - default order
//            Map<String, Person> personMap = service.sort(1);
//            personMap.values().forEach(System.out::println);


        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}