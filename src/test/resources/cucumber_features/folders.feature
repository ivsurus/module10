Feature: Yandex mail box folders test

  Background: 
    Given user navigates to yandex home page
    And user performs login in

  @folders_functionality
  Scenario Outline: Yandex Drafts and Sent folders test
    When user creates an email and saves it to Draft folder
    Then an email is presented in Drafts <presented>
    When user opens an email by subject
    Then correct an email adress is displayed
    And correct an email subject is displayed
    And correct an email body is displayed
    When user sends an email with specified subject from Draft folder
    Then an email is not presented in Drafts <not_presented>

    Examples: 
      | presented | not_presented |
      | true      | false         |

