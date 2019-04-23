package cn.gl.share_knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Article {
    @Id @GeneratedValue
    Long id;

    // 用户邮箱
    String email;
    // 文章标题
    String title;
    String prevTitle;

    // 文章内容路径
    String contentPath;

    // 文章发布事件
    LocalDate localDate;

    // 文章最后更新时间
    LocalDateTime updateTime;

    @Transient
    String content;



    public Article() {
    }

    public Article(String email, String title, String contentPath, LocalDate localDate) {
        this.email = email;
        this.title = title;
        this.contentPath = contentPath;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrevTitle() {
        return prevTitle;
    }

    public void setPrevTitle(String prevTitle) {
        this.prevTitle = prevTitle;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
