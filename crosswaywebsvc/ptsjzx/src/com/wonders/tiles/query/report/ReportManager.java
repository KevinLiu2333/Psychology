package com.wonders.tiles.query.report;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wonders.tiles.query.report.tree.Cell;
import com.wonders.tiles.query.report.tree.CellTree;


public class ReportManager {
    /**
     * 生成报表
     * 
     * @param crossName
     * @param colGroupList
     * @param rowGroupList
     * @param dataList
     * @return
     */
    public static Report toReport(String crossName, List colGroupList, List rowGroupList, List dataList,String totalFlag) {
        Report report = new Report();

        // 所有分组树
        CellTree ctAll = new CellTree();

        // 1、列分组
        CellTree ctc = new CellTree();
        Iterator iterCol = colGroupList.iterator();
        while (iterCol.hasNext()) {
            String[] atmp = (String[]) iterCol.next();
            ctc.addNodeArray(atmp);

            ctAll.addNodeArray(atmp);
        }
        List colHeadList = ctc.toGroupHeadList(ReportConstants.COLUMN_HEAD);
        report.setColHeadList(colHeadList);

        // 2、行分组
        CellTree ctr = new CellTree();
        Iterator iterRow = rowGroupList.iterator();
        while (iterRow.hasNext()) {
            String[] atmp = (String[]) iterRow.next();
            ctr.addNodeArray(atmp);

            ctAll.addNodeArray(atmp);
        }
        List rowHeadList = ctr.toGroupHeadList(ReportConstants.ROW_HEAD);
        report.setRowHeadList(rowHeadList);

        // 3、交叉头
        Cell crossHead = new Cell();
        crossHead.setRowSpan(ctc.getLevel());
        crossHead.setColSpan(ctr.getLevel());
        crossHead.setContent(crossName);
        report.setCrossHead(crossHead);

        // 4、交叉部分的数据
        List crossDataListInLine = ctAll.getCrossDataCells();

        // 交叉数据整理-对应填入统计数据
        for (int i = 0; i < crossDataListInLine.size(); i++) {
            Cell cell = (Cell) crossDataListInLine.get(i);

            for (int j = 0; j < dataList.size(); j++) {
                String data = (String) dataList.get(j);
                if (data.startsWith(cell.getComparedKey())) {
                    Object dataValue = data.substring(data.lastIndexOf(ReportConstants.DATA_SEPARATOR)
                            + ReportConstants.DATA_SEPARATOR.length());
                    cell.setContent(dataValue);

                    dataList.remove(j);
                    break;
                }
            }
        }

        int rowHeadSize = ctr.getLeafSize();// 行数
        int colHeadSize = ctc.getLeafSize();// 列数

        List crossDataList = new ArrayList();
        for (int x = 0; x < rowHeadSize; x++) {
            List rowDataList = new ArrayList();

            for (int y = 0; y < colHeadSize; y++)
                rowDataList.add(crossDataListInLine.get(x + y * rowHeadSize));// 注意算法

            crossDataList.add(rowDataList);
        }
        report.setCrossDataList(crossDataList);
        //合计汇总 0不显示合计  1显示列合计 2显示行合计 3全部显示 
        report.setTotalFlag(totalFlag);

        return report;
    }

