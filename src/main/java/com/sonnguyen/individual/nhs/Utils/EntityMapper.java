package com.sonnguyen.individual.nhs.Utils;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.sonnguyen.individual.nhs.Utils.Constants.*;

public class EntityMapper<T> {
    public static <T> Field getId(Class<T> tClass){
        List<Field> fields=getField(tClass);
        for(Field field:fields){
            if(field.getAnnotation(Id.class)!=null){
                return field;
            }
        }
        return null;
    }
    public static boolean isGenerated(Field field){
        if(field!=null){
            return field.getAnnotation(GeneratedValue.class)!=null;
        }
        return false;
    }
    public static String getColumnName(Field field){
        Column column=field.getAnnotation(Column.class);
        if(column!=null&&column.name()!=null&&!column.name().equals("")) return column.name();
        return field.getName();
    }
    public static <T> String getTableName(Class<T> tClass){
        Table table=tClass.getAnnotation(Table.class);
        if(table!=null&&table.name()!=null&&!table.name().equals("")) return table.name();
        return tClass.getSimpleName();
    }
    public static List<Field> getField(Class clazz){
        List<Field> fields=new ArrayList<>();
        do{
            Collections.addAll(fields,clazz.getDeclaredFields());
            clazz=clazz.getSuperclass();
        }while(clazz!=null);
        return fields;
    }
    public static List<Method> getMethod(Class clazz){
        List<Method> methods=new ArrayList<>();
        do{
            Collections.addAll(methods,clazz.getDeclaredMethods());
            clazz=clazz.getSuperclass();
        }while(clazz!=null);
        return methods;
    }
    public static <T> Map<String,String> objectMap(T object,Class<T> clazz){
        List<Field> fields=getField(clazz);
        Map<String,String> map=new HashMap<>();
        try {
            for(Field field:fields){
                field.setAccessible(true);
                Object fieldValue=field.get(object);
                if(fieldValue!=null){
                    if(fieldValue instanceof String){
                        map.put(getColumnName(field),"\""+fieldValue+"\"");
                    }
                    map.put(getColumnName(field),String.valueOf(fieldValue));
                }
            }
        }catch (IllegalAccessException exception){
            Console.err("Error when mapping object");
            exception.printStackTrace();
        }
        return map;
    }

    public static <T> List<T> mapEntity(ResultSet resultSet,Class<T> clazz){
        List<Field> fields =getField(clazz);
        List<T> results=new ArrayList<T>();
        try{
            while(resultSet.next()){
                T object=clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String type=field.getType().getSimpleName();
                    if(field.getType().isPrimitive()){
                        switch (type){
                            case INT:
                                field.setInt(object,(int) resultSet.getInt(getColumnName(field)));
                                break;
                            case LONG:
                                field.setLong(object,resultSet.getLong(field.getName()));
                                break;
                            case BYTE:
                                field.setByte(object,resultSet.getByte(field.getName()));
                                break;
                            case SHORT:
                                field.setShort(object,resultSet.getShort(field.getName()));
                                break;
                            case FLOAT:
                                field.setFloat(object,resultSet.getFloat(field.getName()));
                                break;
                            case DOUBLE:
                                field.setDouble(object,resultSet.getDouble(field.getName()));
                                break;
                            case BOOLEAN:
                                field.setBoolean(object,resultSet.getBoolean(field.getName()));
                                break;
                        }
                    }
                    else{
                        field.set(object,resultSet.getObject(field.getName()));
                    }
                }
                results.add((T) object);
            }
            return results;
        }catch (SQLException e){
            Console.err("Error when mapping result");
            e.printStackTrace();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            System.out.println("Ambiguous properties type - in "+clazz.getName());
        }
        return results;
    }

}
