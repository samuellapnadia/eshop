# Samuella Putri Nadia Pauntu - 2306170446 

# MODULE 1
## Reflection 1
While working on this project, I applied several clean code principles to enhance readability and maintainability. Here are a few examples:

1. Meaningful Names: I used descriptive and meaningful names throughout the code, such as productId and productName for variables, and updatedProduct for parameters. This helps make the code easier to understand.
2. Comments: I added comments to some functions to clarify their purpose.

I also applied some secure coding practices, such as implementing private variables (productId, productName, and productQuantity) to ensure proper encapsulation. However, I realized there’s some improvement needed, particularly in implementing Input Data Validation (such as using annotations like @Min, @NotBlank, and @Valid) to ensure only valid data is processed. Additionally, while I haven’t explicitly implemented output data encoding, the use of Thymeleaf in my HTML files automatically does output encoding, helping to avoid potential XSS attacks.

During the development process, I encountered an issue with the Whitelabel Error Page while testing the Edit Product feature. The error occurred because of a missing URL mapping in the controller, but I was able to resolve it, and the feature is now functioning as intended.
