package com.sonnguyen.individual.nhs.dao.core;


import com.sonnguyen.individual.nhs.utils.EntityUtils;
import com.sonnguyen.individual.nhs.exception.EntityIntegrityException;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public class QueryBuilder {

    public static String insert(Class<?> entityType){
        List<Field> fields= EntityUtils.getField(entityType);
        StringBuilder query = new StringBuilder("insert into ");
        query.append(EntityUtils.getTableName(entityType));
        query.append("(");
        int insertable=0;
        for(Field field:fields){
            Column column=field.getDeclaredAnnotation(Column.class);
            if(field.getDeclaredAnnotation(Transient.class) != null||(column!=null&& !column.insertable())) continue;
            query.append(EntityUtils.getColumnName(field));
            query.append(",");
            insertable++;
        }
        query.delete(query.length()-1,query.length());
        query.append(") values(");
        query.append(String.join(",", Collections.nCopies(insertable,"?")));
        query.append(")");
        return query.toString();
    }
    public static String findById(Class<?> clazz){
        StringBuilder query = new StringBuilder("select ");
        try{
            Field field=EntityUtils.getIdField(clazz);
            query.append(EntityUtils.getColumnName(field));
            query.append(" from ");
            query.append(EntityUtils.getTableName(clazz));
            return query.toString();
        } catch (EntityIntegrityException e) {
            return null;
        }
    }

    public static String paginationQuery(Pagination pagination){
        String sortQuery="";
        if(pagination.getSorts()!=null) sortQuery=sortQuery(pagination.getSorts());
        StringBuilder query = new StringBuilder(sortQuery);
        query.append(" limit ");
        if(pagination.getPage()==null) query.append(pagination.getSize());
        else{
            query.append(pagination.getPage()*pagination.getSize());
            query.append(",");
            query.append(pagination.getSize());
        }
        return query.toString();
    }
    public static String sortQuery(List<Sort> sorts){
        StringBuilder query = new StringBuilder(" order by ");
        for(Sort sort:sorts){
            query.append(sort.column);
            query.append(" ");
            query.append(sort.order);
            query.append(",");
        }
        query.deleteCharAt(query.length()-1);
        return query.toString();
    }
    public static void setStatementParams(PreparedStatement preparedStatement,Object ...params) throws SQLException{
        int i=1;
        for(Object object:params){
//            System.out.print(object);
//            System.out.print("  |  ");
            try {
                if(object==null) preparedStatement.setNull(i, Types.NULL);
                else if(object instanceof String) preparedStatement.setString(i, (String) object);
                else if(object instanceof Date) preparedStatement.setDate(i,  (Date) object);
                else if(object instanceof BigDecimal) preparedStatement.setBigDecimal(i,(BigDecimal) object);
                else if(object instanceof Integer) preparedStatement.setInt(i,(Integer) object);
                else if(object instanceof Long) preparedStatement.setLong(i,(Long) object);
                else if(object instanceof Float) preparedStatement.setFloat(i,(Float) object);
                else if(object instanceof Double) preparedStatement.setDouble(i,(Double) object);
                else if(object instanceof Short) preparedStatement.setShort(i,(Short) object);
                else if(object instanceof Byte) preparedStatement.setByte(i,(Byte) object);
                else if(object instanceof Boolean) preparedStatement.setBoolean(i,(Boolean) object);
                else if(object instanceof byte[]) preparedStatement.setBytes(i,(byte[]) object);
                else if(object instanceof Blob) preparedStatement.setBlob(i,(Blob) object);
                else if(object.getClass().isPrimitive()){
                    if(object.getClass().getSimpleName().equals("int"))  preparedStatement.setInt(i, (Integer) object);
                    else if(object.getClass().getSimpleName().equals("long")) preparedStatement.setLong(i,(Long)object);
                    else if(object.getClass().getSimpleName().equals("float")) preparedStatement.setFloat(i,(Float)object);
                    else if(object.getClass().getSimpleName().equals("double")) preparedStatement.setDouble(i,(Double)object);
                    else if(object.getClass().getSimpleName().equals("short")) preparedStatement.setShort(i,(Short)object);
                    else if(object.getClass().getSimpleName().equals("byte")) preparedStatement.setByte(i,(Byte)object);
                    else if(object.getClass().getSimpleName().equals("boolean")) preparedStatement.setBoolean(i,(Boolean)object);
                }
                else preparedStatement.setObject(i,object);

            } catch (SQLException e) {
                System.out.println("Error while mapping prepared statement");
                e.printStackTrace();
            }
            i++;
        }
        System.out.println();
    }
}
