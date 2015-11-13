package container;

import movielister.Injector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laputa on 11/12/15.
 */
public class Container {

    private Map<String, Class<?>> componentClassMap;

    private Map<String, Object> componentInstanceMap;

    private Map<Class<?>, Injector> injectorMap;

    public Container() {
        this.componentClassMap = new HashMap<> ();
        this.componentInstanceMap = new HashMap<> ();
    }

    public void registerComponent(String name, Class<T> clazz) {
        componentClassMap.put(name, clazz);
    }

    public void registerInjector(Class<T> injectorClazz, Injector injector) {

    }

    public Object lookup(String name) {
        if (componentInstanceMap.containsKey(name)) {
            return componentInstanceMap.get(name);
        }

        if (componentClassMap.containsKey(name)) {
            return createNewComponent(name);
        } else {
            return null;
        }
    }

    private Object createNewComponent(String name) {
        Class componentClass = componentClassMap.get(name);

        Class[] interfaces = componentClass.getInterfaces();

        for (Class intef : interfaces) {
            if (injectorMap.containsKey(intef)) {
                Injector injector = injectorMap.get(intef);

                injector.inject();
            }
        }
    }
}
