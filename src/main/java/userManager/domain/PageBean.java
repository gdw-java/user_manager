package userManager.domain;

import java.util.List;

public class PageBean {
    private List<User> userData; //每页的数据
    private int totalPage;  //总页数
    private int curPage;     //当前页
    private int pageSize;   //每页显示的数据条数
    private int count;  //数据库数据总条数
    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStartIndex(){
        return (curPage-1)*pageSize;
    }
    public List<User> getUserData() {
        return userData;
    }

    public void setUserData(List<User> userData) {
        this.userData = userData;
    }

    public int getTotalPage() {
        totalPage=(int)Math.ceil(count*1.0/pageSize);
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
            this.curPage = curPage;
            //在这里设置起始页和结束页，如果总页数小于8，就只显示有几页，
            //如果大于8，最开始显示的页码就是1-8
            if(totalPage<=8){
                start=1;
                end=totalPage;
            }else{
                start=curPage-4;
                end=curPage+3;
                if(start<=0){
                    start=1;
                    end=8;
                }
                if(end>totalPage){
                    start=totalPage-8;
                    end=totalPage;
                }
            }


    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
