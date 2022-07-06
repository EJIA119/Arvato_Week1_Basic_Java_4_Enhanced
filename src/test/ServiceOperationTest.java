package test;

import Entity.Person;
import basic.ServiceOperation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ServiceOperationTest {

    ServiceOperation serviceTester = new ServiceOperation();

    @Test
    public void addPersonTest() throws Exception {
        Person newPerson = new Person("1001", "Sunny", 22);
        serviceTester.AddPerson(newPerson);

        Person expectedPerson = new Person("1001", "Sunny", 22);

        Assert.assertEquals(expectedPerson, serviceTester.getPersonMap().get(newPerson.getId()));

    }

    @Test
    public void findPersonTest() throws Exception {
        Person newPerson = new Person("1001", "Sunny", 22);
        serviceTester.AddPerson(newPerson);

        Person expectedPerson = new Person("1001", "Sunny", 22);

        Assert.assertEquals(expectedPerson, serviceTester.findPerson(expectedPerson.getId()));

    }

    @Test
    public void findPersonByNameTest() throws Exception {
        Person newPerson = new Person("1001", "Sunny", 22);
        serviceTester.AddPerson(newPerson);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("1001", "Sunny", 22));

        Assert.assertEquals(personList, serviceTester.findPersonByName(newPerson.getName()));

    }

    @Test
    public void deleteByIdTest() throws Exception {
        Person newPerson = new Person("1001", "Sunny", 22);
        serviceTester.AddPerson(newPerson);

        Assert.assertTrue(serviceTester.deleteById("1001"));
    }

    @Test
    public void updateByIdTest() throws Exception {
        Person newPerson = new Person("1001", "Sunny", 22);
        serviceTester.AddPerson(newPerson);
        newPerson.setName("Chew");

        Person expectedPerson = new Person("1001", "Chew", 22);

        Assert.assertEquals(expectedPerson, serviceTester.updateById(newPerson));

    }

}