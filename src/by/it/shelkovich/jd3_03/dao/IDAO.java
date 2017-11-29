package by.it.shelkovich.jd3_03.dao;

import java.util.List;

public interface IDAO<BeanType> {
    BeanType read(long id) throws  Exception;
    boolean create(BeanType bean) throws Exception;
    Integer update(BeanType bean) throws Exception;
    boolean delete(long id) throws Exception;
    List<BeanType> getAll() throws Exception;
    List<BeanType> get(String condition) throws Exception;
}
