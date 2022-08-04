package com.qingqiao.utils;

public class PageUtil {
    private Integer totalPage;//总页数 总条数/分页单位%2 == 0?总条数/分页单位:总条数/分页单位+1
    private Integer pageCount;// 分页单位 每页展示多少条数据
    private Integer currentPage;// 当前页
    private Integer nextPage;// 下一页 currentPage+1
    private Integer prevPage;// 上一页 currentPage-1
    private Integer count;// 总条数
    private Integer startIndex;// 分页起始位置 (currentPage-1)*pageCount
    // select * from user limit ?,?
    // ps.setInt(1,pageUtil.getStartIndex());
    // ps.setInt(2,pageUtil.getPageCount());

    public PageUtil(Integer pageCount, String currentPage, Integer count) {
        this.pageCount = pageCount;
        if(currentPage == null || "".equals(currentPage)){
            this.currentPage = 1;
        }else{
            this.currentPage = Integer.parseInt(currentPage);
        }
        this.count = count;
        init();
    }

    private void init() {
        // 初始化
        initTotalPage();
        initStartIndex();
        initNextPage();
        initPrevPage();
    }

    private void initStartIndex() {
        this.startIndex = (currentPage - 1) * pageCount;
    }

    private void initTotalPage() {
        //总条数/分页单位 == 0?总条数/分页单位:总条数/分页单位+1
        this.totalPage = count / pageCount + (count % pageCount == 0 ? 0 : 1);
    }

    private void initPrevPage() {
        if(this.currentPage == 1){
            this.prevPage = 1;
        }else{
            this.prevPage = this.currentPage - 1;
        }
    }

    private void initNextPage() {
        if(this.currentPage < this.totalPage){
            this.nextPage = this.currentPage + 1;
        }else{
            this.nextPage = this.totalPage;
        }
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "totalPage=" + totalPage +
                ", pageCount=" + pageCount +
                ", currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                ", prevPage=" + prevPage +
                ", count=" + count +
                ", startIndex=" + startIndex +
                '}';
    }
}
