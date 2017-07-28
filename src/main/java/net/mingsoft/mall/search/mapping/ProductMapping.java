package net.mingsoft.mall.search.mapping;

import org.springframework.data.elasticsearch.annotations.Document;

import net.mingsoft.base.elasticsearch.mapping.BaseMapping;

@Document(indexName="ms-mmall",type="product")
public class ProductMapping extends BaseMapping{
	private String titile;
	private double price;
	private String litPic;
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getLitPic() {
		return litPic;
	}
	public void setLitPic(String litPic) {
		this.litPic = litPic;
	}
	
}
