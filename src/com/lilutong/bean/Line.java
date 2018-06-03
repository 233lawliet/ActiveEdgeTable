package com.lilutong.bean;

public class Line {

	public double  x;
	public double  Y;
	public double  maxY;
	public double dk;
	public Line sonLine=null;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	
	
	public double getY() {
		return Y;
	}
	public void setY(double Y) {
		this.Y = Y;
	}
	public double getMaxY() {
		return maxY;
	}
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
	public double getDk() {
		return dk;
	}
	public void setDk(double dk) {
		this.dk = dk;
	}
	
	
	public Line getSonLine() {
		return sonLine;
	}
	public void setSonLine(Line sonLine) {
		this.sonLine = sonLine;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Line [x=" + String.format("%.2f", x) + ", Y=" + String.format("%.2f", Y) + ",maxY=" + String.format("%.2f", maxY) + ", dk=" + String.format("%.2f", dk) + ", sonLine=" + sonLine + "]";
	}
	//增加一个节点
	public void addLine(Line line) {
		this.getTailLine().setSonLine(line);
	}
	//获取桶的最后一个空节点
	public  Line getTailLine() {
		while(true) {
			if(this.getSonLine()==null) {
				return this;
			}else {
				return this.getSonLine().getTailLine();
			}
		}
	}
	
}
