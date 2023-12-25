public class CalculatorTest{
    @Test
    public void Sum_of_two_numbers(){
        //Arrange
        double first = 10;
        double second = 20;
        var calculator = new Calculator();

        //Act
        double result = calculator.sum(first, second);

        //Assert
        //Expected result, result, delta
        Assert.assertEquals(30, result, 0.01);
    }
}