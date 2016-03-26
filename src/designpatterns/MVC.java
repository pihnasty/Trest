package designpatterns;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by pom on 26.03.2016.
 */
public class MVC {
    public   MVC (Class mClass, Class cClass, Class vClass, Observable o, Class cL )   {
        try {
            Constructor mConstructor = mClass.getConstructor(Observable.class, Class.class);
            Object model = mConstructor.newInstance(o,cL);

            Constructor cConstructor = cClass.getConstructor( mClass);
            Object controller = cConstructor.newInstance(model);

            Constructor vConstructor = vClass.getConstructor( mClass, cClass);
            Object view = vConstructor.newInstance(model,controller);

            Method methodAddObserver = Observable.class.getDeclaredMethod("addObserver",Observer.class );
            methodAddObserver.invoke(model, view);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public   MVC (Class mClass, Class cClass, Class vClass, Observable o )   {
        try {
            Constructor mConstructor = mClass.getConstructor(Observable.class, Class.class);
            Object model = mConstructor.newInstance(o);

            Constructor cConstructor = cClass.getConstructor( mClass);
            Object controller = cConstructor.newInstance(model);

            Constructor vConstructor = vClass.getConstructor( mClass, cClass);
            Object view = vConstructor.newInstance(model,controller);

            Method methodAddObserver = Observable.class.getDeclaredMethod("addObserver",Observer.class );
            methodAddObserver.invoke(model, view);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void createMVC(Class mClass, Class cClass, Class vClass, Observable o, Class cL )   {
        try {
            Constructor mConstructor = mClass.getConstructor(Observable.class, Class.class);
            Object model = mConstructor.newInstance(o,cL);

            Constructor cConstructor = cClass.getConstructor( mClass);
            Object controller = cConstructor.newInstance(model);

            Constructor vConstructor = vClass.getConstructor( mClass, cClass);
            Object view = vConstructor.newInstance(model,controller);

            Method methodAddObserver = Observable.class.getDeclaredMethod("addObserver",Observer.class );
            methodAddObserver.invoke(model, view);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void createMVC(Class mClass, Class cClass, Class vClass, Observable o )   {
        try {
            Constructor mConstructor = mClass.getConstructor(Observable.class, Class.class);
            Object model = mConstructor.newInstance(o);

            Constructor cConstructor = cClass.getConstructor( mClass);
            Object controller = cConstructor.newInstance(model);

            Constructor vConstructor = vClass.getConstructor( mClass, cClass);
            Object view = vConstructor.newInstance(model,controller);

            Method methodAddObserver = Observable.class.getDeclaredMethod("addObserver",Observer.class );
            methodAddObserver.invoke(model, view);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
