import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    port(getHerokuAssignedPort());
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) ->{
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("tasks", request.session().attribute("tasks"));
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/tasks", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();

    ArrayList<Task> tasks = request.session().attribute("tasks");
    if (tasks == null) {
      tasks = new ArrayList<Task>();
      request.session().attribute("tasks", tasks);
    }

    String description = request.queryParams("description");
    Task newTask = new Task(description);
    tasks.add(newTask);

    model.put("template", "templates/success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
}
  static int getHerokuAssignedPort() {
          ProcessBuilder processBuilder = new ProcessBuilder();
          if (processBuilder.environment().get("PORT") != null) {
              return Integer.parseInt(processBuilder.environment().get("PORT"));
          }
          return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
      }
    }
