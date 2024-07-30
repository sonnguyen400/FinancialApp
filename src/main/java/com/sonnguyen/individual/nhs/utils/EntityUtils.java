package com.sonnguyen.individual.nhs.utils;

import com.sonnguyen.individual.nhs.exception.EntityIntegrityException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EntityUtils {
    /**
     * Column name rely on field name or value in @Column annotation is prioritized
     * Ex: @Column(name='Example')
     * @param field
     * @return
     */
    public static String getColumnName(Field field){
        Column column=field.getAnnotation(Column.class);
        if(column!=null&&column.name()!=null&& !column.name().isEmpty()) return column.name();
        return field.getName();
    }

    /**
     * Get name of DB table presented by class
     * Table name rely on class name or value in @Table annotation is prioritized
     * Ex: @Table(name='Example')
     * @param tClass
     * @return String table name
     * @param <T>
     */
    public static <T> String getTableName(Class<T> tClass){
        Table table=tClass.getAnnotation(Table.class);
        if(table!=null&&table.name()!=null&& !table.name().isEmpty()) return table.name();
        return tClass.getSimpleName();
    }

    /**
     * Get all column name from class

     * @param clazz
     * @return
     */
    public static List<Field> getField(Class<?> clazz){
        List<Field> fields=new ArrayList<>();
        do{
            Collections.addAll(fields,clazz.getDeclaredFields());
            clazz=clazz.getSuperclass();
        }while(clazz!=null);
        return fields;
    }

    /**
     * Get Id from entity class
     * If Id is null throw new EntityIntegrityException
     * @param clazz
     * @return ID annotated with @Id
     */
    public static Field getIdField(Class<?> clazz) throws EntityIntegrityException{
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields){
            if(field.getDeclaredAnnotation(Id.class)!=null) return field;
        }
        throw new EntityIntegrityException("There's no Id field in "+clazz.getSimpleName());
    }

    /**
     * Mapping between java class and data object
     * @param clazz
     * @return Map<String,String>
     *     key: field name
     *     value: column name
     *     !WARN: column name is always in lowercase
     */
    public static Map<String,String> propertiesMap(Class<?> clazz){
        Field[] fields= clazz.getDeclaredFields();
        Map<String,String> map=new HashMap<>();
        for (Field field:fields){
            map.put(field.getName().toLowerCase(),getColumnName(field));
        }
        return map;
    }

    /**
     * Mapping setter method
     * @param clazz
     * @return Map<String,Method>
     *     key: field name
     *     value: setter Method
     */
    public static Map<String,Method> setterMap(Class<?> clazz){
        Method[] methods=clazz.getDeclaredMethods();
        Map<String,Method> map=new HashMap<>();
        for (Method method:methods){
            if(method.getName().startsWith("set")){
                String fieldName=method.getName().substring(3).toLowerCase();
                if(method.getParameterTypes()[0]==Boolean.class||method.getParameterTypes()[0]==boolean.class){
                    fieldName="is"+fieldName;
                }
                map.put(fieldName,method);
            }
        }
        return map;
    }

    /**
     * Mapping setter method
     * @param clazz
     * @return Map<String,Method>
     *     key: field name
     *     value: setter Method
     */
    public static Map<String,Method> getterMap(Class<?> clazz){
        Method[] methods=clazz.getDeclaredMethods();
        Map<String,Method> map=new HashMap<>();
        for (Method method:methods){
            if(method.getName().startsWith("get")){
                map.put(method.getName().substring(3).toLowerCase(),method);
            }
        }
        return map;
    }

    public static <T> LinkedHashMap<Field,Object> toMap(T object, Class<T> clazz){
        Map<String,Method> getMethods=EntityUtils.getterMap(clazz);
        LinkedHashMap<Field,Object> objectMap=new LinkedHashMap<>();
        Field[] fields=clazz.getDeclaredFields();
        for (Field field:fields){
            try {
                objectMap.put(field,getMethods.get(field.getName().toLowerCase()).invoke(object));
            } catch (IllegalAccessException | InvocationTargetException e) {

                throw new RuntimeException(e);
            }
        }
        return objectMap;
    }
}
