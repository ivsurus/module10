Feature: Yandex mail box login test

  Background: 
    Given user navigates to yandex home page
    And user performs login in

  @login
  Scenario: Yandex login test
    Then user login name is displayed at home page

