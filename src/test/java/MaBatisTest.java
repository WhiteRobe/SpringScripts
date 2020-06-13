import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pers.model.domain.Teacher;
import pers.model.domain.TeacherMapper;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaBatisTest {
    @Test
    @SneakyThrows
    public void testMyBatisConfig(){
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        val result = sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher");
        result.forEach(System.out::println);

        System.out.println(sqlSession.getMapper(TeacherMapper.class).getById(3L));
    }


    @Test
    @SneakyThrows
    public void testInsert(){
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        @Cleanup SqlSession sqlSession = sqlSessionFactory.openSession();
        val result = sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher");
        result.forEach(System.out::println);

        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        teacherMapper.save(new Teacher(result.size()+1, "insert", 100, "testInsert@qq.com"));

        sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher").forEach(System.out::println);
        sqlSession.commit();
    }

    @Test
    @SneakyThrows
    public void testDelete(){
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        @Cleanup SqlSession sqlSession = sqlSessionFactory.openSession();
        val result = sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher");
        result.forEach(System.out::println);

        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        teacherMapper.delete(result.size());

        sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher").forEach(System.out::println);
        sqlSession.commit();
    }

    @Test
    @SneakyThrows
    public void testUpdate(){
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        @Cleanup SqlSession sqlSession = sqlSessionFactory.openSession();
        val result = sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher");
        result.forEach(System.out::println);

        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        teacherMapper.update(new Teacher(result.size(), "update", 55, "testUpate@qq.com"));

        sqlSession.selectList("pers.model.domain.TeacherMapper.getAllTeacher").forEach(System.out::println);
        sqlSession.commit();
    }

    @Test
    @SneakyThrows
    public void testMultiSelect(){
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        @Cleanup SqlSession sqlSession = sqlSessionFactory.openSession();
        Map<String, Object> param = new HashMap<String, Object>(){
            {
                put("id", 2);
                put("name", "Bill");
            }
        };

        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        teacherMapper.getByIdOrName(param).forEach(System.out::println);
        sqlSession.commit();
    }
}
