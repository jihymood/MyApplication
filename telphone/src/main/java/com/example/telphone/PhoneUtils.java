package com.example.telphone;

import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

/**
 * Created by huangjh on 2017/3/15 0015 14:07
 * Email：huangjihy@163.com
 */
public class PhoneUtils {
    /**
     * 从TelephonyManager中实例化ITelephony，并返回
     */
    static public ITelephony getITelephony(TelephonyManager telMgr) throws Exception {
        Method getITelephonyMethod = telMgr.getClass().getDeclaredMethod("getITelephony");
        getITelephonyMethod.setAccessible(true);//私有化函数也能使用
        return (ITelephony) getITelephonyMethod.invoke(telMgr);
    }

    static public void printAllInform(Class clsShow) {
        try {
            // 取得所有方法
            Method[] hideMethod = clsShow.getDeclaredMethods();
            int i = 0;
            for (; i < hideMethod.length; i++) {
//                Log.e("method name", hideMethod.getName());
            }
            // 取得所有常量
//            Field[] allFields = clsShow.getFields();
//            for (i = 0; i < allFields.length; i++) {
//                Log.e("Field name", allFields.getName());
//            }
        } catch (SecurityException e) {
            // throw new RuntimeException(e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // throw new RuntimeException(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    private ITelephony getITelephony(Context context) {
//        ITelephony iTelephony = null;
//        try {
//            iTelephony = ITelephony.Stub.asInterface(ServiceManager.getService(Context.TELEPHONY_SERVICE));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return iTelephony;
//    }

//    private static ITelephony getITelephony(Context context) {
//        ITelephony iTelephony = null;
//        TelephonyManager mTelephonyManager = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        Class<TelephonyManager> c = TelephonyManager.class;
//        Method getITelephonyMethod = null;
//        Method[] m = c.getMethods();
//        for (int i = 0; i < m.length; i++) {
//            Log.i("jerome", "" + m[i].getName() + "____"
//                    + m[i].getReturnType().getName());
//        }
//        try {
//            //这种方法没有测试成功，直接将源码拷贝过来了；
//            getITelephonyMethod =c.getDeclaredMethod("getITelephony",(Class[])null); // 获取声明的方法
//            getITelephonyMethod.setAccessible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return iTelephony;
//    }
}
