package de.hse.golfclubmanagement.Steps;

import de.hse.golfclubmanagement.models.Member;
import de.hse.golfclubmanagement.services.MemberService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class member_retrivalSteps {
    private MemberService memberService;
    private Member member;
    private Member retrievedMember;
    private String errorMessage;

    public member_retrivalSteps(MemberService memberService) {
        this.memberService = memberService;
    }

    @Given("members exist in the system")
    public void members_exist_in_the_system() {
        member = new Member();
        member.setName("John Doe");
        member.setMembershipStatus("active");
        member.setHandicap(10);
        memberService.addMember(member);  // Register a member for retrieval
    }

    @When("I request to list all members")
    public void i_request_list_all_members() {
        retrievedMember = memberService.getAllMembers().get(0); // Assume at least one member exists
    }

    @Then("the system should return a complete list of members")
    public void system_returns_member_list() {
        assertTrue("Member list should not be empty", memberService.getAllMembers().size() > 0);
    }

    @Given("a member exists with the name {string}")
    public void member_exists_with_name(String name) {
        member = new Member();
        member.setName(name);
        member.setMembershipStatus("active");
        member.setHandicap(10);
        memberService.addMember(member);  // Register a member by name
    }

    @When("I search for the member by the name {string}")
    public void i_search_for_member_by_name(String name) {
        retrievedMember = memberService.findByName(name);
    }

    @Then("the system should return the member details for {string}")
    public void system_returns_member_details(String name) {
        assertEquals("Member details should match", name, retrievedMember.getName());
    }

    @Given("no member exists with the name {string}")
    public void no_member_exists_with_name(String name) {
        // Ensure no member exists by this name (could be empty or test setup)
        retrievedMember = memberService.findByName(name);
    }

    @Then("the system should return a not found response")
    public void system_returns_not_found_response() {
        assertEquals("Should return null for non-existent member", null, retrievedMember);
    }
}