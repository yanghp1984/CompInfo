package aop.advice;

import java.sql.SQLException;

public interface WorklogService {
    WorklogEntity selectWorklog(String worklogId);
    void addWorklog(WorklogEntity worklogEntity);
    void updateWorklog(WorklogEntity worklogEntity) throws SQLException;
    void deleteWorklog(String worklogId);
}
