package de.hse.golfclubmanagement.Steps;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hse.golfclubmanagement.models.Member;
import de.hse.golfclubmanagement.services.MemberService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class member_registrationSteps {

    private MemberService memberService; // The service class to manage members
    private Member testMember;
    private Member savedMember;
    private String registrationError;

    public member_registrationSteps(MemberService memberService) {
        this.memberService = memberService;
    }

    @Given("a valid member with required information")
    public void a_valid_member_with_required_information() {
        testMember = new Member();
        testMember.setName("John Doe");
        testMember.setMembershipStatus("Active");
        testMember.setHandicap(12);
    }

    @When("I submit the member details to the registration endpoint")
    public void i_submit_the_member_details_to_the_registration_endpoint() {
        try {
            // Use the addMember method instead of registerMember
            savedMember = memberService.addMember(testMember);
            registrationError = null;
        } catch (Exception e) {
            registrationError = e.getMessage();
        }
    }

    @Then("the system should create the valid member")
    public void the_system_should_create_the_valid_member() {
        assertNotNull(savedMember, "Member should be created and saved.");
        assertNotNull(savedMember.getId(), "Saved member should have a generated ID.");
    }

    @Then("return the saved valid member details with a successful response")
    public void return_the_saved_valid_member_details_with_a_successful_response() {
        assertNotNull(savedMember, "Saved member should not be null.");
        assertTrue(savedMember.getName().equals(testMember.getName()), "Saved member's name should match.");
    }

    @Given("a member with missing required fields")
    public void a_member_with_missing_required_fields() {
        testMember = new Member();
        testMember.setName(null); // Missing required name
        testMember.setMembershipStatus("Active");
        testMember.setHandicap(12);
    }

    @When("I submit the incomplete member details")
    public void i_submit_the_incomplete_member_details() {
        try {
            savedMember = memberService.addMember(testMember);
            registrationError = null;
        } catch (Exception e) {
            registrationError = e.getMessage();
        }
    }

    @Then("the system should reject the registration")
    public void the_system_should_reject_the_registration() {
        assertNotNull(registrationError, "Registration should be rejected with an error.");
    }

    @Then("provide appropriate validation error messages")
    public void provide_appropriate_validation_error_messages() {
        assertTrue(registrationError.contains("name cannot be null"), "Error message should mention missing name.");
    }
}