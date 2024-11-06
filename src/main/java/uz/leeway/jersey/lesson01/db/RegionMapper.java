package uz.leeway.jersey.lesson01.db;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RegionMapper {
    @Select("SELECT * FROM regions WHERE REGION_ID = #{id}")
    RegionEntity getRegionById(int id);

    @Select("SELECT * FROM regions")
    List<RegionEntity> getAllRegions();

//    @Insert("INSERT INTO regions (name, description) VALUES (#{name}, #{description})")
//    @Options(useGeneratedKeys=true, keyProperty="id")
//    void insertRegion(RegionEntity region);
//
//    @Update("UPDATE regions SET name = #{name}, description = #{description} WHERE id = #{id}")
//    void updateRegion(RegionEntity region);
//
//    @Delete("DELETE FROM regions WHERE id = #{id}")
//    void deleteRegion(int id);

}
