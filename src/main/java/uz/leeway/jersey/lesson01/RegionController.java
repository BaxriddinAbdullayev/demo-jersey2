package uz.leeway.jersey.lesson01;

import org.apache.ibatis.session.SqlSession;
import uz.leeway.jersey.lesson01.db.DbUtils;
import uz.leeway.jersey.lesson01.db.RegionEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/hello")
public class RegionController {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public RegionEntity getById() {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("id", 3);
            return sqlSession.selectOne("getRegionById", param);
        }
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegionEntity> getAll() {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            return sqlSession.selectList("getRegionById");
        }
    }

    @PUT
    @Path("insertPr")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> insertRegionPr(RegionEntity regionEntity) {

        List<String> result = new ArrayList<>();

        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("p_name",regionEntity.getName());
            params.put("p_soni",regionEntity.getSoni());
            params.put("p_new_id",null);
            params.put("p_result",null);
            params.put("p_message",null);

            sqlSession.insert("insertRegionPr", params);

            result.add(params.get("p_new_id").toString());
            result.add(params.get("p_result").toString());
            result.add(params.get("p_message").toString());
        }catch (Throwable th){
            th.printStackTrace();
        }
        return result;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void insertRegion(RegionEntity regionEntity) {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            System.out.println("before: " + regionEntity.getRegion_id());
            sqlSession.insert("insertRegion", regionEntity);
            System.out.println("after: " + regionEntity.getRegion_id());
        }catch (Throwable th){
            th.printStackTrace();
        }
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void updateRegion(RegionEntity regionEntity) {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("region_id", regionEntity.getRegion_id());
            param.put("name", regionEntity.getName());
            param.put("soni", regionEntity.getSoni());
            sqlSession.update("updateRegion", param);
        }catch (Throwable th){
            th.printStackTrace();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int deleteRegion(@PathParam("id") int id) {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            return sqlSession.delete("deleteRegion", param);
        }
    }


}
