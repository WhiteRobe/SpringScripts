import lombok.SneakyThrows;
import lombok.val;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pers.model.domain.TeacherMapper;

import java.io.Reader;

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
}
