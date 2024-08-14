package com.sonnguyen.individual.nhs.context.annotation.processing;

import com.sonnguyen.individual.nhs.context.annotation.Value;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.spi.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Model
public class PropertiesExtension implements Extension {
    <X> void processInjectionTarget(@Observes ProcessInjectionTarget<X> pit) {
        /* wrap this to intercept the component lifecycle */
        final InjectionTarget<X> it = pit.getInjectionTarget();
        final Map<Field, Object> configuredValues = new HashMap<Field, Object>();
        /* use this to read annotations of the class and its members */
        AnnotatedType<X> at = pit.getAnnotatedType();
        /* read the properties file */
        InputStream stream = at.getJavaClass().getResourceAsStream("/application.properties");
        Field[] fields = at.getJavaClass().getDeclaredFields();
        if (stream != null) {
            try {
                Properties props = new Properties();
                props.load(stream);
                for (Field field : fields) {
                    Value value = field.getAnnotation(Value.class);
                    if (value != null && !value.name().isEmpty()) {
                        field.setAccessible(true);
                        try {
                            String value_=props.getProperty(value.name());
                            if(field.getType()==String.class){
                                configuredValues.put(field,value_);
                            }else if(field.getType()==Integer.class){
                                configuredValues.put(field,Integer.parseInt(value_));
                            }else if(field.getType()==Double.class){
                                configuredValues.put(field,Double.parseDouble(value_));
                            }else if(field.getType()==Float.class){
                                configuredValues.put(field,Float.parseFloat(value_));
                            }else if(field.getType()==Long.class){
                                configuredValues.put(field,Long.parseLong(value_));
                            }else if(field.getType()==Boolean.class){
                                configuredValues.put(field,Boolean.parseBoolean(value_));
                            }
                        }catch (Exception e){
                            pit.addDefinitionError(new InjectionException("Field "+field.getName()+": "+at.getJavaClass()+" incompatible with property named "+value.name()));
                        }
                    }
                }
            } catch (IOException ioe) {
                pit.addDefinitionError(ioe);
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        InjectionTarget<X> wrapped = new InjectionTarget<X>() {
            @Override
            public void inject(X instance, CreationalContext<X> ctx) {
                it.inject(instance, ctx);
                /* set the values onto the new instance of the component */
                for (Map.Entry<Field, Object> configuredValue : configuredValues.entrySet()) {
                    try {
                        configuredValue.getKey().set(instance, configuredValue.getValue());
                    } catch (Exception e) {
                        throw new InjectionException(e);
                    }

                }

            }


            @Override
            public void postConstruct(X instance) {
                it.postConstruct(instance);
            }

            @Override
            public void preDestroy(X instance) {
                it.dispose(instance);
            }

            @Override
            public void dispose(X instance) {
                it.dispose(instance);
            }

            @Override
            public Set<InjectionPoint> getInjectionPoints() {
                return it.getInjectionPoints();
            }

            @Override
            public X produce(CreationalContext<X> ctx) {
                return it.produce(ctx);
            }
        };
        pit.setInjectionTarget(wrapped);
    }
}
