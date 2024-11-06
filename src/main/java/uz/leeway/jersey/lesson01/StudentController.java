package uz.leeway.jersey.lesson01;

import org.apache.ibatis.session.SqlSession;
import uz.leeway.jersey.lesson01.db.DbUtils;
import uz.leeway.jersey.lesson01.db.StudentEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/testservice")
public class StudentController {

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> insertStudent(StudentEntity studentEntity) {

        List<String> result = new ArrayList<>();

        try (SqlSession sqlSession = DbUtils.getSqlSession()){
            Map<String, Object> params = new HashMap<>();
            params.put("p_student_id",studentEntity.getStudent_id());
            params.put("p_first_name",studentEntity.getFirst_name());
            params.put("p_last_name",studentEntity.getLast_name());
            params.put("p_result",null);
            params.put("p_message",null);

            sqlSession.insert("insertStudentPr",params);

            result.add(params.get("p_result").toString());
            result.add(params.get("p_message").toString());
        }catch (Throwable e){
            e.printStackTrace();
        }
        return result;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> updateStudent(StudentEntity studentEntity){
        List<String> result = new ArrayList<>();

        try (SqlSession sqlSession=DbUtils.getSqlSession()){
            Map<String, Object> params = new HashMap<>();
            params.put("p_student_id",studentEntity.getStudent_id());
            params.put("p_first_name",studentEntity.getFirst_name());
            params.put("p_last_name",studentEntity.getLast_name());
            params.put("p_result",null);
            params.put("p_message",null);

            sqlSession.update("updateStudentPr",params);

            result.add(params.get("p_result").toString());
            result.add(params.get("p_message").toString());
        }
        return result;
    }



    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> deleteStudent(@QueryParam("id") int id) {

        List<String> results=new ArrayList<>();

        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("p_student_id", id);
            param.put("p_result", null);
            param.put("p_message", null);
            sqlSession.delete("deleteStudentPr", param);
            results.add(param.get("p_result").toString());
            results.add(param.get("p_message").toString());
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return results;
    }

}
