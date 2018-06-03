package com.lilutong.achieve;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.lilutong.bean.Bucket;
import com.lilutong.bean.Line;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Achieve {

	
	List<Bucket>  buckets=new ArrayList<>();   //Ͱ
	List<Line>   lines =new ArrayList<>();     //����
	List<Point>  points=new ArrayList<>();     //��ļ���
	
	Double  minY=null;
	Double  maxY=null;
	
	public boolean  readPoints() throws IOException {

		File file=new File("sources/points.txt");
		FileInputStream  fileInputStream=new FileInputStream(file);
		InputStreamReader  inputStreamReader  =new InputStreamReader(fileInputStream);
		BufferedReader  bufferedReader=new BufferedReader(inputStreamReader);
		
		String line="";
		String []  point=new  String[2];
		while(true) {
			line=bufferedReader.readLine();
            if(line!=null) {
            	point=line.split(",");
            	this.points.add(new Point(Integer.parseInt(point[0]),Integer.parseInt(point[1])));
            }else {
            	break;
            }
		}

        return false;
		
	}
	
	
	public  boolean getEdgeTable() {
		
		this.minY=this.points.get(0).getY();
		this.maxY=this.points.get(0).getY();


		
		for(int i=0;i<this.points.size();i++) {
			Point nowPoint=new Point(this.points.get(i));
			Point nextPoint=new Point(this.points.get(i==(this.points.size()-1)?0:i+1));
			//��ȡ���ֵ����Сֵ
			if(nowPoint.getY()>maxY) {
				maxY=nowPoint.getY();
			}else if(nowPoint.getY()<minY){
				minY=nowPoint.getY();
			}
			
			//��ȡ�߼���
	        Line  line=new Line();
			if(nowPoint.getY()>nextPoint.getY()) {
				line.setX(nextPoint.getX());
				line.setY(nextPoint.getY());
				line.setMaxY(nowPoint.getY());
				line.setDk((nowPoint.getY()-nextPoint.getY())/(nowPoint.getX()-nextPoint.getX()));
				lines.add(line);
			}else {
				line.setX(nowPoint.getX());
				line.setY(nowPoint.getY());
				line.setMaxY(nextPoint.getY());
				line.setDk((nowPoint.getX()-nextPoint.getX())/(nowPoint.getY()-nextPoint.getY()));
				lines.add(i, line);
			}
		}
		return false;
	}
	
	
   public void showLines() {
	   
	   
	   System.out.println("minY:"+minY+"maxY"+maxY);
	   
	   System.out.println("��ȡ�ı�:");
	   for(int i=0;i<lines.size();i++) {
		   System.out.println(lines.get(i).toString());
	   }
}
	
	//����Ͱ�Ľṹ
	//���߼���Ͱ��
	public void  getBucket() {
		for(double  i=minY;i<=maxY;i++) {
			buckets.add(new Bucket());
			for(int j=0;j<lines.size();j++) {
				if(i==lines.get(j).getY()) {
					//Ͱ��x���ߵ�x��ƥ��   Ͱ�������
					buckets.get((int)(i-minY)).addLine(lines.get(j));
				}else {
					//null
				}
			}
			
			
		}
	}
	
	
	//ʹͰ�����ݽ�������
	public void  finishBucket() {
		for(double i=minY-minY;i<maxY-minY;i++) {
			Line line=this.buckets.get((int)i).getLine();
			while(true) {
				if(line!=null) {
					//С�ڵ��ڼ�����һ��
					if(line.getMaxY()>(minY+i)) {
						Line  newLine=new Line();
						newLine.setX(line.getX()+line.getDk());
						newLine.setY(line.getY()+1);
						newLine.setMaxY(line.getMaxY());
						newLine.setDk(line.getDk());
						buckets.get((int)(i+1)).addLine(newLine);
						//this.buckets.get((int)i+1).addLine(newLine);
					}else {
						//null
					}
				}else if(line==null) {
					//buckets.get((int)i+1).sortLine();
					break;
				}
				line=line.getSonLine();
			}
		}
		
	}
	
	
	
	public void showBuckets() {
	
		System.out.println("��Ч�߱��㷨����");
		for(double i=minY-minY;i<=maxY-minY;i++) {
			System.out.println(this.buckets.get((int)i).toString());
		}
	}
	
	
	public static void  main(String args[]) throws IOException {
		
		Achieve achieve=new Achieve();
		achieve.readPoints();
		
		achieve.getEdgeTable();
		achieve.showLines();
		achieve.getBucket();
		//achieve.showBuckets();
		achieve.finishBucket();
		achieve.showBuckets();
	}
	
	
}
