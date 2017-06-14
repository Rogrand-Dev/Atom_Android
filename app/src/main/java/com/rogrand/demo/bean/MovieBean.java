package com.rogrand.demo.bean;

import com.google.gson.annotations.SerializedName;

public class MovieBean {

    /**
     * late : false
     * cnms : 0
     * sn : 0
     * showInfo : 今天101家影院放映1840场
     * pn : 225
     * img : http://p0.meituan.net/165.220/movie/ee5e691b425292f455c3eac5c628cfc7904509.png
     * sc : 8.9
     * ver : 2D/3D/IMAX 3D/中国巨幕/全景声
     * showDate :
     * src :
     * imax : true
     * snum : 192278
     * preSale : 0
     * vd :
     * dir : 乔阿吉姆·罗恩尼,艾斯彭·山德伯格
     * star : 约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹
     * cat : 喜剧,动作,奇幻
     * wish : 518761
     * 3d : true
     * scm : 亡灵要复仇，船长好发愁
     * rt : 2017-05-26上映
     * dur : 129
     * nm : 加勒比海盗5：死无对证
     * time :
     * id : 246012
     */

    private boolean late;
    private int cnms;
    private int sn;
    private String showInfo;
    private int pn;
    private String img;
    private double sc;
    private String ver;
    private String showDate;
    private String src;
    private boolean imax;
    private int snum;
    private int preSale;
    private String vd;
    private String dir;
    private String star;
    private String cat;
    private int wish;
    @SerializedName("3d")
    private boolean is3d;
    private String scm;
    private String rt;
    private int dur;
    private String nm;
    private String time;
    private int id;

    public boolean isLate() {
        return late;
    }

    public void setLate(boolean late) {
        this.late = late;
    }

    public int getCnms() {
        return cnms;
    }

    public void setCnms(int cnms) {
        this.cnms = cnms;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getSc() {
        return sc;
    }

    public void setSc(double sc) {
        this.sc = sc;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isImax() {
        return imax;
    }

    public void setImax(boolean imax) {
        this.imax = imax;
    }

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public int getPreSale() {
        return preSale;
    }

    public void setPreSale(int preSale) {
        this.preSale = preSale;
    }

    public String getVd() {
        return vd;
    }

    public void setVd(String vd) {
        this.vd = vd;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public int getWish() {
        return wish;
    }

    public void setWish(int wish) {
        this.wish = wish;
    }

    public boolean is3d() {
        return is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
