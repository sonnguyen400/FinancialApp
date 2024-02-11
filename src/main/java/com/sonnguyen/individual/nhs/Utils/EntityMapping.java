package com.sonnguyen.individual.nhs.Utils;

import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EntityMapping<T> {
    public static <T> boolean hasGeneratedId(Class<T> tClass){
        List<Field> fields=getField(tClass);
        for(Field field:fields){
            if(field.getAnnotation(Id.class)!=null&&field.getAnnotation(GeneratedValue.class)!=null){
                return true;
            }
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
        List<Field> fields= getField(clazz);
        List<T> results=new ArrayList<T>();
        try{
            while(resultSet.next()){
                Object object=new Object();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(object, resultSet.getObject(field.getName()));
                }
                results.add((T) object);
            }
            return results;
        }catch (SQLException e){
            Console.err("Error when mapping result");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
