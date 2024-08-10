package com.sonnguyen.individual.nhs.utils;


import com.sun.istack.logging.Logger;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class EntityMapper<T> {
    private static final Logger log=Logger.getLogger(EntityMapper.class);

    public  static <T> T mapObject(Class<T> paramType, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try{
            while (resultSet.next()){
                if(paramType==int.class||paramType==Integer.class) return paramType.cast(resultSet.getInt(1));
                else if (paramType==long.class||paramType==Long.class) return paramType.cast(resultSet.getLong(1));
                else if (paramType==short.class||paramType==Short.class) return paramType.cast(resultSet.getShort(1));
                else if (paramType==boolean.class||paramType==Boolean.class) return paramType.cast(resultSet.getBoolean(1));
                else if (paramType==float.class||paramType==Float.class) return  paramType.cast(resultSet.getFloat(1));
                else if (paramType==double.class||paramType==Double.class) return paramType.cast(resultSet.getDouble(1));
                else if (paramType==byte.class||paramType==Byte.class) return paramType.cast(resultSet.getByte(1));
                else if (paramType== BigDecimal.class) return paramType.cast(resultSet.getBigDecimal(1));
                else if (paramType== String.class) return paramType.cast(resultSet.getString(1));
                else if (paramType== Date.class) return paramType.cast(resultSet.getDate(1));
                else if (paramType== java.util.Date.class) return paramType.cast(new java.util.Date(resultSet.getDate(1).getTime()));
                else if (paramType== Time.class) return paramType.cast(resultSet.getTime(1));
                else if (paramType== Timestamp.class) return paramType.cast(resultSet.getTimestamp(1));
                else if (paramType== Blob.class) return paramType.cast(resultSet.getBytes(1));
                else if (paramType== Clob.class) return paramType.cast(resultSet.getClob(1));
                else return (T) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING,"Error why mapping object in EntityMapper class");
            e.printStackTrace();
        }
        return null;
    }
    public static <T> List<T> mapObjects(Class<T> clazz,ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> list=new ArrayList<T>();
        Map<String,String> fieldsToDataCol= EntityUtils.propertiesMap(clazz);
        Map<String,Method> settersForField=EntityUtils.setterMap(clazz);
        while (resultSet.next()){
            T obj=clazz.getConstructor().newInstance();
            Field[] fields=clazz.getDeclaredFields();
            for(Field field:fields){
                if(field.getDeclaredAnnotation(Transient.class)==null){
                    setterObject(obj,settersForField.get(field.getName().toLowerCase()),resultSet,fieldsToDataCol.get(field.getName().toLowerCase()));
                }
            }
            list.add(obj);
        }
        return list;
    }
    public static void setterObject(Object object,Method setter,ResultSet resultSet,String columnName) {
        try{
            Class<?> paramType=setter.getParameterTypes()[0];
            if(paramType==int.class||paramType==Integer.class) setter.invoke(object,resultSet.getInt(columnName));
            else if (paramType==long.class||paramType==Long.class) setter.invoke(object,resultSet.getLong(columnName));
            else if (paramType==short.class||paramType==Short.class) setter.invoke(object,resultSet.getShort(columnName));
            else if (paramType==boolean.class||paramType==Boolean.class) setter.invoke(object,resultSet.getBoolean(columnName));
            else if (paramType==float.class||paramType==Float.class) setter.invoke(object,resultSet.getFloat(columnName));
            else if (paramType==double.class||paramType==Double.class) setter.invoke(object,resultSet.getDouble(columnName));
            else if (paramType==byte.class||paramType==Byte.class) setter.invoke(object,resultSet.getByte(columnName));
            else if (paramType== BigDecimal.class) setter.invoke(object,resultSet.getBigDecimal(columnName));
            else if (paramType== String.class) setter.invoke(object,resultSet.getString(columnName));
            else if (paramType== Date.class) setter.invoke(object,resultSet.getDate(columnName));
            else if (paramType== Time.class) setter.invoke(object,resultSet.getTime(columnName));
            else if (paramType== Timestamp.class) setter.invoke(object,resultSet.getTimestamp(columnName));
            else if (paramType== Blob.class) setter.invoke(object,resultSet.getBlob(columnName));
            else if (paramType== Clob.class) setter.invoke(object,resultSet.getClob(columnName));
            else if(paramType== Instant.class) setter.invoke(object,Instant.ofEpochMilli(resultSet.getDate(columnName).getTime()));
            else setter.invoke(object,resultSet.getObject(columnName));
        } catch (SQLException | InvocationTargetException | IllegalAccessException e) {
            log.warning("Error why mapping object in EntityMapper class Column: "+columnName);
            e.printStackTrace();
        }
    }



}
