package cn.yhs.learn.domain;

import cn.yhs.learn.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.Orders
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 23:39
 * @Description: 订单表
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;
    private String orderNum;

    private Date orderTime;
    private String orderTimeStr;

    private int orderStatus;
    private String orderStatusStr;

    private Integer payType;
    private String payTypeStr;

    private String orderDesc;

    private int peopleCount;
    private List<Traveller> travellers; // 1：N ,封装的是游客信息

    private Integer productId;
    private Product product;// 1：1  产品信息

    private Integer memberId;
    private Member member;// 1:1  会员信息

    public String getOrderStatusStr() {
        return this.getOrderStatus() == 0 ? "未支付" : "已支付";
    }

    public String getPayTypeStr() {
        if (0 == payType) {
            return "支付宝";
        } else if (1 == payType) {
            return "微信";
        } else {
            return "其他";
        }
    }

    public String getOrderTimeStr() {
        return DateUtils.parseString(this.getOrderTime());
    }

}
