/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.classify;

/**
 * 先验概率计算
 * <h3>先验概率计算</h3>
 * P(c<sub>j</sub>)=N(C=c<sub>j</sub>)<b>/</b>N <br>
 * 其中，N(C=c<sub>j</sub>)表示类别c<sub>j</sub>中的训练文本数量； N表示训练文本集总数量。
 */

public class PriorProbability {
	private TrainingDataManager tdm;

	public PriorProbability(TrainingDataManager tdm) {
		this.tdm = tdm;
	}
	
	/**
	 * 先验概率
	 * 
	 * @param c
	 *          给定的分类
	 * @return 给定条件下的先验概率
	 */
	public float calculatePc(String c) {
		float ret = 0F;
		float Nc = tdm.getTrainingFileCountOfClassification(c);
		float N = tdm.getTrainingFileCount();
		ret = Nc / N;
		return ret;
	}
}
