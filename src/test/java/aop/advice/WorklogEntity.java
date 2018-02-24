package aop.advice;

import common.bean.ToStringBean;

import java.util.Date;

public class WorklogEntity extends ToStringBean {
    private String worklogId;
    private String title;
    private String content;
    private Date logTime;

    public String getWorklogId() {
        return worklogId;
    }

    public void setWorklogId(String worklogId) {
        this.worklogId = worklogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
