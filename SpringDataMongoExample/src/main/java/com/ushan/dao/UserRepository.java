package com.ushan.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ushan.bean.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	public User findByEmailAddress( String emailAddress );
    public List<User> findByLastName( String lastName );
    
    @Query( "{ age: { \"$gte\" : ?0 } }")
    public List<User> findByAgeOver( int age );

    @Query( "{ age: { \"$gte\" : ?0, \"$lte\" : ?1 } }")
    public List<User> findByAgeBetween( int lower, int upper );
}
