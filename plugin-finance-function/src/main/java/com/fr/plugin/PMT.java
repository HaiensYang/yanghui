package com.fr.plugin;

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

/**
 * @file: PACKAGE_NAME.PMT
 * @desc:
 * @date:2020/7/7 17:06
 * @author:yanghui
 **/
@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class PMT extends AbstractFunction {
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
		//年利率
		double rate = trans(objects[0]).doubleValue();
		//贷款期数
		double nper = trans(objects[1]).doubleValue();
		//贷款金额
		double pv= trans(objects[2]).doubleValue();

		if (objects.length == 3) {
			return calculatePMT(rate, nper, pv, 0, 0);
		}
		else if (objects.length == 4) {
			//fv为未来值（余值），或在最后一次付款后希望得到的现金余额，如果省略fv，则假设其值为零，也就是一笔贷款的未来值为零
			double fv = trans(objects[3]).doubleValue();
			return calculatePMT(rate, nper, pv, fv, 0);
		}else if(objects.length == 5){
			//fv为未来值（余值），或在最后一次付款后希望得到的现金余额，如果省略fv，则假设其值为零，也就是一笔贷款的未来值为零
			double fv = trans(objects[3]).doubleValue();
			//Type数字0或1，用以指定各期的付款时间是在期初还是期末。1代表期初（先付：每期的第一天付），不输入或输入0代表期末（后付：每期的最后一天付）
			int type=Integer.valueOf(objects[4].toString());
			return calculatePMT(rate,nper,pv,fv,type);
		}else {
			throw new RuntimeException("输入参数个数有误");
		}
	}

	private static BigDecimal trans(Object ele){
		if (ele == null|| StringUtils.isBlank(ele.toString())) {
			return new BigDecimal("0");
		}
		try{
			String val = GeneralUtils.objectToString(ele);
			return new BigDecimal(val.trim());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param rate
	 * @param nper
	 * @param pv
	 * @return
	 */
	public static double calculatePMT(double rate, double nper, double pv,double fv, int type) {
		return -rate * (fv + pv * Math.pow(1 + rate, nper)) / ((Math.pow(1 + rate, nper) - 1) * (1 + rate * type));
	}
}
