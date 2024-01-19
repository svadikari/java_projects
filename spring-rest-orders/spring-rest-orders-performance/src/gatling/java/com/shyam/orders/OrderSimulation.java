package com.shyam.orders;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import jodd.net.MimeTypes;

import java.time.Duration;

import static io.gatling.javaapi.http.HttpDsl.http;

public class OrderSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8081/shyam")
            .acceptHeader(MimeTypes.MIME_APPLICATION_JSON)
            .contentTypeHeader(MimeTypes.MIME_APPLICATION_JSON);

    ScenarioBuilder fetchOrdersScenario = CoreDsl.scenario("Fetch Orders")
            .exec(
                    http("GET Orders").get("/orders")
            );

    ScenarioBuilder saveOrderScenario = CoreDsl.scenario("Validate Save Order")
            .forever().on(
                    CoreDsl.feed(CoreDsl.csv("data/orders.csv").circular())
                            .exec(
                                    http("Save Order").post("/orders")
                                            .body(
                                                    CoreDsl.ElFileBody("requests/order.json")
                                            )
                            )
            );

    {
        setUp(
                saveOrderScenario.injectOpen(
                        CoreDsl.nothingFor(1),
                        CoreDsl.atOnceUsers(1),
                        CoreDsl.rampUsers(3).during(5),
                        CoreDsl.rampUsersPerSec(10).to(20).during(10).randomized()
                ).protocols(httpProtocol),
                fetchOrdersScenario.injectOpen(
                        CoreDsl.nothingFor(1),
                        CoreDsl.atOnceUsers(1),
                        CoreDsl.rampUsers(3).during(5),
                        CoreDsl.rampUsersPerSec(10).to(30).during(15).randomized()
                ).protocols(httpProtocol)

        ).assertions(
                CoreDsl.global().successfulRequests().percent().gt(95.0),
                CoreDsl.global().responseTime().mean().lt(200)
        ).maxDuration(Duration.ofSeconds(60));
    }
}
