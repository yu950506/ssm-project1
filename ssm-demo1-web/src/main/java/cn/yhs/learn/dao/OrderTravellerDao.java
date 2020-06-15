package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Traveller;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.OrderTraveller
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/10 18:19
 * @Description: todo
 **/
@Repository("orderTravellerDao")
public interface OrderTravellerDao {
    /**
     * 根据订单id查询所有旅客id
     *
     * @param orderId
     * @return
     */
    @Select("select traveller_id from order_traveller where order_id = #{orderId}")
    @Results(id = "orderTravellerMap", value = {
            @Result(column = "traveller_id", property = "travellerId")
    })
    List<Integer> findByOrderId(Integer orderId);

    @Select("select t.* from order_traveller ot left join traveller t on ot.`traveller_id` = t.`id` where ot.`order_id` = #{orderId}")
    @Results(id = "travellerMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "phone_num", property = "phoneNum"),
            @Result(column = "credentials_type", property = "credentialsType"),
            @Result(column = "credentials_num", property = "credentialsNum"),
            @Result(column = "traveller_type", property = "travellerType"),

    })
    List<Traveller> findAllTravellerByOrderId(Integer orderId);

}
