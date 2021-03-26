package wechatOrder.util;

import org.aspectj.weaver.bcel.UnwovenClassFile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * @author JJ
 * @date 2019/11/28 - 22:27
 */
public class FatherToChildUtils {

    public static <T> void fatherToChild(T father, T child) throws Exception {
//        if (child.getClass().getSuperclass() != father.getClass()) {
//            throw new Exception(child+" 不是"+father+" 的子类");
//        }
        if(child.getClass().isInstance(father.getClass())){
            throw new Exception(child+" 不是"+father+" 的子类");
        }
        Class<?> fatherClass = father.getClass();
        Field[] declaredFields = fatherClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            Method method = fatherClass.getDeclaredMethod("get" + upperHeadChar(field.getName()));
            Object obj = method.invoke(father);
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            //注意,这里设置了只有protected的类变量会注入给子类
            if(Modifier.isProtected(modifiers)) {
                field.set(child, obj);
            }
        }

    }

    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }
}
