package com;

import java.net.URL;

public class test {
	public static void main(String[] args) {
		GetBussinesData bussinesData= null;
		GetBussinesDataService bussinesDataService = null;
		System.out.println("connect.....");
		String strport= "http://localhost:82/services/XZSP_CI_JCJ_JCF_BusinessCollectService";
		URL str;
		try {
			str = new URL(strport);
			bussinesDataService= new GetBussinesDataServiceLocator();
			bussinesData = bussinesDataService.getXZSP_CI_JCJ_JCF_BusinessCollectService(str);
			String name = bussinesData.saveOrUpdateBusinessCollect("dataxml","businessxml", "authenticatexml");
			System.out.print("·µ»ØÖµÊÇ="+name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
