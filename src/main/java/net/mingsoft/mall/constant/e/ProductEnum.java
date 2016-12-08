package net.mingsoft.mall.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

public enum ProductEnum  implements BaseEnum {
	/**
	 * 仓库 
	 */
	DEPOT_SHELF(2),
	
	/**
	 * 产品上架 
	 */
	ON_SHELF(1),
	/**
	 * 产品下架
	 */
	OFF_SHELF(0);

	ProductEnum(Object code) {
		this.code = code;
	}
	
	private Object code;
	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code+"");
	}
}
