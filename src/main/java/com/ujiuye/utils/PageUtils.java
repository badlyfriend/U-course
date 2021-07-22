package com.ujiuye.utils;

import com.ujiuye.pojo.User;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtils {

    private int pageCount;//每页显示的条数
    private int currentPage;//当前页
    private int sumCount;//总条数

    private int sumPage;//总页数
    private int index;//索引
    private int prePage;//上一页
    private int nextPage;//下一页
    //增加一个属性用来存储对应的list集合
    private List<User> userList;

    public PageUtils(int pageCount, int currentPage, int sumCount) {
        this.pageCount = pageCount;
        this.currentPage = currentPage;
        this.sumCount = sumCount;

        //初始化索引
        initIndex();
        //初始化总页数
        initSumPage();

        //初始化上一页
        initPrePage();
        //初始化下一页
        initNextPage();
    }

    //下一页
    private void initNextPage() {
        this.nextPage = currentPage == sumPage ? currentPage : currentPage + 1;
    }
    //上一页
    private void initPrePage() {
        this.prePage = currentPage == 1 ? 1 : currentPage - 1;
    }

    //初始化总页数
    private void initSumPage() {
        this.sumPage = sumCount / pageCount + (sumCount % pageCount == 0 ? 0 : 1);
    }

    //初始化索引
    private void initIndex() {
        this.index = (currentPage - 1) * pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "pageCount=" + pageCount +
                ", currentPage=" + currentPage +
                ", sumCount=" + sumCount +
                ", sumPage=" + sumPage +
                ", index=" + index +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", userList=" + userList +
                '}';
    }
}
