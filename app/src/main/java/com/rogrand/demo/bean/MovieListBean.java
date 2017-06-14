package com.rogrand.demo.bean;

import java.util.List;

public class MovieListBean {

    /**
     * total : 100
     * page : 1
     * count : 20
     * movies : []
     */

    private int total;
    private int page;
    private int count;
    private List<MovieBean> movies;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MovieBean> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieBean> movies) {
        this.movies = movies;
    }

}
