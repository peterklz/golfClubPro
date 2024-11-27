Feature: Member Registration

    Scenario: Successfully register a new member
        Given a valid member with required information
        When I submit the member details to the registration endpoint
        Then the system should create the valid member
        And return the saved valid member details with a successful response

    Scenario: Attempt to register a member with incomplete information
        Given a member with missing required fields
        When I submit the incomplete member details
        Then the system should reject the registration
        And provide appropriate validation error messages

