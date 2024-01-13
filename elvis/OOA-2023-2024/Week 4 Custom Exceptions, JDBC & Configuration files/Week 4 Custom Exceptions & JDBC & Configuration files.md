# Week 4: Custom Exceptions & JDBC & Configuration files

Created: October 17, 2023 6:48 PM
Materials: Week%204%20Custom%20Exceptions%20&%20JDBC%20&%20Configuration%20fi%20301b19f2914445c6a3f78821dbf56a6d/04-01_Custom_Exceptions.pdf, Week%204%20Custom%20Exceptions%20&%20JDBC%20&%20Configuration%20fi%20301b19f2914445c6a3f78821dbf56a6d/04-02_JDBC.pdf, Week%204%20Custom%20Exceptions%20&%20JDBC%20&%20Configuration%20fi%20301b19f2914445c6a3f78821dbf56a6d/04-03_Configuration_Files.pdf
Reviewed: Yes
Homework: Week%204%20Custom%20Exceptions%20&%20JDBC%20&%20Configuration%20fi%20301b19f2914445c6a3f78821dbf56a6d/Assignment_week_4.pdf

# Custom Exceptions

## Overview

Custom exceptions are user-defined exceptions that are created to handle specific error situations within an application. They provide a way to create more readable and maintainable code by giving meaningful names and behaviors to exceptions.

## Basic Exception Handling

Before diving into custom exceptions, the PDF revisits the basics of exception handling in Java with `try-catch` blocks.

```java
try {
    // the code that might go wrong
} catch (SomeException ex) {
    // the code to recover from the exceptional situation.
}

```

## Throwing Exceptions

The `throw` keyword is used to explicitly throw an exception when a certain condition is met.

```java
public void withdraw(int amount) {
    if (amount < 0) {
        LOGGER.log(Level.WARNING, "Possible steal attempt");
        throw new IllegalArgumentException("Illegal amount");
    }
    if (this.balance < amount) {
        throw new IllegalStateException("Insufficient funds");
    }
    this.balance -= amount;
}

```

## Creating Custom Exceptions

Custom exceptions are created by extending the `Exception` class or one of its subclasses.

```java
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super();
    }
    public InsufficientFundsException(String message) {
        super(message);
    }
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

```

## Throwing your own Exceptions

Custom exceptions can be thrown using the `throw` keyword, similar to built-in exceptions.

```java
public void withdraw(int amount) {
    if (amount < 0) {
        throw new IllegalArgumentException("Illegal amount");
    }
    if (this.balance < amount) {
        throw new InsufficientFundsException();
    }
    this.balance -= amount;
}

```

## Creating your own Exceptions

Custom exceptions can include additional information, such as the state of the application when the exception occurred.

```java
public class InsufficientFundsException extends RuntimeException {
    private final int balance;
    private final int amount;

    public InsufficientFundsException(int balance, int amount) {
        super(String.format("Insufficient Funds: cannot withdraw %d when the balance is only %d.", amount, balance));
        this.balance = balance;
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getAmount() {
        return amount;
    }
}

```

## Using Enhanced Custom Exceptions

When throwing the enhanced custom exception, you can pass in the relevant state information.

```java
public void withdraw(int amount) {
    if (amount < 0) {
        throw new IllegalArgumentException("Illegal amount");
    }
    if (this.balance < amount) {
        throw new InsufficientFundsException(this.balance, amount);
    }
    this.balance -= amount;
}

```

This summary provides a comprehensive look at how custom exceptions can be created and used in Java. Custom exceptions enhance the readability and maintainability of error handling by providing meaningful context and behaviors for specific error conditions.

Now, let's proceed with summarizing the next PDF on JDBC.

# JDBC (Java Database Connectivity)

## Introduction to JDBC

JDBC is an API in Java Standard Edition that allows Java programs to connect to relational databases. It is a critical component for Java applications that need to interact with a database for operations like querying or updating data.

```markdown
- JDBC = Java Database Connectivity
- An API in the Java Standard Edition
- Allows communication with (relational) databases from Java programs
- Native JDBC drivers are available for various databases, including MySQL
```

## Using MySQL with JDBC

The course material focuses on using MySQL with JDBC. MySQL can be installed in various ways, and the use of a GUI administration client like SQLYog, MySQL Workbench, or JetBrains DataGrip is recommended.

