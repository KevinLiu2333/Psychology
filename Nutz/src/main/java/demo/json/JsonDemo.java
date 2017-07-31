package demo.json;


import org.junit.Test;
import org.nutz.json.Json;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/26 0026
 * Time: 14:58
 */
public class JsonDemo {

    @Test
    public void test1(){
        Pet pet = new Pet();
        pet.setAge(19);
        pet.setName("lin");
        String jsonString =Json.toJson(pet);
        System.out.println(jsonString);
        pet = Json.fromJson(Pet.class,jsonString);
        System.out.println(pet.getAge());
    }
}
