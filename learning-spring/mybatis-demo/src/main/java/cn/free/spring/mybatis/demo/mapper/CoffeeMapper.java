package cn.free.spring.mybatis.demo.mapper;

import cn.free.spring.mybatis.demo.model.Coffee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author gaowenjin
 * @date 2020/11/12
 * @description:
 */
@Mapper
public interface CoffeeMapper {

    @Insert("INSERT INTO t_coffee(name, price, create_time, update_time) VALUES (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);

    @Select("SELECT * FROM t_coffee WHERE id = #{id}")
    Coffee findById(@Param("id") Long id);

}