```markdown
- MySQL is the chosen database for examples
- Installation can be done via WampServer/LAMP or standalone installer
- GUI administration clients are recommended for managing the database
```

## Adding MySQL Support to Java Projects

To add MySQL support to a Java project, the **MySQL Connector/J** library is used. This library is added as a dependency in the project's build configuration file.

Make sure to copy-paste the **correct** version reference (depending on your MySQL version) into
your `build.gradle.kts` file

```groovy
// build.gradle.kts example (from personal pc)
dependencies {
		implementation("mysql:mysql-connector-java:8.0.33")
}

```

## Opening a Database Connection

A database connection is established using the `DriverManager.getConnection` method within a **try-with-resources** block to ensure the connection is closed automatically.

```java
private static final String URL = "jdbc:mysql://localhost/shop";
private static final String USERNAME = "myuser";
private static final String PASSWORD = "mypassword";

public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        // use connection 'con'
    } catch (SQLException ex) {
				LOGGER.log(Level.SEVERE, "Unable to connect to database", ex);
				throw new ShopException("Unable to perform operation.");
				// don't give the user too much information
    }
}
```

- When the block closes, the resources (here: Connection) are automatically released
  (closed).
- In case of problem, SQLException is thrown, so we need to catch and process it appropriately.

## Connection String Format

The connection string is crucial for establishing a connection to the database. It typically includes the server name and database name, and may require additional parameters.

```markdown
- Basic format: jdbc:mysql://servername/dbname
- Additional parameters may be required, such as server timezone or SSL configuration
```

## Executing Queries

JDBC distinguishes between SELECT queries, which return a `ResultSet`, and INSERT/UPDATE/DELETE queries, which **return an integer** representing the number of affected rows.

### Executing a SELECT Query

A SELECT query is executed using a `PreparedStatement` to avoid SQL injection and to precompile the SQL statement for potential performance gains.

```java
private static final String SQL_SELECT_ALL_PRODUCTS = "SELECT * FROM product";

private List<Product> getProducts() {
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         PreparedStatement prep = connection.prepareStatement(SQL_SELECT_ALL_PRODUCTS);
         ResultSet rs = prep.executeQuery()) {
        // process ResultSet
    } catch (SQLException ex) {
        // handle exception
    }
}

```

### Executing INSERT/UPDATE/DELETE

For non-SELECT queries, `executeUpdate` is used, and it returns the number of rows affected by the query.

```java
private static final String SQL_ADD_PRODUCT = "INSERT INTO product(name, price) VALUES(?, ?)";

private void addProduct() {
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         PreparedStatement prep = connection.prepareStatement(SQL_ADD_PRODUCT)) {
        prep.setString(1, "tablet");
        prep.setDouble(2, 499);
        prep.executeUpdate();
    } catch (SQLException ex) {
        // handle exception
    }
}

```

## Security Considerations

- Always use prepared statements **with parameters** to avoid SQL injection attacks
- **Never ever concatenate** strings to build SQL queries
- Do not use root credentials for database connections
- Give your database user the lowest privileges necessary for the application to function.

## Custom Exceptions and Logging

When catching `SQLException`, it's recommended to log the error and throw a custom exception without passing sensitive information.

```java
} catch (SQLException ex) {
    logger.log(Level.SEVERE, "DB error", ex);
    throw new ShopException("A database error occurred.");
}

```

## Advanced JDBC Topics

The PDF also touches on more advanced JDBC topics such as retrieving **AUTOINCREMENT values after an INSERT** operation and **working with transactions**.

```java
// Retrieving AUTOINCREMENT value
private void addProduct(Product product) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
				PreparedStatement prep = connection.prepareStatement(SQL_ADD_PRODUCT,
						Statement.RETURN_GENERATED_KEYS)) {
				prep.setString(1, product.getName());
				prep.setDouble(2, product.getPrice());

				prep.executeUpdate();
				try (ResultSet autoId = prep.getGeneratedKeys()) {
						autoId.next();
						product.setId(autoId.getInt(1));
					}
			} catch (SQLException ex) {
					LOGGER.log(Level.SEVERE, "A database error occured adding a product.", ex);
					throw new ShopException(”Unable to add product.");
				}
		}

// Working with transactions
connection.setAutoCommit(false);
Savepoint trx = connection.setSavepoint();

try {
		// execute SQL1
		// execute SQL2
		connection.commit();
} catch (SQLException ex) {
		connection.rollback(trx);
} finally {
		connection.setAutoCommit(true);
}
```

