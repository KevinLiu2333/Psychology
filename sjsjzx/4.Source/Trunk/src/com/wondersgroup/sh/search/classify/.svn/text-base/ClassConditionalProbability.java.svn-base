/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.classify;

/**
 * <b>类</b>条件概率计算
 * 
 * <h3>类条件概率</h3>
 * P(x<sub>j</sub>|c<sub>j</sub>)=( N(X=x<sub>i</sub>, C=c<sub>j
 * </sub>)+1 ) <b>/</b> ( N(C=c<sub>j</sub>)+M+V ) <br>
 * 其中，N(X=x<sub>i</sub>, C=c<sub>j</sub>）表示类别c<sub>j</sub>中包含属性x<sub> i</sub>的训练文本数量；N(C=c<sub>j</sub>)表示类别c<sub>j</sub>中的训练文本数量；M值用于避免
 * N(X=x<sub>i</sub>, C=c<sub>j</sub>）过小所引发的问题；V表示类别的总数。
 * 
 * <h3>条件概率</h3>
 * <b>定义</b> 设A, B是两个事件，且P(A)>0 称<br>
 * <tt>P(B∣A)=P(AB)/P(A)</tt><br>
 * 为在条件A下发生的条件事件B发生的条件概率。
 * 
 */

public class ClassConditionalProbability {
	private static final float M = 0F;
	private TrainingDataManager tdm;

	public ClassConditionalProbability(TrainingDataManager tdm) {
		this.tdm = tdm;
	}
	
	/**
	 * 计算类条件概率
	 * 
	 * @param x
	 *          给定的文本属性
	 * @param c
	 *          给定的分类
	 * @return 给定条件下的类条件概率
	 */
	public float calculatePxc(String x, String c) {
		float ret = 0F;
		float Nxc = tdm.getCountContainKeyOfClassification(c, x);
		float Nc = tdm.getTrainingFileCountOfClassification(c);
		float V = tdm.getTraningClassifications().length;
		ret = (Nxc + 1) / (Nc + M + V);
		return ret;
	}
}
