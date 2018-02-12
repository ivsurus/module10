Feature: Yandex mail box regession testing

  Background: 
    Given user navigates to yandex home page
    And user performs login in

  @login
  Scenario: Yandex login test
    Then user login name is displayed at home page

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
    When user opens Sent folder
    Then an email is presented in Sent

    Examples: 
      | presented | not_presented |
      | true      | false         |

  @advance_actions
  Scenario Outline: Yandex advance actions test
    When user creates <a_number_of_emails> and sends them
    And moves all emails to from Sent folder to trash by drag and drop
    Then Send folder is empty

    Examples: 
      | a_number_of_emails |
      |                  5 |
