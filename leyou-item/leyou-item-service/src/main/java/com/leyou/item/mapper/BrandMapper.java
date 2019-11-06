package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand (category_id,brand_id) values(#{cid},#{id})")
    void saveCategoriesAndBrand(@Param("cid") Long cid,@Param("id") Long id);

    @Select("SELECT * FROM tb_brand a INNER JOIN tb_category_brand b on a.id = b.brand_id WHERE b.category_id = #{cid}")
    List<Brand> queryBrandsByCid(Long cid);
}
