package com.utadeo.miguelabarreram.simuladortransaccional.tools;

import java.io.Serializable;
import java.util.Iterator;

import org.springframework.stereotype.Controller;

@Controller
public class GenericJson implements Serializable {

	private static final long serialVersionUID = 6806539285618829436L;
	
	private String msg;
	private Object obj;
	private Iterable<?> ltObjs;
	private int length;
	
	public GenericJson() {
	}
	
	public GenericJson(String msg) {
		this.msg = msg;
	}
	
	public GenericJson(String msg, Object obj) {
		this.msg = msg;
		this.obj = obj;
	}
	
	public GenericJson(Object obj) {
		this.obj = obj;
	}
	
	public GenericJson(Iterable<?> ltObjs, int length) {
		this.ltObjs = ltObjs;
		this.length = length;
	}
	
	public GenericJson(Iterable<?> ltObjs) {
		this.ltObjs = ltObjs;
		if (null != ltObjs) 
			ltObjs.forEach(obj -> {this.length++;});
		else 
			this.length =0 ;
	}

	public GenericJson(String msg, Iterable<?> ltObjs, int length) {
		this.msg = msg;
		this.ltObjs = ltObjs;
		this.length = length;
	}
	
	public GenericJson(String msg, Iterable<?> ltObjs) {
		this.msg = msg;
		this.ltObjs = ltObjs;
		if (null != ltObjs) 
			ltObjs.forEach(obj -> {this.length++;});
		else 
			this.length =0 ;
	}
	
	public GenericJson(Object obj, Iterable<?> ltObjs, int length) {
		this.obj = obj;
		this.ltObjs = ltObjs;
		this.length = length;
	}
	
	public GenericJson(Object obj, Iterable<?> ltObjs) {
		this.obj = obj;
		this.ltObjs = ltObjs;
		if (null != ltObjs) 
			ltObjs.forEach(o -> {this.length++;});
		else 
			this.length =0 ;
	}
	
	public GenericJson(String msg, Object obj, Iterable<?> ltObjs, int length) {
		this.msg = msg;
		this.obj = obj;
		this.ltObjs = ltObjs;
		this.length = length;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Iterable<?> getLtObjs() {
		return ltObjs;
	}

	public void setLtObjs(Iterable<?> ltObjs) {
		this.ltObjs = ltObjs;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		String string = "Response{msg:" + (null != this.msg ? this.msg : "''") 
				+ ", obj:" + (null != this.obj ? this.obj.toString() : "''") + ",ltObjs: [";
		if (null != ltObjs) {
			Iterator<?> it = ltObjs.iterator();
			while (it.hasNext()) {
				Object next = it.next();
				if (null != next) 
					string += next.toString() + ",";
			}
		}
		string += "], length: " + this.length + "}";
		return string;
	}
}
