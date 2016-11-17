package com.ushan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ushan.bean.Address;
import com.ushan.bean.User;
import com.ushan.dao.AddressRepository;
import com.ushan.dao.UserRepository;

/**
 * Implementation of the UserService
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void addUser( User user )
    {
        // See if we need to add addresses
        if( user.getAddresses() != null )
        {
            for( Address address : user.getAddresses() )
            {
                Address createdAddress = addressRepository.save( address );
                address.setId( createdAddress.getId() );
            }
        }

        userRepository.save( user );
    }

    @Override
    public User getUser(String id)
    {
        return userRepository.findOne( id );
    }

    @Override
    public User findUserByEmailAddress(String emailAddress)
    {
        return userRepository.findByEmailAddress( emailAddress );
    }

    @Override
    public List<User> findUsersByLastName( String lastName )
    {
        return userRepository.findByLastName( lastName );
    }


    @Override
    public List<User> findUsers()
    {
        List<User> users = new ArrayList<User>();
        for( User user : userRepository.findAll() )
        {
            users.add( user );
        }
        return users;
    }

    @Override
    public List<User> findUsersSortByAge( SortOrder sortOrder )
    {
        // Create the Sort object
        Sort sort = null;
        if( sortOrder == SortOrder.ASCENDING )
        {
            sort = new Sort( Sort.Direction.ASC, "age" );
        }
        else
        {
            sort = new Sort( Sort.Direction.DESC, "age" );
        }

        // Query for all users, sorted by age
        List<User> users = new ArrayList<User>();
        for( User user : userRepository.findAll( sort ) )
        {
            users.add( user );
        }
        return users;

    }

    @Override
    public List<User> findUsersByAgeOver( int age )
    {
        return userRepository.findByAgeOver( age );
    }

    @Override
    public List<User> findUsersWithAgeBetween( int lower, int upper )
    {
        return userRepository.findByAgeBetween( lower, upper );
    }

    /*@Override
    public List<User> findByLastNameOverAge( String lastName, int age )
    {
        QUser user = new QUser( "user" );
        List<User> users = new ArrayList<User>();
        for( User u : userRepository.findAll( user.lastName.contains( lastName ).and( user.age.gt( age ) ) ) )
        {
            users.add( u );
        }
        return users;
    }*/

    @Override
    public List<User> findUsers( int pageNumber, int pageSize )
    {
        // Create a Pageable object for the requested page number and page size
        Pageable pageable = new PageRequest( pageNumber, pageSize );

        // Retrieve a page of users
        Page<User> page = userRepository.findAll( pageable );

        // Returns the list of users
        return page.getContent();
    }

    @Override
    public void removeUser(String id)
    {
        userRepository.delete( id );
    }
}