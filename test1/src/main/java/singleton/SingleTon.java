package singleton;

/**
 * Created by huangjh on 2017/3/7 0007 10:02
 * Email：huangjihy@163.com
 */
public class SingleTon {

    /*持有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载*/
    private static SingleTon instance = null;

    /*私有构造函数，防止被实例化*/
    private SingleTon() {
    }

    /*1.懒汉式，静态工程方法，创建实例*/
    public static SingleTon getInstance() {
        if (instance == null) {
            instance = new SingleTon();
        }
        return instance;
    }

    public static synchronized SingleTon getInstance1(){
        if (instance == null) {
            instance = new SingleTon();
        }
        return instance;
    }

    public static SingleTon getInstance2() {
        if(instance)
        return instance;
    }

}
