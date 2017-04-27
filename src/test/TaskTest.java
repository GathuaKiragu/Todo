import org.junit.* ;
import static org.junit.Assert.* ;

public class TaskTest {

  @Test
  public void Task_InstanciatesCorrectly_true() {
    Task myTask = new Task("mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void Task_instanciatesWithDescription_String() {
    Task myTask = new Task("mow the lawn");
    assertEquals(true, myTask.getDescription());
  }
}
