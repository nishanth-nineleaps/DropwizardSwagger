package in.frodo.dropwizard.resources;

import in.frodo.dropwizard.api.Greeting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "Greetings")
@Path(value = "/rest/greetings")
public class GreetingsResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Get a greeting")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Required Parameters not present"),
          @ApiResponse(code = 401, message = "Not authorized to access this resource"),
          @ApiResponse(code = 500, message = "Internal server error")
          })
  public Greeting greet() {
    return new Greeting("hello user");
  }
}
