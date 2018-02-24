package aop.advice;

import java.sql.SQLException;
import java.util.Date;

public class WorklogServiceImpl implements WorklogService {
    @Override
    public WorklogEntity selectWorklog(String worklogId) {
        System.out.println("Select worklog...");
        WorklogEntity worklogEntity = new WorklogEntity();
        worklogEntity.setWorklogId(worklogId);
        worklogEntity.setTitle("test-log");
        worklogEntity.setContent("This is a test log.");
        worklogEntity.setLogTime(new Date());
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return worklogEntity;
    }

    @Override
    public void addWorklog(WorklogEntity worklogEntity) {
        System.out.println("Add worklog...");
        System.out.println(worklogEntity);
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWorklog(WorklogEntity worklogEntity) throws SQLException {
        System.out.println("Update worklog...");
        try {
            Thread.currentThread().sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new SQLException("数据删除异常。");
    }

    @Override
    public void deleteWorklog(String worklogId) {
        System.out.println("Delete worklog...");
        try {
            Thread.currentThread().sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("运行异常");
    }
}
