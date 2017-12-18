package com.chy.order.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月16日 下午2:17:54 
* 类说明 
*/
public enum PaymentStatusEnum {
	UNPAY(0,"未付款"),
	HALF_PAY(1,"部分付款"),
	PAYOVER(2,"已付款");
	private int status;
	private String statusStr;
	
	private PaymentStatusEnum(int status, String statusStr) {
		this.status = status;
		this.statusStr = statusStr;
	}
	
	public String getStatusStr() {
		return statusStr;
	}

	private static Map<Integer, PaymentStatusEnum> map = new HashMap<Integer, PaymentStatusEnum>();//将code和name存到map，用于根据code获取name
    private static List<PaymentStatusEnum> list = new ArrayList<PaymentStatusEnum>();
    static {
        for (PaymentStatusEnum spaceEnum : PaymentStatusEnum.values()) {
            map.put(spaceEnum.status, spaceEnum);
            list.add(spaceEnum);
        }
    }
    
    public static PaymentStatusEnum getEnumByStatus(int status) {
        return map.get(status);
    }
    

	public int getStatus() {
		return status;
	}
}
