package com.wonders.tiles.query.report;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.wonders.tiles.query.report.tree.Cell;


public class Report {
    /** 交叉头 */
    private Cell crossHead;

    /** 行分组列表 */
    private List rowHeadList;

    /** 列分组列表 */
    private List colHeadList;

    /** 交叉数据列表 */
    private List crossDataList;

    /** 合计汇总 0不显示合计  1显示列合计 2显示行合计 3全部显示 */
    private String totalFlag;

    public String getTotalFlag() {
		return totalFlag;
	}

	public void setTotalFlag(String totalFlag) {
		this.totalFlag = totalFlag;
	}

	public Cell getCrossHead() {
        return crossHead;
    }

    public void setCrossHead(Cell crossHead) {
        this.crossHead = crossHead;
    }

    public List getRowHeadList() {
        return rowHeadList;
    }

    public void setRowHeadList(List rowHeadList) {
        this.rowHeadList = rowHeadList;
    }

    public List getColHeadList() {
        return colHeadList;
    }

    public void setColHeadList(List colHeadList) {
        this.colHeadList = colHeadList;
    }

    public List getCrossDataList() {
        return crossDataList;
    }

    public void setCrossDataList(List crossDataList) {
        this.crossDataList = crossDataList;
    }

    public String toHtml() {
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<table cellPadding='1' cellSpacing='1' width='90%' class='reporttable'>");

        // 1、表列头
        // 第一行第一个元素crosshead
        sbHtml.append("<tr>");
        sbHtml.append(
                "<td rowspan='" + crossHead.getRowSpan() + "' colspan='" + crossHead.getColSpan() + "' class='ch'>")
                .append(crossHead.getContent()).append("</td>");
        // 第一行其余元素colHeadList
        List colList = (List) colHeadList.get(0);
        Iterator iter = colList.iterator();
        while (iter.hasNext()) {
            Cell cell = (Cell) iter.next();
            if (!cell.isHidden())
                sbHtml.append("<td rowspan='" + cell.getRowSpan() + "' colspan='" + cell.getColSpan()
                                        + "' class='nh'>").append(cell.getContent()).append("</td>");
        }
        
        //行合计标题
        if("2".equals(totalFlag) || "3".equals(totalFlag)){
        sbHtml.append("<td rowspan='").append(crossHead.getRowSpan()).append("'  class='nh'>合计</td>");
        }
        
        sbHtml.append("</tr>");

        // 其余行
        int csize = colHeadList.size();
        for (int i = 1; i < csize; i++) {
            sbHtml.append("<tr>");
            colList = (List) colHeadList.get(i);
            iter = colList.iterator();
            while (iter.hasNext()) {
                Cell cell = (Cell) iter.next();
                if (!cell.isHidden())
                    sbHtml.append(
                            "<td rowspan='" + cell.getRowSpan() + "' colspan='" + cell.getColSpan() + "' class='nh'>")
                            .append(cell.getContent()).append("</td>");
            }
            sbHtml.append("</tr>");
        }
        
        // 2.表行头及数据
        int rsize = rowHeadList.size();
        for (int j = 0; j < rsize; j++) {
            sbHtml.append("<tr>");
            List rowList = (List) rowHeadList.get(j);
            List rowDateList = (List) crossDataList.get(j);

            // 行头描述
            for (int x = 0; x < rowList.size(); x++) {
                Cell cell = (Cell) rowList.get(x);
                if (!cell.isHidden())
                    sbHtml.append(
                            "<td rowspan='" + cell.getRowSpan() + "' colspan='" + cell.getColSpan() + "' class='nh'>")
                            .append(cell.getContent()).append("</td>");
            }
            // 行合计信息显示
            if("2".equals(totalFlag) || "3".equals(totalFlag)){
            	//行汇总值
            	BigDecimal rowTotal = new BigDecimal("0");
	            int rowspan=0;
	            for (int y = 0; y < rowDateList.size(); y++) {
	                Cell cell = (Cell) rowDateList.get(y);
	                if (!cell.isHidden()){
	                    sbHtml.append(
	                            "<td rowspan='" + cell.getRowSpan() + "' colspan='" + cell.getColSpan() + "' class='cd'>")
	                            .append(cell.getContent() == null ? "&nbsp;" : cell.getContent()).append("</td>");
	                    if(cell.getContent() != null) rowTotal = rowTotal.add(new BigDecimal((String)cell.getContent()));
	                    rowspan=cell.getRowSpan();
	                }
	
	            }
	            sbHtml.append("<td rowspan='").append(rowspan).append("'  class='cd'>"+rowTotal.toString()+"</td>");
            }else{//不显示行合计信息
            	 for (int y = 0; y < rowDateList.size(); y++) {
 	                Cell cell = (Cell) rowDateList.get(y);
 	                if (!cell.isHidden()){
 	                    sbHtml.append(
 	                            "<td rowspan='" + cell.getRowSpan() + "' colspan='" + cell.getColSpan() + "' class='cd'>")
 	                            .append(cell.getContent() == null ? "&nbsp;" : cell.getContent()).append("</td>");
 	                }
 	            }
            }
            sbHtml.append("</tr>");

        }
        //列合计
        if("1".equals(totalFlag) || "3".equals(totalFlag)){
	        //总计（行合计综合或列合计总和）
        	BigDecimal allTotal = new BigDecimal("0");
	        sbHtml.append("<tr>");
	        //列合计标题
	        sbHtml.append("<td colspan='").append(crossHead.getColSpan()).append("'  class='nh'>合计</td>");
	        //循环每一列
	        for(int i=0;i<colList.size();i++){
	        	//每一列汇总值
	        	BigDecimal colTotal = new BigDecimal("0");
	        	//列合并数量
	        	 int colspan=0;
	        	 for (int j = 0; j < rsize; j++) {
	             	List rowDateList = (List) crossDataList.get(j);
	             	Cell cell = (Cell) rowDateList.get(i);
	             	if(cell.getContent() != null) colTotal = colTotal.add(new BigDecimal((String)cell.getContent()));
	             	colspan = cell.getColSpan();
	             }
	        	 sbHtml.append("<td colspan='").append(colspan).append("'  class='cd'>"+colTotal.toString()+"</td>");
	        	 //计算行汇总
	             allTotal=allTotal.add(colTotal);
	        }
	        //设置总计值
	        if("3".equals(totalFlag)){
	        sbHtml.append("<td class='cd'>"+allTotal.toString()+"</td>");
	        }
	        sbHtml.append("</tr>");
        }
        
        sbHtml.append("</table>");
        return sbHtml.toString();
    }

