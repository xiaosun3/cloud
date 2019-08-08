package com.cloud.reflections;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by sunhaidi on 2019-05-28.
 */
public class reflections {

//    public static void check() {
//        List<Class<?>> controllerClasses = Lists.newLinkedList();
//        Reflections reflections = new Reflections("com.wondersgroup.healthcloud");
//        controllerClasses.addAll(reflections.getTypesAnnotatedWith(Controller.class));
//        controllerClasses.addAll(reflections.getTypesAnnotatedWith(RestController.class));
//
//        Map<String, Set<PassCheck>> maps = Maps.newHashMap();
//        for (Class<?> clazz : controllerClasses) {
//            RequestMapping classMappingInfo = clazz.getAnnotation(RequestMapping.class);
//            String rootUrl = classMappingInfo == null ? "" : removeEndSlash(classMappingInfo.value().length > 0 ? classMappingInfo.value()[0] : "");
//            for (Method method : clazz.getDeclaredMethods()) {
//                if (method.getAnnotation(RequestMapping.class) != null) {
//                    PassCheck withoutToken = method.getAnnotation(PassCheck.class);
//                    RequestMapping mappingInfo = method.getAnnotation(RequestMapping.class);
//                    if(withoutToken != null){
//                        String path = mappingInfo.value().length == 0 ? "" : mappingInfo.value()[0];
//                        String httpMethod = mappingInfo.method().length == 1 ? mappingInfo.method()[0].toString() : "GET";
//                        String urlInfo = httpMethod + rootUrl + removeEndSlash(path);
//                        System.out.println(urlInfo);
//                    }
//                }
//            }
//        }
//    }
    private static String removeEndSlash(String str) {
        if ("/".equals(str)) {//special case
            return "";
        }
        str = StringUtils.removeEnd(str, "/");
        return str.startsWith("/") ? str : ("/" + str);
    }

    public static void main(String[] args) {
        try {
//            Class aClass1 = Class.forName("com.cloud.reflections.RefBean");
//            RefBean o = (RefBean) aClass1.newInstance();
//            o.setName("sunhaidi");
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class clazz = loader.loadClass("com.cloud.reflections.RefBean");
//            aClass2.getClasses();
//            System.out.println(aClass1 == aClass2);

            refInfo(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }


        boolean isSafe = false;
        assert isSafe : "Not safe at all";

//        for (int i =1; i < 210; i ++){
//            System.out.println(i + "=" + i);
//        }
    }

    public static void refInfo(Class clazz) throws Exception {
            RefBean o = (RefBean) clazz.newInstance();
            o.age = "33";



        Field[] field = clazz.getDeclaredFields();
        System.out.println("-----------------------显示类的属性-----------------------------");
        for (Field f : field) {
            //getName()返回此 Field 对象表示的字段的名称
            //getType()返回一个 Class 对象，它标识了此 Field 对象所表示字段的声明类型。
            System.out.println(f.getName() + "        " + f.getType());
//            f.set(o, new String("孙海迪"));
//            System.out.println("name2:"+f.get(o));
        }
        System.out.println("-----------------------显示类的方法------------------------------");
        //getDeclaredMethods() 返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method) {
            System.out.println(m.getName());
        }
        System.out.println("----------------------显示类的构造方法----------------------------");
        //getDeclaredConstructors() 返回 Constructor 对象的一个数组，这些对象反映此 Class 对象表示的类声明的所有构造方法。
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for(Constructor c:constructors)
        {
            System.out.println(c);
        }
        System.out.println("----------------------获取类的相关的信息--------------------------");
        System.out.println("类所在的包为："+ clazz.getPackage().getName());
        System.out.println("类名："+ clazz.getName());
        System.out.println("父类的名称："+ clazz.getSuperclass().getName());

        System.out.println("-------------------------set value-----------------------------");
        Field fie = clazz.getDeclaredField("name");
        fie.set(o,"孙海迪");
        System.out.println("name:" + fie.get(o));

        Field fie2 = clazz.getDeclaredField("age");
        fie2.set(o,"26岁");
        System.out.println("age:" + fie2.get(o));

    }
}
