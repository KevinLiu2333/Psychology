package jobs;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/28
 * Time: 9:06
 */
public class Test {
    public static void main(String[] args) {
        final A a = new A();
        a.setId("1");
        System.out.println(a.getId());

    }
}

class A{
    String name;
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}