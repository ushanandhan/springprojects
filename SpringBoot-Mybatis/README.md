![image](https://user-images.githubusercontent.com/8769673/62115372-f33ab580-b2d5-11e9-995d-983ed6a52ba2.png)

# SPRING BOOT & MYBATIS INTEGRATION WITH HSQL DATABASE
MyBatis is a SQL Mapping framework with support for custom SQL, stored procedures and advanced mappings.

## 1. Create a SpringBoot Maven project and add following MyBatis Starter Dependency:

```xml
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
	<version>1.0.0</version>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

# 2. Create Domain class:
```java
public class User	{
    private Integer id;
    private String name;
    private String email;
 
    // setters & getters
}
```
# 3. Create schema.sql: 
```sql
CREATE TABLE users
(
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    email varchar(100) DEFAULT NULL,
    PRIMARY KEY (id)
);
```
# 4. Create data.sql:
```sql
insert into users(id, name, email) values(1,'Siva','siva@gmail.com');
insert into users(id, name, email) values(2,'Prasad','prasad@gmail.com');
insert into users(id, name, email) values(3,'Reddy','reddy@gmail.com');
```

# 5. Create MyBatis SQL mapper interface (UserMapper.java):
```java
public interface UserMapper	{
    void insertUser(User user);
    User findUserById(Integer id);
    List<User> findAllUsers();
}
```

We need to create Mapper XML files to define the queries for the mapped SQL statements for the corresponding Mapper interface methods.

# 6. Create UserMapper.xml: (Using XML configuration)
```xml
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
				    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sivalabs.demo.mappers.UserMapper">

	<resultMap id="UserResultMap" type="User">
		<id column="id" property="id" />
		<result column="name" property="name" />
		<result column="email" property="email" />
	</resultMap>

	<select id="findAllUsers" resultMap="UserResultMap">
		select id, name, email from users
	</select>

	<select id="findUserById" resultMap="UserResultMap">
		select id, name, email from users WHERE id=#{id}
	</select>
<insert id="insertUser" parameterType="User" useGeneratedKeys="true" keyProperty="id">
	insert into users(name,email) values(#{name},#{email})
</insert>
</mapper>
```
A few things to observe here are:

    • Namespace in Mapper XML should be same as Fully Qualified Name (FQN) for Mapper Interface
    • Statement id values should be same as Mapper Interface method names.
    • If the query result column names are different from bean property names we can use <resultMap> configuration to provide mapping between column names and their corresponding bean property names. 

MyBatis also provides annotation based query configurations without requiring Mapper XMLs.

# 7. Create UserMapper.java: (Using annotation configuration)
```java
public interface UserMapper{
    @Insert("insert into users(name,email) values(#{name},#{email})")
    @SelectKey(statement="call identity()", keyProperty="id",
    before=false, resultType=Integer.class)
    void insertUser(User user);
    @Select("select id, name, email from users WHERE id=#{id}")
    User findUserById(Integer id);
    @Select("select id, name, email from users")
    List<User> findAllUsers();
}
```

SpringBoot MyBatis starter provides the following MyBatis configuration parameters which we can use to customize MyBatis settings in application.properties file.

# 8. Application.properties:
```properties
mybatis.config = mybatis config file name
mybatis.mapperLocations = classpath*:**/mappers/*.xml
mybatis.typeAliasesPackage = domain object's package
mybatis.typeHandlersPackage = handler's package
mybatis.check-config-location = check the mybatis configuration exists
mybatis.executorType = mode of execution. Default is SIMPLE
```

# 9. Application.java:
```java
@SpringBootApplication
@MapperScan("com.ushan.demo.mappers")
public class Application{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
```

# 10. JUnit Test Class:
```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests
{
    @Autowired
    private UserMapper userMapper;
    @Test
    public void findAllUsers() {
        List<User> users = userMapper.findAllUsers();
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }
    @Test
    public void findUserById() {
        User user = userMapper.findUserById(1);
        assertNotNull(user);
    }
    @Test
    public void createUser() {
        User user = new User(0, "Ushan", "ushan@gmail.com");
        userMapper.insertUser(user);
        User newUser = userMapper.findUserById(user.getId());
        assertEquals("Ushan", newUser.getName());
        assertEquals("ushan@gmail.com", newUser.getEmail());
    }
}
```
