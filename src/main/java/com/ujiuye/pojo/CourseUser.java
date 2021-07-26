package com.ujiuye.pojo;

/**
 * @Author Bob
 * @Create 2021-07-26-11:34
 */
public class CourseUser {
    private int id;
    private int cid;
    private int uid;

    private User user;
    private Course course;

    public CourseUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "id=" + id +
                ", cid=" + cid +
                ", uid=" + uid +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
