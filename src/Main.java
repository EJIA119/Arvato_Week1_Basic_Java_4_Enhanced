import Entity.Person;
import com.sun.source.tree.Tree;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main {

    static Map<String, Person> personMap = new LinkedHashMap<>();

    public static void main(String[] args) {

        personMap.put("1003", new Person("1003", "Donavan", 23));
        personMap.put("1002", new Person("1002", "Martin", 25));
        personMap.put("1001", new Person("1001", "Philip", 33));
        personMap.put("1004", new Person("1004", "Giovanny", 29));
        personMap.put("1006", new Person("1006", "Ayaan", 36));
        personMap.put("1005", new Person("1005", "Bruce", 42));
        personMap.put("1007", new Person("1007", "Margaret", 27));
        personMap.put("1009", new Person("1009", "Valerie", 20));
        personMap.put("1010", new Person("1010", "Karla", 19));
        personMap.put("1008", new Person("1008", "Mylee", 38));

        //==============================//
        // Enhanced Code
        // Write to file - copy the map to file
//        try{
//            writeFile(personMap,"src/person.txt");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        // Read from file
        try{
            personMap.putAll(readFile("src/person.txt"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //.....Any other operation......//

//        // Scenario A - Add new person with unique id
//        Person personA = new Person("1011", "Amelia", 23);
//        try {
//            addPerson(personA);
//            System.out.println("Scenario A : The person has been added successfully");
//            personMap.values().forEach(System.out::println);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        // Scenario B - find person object by id
//        String id = "1010";
//        try{
//            displayById(id);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }

        // Scenario C - find person object by name
//        try {
//            displayByName("Donavan"); // Method 1
//            displayByName2("Donavan"); // Method 2
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        // Scenario D - delete a person from the map by id
//        try{
//            deleteById("1001");
//            personMap.values().forEach(System.out::println);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }

        // Scenario E - update a person in the map by ID
//        Person updatePerson = new Person("1002","Kelvin",30);
//        try{
//            updateById(updatePerson);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        // Scenario F
        // 1 - sort by id
        // 2 - sort by name
        // 3 - default order
        sort(1);

    }

    /*
    Scenario A
     */
    static void addPerson(Person newPerson) throws Exception {

        if (!personMap.containsKey(newPerson.getId()))
            personMap.put(newPerson.getId(), newPerson);
        else
            throw new Exception("Exception: Duplicated ID");
    }

    /*
    Scenario B
     */
    static void displayById(String id) throws Exception {

        if (personMap.containsKey(id))
            System.out.println(personMap.get(id));
        else
            throw new Exception("Exception: ID not found");
    }

    /*
    Scenario C - Method 1
     */
    static void displayByName(String name) throws Exception {

        Optional<Person> ps = personMap.values().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
        if (ps.isPresent())
            System.out.println(ps.get());
        else
            throw new Exception("Exception: Name not found");
    }

    /*
    Scenario C - Method 2
     */
    static void displayByName2(String name) throws Exception {

        boolean isExist = false;

        for (Map.Entry<String, Person> entry : personMap.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                isExist = true;
                System.out.println(entry.getValue().toString());
            }
        }

        if (!isExist)
            throw new Exception("Exception: Name not found");
    }

    /*
    Scenario D
     */
    static void deleteById(String id) throws Exception {

        if(personMap.containsKey(id)){
            personMap.remove(id);
            System.out.println("Person with ID(" + id + ") has been removed from the map");
        }else
            throw new Exception("Exception: ID not found");
    }

    /*
    Scenario E
     */
    static void updateById(Person updatePerson) throws Exception {

        if(personMap.containsKey(updatePerson.getId())){
            Person tempPerson = personMap.get(updatePerson.getId());
            personMap.replace(updatePerson.getId(), updatePerson);
            System.out.println("Updated Successfully!");
            System.out.println("Previous Value : " + tempPerson);
            System.out.println("Updated Value  : " + personMap.get(updatePerson.getId()));
        }else
            throw new Exception("Exception : ID not found");
    }

    /*
    Scenario F - Sort by id / name
     */
    static void sort(int choice){

        switch(choice){
            case 1:{ // sort by id
                Map<String, Person> sortedMap = new TreeMap<>(personMap);
                personMap.clear();
                personMap.putAll(sortedMap);
                System.out.println("Sorted by ID");
                break;
            }
            case 2:{ // sort by name
                List<Person> sortedList = new ArrayList<>(personMap.values());
                Collections.sort(sortedList);
                personMap.clear();
                for (Person p: sortedList) {
                    personMap.put(p.getId(),p);
                }
                System.out.println("Sorted by Name");
                break;
            }
            default:
                System.out.println("Default : No sort");
        }

        personMap.values().forEach(System.out::println);
    }

    /*
    Write to file
     */
    static void writeFile(Map<String, Person> personMap, String fileName) throws Exception{

        try(FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);){
            out.writeObject(personMap);
            System.out.println("The person map has been written to file : " + fileName);
        }
    }

    /*
    Read from file
     */
    static Map<String, Person> readFile(String fileName) throws Exception{

        Map<String, Person> newPersonMap = new LinkedHashMap<>();
        try(FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);){
            newPersonMap = (Map<String,Person>)in.readObject();
        }

        return newPersonMap;
    }
}