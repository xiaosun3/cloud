package com.cloud.reflections;

import com.sun.tools.javac.util.Assert;
import org.apache.commons.lang3.StringUtils;

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
        boolean isSafe = false;
        assert isSafe : "Not safe at all";

        for (int i =1; i < 210; i ++){
            System.out.println(i + "=" + i);
        }
    }
}
