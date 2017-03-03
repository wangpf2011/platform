package cn.qqtheme.framework.entity;

/**
 * 区县
 * <br/>
 * @author wangpf
 * @since 2017/02/28
 */
public class County extends Area {
    private String cityId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}