    /**
     * 将行分组的第一个分组转变为数组
     * 
     * @return 分组数组
     */
    public String[] firstRowGroupArray() {
        int groupSize = rowHeadList.size();
        String[] groups = new String[groupSize];
        for (int i = 0; i < groupSize; i++) {
            List perList = (List) rowHeadList.get(i);
            Cell cell = (Cell) perList.get(0);// 每行第一个分组
            groups[i] = (String) cell.getContent();
        }

        return groups;
    }

    /**
     * 将列分组的第一个分组转变为数组
     * 
     * @return 分组数组
     */
    public String[] firstColGroupArray() {
        List colGroups = (List) colHeadList.get(0);// 分组第一行
        int groupSize = colGroups.size();

        String[] groups = new String[groupSize];
        for (int i = 0; i < groupSize; i++) {
            Cell cell = (Cell) colGroups.get(i);
            groups[i] = (String) cell.getContent();
        }

        return groups;
    }

    /**
     * 如果是一个交叉分组的时候，整理交叉部分的数据，以便图形展现
     * 
     * @return {{"g1",[21,22,23]},{"g2",[31,32,33]}...}
     */
    public LinkedHashMap firstCrossData() {
        LinkedHashMap data = new LinkedHashMap();

        // 如果列分组或行分组的多于1个则不可以生成数据，即返回数据为空
        if (colHeadList.size() > 1 || ((List) rowHeadList.get(0)).size() > 1) {
            return data;
        }

        String[] colGroup = firstColGroupArray();
        for (int i = 0; i < colGroup.length; i++) {
            // 行数即每一个列分组对应的行分组数据个数
            int dataSize = crossDataList.size();

            Double[] d = new Double[dataSize];
            for (int j = 0; j < dataSize; j++) {
                List rowList = (List) crossDataList.get(j);

                for (int m = 0; m < rowList.size(); m++) {
                    Cell cell = (Cell) rowList.get(m);

                    // 比较包含列分组描述的结果
                    String cp = (String) cell.getComparedKey();
                    if (cp.indexOf("#"+colGroup[i]+"#") >= 0) {
                        String c = (String) cell.getContent();
                        // 没有值时设为零
                        d[j] = (c == null ? new Double(0.00) : Double.valueOf(c));
                    }
                }
            }

            data.put(colGroup[i], d);
        }

        return data;
    }
}