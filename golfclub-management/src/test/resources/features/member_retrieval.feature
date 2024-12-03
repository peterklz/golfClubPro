Feature: Member Retrieval

    Scenario: Retrieve all members
        Given members exist in the system
        When I request to list all members
        Then the system should return a complete list of members

    Scenario: Find a member by name
        Given a member exists with the name "John Doe"
        When I search for the member by the name "John Doe"
        Then the system should return the member details for "John Doe"

    Scenario: Search for a non-existent member
        Given no member exists with the given name
        When I search for the member by the name "Jane Doe"
        Then the system should return a not found response