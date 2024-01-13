# Week 5: Client-Server

Client-server architecture is a network model where multiple clients request and receive services from a centralized server. In Java, this communication is facilitated through sockets, which provide an endpoint for sending and receiving data.

## Understanding Sockets in Java

Sockets are the cornerstone of network communication in Java, allowing for data exchange between a client and a server.

### Server Side Sockets

On the server side, a `ServerSocket` listens for incoming client connections on a specified port. When a client connects, the `ServerSocket` accepts the connection and creates a `Socket` instance for communication.

```java
ServerSocket serverSock = new ServerSocket(1234); // Server listens on port 1234
Socket sock = serverSock.accept(); // Server accepts the client connection
```

### Client Side Sockets

The client uses a `Socket` to connect to the server's IP address and port number. Once connected, it can send and receive data using input and output streams.

```java
Socket sock = new Socket("localhost", 1234); // Client connects to server

InputStream input = sock.getInputStream(); // For receiving data from the server
OutputStream output = sock.getOutputStream(); // For sending data to the server

// We know its family (file streams, system.in, sout, ...)
```

![Untitled](/OOA-2023-2024/Week%205%20Client-Server/Untitled.png)

## Communication Over Sockets

Data is sent and received over the socket's input and output streams. Java provides various stream classes to handle different types of data.

### **Example of Sending and Receiving Text Messages**

Here's how you can send and receive text messages using sockets:

```java
// Client-side setup for sending and receiving text
Socket sock = new Socket("localhost", 1234);

Scanner in = new Scanner(sock.getInputStream()); // To read text from the server
PrintStream out = new PrintStream(sock.getOutputStream(), true); // To send text to the server
```

### **Echo Server: A Simple Communication Example**

An echo server reads a line of text from the client and sends it back in uppercase. This is a simple interaction that demonstrates the request-response pattern.

```java
// SERVER
try (ServerSocket serverSocket = new **ServerSocket**(1234)) {
	while (true) {
		try (Socket socket = serverSocket.**accept**()) {
			Scanner in = new Scanner(**socket.getInputStream()**);
			PrintStream out = new PrintStream(**socket.getOutputStream()**);

			while (in.**hasNextLine**()) {
				String line = in.**nextLine**();
				out.println(line.toUpperCase()); // Echo back the received line in uppercase
				}
			}
		}
} catch (IOException ex) {
		// Handle exceptions, such as logging the error
}

// CLIENT
try (Socket socket = new Socket("localhost", 1234)) {
    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    Scanner kbd = new Scanner(System.in); // Keyboard input
    String line = kbd.nextLine();

    while (!line.equalsIgnoreCase("STOP")) {
        out.println(line); // Send the line to the server
        String response = in.nextLine(); // Read the response from the server
        System.out.println(response); // Display the server's response
        line = kbd.nextLine(); // Read the next line from the keyboard
    }
} catch (IOException ex) {
    // Handle exceptions, such as logging the error
}
```

## **Protocols in** Client-Server Communication

The communication between a client and a server follows a set of rules known as a protocol . Protocols can range from simple, like the echo server example, to complex, like HTTP.

Developers can use existing protocols or define custom ones for their applications.

## Sending an receiving custom objects as messages

Java allows the transmission of custom objects over sockets through serialization, which converts objects into byte streams.

### **Serialization in Java**

To serialize an object, it must implement the `Serializable` interface. Here's an example of a serializable `Message` class:

```java
public class Message implements Serializable {
    // Class contents that are also serializable
}
```

### **Security Considerations with Serialization**

<aside>
🚨 ***Note: Deserialization of untrusted data is inherently dangerous and should be avoided.***

</aside>

Serialization can pose security risks, particularly when deserializing untrusted data. It's crucial to follow best practices and guidelines to mitigate these risks:

Java Serialization provides an interface to classes that sidesteps the field access control mechanisms of the Java language. As a result, care must be taken when performing serialization and deserialization. Furthermore, deserialization of untrusted data should be avoided whenever possible, and should be performed carefully when it cannot be avoided

[https://www.oracle.com/java/technologies/javase/seccodeguide.html](https://www.oracle.com/java/technologies/javase/seccodeguide.html)

- Avoid serialization for security-sensitive classes
- Guard sensitive data during serialization
- View deserialization the same as object construction
- Duplicate the SecurityManager checks enforced in a during serialization and deserialization
- Understand the security permissions given to serialization and deserialization
- Filter untrusted serial data

## **Stream Decoration with ObjectOutputStream and ObjectInputStream**

Java simplifies serialization and will do the conversion for us if we “decorate**”** our OutputStreams and InputStreams with ObjectOutputStream and ObjectInputStream, respectively.

- Decorating is design pattern wich “adds new functionality” to an object by wrapping it in another (more powerful) object.

  ![Untitled](/OOA-2023-2024/Week%205%20Client-Server/Untitled%201.png)

### **Server-Side Object Streams**

The server uses `ObjectOutputStream` to send serialized objects to the client.

```java
// Message.java
public class Message implements Serializable {...}

// Server.java
serverSocket = new ServerSocket(1234);

while (true) {
	try (Socket socket = serverSocket.accept();
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	) {
				oos.writeObject( new Message(/* message contents */) );
	} catch (IOException ex) {
			// Handle exceptions, such as logging the error	}
}
```

### **Client-Side Object Streams**

The client uses `ObjectInputStream` to read serialized objects sent by the server.

```java
try (Socket socket = new Socket("localhost", 1234);
	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
	Message msg = (Message) ois.readObject();
		// do something with message
} catch (IOException | ClassNotFoundException ex) {
		// Handle exceptions, such as logging the error	}
	}
}
// We (not Java) know it is a Message
```

### When both Client and Server send and read Objects (Bidirectional)

When both the client and server send and receive objects, it's important to create the `ObjectOutputStream` before the `ObjectInputStream` to prevent deadlocks!

```java
try (Socket socket = new Socket("localhost", 1234);
1)	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()) {
2)	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
		...

} catch (IOException | ClassNotFoundException ex) {
		LOGGER.log(Level.SEVERE, "Unable to read message from network.", ex);
		throw new MySpecialException("Unable to retrieve message.");
	}
}
// When both Client and Server read Objects: make sure to create the
// object-output stream **before** the in object-input stream! Else both
// your applications will block.
```
