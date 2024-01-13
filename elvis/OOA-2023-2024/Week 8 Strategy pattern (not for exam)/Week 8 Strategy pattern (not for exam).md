# Week 8: Strategy pattern (not for exam)

Created: December 4, 2023 1:21 PM
Reviewed: Yes
Homework: Week%208%20Strategy%20pattern%20(not%20for%20exam)%20a32b83eabe674cdbb50984601cdaa2b0/Assignment_week_8.pdf

The Strategy Pattern is a behavioral design pattern in object-oriented programming that enables an object to change its behavior at runtime by changing its internal algorithm strategy. This pattern is used when you want to define a class that will have one behavior among many possible behaviors based on certain conditions.

### Core Concept of Strategy Pattern

- **Strategy Interface**: This defines a common interface for all supported algorithms or strategies. Each strategy or algorithm must implement this interface.
- **Concrete Strategies**: These are classes that implement the Strategy interface, each providing a different implementation of a behavior or algorithm.
- **Context**: This is a class that uses a Strategy. It maintains a reference to a Strategy object and is configured with a Concrete Strategy object. The Context doesn’t know the concrete class of a strategy. It should work with all strategies via the Strategy interface.
- **Client**: The client chooses a Concrete Strategy and passes it to the Context. The Context calls the algorithm defined by the chosen Concrete Strategy.

### Example in Java

Here’s a basic example to illustrate the Strategy Pattern:

1. **Strategy Interface**:
    
    ```java
    public interface SortingStrategy {
        void sort(int[] array);
    }
    
    ```
    
2. **Concrete Strategies**:
    
    ```java
    public class BubbleSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            // Implement bubble sort
        }
    }
    
    public class QuickSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            // Implement quicksort
        }
    }
    
    ```
    
3. **Context**:
    
    ```java
    public class SortedList {
        private SortingStrategy strategy;
    
        public void setSortingStrategy(SortingStrategy strategy) {
            this.strategy = strategy;
        }
    
        public void sort(int[] array) {
            strategy.sort(array);
        }
    }
    
    ```
    
4. **Client Usage**:
    
    ```java
    public class Main {
        public static void main(String[] args) {
            SortedList list = new SortedList();
    
            int[] numbers = {34, 7, 23, 32, 5, 62};
    
            list.setSortingStrategy(new BubbleSortStrategy());
            list.sort(numbers);
    
            list.setSortingStrategy(new QuickSortStrategy());
            list.sort(numbers);
        }
    }
    
    ```
    

### Advantages of Strategy Pattern

1. **Flexibility**: Allows changing the behavior of an object at runtime by changing its strategy.
2. **Encapsulation**: Different strategies can be encapsulated within different classes.
3. **Maintainability**: Adding new strategies or modifying existing ones doesn’t affect the context class.
4. **Decoupling**: The Context class is decoupled from the concrete strategies, so the implementation details of each strategy are hidden from it.

### When to Use

Use the Strategy Pattern when:

- You have multiple algorithms for a specific task, and you want to choose between them at runtime.
- You need to encapsulate an algorithm behind an interface.
- You want to avoid exposing complex, algorithm-specific data structures.
- You want to replace inheritance with composition for changing behavior.

In summary, the Strategy Pattern provides a way to define a family of algorithms, encapsulate each one of them, and make them interchangeable. This pattern lets the algorithm vary independently from clients that use it, leading to cleaner, more maintainable code.

*⇒ ChatGPT summary, kijk naar meneer zijn voorbeeld van week 8 als je hier meer over wilt weten*