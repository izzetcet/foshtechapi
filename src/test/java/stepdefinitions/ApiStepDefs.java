package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.*;
import pojos.TeamPojo;
import utilities.ConfigReader;
import java.text.ParseException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiStepDefs {

    // We create the response at the class level so that we can use it in all methods.
    Response response;

    @Given("get data using with api endpoint")
    public void get_data_using_with_api_endpoint() throws ParseException {
    // This is how we get the response body.
    response= given().when().contentType(ContentType.JSON).get(ConfigReader.getProperty("api_endpoint"));

    }

    @Then("verify if status code is {int}")
    public void verifyIfStatusCodeIs(int expectedStatusCode) {

    //We get the expected statuscode from the feature file and verify it.
    Assert.assertEquals("status code is not expected",expectedStatusCode,response.getStatusCode());

    }


    @Then("verify response body")
    public void verify_response_body(List<String> data) {

     //We create expected data using setting methods.We got the data from the feature file to make the code dynamic
    TeamPojo expectedData = new TeamPojo();
        expectedData.setId(Integer.parseInt(data.get(0)));
        expectedData.setAbbreviation(data.get(1));
        expectedData.setCity(data.get(2));
        expectedData.setConference(data.get(3));
        expectedData.setDivision(data.get(4));
        expectedData.setFull_name(data.get(5));
        expectedData.setName(data.get(6));

        //To get the actual data, we converted the Json object to a java object using the response (Diserialization)
    TeamPojo actualData = response.as(TeamPojo.class);

    //We made the assertions using the assertEquals() method in Junit.Messages in Assertions appear in case of any failure.
        Assert.assertEquals("Id is not expected",expectedData.getId(),actualData.getId());
        Assert.assertEquals("Abbreviation is not expected",expectedData.getAbbreviation(),actualData.getAbbreviation());
        Assert.assertEquals("City is not expected",expectedData.getCity(),actualData.getCity());
        Assert.assertEquals("Conference is not  expected",expectedData.getConference(),actualData.getConference());
        Assert.assertEquals("Division is not expected",expectedData.getDivision(),actualData.getDivision());
        Assert.assertEquals("Full_name is not expected",expectedData.getFull_name(),actualData.getFull_name());
        Assert.assertEquals("Name is not expected",expectedData.getName(),actualData.getName());

    }


}