# Configuration Files in Java

## Introduction to Configuration Files

Configuration files are essential for setting up parameters and initial settings for applications without hardcoding them into the Java files. This approach allows for easier changes without the need to recompile the application.

```markdown
- Configuration files store parameters like database details or SMTP server settings.
- They can be changed without recompiling the application.
- They can be stored anywhere, influencing the application's behavior.
```

## Utilizing .properties Files in Java

Java has a built-in mechanism for configuration files using `.properties` files, which are simple text files containing key-value pairs.

```
# Example of a .properties file
name=Frédéric
age=43

```

## Storing Configuration Files

The convention for storing `.properties` files in Java projects is within the `/resources` directory, typically at `/resources/config/config.properties`.

```markdown
- Store `.properties` files in `/resources` or elsewhere as needed.
- Multiple `.properties` files can be used for different configurations.
```

![Untitled](/OOA-2023-2024/Week%204%20Custom%20Exceptions%20&%20JDBC%20&%20Configuration%20fi%20301b19f2914445c6a3f78821dbf56a6d/Untitled.png)

## Reading from .properties Files

Java provides a `Properties` class to load and read properties from these files.

```java
// 1.
Properties properties = new Properties();

// 2.
try (InputStream ris = getClass().getResourceAsStream("/config/config.properties")) {
    properties.load(ris);
} catch (IOException ex) {
    LOGGER.log(Level.SEVERE, "Unable to read config file", ex);
    throw new RuntimeException("Unable to load configuration.");
}

// 3.
String name = properties.getProperty("name");

```

## Updating and writing to .properties Files

Updating and writing back to a `.properties` file is also straightforward in Java.

```java
// 1.
properties.setProperty("name", "Mattias");

// 2.
String path = getClass().getResource(CONFIG_FILE).getPath();

try (FileOutputStream fos = new FileOutputStream(path)) {
    properties.store(fos, null);
} catch (IOException ex) {
    LOGGER.log(Level.SEVERE, "Unable to write config file", ex);
    throw new RuntimeException("Unable to save configuration.");
}

```

## Attention Points When Writing to Files

When dealing with configuration files in Java, particularly when writing to them at runtime, it's important to remember:

- Configuration files are typically stored in `/src/resources` and are copied to `/build/resources` during the build process.
- Any changes made to configuration files at runtime are made to the copies in `/build/resources`, not the originals in `/src/resources`.
- These runtime changes are not reflected back to the source files in `/src/resources`, meaning they won't persist after a rebuild or be included in version control.

<aside>
💡 This problem does not occur once you deploy your application

</aside>

## Encapsulating Configuration Logic

The PDF suggests encapsulating the configuration logic within a `Config` utility class, following the Singleton pattern to ensure only one instance manages the configuration settings.

The **Config** class will be responsible for all reading and writing from/towards the
config file.

```java
public class Config {
    private static final String CONFIG_FILE = "/config/config.properties";
    private static final Config INSTANCE = new Config();
    private final Properties properties = new Properties();

    public static Config getInstance() {
        return INSTANCE;
    }

		private Config() {
				try (InputStream ris = getClass().getResourceAsStream(CONFIG_FILE)) {
						properties.load(ris);
				} catch (IOException ex) {
						LOGGER.log(Level.SEVERE, "Unable to read config file", ex);
						throw new PropException("Unable to load configuration.");
				}
		}

    // Methods to read and write settings...
		public String readSetting(String key, String defaultValue) {
				return properties.getProperty(key, defaultValue);
		}
		public String readSetting(String key) {
				return readSetting(key, null);
		}
		public void writeSetting(String key, String value) {
				properties.setProperty(key, value);
				storeSettingsToFile();
		}
}

```

_Note: The Singleton pattern is a design pattern that restricts the instantiation of a class to one single instance. This is useful when exactly one object is needed to coordinate actions across the system. The pattern ensures that a class has only one instance and provides a global point of access to it._

## Using the Config Class

