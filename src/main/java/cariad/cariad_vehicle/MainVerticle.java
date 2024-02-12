package cariad.cariad_vehicle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceResponse;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);

    router
      .get("/some/path")
      // this handler will ensure that the response is serialized to json
      // the content type is set to "application/json"
      .respond(
        ctx -> Future.succeededFuture(new JsonObject().put("hello", "world")));

    router
      .post("/vehicle")
      // this handler will ensure that the Pojo is serialized to json
      // the content type is set to "application/json"
      .respond(
        ctx -> Future.succeededFuture(ServiceResponse.completedWithJson(Vehicle.toJson(new Vehicle()))
          .setStatusCode(200)
          .setStatusMessage("created")));

    vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
