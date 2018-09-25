package util;

import algorithm.RemoveElem;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hui10.yang on 18/9/25.
 */
public class CgLib implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    //通过Class对象获取代理对象
    public Object getProxy(Class c){
        //设置创建子类的类
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始");
        Long a=(Long)methodProxy.invokeSuper(o, objects);
        System.out.println("结束");
        return a;
    }

    public static void main(String[] args) {
        //创建我们的代理类
        CgLib Proxy = new CgLib();

        RemoveElem removeElem = (RemoveElem)Proxy.getProxy(RemoveElem.class);
        int[] a = new int[]{0,1,2,2,3, 0, 4, 2};
        System.out.println(removeElem.removeElem(a, 2));

    }

}
