# Design Patterns

## Creational Patterns
- **Singleton Pattern:**
  - Ensures that only one instance of a class is created and provides a global point of access to it.
  - Useful when you want to have a single, shared instance of a class throughout the application.
  - [Read more](https://medium.com/@androidtechsolution/singleton-design-pattern-42b97c9b6483)

- **Builder Pattern:**
  - Provides a way to construct complex objects step by step.
  - Allows you to create different representations of an object using the same construction process.
  - [Read more](https://medium.com/@androidtechsolution/builder-design-pattern-a9ef850a7230)

- **Factory Pattern:**
  - Provides an interface for creating objects, but allows subclasses to decide which class to instantiate.
  - Useful when you want to abstract the object creation process and create objects without specifying the exact class.
  - [Read more](https://medium.com/@androidtechsolution/factory-design-pattern-aff38fdc56a1)

## Structural Patterns
- **Adapter Pattern:**
  - Allows incompatible interfaces of different classes to work together by wrapping one class with another.
  - Acts as a bridge between two interfaces, converting the interface of one class into another interface that clients expect.
  - [Read more](https://medium.com/@androidtechsolution/adapter-design-pattern-4a22052bf093)

- **Decorator Pattern:**
  - Allows adding new behaviors or functionalities to an object dynamically by wrapping it with one or more decorator objects.
  - Provides an alternative to subclassing for extending the functionality of an object at runtime.
  - [Read more](https://medium.com/@androidtechsolution/decorator-design-pattern-cafdf7c3f0b2)

## Behavioral Patterns
- **Observer Pattern:**
  - Defines a one-to-many relationship between objects, so that when one object changes its state, all its dependents are notified and updated automatically.
  - Commonly used in event handling systems, UI updates, and decoupling components.
  - [Read more](https://medium.com/@androidtechsolution/observer-design-pattern-40b65b93f09c)

- **Strategy Pattern:**
  - Defines a family of interchangeable algorithms and encapsulates each one into separate classes.
  - Allows the algorithm to be selected at runtime based on specific conditions or requirements.
  - [Read more](https://medium.com/@androidtechsolution/strategy-design-pattern-12bc747fc009)

- **Facade Pattern:**
  - Provides a unified interface to a set of interfaces in a subsystem.
  - Simplifies the usage of complex systems by providing a higher-level interface that hides the underlying complexity.
  - [Read more](https://medium.com/@androidtechsolution/facade-design-pattern-9581d1edd243)

## Architectural Patterns
- **MVC (Model-View-Controller) Pattern:**
  - Separates the application logic into three interconnected components: the model (data and business logic), the view (user interface), and the controller (handles user input and updates the model and view).
  - Promotes separation of concerns and facilitates modular development.

- **MVVM (Model-View-ViewModel) Pattern:**
  - A variation of the MVC pattern that separates the user interface (view) from the business logic and data (model) by introducing a view model.
  - The view model acts as an intermediary between the view and the model, exposing data and operations to the view and facilitating data binding.
