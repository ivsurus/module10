Feature: Yandex mail box advance actions test

  Background: 
    Given user navigates to yandex home page
    And user performs login in
       """
      {	
        "login": "module5testmailbox",
        "password": "qwerty12345"
      }
      """

  @advance_actions
  Scenario Outline: Yandex advance actions test
    When user creates <a_number_of_emails> and sends them
    And moves all emails to from Sent folder to trash by drag and drop
    Then Send folder is empty

    Examples: 
      | a_number_of_emails |
      |                  5 |
