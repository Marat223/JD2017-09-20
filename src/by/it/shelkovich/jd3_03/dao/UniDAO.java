package by.it.shelkovich.jd3_03.dao;



import by.it.shelkovich.jd3_03.dao.annotations.Entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniDAO<BeanType> implements IDAO<BeanType> {
    private Class<?> cl;
    private Field[] fields;
    private String tableName;

    public UniDAO(Class<?> cl){
        this.cl = cl;
        fields = cl.getDeclaredFields();
        tableName = getTableName(cl);
    }

    protected String getTableName(Class<?> cl){
        if (cl.isAnnotationPresent(Entity.class)){
            Entity annotation = cl.getAnnotation(Entity.class);
            return annotation.tableName();
        }else{
            throw new IllegalArgumentException("Класс "+cl+" не аннотирован!");
        }
    }

    public boolean create(BeanType bean) throws IllegalAccessException{
        StringBuilder query = new StringBuilder();
        StringBuilder fieldNames = new StringBuilder();
        StringBuilder fieldValues = new StringBuilder();
        String delimiter = "";
        for (int i = 1; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            fieldNames.append(delimiter).append(field.getName());
            fieldValues.append(delimiter).append("'").append(field.get(bean)).append("'");
            delimiter = ", ";
        }
        query.append("INSERT INTO ").append(tableName).append(" (").append(fieldNames).append(") VALUES (").append(fieldValues).append(");");
        Integer id = DataBase.exeCreateQuery(query.toString());
        if (id != null) {
            fields[0].setAccessible(true);
            fields[0].set(bean, id);
            return true;
        } else {
            return false;
        }
    }

    public Integer update(BeanType bean) throws IllegalAccessException{
        StringBuilder query = new StringBuilder();

        String delimiter = "";
        query.append("UPDATE ").append(tableName).append("SET ");
        for (int i = 1; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            query.append(delimiter).append(field.getName()).append(" = '").append(field.get(bean)).append("'");
            delimiter = ", ";
        }
        fields[0].setAccessible(true);
        query.append(" WHERE ").append(fields[0].getName()).append(" = '").append(fields[0].get(bean)).append("';");
        return DataBase.exeChangeQuery(query.toString());
    }

    public BeanType read(long id) throws IllegalAccessException, SQLException, InstantiationException {
        BeanType bean = (BeanType) cl.newInstance();
        fields[0].setAccessible(true);
        String query = "SELECT * FROM "+tableName+" WHERE "+fields[0].getName()+" = '"+fields[0].get(bean)+"';";
        ResultSet rs = DataBase.exeSelectQuery(query);
        if(rs.next()){
            for (int i = 0; i < fields.length; i++) {
                fillField(bean, fields[i], rs.getString(i+1));
            }
        }
        return bean;
    }

    public boolean delete(long id) throws IllegalAccessException, SQLException {
        fields[0].setAccessible(true);
        String query = "DELETE FROM "+tableName+" WHERE id='"+id+"';";
        Integer count = DataBase.exeChangeQuery(query);
        if (count != null && count == 1) return true;
        else return false;
    }

    public List<BeanType> getAll() throws SQLException, IllegalAccessException, InstantiationException {
        return get(null);
    }

    public List<BeanType> get(String whereCond) throws SQLException, IllegalAccessException, InstantiationException {
        List<BeanType> result = new ArrayList<>();

        String where = "";
        if (whereCond != null) where = " WHERE "+whereCond;
        String query = "SELECT * FROM "+tableName + where;

        ResultSet rs = DataBase.exeSelectQuery(query);
        while(rs.next()){
            BeanType bean = (BeanType) cl.newInstance();
            for (int i = 0; i < fields.length; i++) {
                fillField(bean, fields[i], rs.getString(i+1));
            }
            result.add(bean);
        }
        return result;
    }


    protected void fillField(BeanType b, Field field, String value) throws IllegalAccessException {
        String strType = field.getType().toString();
        field.setAccessible(true);
        if(field.getType() == Boolean.class || strType.equals("boolean")) field.set(b, Boolean.parseBoolean(value));
        else if(field.getType() == Integer.class || strType.equals("int")) field.set(b, Integer.parseInt(value));
        else if(field.getType() == Long.class || strType.equals("long")) field.set(b, Long.parseLong(value));
        else if(field.getType() == Double.class || strType.equals("double")) field.set(b, Double.parseDouble(value));
        else if(field.getType() == Float.class || strType.equals("float")) field.set(b, Float.parseFloat(value));
        else if(field.getType() == Byte.class || strType.equals("byte")) field.set(b, Byte.parseByte(value));
        else if(field.getType() == String.class) field.set(b, value);
    }


}
