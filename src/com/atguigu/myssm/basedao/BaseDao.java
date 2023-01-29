package com.atguigu.myssm.basedao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BaseDao<T> {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    private void startDisposition() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private Class entryClass;

    public BaseDao(){
        startDisposition();
         Type superClass = getClass().getGenericSuperclass();
        Type[] actualTypes = ((ParameterizedType) superClass).getActualTypeArguments();
        Type type = actualTypes[0];
        try {
            entryClass=Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url,user,password);
    }

    private void setValue(PreparedStatement ps,Object ...args){
        for(int i = 0;i < args.length;i++){
            try {
                ps.setObject(i+1,args[i]);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected int update(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setValue(ps,args);
            result = ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn,ps,null);
        }
        return result;
    }

    protected List<T> selectAll(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        Constructor constructor = null;
        ArrayList<T> list = null;
        ResultSet resultSet = null;
        try {
            constructor=entryClass.getConstructor();
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setValue(ps,args);
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            while(resultSet.next()){
                if(list == null){
                    list = new ArrayList<>();
                }
                T t = (T)constructor.newInstance();
                for(int i =0; i < count; i++){
                    Object value = resultSet.getObject(i + 1);
                    String label = metaData.getColumnLabel(i + 1);
                    Field field = entryClass.getDeclaredField(label);
                    field.setAccessible(true);
                    field.set(t,value);
                }
                list.add(t);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn,ps,resultSet);
        }
        return list;
    }

    private void close(Connection conn, Statement st, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
