package com.klsw.wk.hades.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.klsw.unbiz.common.StringUtil;
import com.klsw.wk.hades.domain.Resource;

/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 *
 */
public class TreeUtil {
	
	/*
	 * select2下来组件数据对象
	 */
	private List<Select2Entity> selectTree = new ArrayList<Select2Entity>();
	/*
	 * 生成select2下来组件数据时遍历的次数
	 */
	private int selectCnt = 0;
	
	/**
	 * 生成jstree要求的数据结构
	 * @param resourceList
	 * @return
	 */
	public List<JSTreeEntity> generateJSTree(List<Resource> resourceList)
	{
		List<JSTreeEntity> jstreeList = new ArrayList<JSTreeEntity>();
		
		for (Resource resourceEntity : resourceList) {
			JSTreeEntity jstree = new JSTreeEntity();
			
			jstree.setId(resourceEntity.getId().toString());
			jstree.setParent(resourceEntity.getParentId()==null ? "#" : resourceEntity.getParentId().toString());
			jstree.setText(resourceEntity.getName());
			jstree.setIcon(StringUtil.isBlank(resourceEntity.getIcon()) ? "fa fa-cog" : resourceEntity.getIcon());
			JSTreeEntity.State state = new JSTreeEntity().new State();
			state.setDisabled(false);
			state.setSelected(resourceEntity.getSelected());
			state.setOpened(true/*resourceEntity.getParentId()==null ? true : false*/);
			jstree.setState(state);
			jstreeList.add(jstree);
		}
		
		return jstreeList;
	}


	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list	具有树形结构特点的集合
	 * @param parentId	父节点ID
	 * @return	树形结构集合
	 */
	public List<Select2Entity> getSelectTree(List<Resource> list,Integer parentId) {
		List<Resource> returnList = getChildResourceEntitys(list, parentId);
		recursionForSelect(returnList);
		return selectTree;
	}
	
	
	/**
	 * 递归列表
	 * @param list
	 */
	private void recursionForSelect(List<Resource> list) {
		String str = "";
		for(int i=0; i< selectCnt; i++)
		{
			str += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		for (Resource re : list) {
			if(null == re.getParentId())
			{
				str = "";
				selectCnt = 0;
			}
			Select2Entity se = new Select2Entity();
			se.setId(re.getId().toString());
			se.setText(str + re.getName());
			se.setName(re.getName());
			selectTree.add(se);
			if(re.getChildren().size() > 0)
			{
				selectCnt ++;
				recursionForSelect(re.getChildren());
			}
		}
	}
	
	
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list	具有树形结构特点的集合
	 * @param parentId	父节点ID
	 * @return	树形结构集合
	 */
	public List<Resource> getChildResourceEntitys(List<Resource> list,Integer parentId) {
		List<Resource> returnList = new ArrayList<Resource>();
		for (Iterator<Resource> iterator = list.iterator(); iterator.hasNext();) {
			Resource t = iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId()==parentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 * @param list
	 * @param t
	 */
	private void recursionFn(List<Resource> list, Resource t) {
		List<Resource> childList = getChildList(list, t);// 得到子节点列表
		t.setChildren(childList);
		for (Resource tChild : childList) {
			if (hasChild(list, tChild)) {// 判断是否有子节点
				Iterator<Resource> it = childList.iterator();
				while (it.hasNext()) {
					Resource n = (Resource) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	/**
	 * 得到子节点列表
	 * @param list
	 * @param t
	 * @return
	 */
	private List<Resource> getChildList(List<Resource> list, Resource t) {
		List<Resource> tlist = new ArrayList<Resource>();
		Iterator<Resource> it = list.iterator();
		while (it.hasNext()) {
			Resource n = (Resource) it.next();
			if(t.getType()!=2)
			{
				if (n.getParentId() == Integer.valueOf(t.getId().toString())) {
					//n.setParentName(t.getName());
					tlist.add(n);
				}
			}
		}
		return tlist;
	} 
	
	/**
	 * 判断是否有子节点
	 * @param list
	 * @param t
	 * @return
	 */
	private boolean hasChild(List<Resource> list, Resource t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
}
