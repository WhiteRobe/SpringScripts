package pers.model.domain;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    Teacher getById(Long id);
    int save(Teacher teacher);
    int update(Teacher teacher);
    int delete(Integer id);

    List<Teacher> getByIdOrName(Map<String, Object> param);
}