    /**
     * 根据xml文件生成报表
     * 
     * @param reportXml
     * @return
     */
    public static Report toReport(String reportXml) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new StringReader(reportXml));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 初始化临时变量
        String crossName = null;

        List colGroupList = new ArrayList();
        List rowGroupList = new ArrayList();
        List dataList = new ArrayList();

        Element el = null;

        // 交叉部分命名
        Attribute attribute = (Attribute) document.selectSingleNode(ReportConstants.XML_CROSS_NAME);
        crossName = (String) attribute.getData();

        // 行分组
        Iterator iter = document.selectNodes(ReportConstants.XML_ROW_GROUP).iterator();
        while (iter.hasNext()) {
            el = (Element) iter.next();
            rowGroupList.add(((String) el.getData()).split(ReportConstants.DATA_SEPARATOR));
        }

        // 列分组
        iter = document.selectNodes(ReportConstants.XML_COLUMN_GROUP).iterator();
        while (iter.hasNext()) {
            el = (Element) iter.next();
            colGroupList.add(((String) el.getData()).split(ReportConstants.DATA_SEPARATOR));
        }

        // 交叉数据
        iter = document.selectNodes(ReportConstants.XML_DATA).iterator();
        while (iter.hasNext()) {
            el = (Element) iter.next();
            dataList.add(el.getData());
        }
        // 汇总标识
        Attribute totalAttribute = (Attribute) document.selectSingleNode(ReportConstants.XML_TOTAL_FLAG);
        String totalFlag = (String) totalAttribute.getData();
        return ReportManager.toReport(crossName, colGroupList, rowGroupList, dataList,totalFlag);
    }

    public static void main(String[] args) {
        ReportManager.test2();
    }

    private static void test1() {
        String[] s1 = { "地面价", "楼地价" };
        String[] s2 = { "最高值", "最低值", "平均值" };

        String[] s3 = { "宝山区", "长宁区", "虹口区", "黄浦区", "嘉定区", "静安区", "卢湾区" };

        String d1 = "#地面价#最高值#宝山区#29424";
        String d2 = "#楼地价#平均值#虹口区#19567";

        List colGroupList = new ArrayList();
        colGroupList.add(s1);
        colGroupList.add(s2);

        List rowGroupList = new ArrayList();
        rowGroupList.add(s3);

        List dataList = new ArrayList();
        dataList.add(d1);
        dataList.add(d2);

        Report report = ReportManager.toReport("数量", colGroupList, rowGroupList, dataList,"3");

        System.out.println(report.toHtml());
    }

    private static void test2() {
        String reportXml = "<group_report cross_name=''><row_group name='楼栋编号'>300001795#300001796#300001804</row_group><row_group name='户型'>1#7#8#9</row_group><column_group name='所在的楼层'>2#3#4#5#6#7#8#17#18#19#20#21#22#23#24#25#30#31#32</column_group><column_group name=''>实测面积#预测面积</column_group><data>#3#300001795#1#实测面积#217.47</data><data>#4#300001795#1#实测面积#137.38</data><data>#5#300001795#1#实测面积#252.98</data><data>#6#300001795#1#实测面积#80.09</data><data>#7#300001795#1#实测面积#115.6</data><data>#8#300001795#1#实测面积#195.69</data><data>#2#300001795#8#实测面积#327.24</data><data>#3#300001795#8#实测面积#115.6</data><data>#4#300001795#8#实测面积#195.69</data><data>#5#300001795#8#实测面积#80.09</data><data>#6#300001795#8#实测面积#252.98</data><data>#7#300001795#8#实测面积#217.47</data><data>#8#300001795#8#实测面积#137.38</data><data>#17#300001796#1#实测面积#80.09</data><data>#18#300001796#1#实测面积#252.98</data><data>#19#300001796#1#实测面积#252.98</data><data>#20#300001796#1#实测面积#333.07</data><data>#21#300001796#1#实测面积#333.07</data><data>#22#300001796#1#实测面积#330.09</data><data>#23#300001796#1#实测面积#250</data><data>#24#300001796#1#实测面积#330.09</data><data>#25#300001796#1#实测面积#330.09</data><data>#19#300001796#7#实测面积#80.09</data><data>#17#300001796#8#实测面积#137.38</data><data>#18#300001796#8#实测面积#80.09</data><data>#30#300001804#1#实测面积#134.4</data><data>#31#300001804#1#实测面积#441.16</data><data>#32#300001804#9#实测面积#183.94</data><data>#3#300001795#1#预测面积#217.44</data><data>#4#300001795#1#预测面积#137.36</data><data>#5#300001795#1#预测面积#252.95</data><data>#6#300001795#1#预测面积#80.08</data><data>#7#300001795#1#预测面积#115.59</data><data>#8#300001795#1#预测面积#195.67</data><data>#2#300001795#8#预测面积#327.19</data><data>#3#300001795#8#预测面积#115.59</data><data>#4#300001795#8#预测面积#195.67</data><data>#5#300001795#8#预测面积#80.08</data><data>#6#300001795#8#预测面积#252.95</data><data>#7#300001795#8#预测面积#217.44</data><data>#8#300001795#8#预测面积#137.36</data><data>#17#300001796#1#预测面积#80.08</data><data>#18#300001796#1#预测面积#252.95</data><data>#19#300001796#1#预测面积#252.95</data><data>#20#300001796#1#预测面积#333.03</data><data>#21#300001796#1#预测面积#333.03</data><data>#22#300001796#1#预测面积#330.06</data><data>#23#300001796#1#预测面积#249.98</data><data>#24#300001796#1#预测面积#330.06</data><data>#25#300001796#1#预测面积#330.06</data><data>#19#300001796#7#预测面积#80.08</data><data>#17#300001796#8#预测面积#137.36</data><data>#18#300001796#8#预测面积#80.08</data><data>#30#300001804#1#预测面积#134.39</data><data>#31#300001804#1#预测面积#441.11</data><data>#32#300001804#9#预测面积#183.91</data></group_report>";

        Report report = ReportManager.toReport(reportXml);

        System.out.println(report.toHtml());
    }
}
