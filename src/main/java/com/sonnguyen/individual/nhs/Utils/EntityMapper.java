package com.sonnguyen.individual.nhs.Utils;


import com.sun.istack.logging.Logger;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;

import static com.sonnguyen.individual.nhs.Utils.Constants.*;

public class EntityMapper<T> {

    static final Logger logger = Logger.getLogger(EntityMapper.class);

    public static <T> Field getId(Class<T> tClass){
        List<Field> fields=getField(tClass);
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
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
        if(column!=null&&column.name()!=null&& !column.name().isEmpty()) return column.name();
        return field.getName();
    }
    public static <T> String getTableName(Class<T> tClass){
        Table table=tClass.getAnnotation(Table.class);
        if(table!=null&&table.name()!=null&& !table.name().isEmpty()) return table.name();
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

    public static <T> Map<String,Object> objectMap(T object, Class<T> clazz){
        List<Field> fields=getField(clazz);
        Map<String,Object> map=new HashMap<>();
        try {
            for(Field field:fields){

                field.setAccessible(true);
                Object fieldValue=field.get(object);
                if(fieldValue!=null&&field.getDeclaredAnnotation(Transient.class)==null){
                    if(fieldValue instanceof String){
                        map.put(getColumnName(field),fieldValue);
                    }
                    map.put(getColumnName(field),fieldValue);
                }
            }
        }catch (IllegalAccessException exception){
            Console.err("Error when mapping object");
            exception.printStackTrace();
        }
        return map;
    }
    public static <T> T map(ResultSet resultSet,Class<T> clazz){
        List<Field> fields =getField(clazz);
        try{
            return mapObject(clazz,resultSet);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return null;
    }
    public static <T> List<T> mapEntity(ResultSet resultSet,Class<T> clazz){
        List<Field> fields =getField(clazz);
        List<T> results=new ArrayList<T>();
        try{
            while(resultSet.next()){
                results.add(mapObject(clazz,resultSet));
            }
            return results;
        }catch (SQLException|NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e){
            e.printStackTrace();
        }
        return results;
    }
    public static <T> T mapTest(ResultSet resultSet,Class<T> clazz,Map<String,Integer> tableMap) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            List<Field> fields=getField(clazz);
            T object=clazz.getConstructor().newInstance();
            for(Field field : fields){
                if(field.getAnnotation(Transient.class)==null){
                    mapColumn(field,object,resultSet,tableMap.get(getColumnName(field)));
                }
            }
            return object;
    }
    public static List<Object[]> mapList(ResultSet resultSet,Class<?> ...clazzs) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResultSetMetaData metadata=resultSet.getMetaData();
        Map<String,Map<String,Integer>> mapObjects= new HashMap<>();
        for(Class<?> clazz : clazzs){
            String tableName=getTableName(clazz);
            Map<String,Integer> tableMap= new HashMap<>();
            for(int i=1;i<=metadata.getColumnCount();i++){
                if(metadata.getTableName(i).equalsIgnoreCase(tableName)){
                    tableMap.put(metadata.getColumnLabel(i),i);
                }
            }
            mapObjects.put(tableName,tableMap);
        }
        List<Object[]> list=new ArrayList<>();
        while(resultSet.next()){
            Object[] objects=new Object[clazzs.length];
            for(int i=0;i< clazzs.length;i++){
                objects[i]=mapTest(resultSet,clazzs[i],mapObjects.get(getTableName(clazzs[i])));
            }
            list.add(objects);
        }
        return list;
    }
    public  static <T> T mapObject(Class<T> clazz,ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Field> fields=getField(clazz);
        T object=clazz.getConstructor().newInstance();
        for (Field field : fields) {
            try{
                if(field.getAnnotation(Transient.class)!=null) continue;
                field.setAccessible(true);
                Class<?> type=field.getType();
                if (type.getSimpleName().equals(INT) || type== Integer.class) {
                    field.set(object, resultSet.getInt(getColumnName(field)));
                } else if (type.getSimpleName().equals(LONG) || type== Long.class) {
                    field.set(object, resultSet.getLong(getColumnName(field)));
                } else if (type.getSimpleName().equals(DOUBLE) || type == Double.class) {
                    field.set(object, resultSet.getDouble(getColumnName(field)));
                } else if (type.getSimpleName().equals(FLOAT) || type== Float.class) {
                    field.set(object, resultSet.getFloat(getColumnName(field)));
                } else if (type.getSimpleName().equals(BOOLEAN) ||type == Boolean.class) {
                    field.set(object, resultSet.getBoolean(getColumnName(field)));
                } else if (type.getSimpleName().equals(SHORT) || type == Short.class) {
                    field.set(object, resultSet.getShort(getColumnName(field)));
                } else if (type.getSimpleName().equals(BYTE) || type == Byte.class) {
                    field.set(object, resultSet.getByte(getColumnName(field)));
                } else if (type.getSimpleName().equals(CHAR) || type== Character.class) {
                    field.set(object,resultSet.getByte(getColumnName(field)));
                } else if (type==java.sql.Date.class) {
                    field.set(object,resultSet.getDate(getColumnName(field)));
                } else if (type==Date.class) {
                    field.set(object,new Date(resultSet.getDate(getColumnName(field)).getTime()));
                } else if(type==Instant.class){
                    field.set(object,resultSet.getDate(getColumnName(field)).toInstant());
                } else {
                    field.set(object,resultSet.getObject(getColumnName(field)));
                }
            }catch (SQLException | IllegalArgumentException | IllegalAccessException dataException){
                logger.log(Level.ALL,dataException.getMessage()+": "+object.getClass()+" "+field.getName()+"["+field.getType().getName()+"]",object.getClass());

            }
        }
        return object;
    }
    public static void mapColumn(Field field,Object object,ResultSet resultSet,int colIndex) throws IllegalAccessException, SQLException {
        Class<?> type=field.getType();
        field.setAccessible(true);
        if (type.getSimpleName().equals(INT) || type== Integer.class) {
            field.set(object, resultSet.getInt(colIndex));
        } else if (type.getSimpleName().equals(LONG) || type== Long.class) {
            field.set(object, resultSet.getLong(colIndex));
        } else if (type.getSimpleName().equals(DOUBLE) || type == Double.class) {
            field.set(object, resultSet.getDouble(colIndex));
        } else if (type.getSimpleName().equals(FLOAT) || type== Float.class) {
            field.set(object, resultSet.getFloat(colIndex));
        } else if (type.getSimpleName().equals(BOOLEAN) ||type == Boolean.class) {
            field.set(object, resultSet.getBoolean(colIndex));
        } else if (type.getSimpleName().equals(SHORT) || type == Short.class) {
            field.set(object, resultSet.getShort(colIndex));
        } else if (type.getSimpleName().equals(BYTE) || type == Byte.class) {
            field.set(object, resultSet.getByte(colIndex));
        } else if (type.getSimpleName().equals(CHAR) || type== Character.class) {
            field.set(object,resultSet.getByte(colIndex));
        } else if (type==java.sql.Date.class) {
            field.set(object,resultSet.getDate(colIndex));
        } else if (type==Date.class) {
            field.set(object,new Date(resultSet.getDate(colIndex).getTime()));
        } else if(type==Instant.class){
            field.set(object,resultSet.getDate(colIndex).toInstant());
        } else {
            field.set(object,resultSet.getObject(colIndex));
        }
    }
}
