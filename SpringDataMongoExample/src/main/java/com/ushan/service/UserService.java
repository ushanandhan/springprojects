package com.ushan.service;

import java.util.List;

import com.ushan.bean.User;

public interface UserService {

	public enum SortOrder {
        ASCENDING, DESCENDING
    }

    public void addUser( User user );
    public User getUser( String id );
    public User findUserByEmailAddress( String emailAddress );
    public List<User> findUsersByLastName( String lastName );
    public List<User> findUsers();
    public List<User> findUsers( int pageNumber, int pageSize );
    public List<User> findUsersSortByAge( SortOrder sortOrder );
    public List<User> findUsersByAgeOver( int age );
    public List<User> findUsersWithAgeBetween( int lower, int upper );
//    public List<User> findByLastNameOverAge( String lastName, int age );
    public void removeUser( String id );
	
}
