package cn.yhs.learn.domain;

import cn.yhs.learn.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.Product
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 11:34
 * @Description: 产品表
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productNum;
    private String productName;
    private String cityName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureTime; // todo 日期转换格式
    private String departureTimeStr;// 用于前端显示的
    private Double productPrice;
    private String productDesc;
    private Boolean productStatus;
    private String productStatusStr; // 用于前端显示

    public String getDepartureTimeStr() {
        return DateUtils.parseString(this.getDepartureTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getProductStatusStr() {
        return this.getProductStatus() ? "开启" : "关闭";
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", departureTime=" + departureTime +
                ", departureTimeStr='" + departureTimeStr + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }
}
