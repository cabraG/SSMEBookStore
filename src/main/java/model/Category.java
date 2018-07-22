package model;

import java.util.List;

/**
 * 分类模块的实体类
 * 
 * @author qdmmy6
 * 
 */
public class Category {
	private String cid;// 主键
	private String cname;// 分类名称
	private String pid;//父类id
	private String desc;// 分类描述
	private List<Category> children;//子类

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	private int orderBy;//排序


	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	@Override
	public String toString() {
		return "Category{cid:"+cid+
				",cname:"+cname+
				",pid:"+pid+
				",children:"+children+
				"}";
	}

}
