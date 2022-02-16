Feature:Api test
  @api
  Scenario: get data and verify
    Given get data using with api endpoint
    Then verify if status code is 200
    Then verify response body
      | 14                 |
      | LAL                |
      | Los Angeles        |
      | West               |
      | Pacific            |
      | Los Angeles Lakers |
      | Lakers             |





