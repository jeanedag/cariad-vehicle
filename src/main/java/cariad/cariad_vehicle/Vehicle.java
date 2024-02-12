package cariad.cariad_vehicle;

import io.vertx.core.json.JsonObject;

import java.util.concurrent.ThreadLocalRandom;

public class Vehicle {
  String number;
  String name;
  String status;
  Integer id;

  public Vehicle() {
    Long random = ThreadLocalRandom.current().nextLong(11111111111111111L, 99999999999999999L);
    number = "EDAG" + random.toString();
    status = "Available";
  }

  public static JsonObject toJson(Vehicle obj) {
    JsonObject json = new JsonObject();
    toJson(obj, json.getMap());
    return json;
  }

  public static void toJson(Vehicle obj, java.util.Map<String, Object> json) {
    json.put("number", obj.number);
    json.put("name", obj.name);
    json.put("status", obj.status);
    json.put("id", obj.id);
  }
}
