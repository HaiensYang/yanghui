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
 * @file: PACKAGE_NAME.Rate
 * @desc:
 * @date:2020/7/7 17:13
 * @author:yanghui
 **/
@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class Rate extends AbstractFunction {
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
		//总期数
		int nper = trans(objects[0]).intValue();
		//每期付款金额
		double pmt= trans(objects[1]).doubleValue();
		//付款总金额
		double pv= trans(objects[2]).doubleValue();
		double fv = 0D;
		double type = 0D;
		double guess = 0.1D;
		if(objects.length==3){
			return simpleCalculateRate(nper,pmt,pv);
		} else if (objects.length == 4) {
			fv = trans(objects[3]).doubleValue();
			return calculateRate(nper,pmt,pv,fv,type,guess);
		} else if (objects.length == 5) {
			fv = trans(objects[3]).doubleValue();
			type=trans(objects[4]).doubleValue();
			return calculateRate(nper,pmt,pv,fv,type,guess);
		} else if (objects.length == 6) {
			fv = trans(objects[3]).doubleValue();
			type=trans(objects[4]).doubleValue();
			guess=trans(objects[5]).doubleValue();
			return calculateRate(nper,pmt,pv,fv,type,guess);
		}else {
			throw new RuntimeException("输入参数格式有误");
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

	public double calculateRate(int nper, double pmt, double pv, double fv, double type, double guess) {
		//FROM MS http://office.microsoft.com/en-us/excel-help/rate-HP005209232.aspx
		int FINANCIAL_MAX_ITERATIONS = 20;//Bet accuracy with 128
		double FINANCIAL_PRECISION = 0.0000001;//1.0e-8

		double y, y0, y1, x0, x1 = 0, f = 0, i = 0;
		double rate = guess;
		if (Math.abs(rate) < FINANCIAL_PRECISION) {
			y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
		} else {
			f = Math.exp(nper * Math.log(1 + rate));
			y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
		}
		y0 = pv + pmt * nper + fv;
		y1 = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;

		// find root by Newton secant method
		i = x0 = 0.0;
		x1 = rate;
		while ((Math.abs(y0 - y1) > FINANCIAL_PRECISION) && (i < FINANCIAL_MAX_ITERATIONS)) {
			rate = (y1 * x0 - y0 * x1) / (y1 - y0);
			x0 = x1;
			x1 = rate;

			if (Math.abs(rate) < FINANCIAL_PRECISION) {
				y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
			} else {
				f = Math.exp(nper * Math.log(1 + rate));
				y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
			}

			y0 = y1;
			y1 = y;
			++i;
		}
		return rate;

	}

	public double simpleCalculateRate(int nper, double pmt, double pv) {

		double fv = 0;

		//0或省略-期末支付
		double type = 0;

		//如果省略预期利率，则假设该值为 10%。
		double guess = 0.1;

		return calculateRate(nper, pmt, pv, fv, type, guess);
	}



}