/**
The MIT License (MIT) * Copyright (c) 2016 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.mingsoft.mall.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.*;
import java.util.*;
import net.mingsoft.mall.entity.ColumnAttributeEntity;
import net.mingsoft.mall.biz.IColumnAttributeBiz;
import net.mingsoft.mall.dao.IColumnAttributeDao;

/**
 * 默认规格数据管理持久化层
 * @author 伍晶晶
 * @version 
 * 版本号：0.0<br/>
 * 创建日期：2017-8-15 14:29:39<br/>
 * 历史修订：<br/>
 */
 @Service("columnAttributeBizImpl")
public class ColumnAttributeBizImpl extends BaseBizImpl implements IColumnAttributeBiz {

	
	@Autowired
	private IColumnAttributeDao columnAttributeDao;
	
	
		@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return columnAttributeDao;
	}


	@Override
	public String queryByCategoryId(int categoryId) {
		ColumnAttributeEntity columnAttribute = new ColumnAttributeEntity();
		columnAttribute.setCaCategoryId(categoryId);
		List columnAttributeList = columnAttributeDao.query(columnAttribute);
		List<ColumnAttributeEntity> columnAttributeEntityList = new ArrayList<ColumnAttributeEntity>();
		List<Map> List = new ArrayList<Map>();
		for(int i = 0 ;i<columnAttributeList.size();i++){
			//转实体，获取规格名称
			ColumnAttributeEntity temp = (ColumnAttributeEntity) columnAttributeList.get(i);
			Map tempMap =new HashMap();
			tempMap.put("columnAttributeName", temp.getCaName());
			//切割默认值组成数组
			String[] defaultFields = temp.getCaFields().split(",");
			List<Map> field = new ArrayList<Map>();
			for(int j=0; j < defaultFields.length; j++){
				//获取默认规格参数，组成list
				Map tempDefaultField =new HashMap();
				tempDefaultField.put("columnAttributeDefaultField", defaultFields[j]);
				field.add(tempDefaultField);
			}
			tempMap.put("columnAttributeDefaultFields", field);
			List.add(tempMap);
		}
		String jsonStr = JSONArray.toJSONString(List);
		return jsonStr;
	} 
}