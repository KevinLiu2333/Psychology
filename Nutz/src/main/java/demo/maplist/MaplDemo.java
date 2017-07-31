package demo.maplist;

import org.junit.Test;
import org.nutz.json.Json;
import org.nutz.lang.stream.StringReader;
import org.nutz.mapl.Mapl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/31 0031
 * Time: 15:23
 */
public class MaplDemo {
    class A {
        String name;
        Integer id;
    }

    class B {
        String name;
        List<A> as;
    }

    @Test
    public void test1() {
        String json = "{'name':'b', 'as':[{'name':'nutz','id':1},{'name':'jk','id':2}]}";
//        这样得到的就是Mapl结构的数据了
        Object obj = Json.fromJson(json);
        B b = (B) Mapl.maplistToObj(obj, B.class);

    }

    @Test
    public void test2() {
//        对象转maplist
        A a = new A();
        a.name = "a";
        B b = new B();
        b.name = "b";
        b.as = new ArrayList<>();
        b.as.add(a);
        Mapl.toMaplist(b);
        System.out.println(b);

    }

    @Test
    public void test3() {
        String json = "{'name':'b', 'as':[{'name':'nutz','id':1},{'name':'jk','id':2}]}";
//        这样得到的就是一个maplist结构的数据
        Object obj = Json.fromJson(json);
//        取索引为1的name值
//        Map map = (Map) obj;
//        List list = (List) map.get("as");
//        Map item = (Map) list.get(1);
//        String name = (String) item.get("name");
//        System.out.println(name);
//        简洁的表达式搞定
        String name = (String) Mapl.cell(obj, "as[1].name");
        System.out.println(name);

    }

    @Test
    public void test4() {
//        maplist合并
        String json1 = "{'name':'nutz'}";
        String json2 = "{'age':12}";
        Object obj1 = Json.fromJson(json1);
        Object obj2 = Json.fromJson(json2);
        Object obj3 = Mapl.merge(obj1, obj2);
//        单引号呢???
        System.out.println(obj3.toString());

    }

    @Test
    public void test5() {
        String json = "{name:'nutz', age:12, address:[{area:1,name:'abc'}, {area:2,name:'123'}]}";
        Object obj = Json.fromJson(json);
        List<String> list = new ArrayList<>();
        list.add("age");
        list.add("address[].area");
//        排除了指定字段
        Object newobj = Mapl.excludeFilter(obj, list);
        System.out.println(newobj.toString());
    }

    @Test
    public void test6(){
        String json = "{name:'nutz', age:12, address:[{area:1,name:'abc'}, {area:2,name:'123'}]}";
        Object obj = Json.fromJson(json);
        List<String> list = new ArrayList<>();
        list.add("age");
        list.add("address[].area");
//        只包含指定字段
        Object newobj = Mapl.includeFilter(obj, list);
        System.out.println(newobj.toString());
    }

    @Test
    public void test7(){
        String json = "[{'name':'jk', 'age':12},{'name':'nutz', 'age':5}]";
        String model = "[{'name':['user[].姓名', 'people[].name'], 'age':['user[].年龄', 'people[].age']}]";
        String dest = "{\"people\":[{\"age\":12,\"name\":\"jk\"}, "
                + "{\"age\":5,\"name\":\"nutz\"}],"
                + "\"user\":[{\"姓名\":\"jk\",\"年龄\":12}, "
                + "{\"姓名\":\"nutz\",\"年龄\":5}]}";
        Object obj = Mapl.convert(Json.fromJson(new StringReader(json)), new StringReader(model));
//        assertEquals(dest, Json.toJson(obj, new JsonFormat()));
        System.out.println(obj);
    }
}





