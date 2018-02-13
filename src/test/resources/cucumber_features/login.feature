Feature: Yandex mail box login test

  Background: 
    Given user navigates to yandex home page
    And user performs login in
      """
      {	
        "login": "module5testmailbox",
        "password": "qwerty12345"
      }
      """

  @login
  Scenario: Yandex login test
    Then user login name is displayed at home page shoud be:
      """
          {	
            "login": "module5testmailbox"
          }
      """
