package com.yc.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 采用单例模式读取配置文件
 * 源辰信息
 * @author navy
 * @2019年8月1日
 */
public class ReadPro extends Properties {
	private static final long serialVersionUID = -3559761464263983689L;
	
	/**
	 * 因为在类的静态方法中，只能访问类的静态成员，所以这个属性也必须定义为静态的
	 */
	private static ReadPro instance = new ReadPro();
	
	/**
	 * 构造方法私有化，这样调用者就不能去new这个对象
	 */
	private ReadPro() {
		// 去读取并解析配置文件db.properties
		InputStream is = null;
		
		try {
			is = ReadPro.class.getClassLoader().getResourceAsStream("db.properties");
			this.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 因为构造方法已经被私有化，所以调用者不能通过对象名来访问对象中的方法
	 * 所以我们必须提供一个公有的静态方法，让调用者通过类名访问次方法
	 * 来获取这个类的一个实例化对象
	 * @return
	 */
	public static ReadPro getInstance() {
		return instance;
	}	
}
