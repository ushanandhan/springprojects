package com.ushan.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ushan.bean.Address;
import com.ushan.bean.User;
import com.ushan.service.UserService;

/**
 * "Unit Test" for the user service; in quotes because this is more of an integration test than a unit test because
 * we're calling a real repository and talking to a real MongoDB instance
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-context.xml"})
public class UserServiceTest
{
	@Autowired
    private static UserService userService;
	
	@Autowired
    private static UserRepository userRepository;
    @Autowired	
	private static AddressRepository addressRepository;

    @Before
    public void before()
    {
        userRepository.deleteAll();
    }

    @Test
//    @Ignore
    public void testAddUser()
    {
        // Create a user
        User user = new User( "Steven", "Haines", "steve@geekcap.com", 41 );

        // Insert it into the repository
        userService.addUser( user );

        // Check to see if its there
        User repositoryUser = userService.findUserByEmailAddress( "steve@geekcap.com" );
        Assert.assertNotNull( repositoryUser );
        Assert.assertEquals( "The user's first name is not correct", "Steven", repositoryUser.getFirstName() );
        Assert.assertEquals( "The user's last name is not correct", "Haines", repositoryUser.getLastName() );

        // Remove the user
        userService.removeUser( repositoryUser.getId() );
    }

    @Test
    @Ignore
    public void testAddUserWithAddress()
    {
        // Create a user
        User user = new User( "Steven", "Haines", "steve@geekcap.com", 41 );
        Address address = new Address( "123 Some Street", "", "My City", "CA", "90210" );
        List<Address> addresses = new ArrayList<Address>();
        addresses.add( address );
        user.setAddresses( addresses );

        // Insert it into the repository
        userService.addUser( user );

        // Check to see if its there
        User repositoryUser = userService.findUserByEmailAddress( "steve@geekcap.com" );
        Assert.assertNotNull( repositoryUser );
        Assert.assertEquals( "The user's first name is not correct", "Steven", repositoryUser.getFirstName() );
        Assert.assertEquals( "The user's last name is not correct", "Haines", repositoryUser.getLastName() );
        Assert.assertNotNull( "Address is null", repositoryUser.getAddresses() );
        Assert.assertEquals( "The wrong number of addresses", 1, repositoryUser.getAddresses().size() );

        Address repositoryAddress = repositoryUser.getAddresses().get( 0 );
        Assert.assertEquals( "Street 1 is wrong", "123 Some Street", repositoryAddress.getStreet1() );
        Assert.assertEquals( "Street 2 is wrong", "", repositoryAddress.getStreet2() );
        Assert.assertEquals( "City is wrong", "My City", repositoryAddress.getCity() );
        Assert.assertEquals( "State is wrong", "CA", repositoryAddress.getState() );
        Assert.assertEquals( "Zipcode is wrong", "90210", repositoryAddress.getZipcode() );

        // Remove the user
        userService.removeUser( repositoryUser.getId() );
    }


    @Test
    @Ignore
    public void testFindAllUsers()
    {
        // Add 10 users
        for( int i=0; i<10; i++ )
        {
            userService.addUser( new User( "Test", "User", "test" + i + "@test.com", 18 ) );
        }

        // Query for all users
        List<User> users = userService.findUsers();
        Assert.assertNotNull( "The user list was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 10, users.size() );
    }

    @Test
    @Ignore
    public void testFindUsersNByLastName()
    {
        // Add 10 users
        for( int i=0; i<10; i++ )
        {
            String lastName = "Smith";
            if( i%2 == 0 )
            {
                lastName = "Jones";
            }
            userService.addUser(new User("Test", lastName, "test" + i + "@test.com", 18));
        }

        // Query for all users
        List<User> users = userService.findUsersByLastName( "Jones" );
        Assert.assertNotNull( "The user list was null", users );
        Assert.assertEquals( "findUsersByLastName() did not return the correct number of users with the last name of Jones", 5, users.size() );
    }

    @Test
    @Ignore
    public void testPagedFindAllUsers()
    {
        // Add 10 users
        for( int i=0; i<10; i++ )
        {
            userService.addUser(new User("Test", "User", "test" + i + "@test.com", 18));
        }

        // Query for all users
        List<User> users = userService.findUsers( 0, 5 );
        Assert.assertNotNull( "The first page of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 5, users.size() );

        users = userService.findUsers( 1, 5 );
        Assert.assertNotNull( "The second page of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 5, users.size() );

        users = userService.findUsers( 2, 5 );
        Assert.assertNotNull( "The third page of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 0, users.size() );
    }

    @Test
    @Ignore
    public void testFindAllUsersSortByAgeAscending()
    {
        // Add 10 users
        for( int i=0; i<10; i++ )
        {
            userService.addUser( new User( "Test", "User", "test" + i + "@test.com", 18+i ) );
        }

        // Query for all users
        List<User> users = userService.findUsersSortByAge( UserService.SortOrder.ASCENDING );
        Assert.assertNotNull( "The list of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 10, users.size() );

        int lastAge = 0;
        for( User user : users )
        {
            Assert.assertTrue( "User is not in age ascending order", lastAge < user.getAge() );
            lastAge = user.getAge();
        }
    }

    @Test
    @Ignore
    public void testFindAllUsersSortByAgeDescending()
    {
        // Add 10 users
        for( int i=0; i<10; i++ )
        {
            userService.addUser( new User( "Test", "User", "test" + i + "@test.com", 18+i ) );
        }

        // Query for all users
        List<User> users = userService.findUsersSortByAge( UserService.SortOrder.DESCENDING );
        Assert.assertNotNull( "The list of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 10, users.size() );

        int lastAge = 100;
        for( User user : users )
        {
            Assert.assertTrue( "User is not in age ascending order", lastAge > user.getAge() );
            lastAge = user.getAge();
        }
    }

    @Test
    @Ignore
    public void testFindUsersOverAge18()
    {
        // Add 10 users
        for( int i=0; i<10; i++ )
        {
            userService.addUser( new User( "Test", "User", "test" + i + "@test.com", 13+i ) );
        }

        // Query for all users
        List<User> users = userService.findUsersByAgeOver( 18 );
        Assert.assertNotNull( "The list of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 5, users.size() );

        for( User user : users )
        {
            Assert.assertTrue( "Age is not over 18", user.getAge() >= 18 );
        }
    }

    @Test
    @Ignore
    public void testFindUsersBetween18and35()
    {
        // Add 10 users
        for( int i=0; i<30; i++ )
        {
            userService.addUser( new User( "Test", "User", "test" + i + "@test.com", 15+i ) );
        }

        // Query for all users
        List<User> users = userService.findUsersWithAgeBetween( 18, 35 );
        Assert.assertNotNull( "The list of users was null", users );
        Assert.assertEquals( "findUsers() did not return the correct number of users", 18, users.size() );

        for( User user : users )
        {
            Assert.assertTrue( "Age is not between 18 and 35", ( user.getAge() >= 18 && user.getAge() <= 35 ) );
        }
    }

//    @Test
//    public void testFindUsersByLastNameOverAge()
//    {
//        // Add 10 users
//        for( int i=0; i<30; i++ )
//        {
//            String lastName = "User";
//            if( i%2 == 0 )
//            {
//                lastName = "Search";
//            }
//
//            userService.addUser( new User( "Test", lastName, "test" + i + "@test.com", 15+i ) );
//        }
//
//        // Query for all users
//        List<User> users = userService.findByLastNameOverAge( "Search", 25 );
//        Assert.assertNotNull( "The list of users was null", users );
//        Assert.assertEquals( "findUsers() did not return the correct number of users", 9, users.size() );
//
//        for( User user : users )
//        {
//            Assert.assertEquals( "Last name is not correct", "Search", user.getLastName() );
//            Assert.assertTrue( "Age is not over 25", user.getAge() > 25 );
//        }
//    }
}