package com.fr.plugin;

import java.util.ArrayList;

/**
 * 
 * @author yanghui
 * 这是真正计算xirr算法的类，通过传进来的多条现金流进行计算xirr值和收益值
 */
public class XirrData {
	private static final double Max_Rate=99999.9;//最大收益率
	private static final double Min_Rate=-0.99999999;//最小收益率
	private static final double Critical=0.00000001;//精确值
	
	public static final int Error_Null_List=501;//代表传进来的list为空
	public static final int Error_Less_Cash=502;//少于一条现金流
	public static final int Error_Date=503;//传进来的现金流的第一条现金流记录的时间不是最早的时间
	public static final int Error_First_Payment=504;//第一条现金流的payment的值不为负
	
	
	/**
	 * 第一条现金流具体某个时间点的差值天数，这个天数应该是所有现金流里面的差值天数最大的
	 */
	private long startDays = 0;
	private ArrayList<UpbaaDate> listUpbaa;
	public XirrData(ArrayList<UpbaaDate> listUpbaa){		
		this.listUpbaa=listUpbaa;
		if (listUpbaa != null) {
			try {
				startDays = listUpbaa.get(0).getDaysFrom1970();
			} catch (Exception e) {
			}		
		}
	}
	/**
	 * 计算收益值 
	 * @return double
	 */
	public double getPal(){
		if(listUpbaa==null){
			return 0.0;
		}
		double pal=0;
		int count=listUpbaa.size();
		for (int i = 0; i < count; i++) {
			pal=pal+listUpbaa.get(i).payment;
		}
		return pal;
	}
	/**
	 * 通过传进来的多条现金流获得xirr值
	 * @return 返回收益率
	 */
	public double getXirr(double guess) {
		if(listUpbaa == null){
			return Error_Null_List;
		}
		int count=listUpbaa.size();
		if (count <= 1) {
			return Error_Less_Cash;// 如果只有一条现金流则返回Error_Less_Cash
		}
		
		if (listUpbaa.get(0).payment > 0) {
			return Error_First_Payment;
		}
		
		for (int i = 0; i < count; i++) {
			if (listUpbaa.get(1).getDaysFrom1970() < startDays) {
				return Error_Date;// 如果不止一条现金流则判断第一条现金流是否为时间最早的，如果不是的话则返回ERROR_DATE
			}
		}

		boolean isEarn = getXNPVByRate(0) > 0;// 记录是赚钱了还是亏本了

		if (isEarn) {
			return calXirr(0, Max_Rate,guess);
		} else {
			return calXirr(Min_Rate, 0,guess);
		}
	}

	private double calXirr(double min,double Max,double guess){
		int calculateCount = 50;
		double XIRR = guess;
		while (calculateCount > 0) {
			XIRR = (min + Max) / 2f;
			double xnvp = getXNPVByRate(XIRR);
			if (xnvp > 0) {
				min = XIRR;
			} else {
				Max = XIRR;
			}
			if (Math.abs(XIRR) < Critical) {
				break;
			}
			calculateCount--;
		}
		return XIRR;
	}

	private double getXNPVByRate(double rate) {
		double result = 0;
		int size = listUpbaa.size();
		for (int i = 0; i < size; i++) {
			UpbaaDate date = listUpbaa.get(i);
			result = result
					+ getOneValue(date.payment, rate, (int) date.getDaysFrom1970()
					- (int) startDays);
		}
		return result;
	}

	private double getOneValue(double payment, double rate, int dateDistance) {
		return payment / ((Math.pow((1 + rate), dateDistance / 365f)));
	}
}
