@Chrome

Feature: Shopping and Checkout Process

  Scenario: Complete the checkout process with invalid credit card details
    Given I go to Shapermint
    When I enter the first product within the section Our Best Sellers
    And I click on the add to cart button and proceed to checkout from the cart
    And I complete email data and shipping address data with the following information
      | email      | qa.mail@gmail.com  |
      | firstName  | My Name            |
      | lastName   | My Lastname        |
      | address    | 123 William Street |
      | aptSuite   | apt 1              |
      | city       | New York           |
      | country    | United State       |
      | state      | New York           |
      | postalCode | 10038              |
      | phone      | 1234567890         |
    And I complete credit card data with the following information
      | number       | 1234 1234 1234 1234 |
      | name on card | Name Lastname       |
      | MM/YY        | 01/25               |
      | CVV          | 999                 |
    Then the shipping method "Standard Delivery (4-8 business days)" must be displayed
    And the message "Your card number is invalid." within Payment Information section must be displayed
    And the URL must contain "/hc/checkout/"