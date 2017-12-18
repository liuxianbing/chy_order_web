package com.chy.order.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月16日 下午2:03:58 
* 类说明 
*/
public enum OrderStatusEnum {

	AUDIT_SALE(0,"销售审核"),
	CONFIRM_CLIENT(1,"客户确认"),
	UP_PAY(2,"上传支付凭证"),
	FIANCE_AUDIT(3,"财务审核"),
	DO_MAKING(4,"生产中"),
	SENDED(5,"发货"),
	RECEIVED(6,"已送达");
	
	private int status;
	private String statusStr;
	
	private OrderStatusEnum(int status, String statusStr) {
		this.status = status;
		this.statusStr = statusStr;
	}
	
	public String getStatusStr() {
		return statusStr;
	}

	private static Map<Integer, OrderStatusEnum> map = new HashMap<Integer, OrderStatusEnum>();//将code和name存到map，用于根据code获取name
    private static List<OrderStatusEnum> list = new ArrayList<OrderStatusEnum>();
    static {
        for (OrderStatusEnum spaceEnum : OrderStatusEnum.values()) {
            map.put(spaceEnum.status, spaceEnum);
            list.add(spaceEnum);
        }
    }
    
    public static OrderStatusEnum getEnumByStatus(int status) {
        return map.get(status);
    }
    

	public int getStatus() {
		return status;
	}
}
