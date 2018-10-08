package net.vv2.baby.domain;

import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-08 12:53
 **/
public class Baby {
    private Integer id;
    private Integer partent_id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date brithday;
    private String partent_name;





    public Baby(){}
    public Baby(String name) {
        this.name = name;
    }
    public Baby(String name, Date brithday) {
        this.name = name;
        this.brithday = brithday;
    }
    public Baby(String name,Integer partent_id,Date brithday) {
        this.name = name;
        this.partent_id = partent_id;
        this.brithday = brithday;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPartent_id() {
        return partent_id;
    }

    public String getName() {
        return name;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getPartent_name() {
        return partent_name;
    }

    public void setPartent_name(String partent_name) {
        this.partent_name = partent_name;
    }

    @Override
    public String toString() {
        return "baby{" +
                "id=" + id +
                ",partent_id="+partent_id+
                ", name='" + name + '\'' +
                ", brithday=" + brithday +
                '}';
    }

    /**
     * 获得孩子的年龄
     * @return int 年龄
     */
    public int getAge(){
        return DateUtil.ageOfNow(getBrithday());
    }


    /**
     * 返回出生天数
     * @return long
     */
    public long getBetweenDay(){

        return DateUtil.between(getBrithday(), new Date(), DateUnit.DAY);
    }



}
