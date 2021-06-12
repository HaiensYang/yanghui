package com.fr.plugin;

import com.fr.general.FArray;
import com.fr.general.GeneralUtils;
import com.fr.intelli.record.Focus;
import com.fr.intelli.record.Original;
import com.fr.plugin.context.PluginContexts;
import com.fr.record.analyzer.EnableMetrics;
import com.fr.script.AbstractFunction;
import com.fr.stable.StringUtils;
import com.fr.stable.exception.FormulaException;
import com.fr.stable.fun.Authorize;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @file: PACKAGE_NAME.XIRR
 * @desc:
 * @date:2020/7/7 17:41
 * @author:yanghui
 **/
@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class XIRR extends AbstractFunction {

	private static final String ERROR_VALUE = "#NUM!";

	@Override
	@Focus(id = "com.fr.plugin.function.finance", text = "Plugin-Test_Function_Finance", source = Original.PLUGIN)
	public Object run(Object[] objects) throws FormulaException {
		if (PluginContexts.currentContext().isAvailable()) {
			return cal(objects);
		} else {
			return "插件未激活，请购买使用";
		}
	}

	private Object cal(Object[] objects) {
		try{
			double guess = 0.1;
			if (objects.length == 3) {
				guess = trans(objects[2]).doubleValue();
			}
			if (objects.length > 3) {
				FArray<BigDecimal> cash = new FArray<BigDecimal>();
				FArray<String> date = new FArray<>();
				for (int i = 0; i < objects.length; i++) {
					if(objects[i] == null|| StringUtils.isBlank(objects[i].toString())){
						continue;
					}
					String trim = GeneralUtils.objectToString(objects[i]).trim();
					int start = trim.indexOf("-")>trim.indexOf("/")?trim.indexOf("-"):trim.indexOf("/");
					if (start > 0) {
						date.add(trim);
					}else{
						BigDecimal var = new BigDecimal(trim);
						if ((var.compareTo(BigDecimal.ONE) <1) && (var.compareTo(BigDecimal.ZERO)>-1)) {
							guess = var.doubleValue();
						}else {
							cash.add(var);
						}
					}
				}
				return run(cash, date, guess);
			}
			return run( transArr( (FArray) objects[0] ), transToString((FArray)objects[1]),guess);
		}catch(Exception e){
			System.out.println(e);
		}
		return ERROR_VALUE;
	}

	/**
	 * 将数组转换为大数数组
	 * @param in
	 * @return
	 */
	private static FArray<BigDecimal> transArr(FArray in){
		FArray<BigDecimal> rt = new FArray<BigDecimal>();
		for(int i=0;i<in.length();i++){
			Object ele = in.elementAt(i);
			if(ele == null|| StringUtils.isBlank(ele.toString())){
				continue;
			}

			rt.add(trans(ele));
		}
		return rt;
	}

	/**
	 * 将其他类型的数字转换为大数（保证精度）
	 * @param ele
	 * @return
	 */
	private static BigDecimal trans(Object ele){
		try{
			String val = GeneralUtils.objectToString(ele);
			return new BigDecimal(val.trim());
		}catch(Exception e){

		}
		return (BigDecimal) ele;
	}

	/**
	 * 将其他类型转换成String
	 * @param in
	 * @return
	 */
	private static FArray<String> transToString(FArray in){
		FArray<String> rt = new FArray<>();
		for(int i=0;i<in.length();i++){
			Object ele = in.elementAt(i);
			if(ele == null|| StringUtils.isBlank(ele.toString())){
				continue;
			}
			rt.add(GeneralUtils.objectToString(ele));
		}
		return  rt;
	}

	private static double run(FArray<BigDecimal> cashflow, FArray<String> dueDate,double guess){

		ArrayList<UpbaaDate> list=new ArrayList<UpbaaDate>();
		for(int i=0;i<cashflow.length();i++){
			UpbaaDate  date=new UpbaaDate(dueDate.elementAt(i),cashflow.elementAt(i).doubleValue());
			list.add(date);
		}
		XirrData Xirr=new XirrData(list);
		return Xirr.getXirr(guess);

	}

}
