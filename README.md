# Samuella Putri Nadia Pauntu - 2306170446 

# MODULE 1
## Reflection 1
While working on this project, I applied several clean code principles to enhance readability and maintainability. Here are a few examples:

1. **Meaningful Names**: I used descriptive and meaningful names throughout the code, such as productId and productName for variables, and updatedProduct for parameters. This helps make the code easier to understand.
2. **Comments**: I added comments to some functions to clarify their purpose.

I also applied some secure coding practices, such as implementing private variables (productId, productName, and productQuantity) to ensure proper encapsulation. However, I realized there’s some improvement needed, particularly in implementing Input Data Validation (such as using annotations like @Min, @NotBlank, and @Valid) to ensure only valid data is processed. Additionally, while I haven’t explicitly implemented output data encoding, the use of Thymeleaf in my HTML files automatically does output encoding, helping to avoid potential XSS attacks.

During the development process, I encountered an issue with the Whitelabel Error Page while testing the Edit Product feature. The error occurred because of a missing URL mapping in the controller, but I was able to resolve it, and the feature is now functioning as intended.

## Reflection 2
1. I believe that learning to write unit tests plays an important role in this course, as it has helped me understand that unit tests provide “confirmation” of how well and efficiently my code works. There is no exact amount of how much unit tests we should write but I feel like we should make unit tests that cover both positive and negative scenarios in our code. Additionally, 100% code coverage doesn’t necesarilly mean that our code is bug-free or error free, as code coverage does not guarantee code quality. This is where Unit Tests and Functional Tests become essential, since they serve as a good practice on ensuring our code quality.

2. Creating this new functional test suite may introduce clean code concerns that could reduce overall code quality. Some of these include code duplication which leads to increased maintenance effort and makes changes more error-prone, and another concern is a lack of reusability which violates the DRY (Don't Repeat Yourself) principle. To address these issues, we can implement improvements by extracting common setup logic into a base class, allowing all test classes to extend it and eliminate redundant code. By doing this, we can achieve cleaner, more reusable, and maintainable test suites. 

# MODULE 2
## Reflection 
1. Code quality issues i fixed:
- **Removing unused imports** – Several files in this project contained unused import statements. I removed them to improve code clarity and maintainability. After each removal, I re-ran the tests to ensure that no dependencies were mistakenly deleted.
- **Removing the declaration of a thrown exception** – In one of the functional tests, there was an unnecessary throws declaration. Exception in ‘throws’ clauses should not be superfluous. Since it wasn’t needed, I removed it and also used NoSuchElementException to handle elements that are not found.
- **Adding a nested comment** – SonarCloud flagged an issue in ProductRepositoryTest file where an empty setUp() method lacked explanation. To fix this, I added a comment clarifying why the method was left empty.

2. My project meets the definition of Continuous Integration (CI) and Continuous Deployment (CD). The CI process includes setting up jobs in GitHub Actions, running unit tests, performing code analysis with SonarCloud, and ensuring security checks are in place. Every commit triggers an automated workflow to validate the codebase and detect potential regressions. For CD, my workflow integrates with Koyeb for automatic deployment upon successful merges. This ensures that the version of the project is always updated.

# MODULE 3
## Reflection 
1. SOLID principles I applied to my project:

- SRP: Each class in my project has one clear responsibility. For example, I separated CarController and ProductController, ensuring each controller only handles a single type of entity. The service classes (CarServiceImpl and ProductServiceImpl) focus solely on business logic. Repositories (CarRepository and ProductRepository) are responsible for data persistence.
- OCP: I used an abstract class (GenericRepository) and interfaces such as IRepository, CarRepositoryInterface, ProductRepositoryInterface. This means that new entity types or persistence mechanisms can be added without modifying existing code.
- LSP: The repository interfaces and abstract class design ensure that any subclass like CarRepository or ProductRepository can be used in place of their abstract counterparts (IRepository or GenericRepository), without altering the correctness of the program. For example, CarServiceImpl depends on CarRepositoryInterface but uses CarRepository, which is an implementation of that interface, interchangeably.
- ISP: The service and repository interfaces define only the methods required for their specific domain needs (such as CarService, ProductService, CarRepositoryInterface, ProductRepositoryInterface).
- DIP: The Service files (CarServiceImpl or ProductServiceImpl) depend on a repository interface (example: CarRepositoryInterface), rather than a concrete implementation (CarRepository).

2. Advantages of applying SOLID principles to my project with examples.

- SRP: The CarService only handles business logic related to Cars, while CarRepository deals only with data persistence. I also separated the CarController and ProductController to ensure each controller only handle a single type of entity. This clear separation makes it easier to update one part of the application without mistakenly affecting others.
- OCP: By using an abstract class (GenericRepository) and interfaces like IRepository, CarRepositoryInterface, and ProductRepositoryInterface, I can add new entity types or switch persistence mechanisms without modifying existing code.
- LSP: Since CarRepository and ProductRepository extend GenericRepository<T> and follow the expected behavior, this allows them to be used interchangeably without breaking the system, ensuring flexibility and consistency when adding new repository types.
- ISP: The service and repository interfaces define only the necessary methods for their domains (such as CarService for Car operations). This prevents bloated interfaces and allows working with only needed parts, simplifying development and reducing complexity.
- DIP: In CarServiceImpl, the dependency on CarRepositoryInterface is injected rather than hard-coded to a specific implementation. This makes it easier to write unit tests by mocking the repository, leading to more robust and maintainable code.

3. Disadvantages of not applying SOLID principles to this project with examples.
- Increased Complexity and Reduced Maintainability (SRP): Before implementing the SRP to the controllers, the combined controller would handle more than one logic, making it harder to understand and navigate. For example, if CarController and ProductController are combined into a single controller, it would be more difficult to maintain and it would be harder to debug.
- Difficulty in Extending Functionality (OCP): Without using abstraction layers, the code becomes rigid. Every time a new entity or repository is added, multiple parts of the existing codebase might have to be modified. This makes the system less adaptable to future changes. For example, without using abstraction (like GenericRepository), adding a new type of repository would likely require changes in multiple parts of the codebase. This could lead to modifying existing code rather than simply extending it.
- Tight Coupling (LSP and DIP): Tight coupling occurs when a high-level module directly depends on a low-level module. For example, if CarServiceImpl directly depends on a concrete class like CarRepository, any changes to the repository’s implementation would force to modify the service layer. This makes the system less flexible and harder to test.
- Bloated and Unfocused Interfaces (ISP): When interfaces are not properly segregated, they tend to include methods that are not relevant to every implementing class. This forces classes to implement unnecessary functionalities, resulting in bloated interfaces. It can cause confusion and increase the likelihood of errors, as each class ends up handling more than what is required for its specific domain. For example, If I define a single, large repository interface that includes methods for handling operations for cars, products, and users. A repository meant only for car operations would then be forced to implement methods that are irrelevant to it. By breaking down the interfaces into smaller, domain-specific ones (like CarRepositoryInterface and ProductRepositoryInterface), each interface is kept focused on its intended purpose, reducing complexity and potential errors.

