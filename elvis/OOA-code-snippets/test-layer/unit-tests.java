// NTIERMODEL TESTS

/* 
Service Layer Functionality: 
- Test the methods in your service layer to ensure they are correctly implementing the business logic. 
- For example, if you have a method in your service layer that retrieves a specific entity from the database, you can write a test to ensure that it returns the correct entity when given a valid ID, and appropriate error handling when given an invalid ID. 
*/

@Test
public void testGetEntityById() {
    // Arrange
    int validId = 1;
    Entity expectedEntity = new Entity(validId, "Test Entity");

    // Act
    Entity actualEntity = entityService.getEntityById(validId);

    // Assert
    assertEquals(expectedEntity, actualEntity);
}

/*
Data Layer Functionality:
- Test the methods in your data layer to ensure they are correctly interacting with the database. 
- For example, if you have a method that adds an entity to the database, you can write a test to ensure that the entity is correctly added and can be retrieved afterwards.
 */

@Test
public void testAddEntity() {
    // Arrange
    Entity newEntity = new Entity("New Entity");

    // Act
    entityRepository.addEntity(newEntity);
    Entity retrievedEntity = entityRepository.getEntityByName("New Entity");

    // Assert
    assertEquals(newEntity, retrievedEntity);
}

// CLIENT - SERVER SOCKET TESTS

/*
Data Transmission Test: 
- Test if the client can successfully send data to the server and receive a response.
*/

@Test
public void testSocketConnection() {
    try {
        Socket socket = new Socket("localhost", 8080);
        assertTrue(socket.isConnected());
        socket.close();
    } catch (IOException e) {
        fail("Should not have thrown any exception");
    }
}

/*
Data Transmission Test:
 - Test if the client can successfully send data to the server and receive a response.
*/
@Test
public void testDataTransmission() {
    try {
        Socket socket = new Socket("localhost", 8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String testMessage = "Test Message";
        out.println(testMessage);
        String response = in.readLine();

        assertEquals(testMessage, response);

        in.close();
        out.close();
        socket.close();
    } catch (IOException e) {
        fail("Should not have thrown any exception");
    }
}