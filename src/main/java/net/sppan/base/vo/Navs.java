package net.sppan.base.vo;

import java.util.List;

public class Navs {
	private String title;
	private String icon;
	private String spread;
	private String href;

	private List<Navs> children;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<Navs> getChildren() {
		return children;
	}

	public void setChildren(List<Navs> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Navs [title=" + title + ", icon=" + icon + ", spread=" + spread
				+ ", href=" + href + ", children=" + children + "]";
	}
	
	

}
