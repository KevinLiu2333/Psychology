package com.wonders.tiles.query.report.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wonders.tiles.query.report.ReportConstants;
import com.wonders.util.DeepCloneUtil;

public class CellTree {
    // 节点容器
    private List nodeList = new ArrayList();

    // 树的层次
    private int level = 0;

    public int getLevel() {
        return level;
    }

    // 节点编号生成器
    private CellKeyGen keygen = new CellKeyGen();

    // 加入一个节点
    private void addNode(String fid, Object o) {
        if (o != null) {
            // 构造节点
            Cell node = new Cell();
            node.setContent(o);
            node.setLevel(level);
            node.setLeaf(true);

            // 获得id
            String key = keygen.getNextKey();
            // 设置节点id
            node.setId(key);
            node.setFid(fid);

            nodeList.add(node);
        }
    }

    // 由数组构建节点树
    public void addNodeArray(Object[] o) {
        level++;

        if (nodeList.size() == 0) {
            // 第一层节点，fid为0
            for (int i = 0; i < o.length; i++) {
                addNode("0", o[i]);
            }
        } else {
            // ********其他层节点，加入步骤：*************//
            // 1、取出所有的叶节点，作为父节点
            // 2、取出父节点的id
            // 3、循环加入新的节点，将父节点的id作为本节点的fid
            // 4、改变父节点的isleaf为false
            // ******************end*****************//
            ArrayList list = getLeafNodes();
            for (int j = 0; j < list.size(); j++) {
                Cell fNode = (Cell) list.get(j);
                // 加入字节点个数
                fNode.setSize(o.length);

                String fid = fNode.getId();
                for (int k = 0; k < o.length; k++) {
                    addNode(fid, o[k]);
                }

                fNode.setLeaf(false);
            }
        }
    }

    // 取出所有交叉数据节点节点
    public ArrayList getCrossDataCells() {
        ArrayList crossDataList = new ArrayList();

        // 得到叶节点
        ArrayList ll = getLeafNodes();
        int offset = ll.size();
        for (int i = 0; i < offset; i++) {
            Cell cell = (Cell) ll.get(i);
            String nodeLink = getNodeLetterRoutes(new StringBuffer(), cell.getId());

            Cell newCell = (Cell) DeepCloneUtil.deepClone(cell);
            newCell.setComparedKey(nodeLink);
            newCell.setContent(null);

            crossDataList.add(newCell);
        }

        return crossDataList;
    }

    // 取出一个节点描述链
    private String getNodeLetterRoutes(StringBuffer buffer, String id) {
        Cell node = getNodeById(id);

        buffer.insert(0, ReportConstants.DATA_SEPARATOR + node.getContent());
        if (!"0".equals(node.getFid())) {
            getNodeLetterRoutes(buffer, node.getFid());
        }

        return buffer.toString();
    }

    // 生成分组列表
    public ArrayList toGroupHeadList(int type) {
        // 计算节点对应的叶节点个数
        calcNodeSize();
        // 新分组列表
        ArrayList groupList = new ArrayList();

        switch (type) {
        case ReportConstants.COLUMN_HEAD:// 行分组
            for (int i = 0; i < level; i++) {
                List colRowList = this.getNodesByLevel(i + 1);

                // colspan
                for (int j = 0; j < colRowList.size(); j++) {
                    Cell cell = (Cell) colRowList.get(j);
                    cell.setColSpan(cell.getSize());
                }

                groupList.add(colRowList);
            }
            break;
        case ReportConstants.ROW_HEAD:// 列分组

            // 整理行分组
            ArrayList leafList = getLeafNodes();

            for (int j = 0; j < leafList.size(); j++) {
                Cell node = (Cell) leafList.get(j);

                // 递归获得行分组节点
                ArrayList listR = new ArrayList();
                listR.add(node);
                getNodeRecursive(listR, node.getFid());

                groupList.add(listR);
            }

            // 隐藏不显示的cell
            groupList = (ArrayList) hiddenRowGroupList(groupList);

            break;
        }

        return groupList;
    }

    // 递归获得节点
    private void getNodeRecursive(List list, String fid) {
        Cell node = getNodeByFid(fid);
        if (null != node) {// 防止只有一个分组
            list.add(0, node);// 重要：保证顺序
            if (!"0".equals(node.getFid())) {
                getNodeRecursive(list, node.getFid());
            }
        }
    }

    // 隐藏不显示的cell
    // 思路：先把行分组的第一行保存在数组，然后逐行比较，将内容重复的标注为不显示
    private List hiddenRowGroupList(List list) {
        // 新行分组列表
        List rowHeadList = new ArrayList();

        Object[] rowArray = new Object[level];

        for (int i = 0; i < list.size(); i++) {
            List rowList = (List) list.get(i);

            // 新行
            List newRowList = new ArrayList();

            for (int j = 0; j < level; j++) {
                Cell node = (Cell) rowList.get(j);

                if (!node.isLeaf())
                    if (!node.getContent().equals(rowArray[j]))
                        rowArray[j] = node.getContent();
                    else
                        node.setHidden(true);// 在打印时不显示

                // rowspan
                node.setRowSpan(node.getSize());

                // 此处deepclone很重要
                newRowList.add(DeepCloneUtil.deepClone(node));
            }

            rowHeadList.add(newRowList);
        }

        return rowHeadList;
    }

    // 计算节点对应的叶节点个数
    private void calcNodeSize() {
        List ll = this.getLeafNodes();

        for (int i = 0; i < ll.size(); i++) {
            Cell node = (Cell) ll.get(i);

            calcNodeSizeRecursive(node);
        }
    }

    // 递归计算节点对应的叶节点个数
    private void calcNodeSizeRecursive(Cell node) {
        Cell fnode = getNodeByFid(node.getFid());

        if (fnode != null) {
            // 排除叶子节点
            if (!node.isLeaf()) {
                if (!fnode.isHasLeafSize())
                    fnode.setSize(node.getSize() * fnode.getSize());

                fnode.setHasLeafSize(true);
            }

            calcNodeSizeRecursive(fnode);
        }
    }

    // 取出所有的叶节点
    private ArrayList getLeafNodes() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < nodeList.size(); i++) {
            Cell node = (Cell) nodeList.get(i);
            if (node.isLeaf())
                list.add(node);
        }
        return list;
    }

    // 获得叶节点数目
    public int getLeafSize() {
        return getLeafNodes().size();
    }

    // 根据level获得节点列表
    private ArrayList getNodesByLevel(int level) {
        ArrayList list = new ArrayList();

        Iterator iter = nodeList.iterator();

        while (iter.hasNext()) {
            Cell node = (Cell) iter.next();

            if (node.getLevel() == level) {
                list.add(node);
            }
        }

        return list;
    }

    // 根据id获得节点
    private Cell getNodeById(String id) {
        Cell node = null;

        for (int i = 0; i < nodeList.size(); i++) {
            node = (Cell) nodeList.get(i);

            if (id.equals(node.getId()))
                break;

            node = null;
        }

        return node;
    }

    // 根据fid获得节点
    private Cell getNodeByFid(String fid) {
        Cell node = null;

        for (int i = 0; i < nodeList.size(); i++) {
            node = (Cell) nodeList.get(i);

            if (fid.equals(node.getId()))
                break;

            node = null;
        }

        return node;
    }

}