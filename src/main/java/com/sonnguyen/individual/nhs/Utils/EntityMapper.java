package com.sonnguyen.individual.nhs.Utils;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

import static com.sonnguyen.individual.nhs.Utils.Constants.*;

public class EntityMapper<T> {
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
    public static <T> Map<String,Object> objectMap(T object,Class<T> clazz){
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
            T object=clazz.getConstructor().newInstance();
            for (Field field : fields) {
                if(field.getAnnotation(Transient.class)!=null) continue;
                field.setAccessible(true);
                String type=field.getType().getSimpleName();
                if(field.getType().isPrimitive()){
                    switch (type){
                        case INT:
                            field.setInt(object,(int) resultSet.getInt(getColumnName(field)));
                            break;
                        case LONG:
                            field.setLong(object,resultSet.getLong(getColumnName(field)));
                            break;
                        case BYTE:
                            field.setByte(object,resultSet.getByte(getColumnName(field)));
                            break;
                        case SHORT:
                            field.setShort(object,resultSet.getShort(getColumnName(field)));
                            break;
                        case FLOAT:
                            field.setFloat(object,resultSet.getFloat(getColumnName(field)));
                            break;
                        case DOUBLE:
                            field.setDouble(object,resultSet.getDouble(getColumnName(field)));
                            break;
                        case BOOLEAN:
                            field.setBoolean(object,resultSet.getBoolean(getColumnName(field)));
                            break;
                    }
                }
                else{
                    if(field.getType().getSimpleName().equals(Date.class.getSimpleName())){
                        field.set(object,resultSet.getDate(getColumnName(field)));
                        continue;
                    }
                    if(field.getType().getSimpleName().equals(Instant.class.getSimpleName())){
                        field.set(object,Instant.ofEpochMilli(resultSet.getDate(getColumnName(field)).getTime()));
                        continue;
                    }
                    field.set(object,resultSet.getObject(getColumnName(field)));
                }
            }
            return object;
        }catch (SQLException e){
            Console.err("Error when mapping result");
            e.printStackTrace();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            System.out.println("Ambiguous properties type - in "+clazz.getName());
        }
        return null;
    }
    public static <T> List<T> mapEntity(ResultSet resultSet,Class<T> clazz){
        List<Field> fields =getField(clazz);
        List<T> results=new ArrayList<T>();
        try{
            while(resultSet.next()){
                T object=clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    if(field.getAnnotation(Transient.class)!=null) continue;
                    field.setAccessible(true);
                    String type=field.getType().getSimpleName();
                    if(field.getType().isPrimitive()){
                        switch (type){
                            case INT:
                                field.setInt(object,(int) resultSet.getInt(getColumnName(field)));
                                break;
                            case LONG:
                                field.setLong(object,resultSet.getLong(getColumnName(field)));
                                break;
                            case BYTE:
                                field.setByte(object,resultSet.getByte(getColumnName(field)));
                                break;
                            case SHORT:
                                field.setShort(object,resultSet.getShort(getColumnName(field)));
                                break;
                            case FLOAT:
                                field.setFloat(object,resultSet.getFloat(getColumnName(field)));
                                break;
                            case DOUBLE:
                                field.setDouble(object,resultSet.getDouble(getColumnName(field)));
                                break;
                            case BOOLEAN:
                                field.setBoolean(object,resultSet.getBoolean(getColumnName(field)));
                                break;
                        }
                    }
                    else{
                        if(field.getType().getSimpleName().equals(Date.class.getSimpleName())){
                            field.set(object,resultSet.getDate(getColumnName(field)));
                            continue;
                        }
                        if(field.getType().getSimpleName().equals(Instant.class.getSimpleName())){
                            field.set(object,Instant.ofEpochMilli(resultSet.getDate(getColumnName(field)).getTime()));
                            continue;
                        }
                        field.set(object,resultSet.getObject(getColumnName(field)));
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
    };
    public static void mapColumn(Field field,Object target,ResultSet resultSet,int colIndex) throws IllegalAccessException, SQLException {
        String type=field.getType().getSimpleName();
        field.setAccessible(true);
        if(field.getType().isPrimitive()){
            switch (type){
                case INT:
                    field.setInt(target,resultSet.getInt(colIndex));
                    break;
                case LONG:
                    field.setLong(target,resultSet.getLong(colIndex));
                    break;
                case BYTE:
                    field.setByte(target,resultSet.getByte(colIndex));
                    break;
                case SHORT:
                    field.setShort(target,resultSet.getShort(colIndex));
                    break;
                case FLOAT:
                    field.setFloat(target,resultSet.getFloat(colIndex));
                    break;
                case DOUBLE:
                    field.setDouble(target,resultSet.getDouble(colIndex));
                    break;
                case BOOLEAN:
                    field.setBoolean(target,resultSet.getBoolean(colIndex));
                    break;
            }
        } else{
            if(field.getType().getSimpleName().equals(Date.class.getSimpleName())){
                field.set(target,resultSet.getDate(colIndex));
            }
            else if(field.getType().getSimpleName().equals(Instant.class.getSimpleName())){
                field.set(target,Instant.ofEpochMilli(resultSet.getDate(getColumnName(field)).getTime()));
            }else  field.set(target,resultSet.getObject(getColumnName(field)));
        }
    }
}
