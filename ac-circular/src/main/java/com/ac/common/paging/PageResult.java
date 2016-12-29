package com.ac.common.paging;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ac.common.util.BeanUtils;

public class PageResult<T> {
	private int iTotalRecords;//总数目
	private int iTotalPage;//总页数
	private int iTotalDisplayRecords;//显示数
	private Integer pageNo;//当前页
	private List<Order> order;//排序方式
	private Map params;
	private Integer length;//每页显示多少条
	private List<T> aaData;//数据

	/**
	 * 解析datable格式的分页json字符串
	 * @param <T>
	 * @param page
	 * @return
	 */
	public static <T> PageResult<T> newInstance(String page)
	{
		Class clas= PageResult.class;
		JSONObject pageJson =JSONObject.fromObject(page) ;
		PageResult<T> reObj = null ;
		try {
			reObj = (PageResult<T>) clas.newInstance();
			Field[] fields = clas.getDeclaredFields();
			
			for(Field field:fields)
			{
				field.setAccessible(true);
				Object val = BeanUtils.getFieldValueFromJSONObject(field,pageJson);
				if(val == null)
					continue;
				field.set(reObj,val );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return reObj;
	}
	public String getMysqlPageSql(String sql)
	{
		int min = (this.pageNo-1)*this.length;
		int max =  min+this.length;
		StringBuilder pageSQL = new StringBuilder("select *  from (");
		pageSQL.append(sql);
		pageSQL.append(" ) t");
		pageSQL.append(this.createOrder());
		if(this.length>=0)
		{
			pageSQL.append(" limit ");
			pageSQL.append(min);
			pageSQL.append(" , ");
			pageSQL.append(this.length);	
		}
		return pageSQL.toString();
	}
	private String createOrder() {
		if(this.order==null||this.order.isEmpty())
			return " ";
		StringBuilder orderSql = new StringBuilder(" order by ");
		boolean isfirst  = true;
		for(Order ord :this.order)
		{
			if(isfirst)
			{
				isfirst = false;
			}
			else
			{
				orderSql.append(" ,");
			}
			orderSql.append(" ");
			orderSql.append(ord.getColumn());
			orderSql.append(" ");
			orderSql.append(ord.getDir());
		}
		return  orderSql.toString();
	}
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		
		//this.iTotalPage=this.iTotalRecords%this.length==0?this.iTotalRecords/this.length:this.iTotalRecords/this.length+1;
		this.aaData = aaData;
		this.iTotalRecords =
			length*pageNo < iTotalRecords ? length : (length*pageNo + iTotalRecords - length*pageNo);
	}
	public Map getParams() {
		return params;
	}
	public void setParams(Map params) {
		this.params = params;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public int getiTotalPage() {
		return iTotalPage;
	}
	public void setiTotalPage(int iTotalPage) {
		this.iTotalPage = iTotalPage;
	}
}