With the `Config` class, reading and writing settings become centralized and can be easily managed without dealing with file paths and `Properties` object management.

```java
Config conf = Config.getInstance();
System.out.println(conf.readSetting("name"));
conf.writeSetting("name", "Joske");

```

## Storing Database Connection Details in Configuration Files

The PDF also covers storing sensitive information like database URLs, usernames, and passwords in configuration files, highlighting the problems with hardcoding such information and providing a solution using the `Config` class.

### The Problem with Hardcoding Credentials

- Hardcoding database connection details (like URL, username, and password) directly in Java code is not a good practice for several reasons:
  - **Inflexibility**: If the database server address or credentials change, you must edit the source code and recompile the application.
  - **Security Risk**: Anyone with access to the source code can see sensitive information, such as the database password.

### The Solution: External Configuration Files

- To overcome these issues, it's better to externalize the configuration parameters by storing them in a separate configuration file. This file can be a `.properties` file, which is a simple key-value pair text file.
- By doing this, you can change the database details without modifying the source code and recompiling the application.

### Implementing the Solution

- The PDF suggests using a `Config` class that encapsulates the logic for reading from and writing to the configuration file. This class follows the Singleton pattern to ensure there's only one instance of it throughout the application.

### Using the Config Class for Database Details

- You can define keys for the database URL, username, and password in the `Config` class and use these keys to read the respective values from the configuration file.

### Example of Database Connection Using Config:

```java
public class MySqlConnection {
    private static final String KEY_DB_URL = "db.url";
    private static final String KEY_DB_USERNAME = "db.username";
    private static final String KEY_DB_PASSWORD = "db.password";

    private static String url;
		private static String username;
		private static String password;

		static {
				url = Config.getInstance().readSetting(KEY_DB_URL);
				username = Config.getInstance().readSetting(KEY_DB_USERNAME);
				password = Config.getInstance().readSetting(KEY_DB_PASSWORD);
		}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
// Static initialization block -> Executed once when class is loaded
```

### Security Enhancement: Encrypting Credentials

- Storing credentials in plain text, even in configuration files, is not secure. The PDF introduces a method to enhance security by encrypting the credentials.
- It suggests using the Spring Framework's Crypto library for two-way encryption, which allows you to encrypt credentials before storing them in the configuration file and decrypt them when the application reads the credentials.

To use Spring Framework’s Crypto library, add this to your dependencies in the `build.gradle.kts` file:

```java
dependencies {
    **implementation("org.springframework.security:spring-security-crypto:6.1.4")**
}
```

### Example of Encryption and Decryption:

```java
public class Crypto {
    private static final String PASSWORD = "encryption-password";
    private static final String SALT = "encryption-salt";
    private final TextEncryptor encryptor;

		private static final Crypto instance = new Crypto()

		public static Crypto getInstance() {
				return instance;
		}

    private Crypto() {
        encryptor = Encryptors.text(PASSWORD, SALT);
    }

    public String encrypt(String input) {
        return encryptor.encrypt(input);
    }

    public String decrypt(String input) {
        return encryptor.decrypt(input);
    }
}

```

### Applying Encryption to Database Credentials

- You can use the `Crypto` class to encrypt the database username and password before storing them in the `.properties` file.
- When the application starts, it reads the encrypted values from the configuration file and uses the `Crypto` class to decrypt them before establishing the database connection.

```java
public class MySqlConnection {
// ...
static {
		String usernameEncrypted = Config.getInstance().readSetting(KEY_DB_USERNAME);
		String passwordEncrypted = Config.getInstance().readSetting(KEY_DB_PASSWORD);

		Crypto crypto = Crypto.getInstance();

		username = crypto.decrypt(usernameEncrypted);
		password = crypto.decrypt(passwordEncrypted);

		url = Config.getInstance().readSetting(KEY_DB_URL);
}
```

### Limitations and Further Security Considerations

- The PDF acknowledges that even with encryption, there are limitations. For instance, the encryption key itself is still stored in the Java class, which could be decompiled to reveal the key.
- As additional security measures, it suggests:
  - Storing the encryption key securely in the operating system or server environment.
  - Prompting the user for credentials at runtime instead of storing them, even in an encrypted form.

This approach to managing database connection details enhances security and flexibility, making the application more robust and easier to maintain.
