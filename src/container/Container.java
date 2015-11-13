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
        this.injectorMap = new HashMap<> ();
    }

    public void registerComponent(String name, Class<?> clazz) {
        componentClassMap.put(name, clazz);
    }

    public void registerInjector(Class<?> injectorClazz, Injector injector) {
        injectorMap.put(injectorClazz, injector);
    }

    public Object lookup(String name) {
        if (componentInstanceMap.containsKey(name)) {
            return componentInstanceMap.get(name);
        }

        if (componentClassMap.containsKey(name)) {
            try {
                return createNewComponent(name);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    private Object createNewComponent(String name) throws IllegalAccessException, InstantiationException {
        Class componentClass = componentClassMap.get(name);

        Object component = componentClass.newInstance();

        Class[] interfaces = componentClass.getInterfaces();

        for (Class intef : interfaces) {
            if (injectorMap.containsKey(intef)) {
                Injector injector = injectorMap.get(intef);

                injector.inject(component);
            }
        }

        return component;
    }
}
