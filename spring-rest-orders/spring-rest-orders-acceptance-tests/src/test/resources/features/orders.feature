# Created by svadika at 1/20/24
Feature: Order CRUD operations

  Scenario Outline: Fetch Orders
    Given Order Service is ALIVE
    When Fetch Order call initiated for <orderNumber>
    Then Service return <orderNumber> with <status>
    Examples:
      | orderNumber | status |
      | 123         | 200    |

  Scenario Outline: Create Order
    Given Order Service ready to create orders
    When Order request submitted with <request_payload> payload
    Then Order Service response with <status>
    Examples:
      | request_payload           | status |
      | order_create_payload.json | 201    |

