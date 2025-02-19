# Samuella Putri Nadia Pauntu - 2306170446 

# MODULE 1
## Reflection 1
While working on this project, I applied several clean code principles to enhance readability and maintainability. Here are a few examples:

1. Meaningful Names: I used descriptive and meaningful names throughout the code, such as productId and productName for variables, and updatedProduct for parameters. This helps make the code easier to understand.
2. Comments: I added comments to some functions to clarify their purpose.

I also applied some secure coding practices, such as implementing private variables (productId, productName, and productQuantity) to ensure proper encapsulation. However, I realized there’s some improvement needed, particularly in implementing Input Data Validation (such as using annotations like @Min, @NotBlank, and @Valid) to ensure only valid data is processed. Additionally, while I haven’t explicitly implemented output data encoding, the use of Thymeleaf in my HTML files automatically does output encoding, helping to avoid potential XSS attacks.

During the development process, I encountered an issue with the Whitelabel Error Page while testing the Edit Product feature. The error occurred because of a missing URL mapping in the controller, but I was able to resolve it, and the feature is now functioning as intended.

## Reflection 2
1. I believe that learning to write unit tests plays an important role in this course, as it has helped me understand that unit tests provide “confirmation” of how well and efficiently my code works. There is no exact amount of how much unit tests we should write but I feel like we should make unit tests that cover both positive and negative scenarios in our code. Additionally, 100% code coverage doesn’t necesarilly mean that our code is bug-free or error free, as code coverage does not guarantee code quality. This is where Unit Tests and Functional Tests become essential, since they serve as a good practice on ensuring our code quality.

2. Creating this new functional test suite may introduce clean code concerns that could reduce overall code quality. Some of these include code duplication which leads to increased maintenance effort and makes changes more error-prone, and another concern is a lack of reusability which violates the DRY (Don't Repeat Yourself) principle. To address these issues, we can implement improvements by extracting common setup logic into a base class, allowing all test classes to extend it and eliminate redundant code. By doing this, we can achieve cleaner, more reusable, and maintainable test suites. 

# MODULE 2
## Reflection 
1. Code quality issues i fixed:
- Removing unused imports – Several files in this project contained unused import statements. I removed them to improve code clarity and maintainability. After each removal, I re-ran the tests to ensure that no dependencies were mistakenly deleted.
- Removing the declaration of a thrown exception – In one of the functional tests, there was an unnecessary throws declaration. Exception in ‘throws’ clauses should not be superfluous. Since it wasn’t needed, I removed it and also used NoSuchElementException to handle elements that are not found.
- Adding a nested comment – SonarCloud flagged an issue in ProductRepositoryTest file where an empty setUp() method lacked explanation. To fix this, I added a comment clarifying why the method was left empty.

2. My project meets the definition of Continuous Integration (CI) and Continuous Deployment (CD). The CI process includes setting up jobs in GitHub Actions, running unit tests, performing code analysis with SonarCloud, and ensuring security checks are in place. Every commit triggers an automated workflow to validate the codebase and detect potential regressions. For CD, my workflow integrates with Koyeb for automatic deployment upon successful merges. This ensures that the version of the project is always updated.
