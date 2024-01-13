# Week 2: File-IO

Created: November 2, 2023 9:38 AM
Materials: Week%202%20File-IO%2005559ae181fb4d29b6b514e1d85c2ca0/02-01_File-IO-1.pdf
Reviewed: No
Homework: Week%202%20File-IO%2005559ae181fb4d29b6b514e1d85c2ca0/Assignment_week_2.pdf

## Object Oriented Architectures and Secure Development

### Reading and Writing Objects from/to File

The lesson begins with a continuation of a hospital system project, focusing on the management of patient records and diagnoses.

### Our Hospital System: Evolution of Data Management

- Initially, patient diagnoses were stored as simple text strings within a `Map<Patient, String>`.
- Complexity increased as the need to store multiple diagnoses for a single patient arose, leading to a `Map<Patient, Map<Calendar, String>>`.
- To simplify the interaction for doctors, a new class `PatientLog` was introduced to abstract the complexity.

```java
public interface PatientLog {
    void add(String diagnosis); // Add new diagnosis for today
    void add(Calendar date, String diagnosis); // Add (old?) diagnosis for given date
}

```

### Data Serialization Options

- **Custom Format**: Human-readable and editable but time-consuming and error-prone to implement.
- **Java Serialization**: Effortless in Java, minimal coding, but not human-readable and tightly coupled to Java, limiting interoperability.
- **JSON/XML/YAML Serialization**: Easier to program with libraries, human-readable, and interoperable, but slightly more complex than Java serialization.

### Technical Needs for the Hospital System

- Read/write text to the command line and files.
- Serialize and deserialize objects to and from files.
- Manage directories and files.
- Serialize objects to JSON.

### Standard IO

The lesson covers the basics of standard input and output streams in Java, using `Scanner` for input and `System.out` for output.

```java
Scanner in = new Scanner(System.in);
System.out.println("What is your name?");
String name = in.nextLine();
System.out.println("Hello " + name);

```

### PrintStream and Formatting Output

- `PrintStream` is used for formatted output to the console or files.
- `printf` and `String.format` offer control over the formatting of the output.

```java
PrintStream ps = System.out;
ps.printf("Name:\\t%s%nPrice:\\t%.2f%n", productName, productPrice);

```

### File IO

- **PrintStream to File**: Demonstrates writing formatted text to a file using `PrintStream`.
- **Scanner from File**: Shows how to read from a file using `Scanner`.

```java
// Writing to a file
PrintStream ps = new PrintStream("product.txt");
ps.printf("Name:\\t%s%nPrice:\\t%.2f%n", productName, productPrice);

// Reading from a file
Scanner s = new Scanner(new File("test.txt"));
while (s.hasNext()) {
    System.out.println(s.next());
}

```

### Exception Handling in File IO

- Emphasizes the importance of handling `FileNotFoundException` and ensuring resources like files are closed properly, preferably using try-with-resources.

```java
try (Scanner s = new Scanner(new File("test.txt"))) {
    while (s.hasNext()) {
        System.out.println(s.next());
    }
} catch (FileNotFoundException e) {
    // handle it!
}
```

### Serialization and Deserialization

- **Writing Objects**: Using `ObjectOutputStream` to serialize and write objects to a file.
- **Reading Objects**: Utilizing `ObjectInputStream` to deserialize and read objects from a file.

```java
// Writing objects to a file
try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("path/to/file.ext"))) {
    out.writeObject(patient);
} catch(IOException e) {
    // handle exception
}

// Reading objects from a file
try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("path/to/file.ext"))) {
    patients.add((Patient) in.readObject());
} catch (IOException | ClassNotFoundException e) {
    // handle exception
}
/* Cast to patient: WE know the file contains a patient, but Java cannot.
This is one of the rare cases where the programmers know more about
types than Java */
```

### Serializable

This only works if the class you read/write is Serializable.
A class is Serializable if:

- the class implements Serializable;
- all the fields are Serializable.

By default, many Java built-in classes are serializable by default:

- primitive types,
- most collections (implementations).

### De-Serializable

If you want your class to be deserializable as well, make sure the super-class is also serializable,
The class of the object you are reading (deserialize) and the current class are **the same version!**
You can make the latter explicit by providing a *serialVersionUID*: 

```java
@Serial
private static final long serialVersionUID = 1L;
```

### File and Directory Management

- The `File` class is used to represent files and directories, providing methods to create, delete, and check the existence of files.

```java
File f = new File("data.txt");
if (!f.exists()) {
    boolean success = f.createNewFile();
    // handle success or failure
}

```

### Serialization to JSON

- **Jackson Library**: Not part of standard Java, but can be included to serialize and deserialize objects to and from JSON.

```java
ObjectMapper json = new ObjectMapper();
Patient original = new Patient(...);
String txt = json.writeValueAsString(original);
Patient deserialized = json.readValue(txt, Patient.class);

```

---

This summary encapsulates the key topics and code examples from the PDF. It provides a comprehensive overview of the concepts related to file IO, serialization, and the Java IO system, which are crucial for developing robust Java applications that handle file operations securely and efficiently.

# This doesn’t include everything

- Went over subjects a bit fast
- Read/write directories/files.
- Serialize objects to JSON. (not completely)