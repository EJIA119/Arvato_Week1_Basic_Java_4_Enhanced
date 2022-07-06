package basic;

import Entity.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceOperation {

    private Map<String, Person> personMap = new LinkedHashMap<>();

	public Map<String, Person> getPersonMap() {
		return personMap;
	}

	public void AddPerson(Person newPerson) throws Exception {
        if (!personMap.containsKey(newPerson.getId()))
            personMap.put(newPerson.getId(), newPerson);
        else
            throw new Exception("Exception: Duplicated ID");
	}
	
	public void showAllPerson() {
		for (Person person : personMap.values()){
			System.out.println(person);
		}
	}
	
	public Person findPerson(String personId) throws Exception {
		
		if(personMap.containsKey(personId))
			return personMap.get(personId);
		else
			throw new Exception("Exception: ID not Found.");
	}
	
	public List<Person> findPersonByName(String name) throws Exception {
		List<Person> personList = personMap.values().stream().filter( p -> name.equals( p.getName() )).collect(Collectors.toList());
		
		if(!personList.isEmpty())
			return personList;
		else
			throw new Exception("Exception: Name not found.");
	}
	
	public List<Person> findPersonByName2(String name) throws Exception {
		List<Person> persons = new ArrayList<Person>();		
		for (Person person : personMap.values()) {
			if (name.equals(person.getName())) {
				persons.add(person);
			}
		}
		
		if(!persons.isEmpty())
			return persons;
		else
			throw new Exception("Exception : Name not found.");
	}

	
	public boolean deleteById(String id) throws Exception {
		
		if(personMap.containsKey(id))
			return (personMap.remove(id) != null ? true : false);
		else
			throw new Exception("Exception : ID not found.");
    }
	
	public Person updateById(Person updatePerson) throws Exception {

		if (personMap.containsKey(updatePerson.getId())) {
			personMap.replace(updatePerson.getId(), updatePerson);
			return personMap.get(updatePerson.getId());
		} else
			throw new Exception("Exception : ID not found");
	}
	
	public Map<String, Person> sort(int choice){

		switch (choice) {
		case 1: { // sort by id
			Map<String, Person> sortedMap = new TreeMap<>(personMap);
			personMap.clear();
			personMap.putAll(sortedMap);
			System.out.println("Sorted by ID");
			break;
		}
		case 2: { // sort by name
			List<Person> sortedList = new ArrayList<>(personMap.values());
			Collections.sort(sortedList);
			personMap.clear();
			for (Person p : sortedList) {
				personMap.put(p.getId(), p);
			}
			System.out.println("Sorted by Name");
			break;
		}
		default:
			System.out.println("Default : No sort");
		}

		return personMap;
	}

	public void readFile(String fileName) throws Exception{

		try(FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);){
			personMap = (Map<String,Person>)in.readObject();
		}


	}

	public void writeFile(Map<String, Person> personMap, String fileName) throws Exception{

		try(FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);){
			out.writeObject(personMap);
			System.out.println("The person map has been written to file : " + fileName);
		}
	}
	
}
