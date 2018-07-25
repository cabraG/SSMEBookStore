package dao;


import model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    Admin checkAdminLogin(@Param("admin") Admin admin);
}
