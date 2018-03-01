package cn.qqtheme.framework.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 省份
 * <br/>
 * @author wangpf
 * @since 2017/02/28
 */
public class Province extends Area {
    private List<City> cities = new ArrayList<City>();

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}