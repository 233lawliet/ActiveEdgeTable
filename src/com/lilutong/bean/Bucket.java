package com.lilutong.bean;

import java.util.ArrayList;
import java.util.List;

public class Bucket {

	Line line=null;
	
	
	public Line getLine() {
		return line;
	}


	public void setLine(Line line) {
		this.line = line;
	}

	



	//judge:对桶内 的两个line判断    进行小->大=true
	public boolean judge(List<Line> two) {
		List<Line> newTwo=new ArrayList<>();
		if(two.get(0).getX()<two.get(1).getX()) {
			return true;
		}else if(two.get(0).getX()==two.get(1).getX()){
			
			
			if(two.get(0).getDk()<=two.get(1).getDk()) {
				return true;
			}else {
				return false;
			}
			
			
		}else {
               return false;
		}
		
	}
	
	public void sortLine(){
	
		List<Line>  lines=new ArrayList<Line>();
		while(true) {
			if(this.line.getSonLine()!=null) {
				Line  line=new Line();
				line.setX(line.getX()+line.getDk());
				line.setY(line.getY()+1);
				line.setMaxY(line.getMaxY());
				line.setDk(line.getDk());
				line.setSonLine(null);
				this.line.setSonLine(this.line.sonLine.getSonLine());
			}else{
				break;
			}
		}
		
		
		
		for(int i=0;i<lines.size();i++) {
			for(int j=0;j<lines.size();j++) {
	               List<Line> two=new ArrayList<>();
	               two.add(lines.get(i));
	               two.add(lines.get(j));
	               if(this.judge(two)) {
	            	   
	               }else {
	            	   lines.set(i, two.get(j));
	            	   lines.set(j,two.get(i));
	               }
	               
			}
		}
		
		
		for(int i=lines.size()-1;i==0;i--) {
			lines.get(i).setSonLine(lines.get(i+1).getSonLine());
		}
		this.setLine(lines.get(0));
		
	}
	
	
	//递归查找最后的节点
	public void addLine(Line line) {
		if(this.line==null) {
			this.setLine(line);
		}else{
			this.line.addLine(line);
		}
	}


	@Override
	public String toString() {
		return "Bucket [line=" + line + "]";
	}


}
