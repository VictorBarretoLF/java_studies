package computerdatabase.fundamentals;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class VideoGameDb extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://videogamedb.uk/api")
            .acceptHeader("application/json");

    // Pause Docs - https://docs.gatling.io/reference/script/core/scenario/#pause
    // Check Docs - https://docs.gatling.io/reference/script/core/checks/
    private ScenarioBuilder scn = scenario("Video Game Db - Section 5 code")

            .exec(http("Get all video games - 1st call")
                    .get("/videogame")
                    .check(status().is(200)))
            .pause(5)

            .exec(http("Get specific game")
                    .get("/videogame/1")
                    .check(status().in(200, 201, 202)))
            .pause(1, 10)

            .exec(http("get all video games - 2nd call")
                    .get("/videogame")
                    .check(status().not(404), status().not(500)))
            .pause(Duration.ofMillis(4000));

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }

}
