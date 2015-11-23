package container;

import movielister.Injector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laputa on 11/12/15.
 */
public class Container {

    private Map<String, Object> componentInstanceMap;

    private Map<Class<?>, Injector> injectorMap;

    public Container() {
        this.componentInstanceMap = new HashMap<> ();
        this.injectorMap = new HashMap<> ();
    }

    public void registerComponent(String name, Class<?> clazz) {
        componentInstanceMap.put(name, createNewComponent(clazz));
    }

    public void registerInjector(Class<?> injectorClazz, Injector injector) {
        injectorMap.put(injectorClazz, injector);
    }

    public Object lookup(String name) {
        if (componentInstanceMap.containsKey(name)) {
            return componentInstanceMap.get(name);
        } else {
            return null;
        }
    }

    public void start() {
        componentInstanceMap.forEach((name, component) ->  {
            initializeComponent(component);
        });
    }

    private void initializeComponent(Object component) {
        Class componentClass = component.getClass();

        Class[] interfaces = componentClass.getInterfaces();

        for (Class intef : interfaces) {
            if (injectorMap.containsKey(intef)) {
                Injector injector = injectorMap.get(intef);

                injector.inject(component);
            }
        }
    }

    private Object createNewComponent(Class<?> componentClass) {

        Object component = null;
        try {
            component = componentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return component;
    }
}
