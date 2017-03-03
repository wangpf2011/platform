package cn.qqtheme.framework.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 地市
 * <br/>
 * @author wangpf
 * @since 2017/02/28
 */
public class City extends Area {
    private String provinceId;
    private List<County> counties = new ArrayList<County>();

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }

}