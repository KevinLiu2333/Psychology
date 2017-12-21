package com.wonders.ws.receive.task;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.log.entity.InterfaceReceiveLog;
import com.wonders.ws.receive.bean.BuildingBean;
import com.wonders.ws.receive.bean.BuildingCorp;
import com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoapProxy;
import com.wonders.ws.receive.getMethod.building.Response;

@IocBean
public class BuildingTask {

	@Inject
	private Dao	dao;

	public void extract() {
		// 楼宇信息
		getBuilding();
		// 楼宇内企业信息
		getBuildingCorpinfo();
	}

	public void getBuilding() {
		GetBuildingInfoServiceSoapProxy service = new GetBuildingInfoServiceSoapProxy();
		Response json = null;
		try {
			json = service.getAllBuildingInfoList("zMqAdNoRLHomm4AECoURl7gds1sUIjun", "json");
			JSONObject jsonObject = JSONObject.fromObject(json);
			JSONArray ja = jsonObject.getJSONArray("result");
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInputNum(ja.size());
			log.setInterfaceName("GetAllBuildingInfoList");
			log.setServiceName("获取全量楼宇信息");
			log.setReceiveName("楼宇信息");
			log.setDeptName("投资办");
			log.setDeptId("DT50");
			log.setCallNum(1);
			log.setTableName("PT_TCB_BUILDING");
			Sql sql = Sqls.create("truncate table PT_TCB_BUILDING");
			dao.execute(sql);
			for (int i = 0; i < ja.size(); i++) {
				BuildingBean building = new BuildingBean();
				building.setInsertDate(date.format(new Date()));
				building.setId(ja.getJSONObject(i).getString("Id"));
				building.setOwnertypes(ja.getJSONObject(i).getString("OwnerTypes"));
				building.setBuildingname(ja.getJSONObject(i).getString("BuildingName"));
				building.setTemporarilyname(ja.getJSONObject(i).getString("TemporarilyName"));
				building.setDistrict(ja.getJSONObject(i).getString("District"));
				building.setAddresscn(ja.getJSONObject(i).getString("AddressCn"));
				building.setAddressen(ja.getJSONObject(i).getString("AddressEn"));
				building.setTemporarilyaddress(ja.getJSONObject(i).getString("TemporarilyAddress"));
				building.setCompletiontime(ja.getJSONObject(i).getString("CompletionTime"));
				building.setPlancomptime(ja.getJSONObject(i).getString("PlanCompTime"));
				building.setPostalcode(ja.getJSONObject(i).getString("PostalCode"));
				building.setTotalacreage(ja.getJSONObject(i).getString("TotalAcreage"));
				building.setBuildingheight(ja.getJSONObject(i).getString("BuildingHeight"));
				building.setInvestmenthotline(ja.getJSONObject(i).getString("InvestmentHotline"));
				building.setCooperationmode(ja.getJSONObject(i).getString("CooperationMode"));
				building.setBusinessacreage(ja.getJSONObject(i).getString("BusinessAcreage"));
				building.setBusinessroomnum(ja.getJSONObject(i).getString("BusinessRoomNum"));
				building.setOccupiedacreage(ja.getJSONObject(i).getString("OccupiedAcreage"));
				building.setOccupiedroom(ja.getJSONObject(i).getString("OccupiedRoom"));
				building.setInteriorheight(ja.getJSONObject(i).getString("InteriorHeight"));
				building.setRoomrate(ja.getJSONObject(i).getString("RoomRate"));
				building.setGroundlayer(ja.getJSONObject(i).getString("GroundLayer"));
				building.setUndergroundlayer(ja.getJSONObject(i).getString("UndergroundLayer"));
				building.setUndergroundparking(ja.getJSONObject(i).getString("UndergroundParking"));
				building.setUndergroundparkingacreage(ja.getJSONObject(i).getString("UndergroundParkingAcreage"));
				building.setPavementparking(ja.getJSONObject(i).getString("PavementParking"));
				building.setOtherparking(ja.getJSONObject(i).getString("OtherParking"));
				building.setAirconditioning(ja.getJSONObject(i).getString("AirConditioning"));
				building.setDecoration(ja.getJSONObject(i).getString("Decoration"));
				building.setHourparkingfeemin(ja.getJSONObject(i).getString("HourParkingFeeMin"));
				building.setHourparkingfeemax(ja.getJSONObject(i).getString("HourParkingFeeMax"));
				building.setMonthparkingfeemin(ja.getJSONObject(i).getString("MonthParkingFeeMin"));
				building.setMonthparkingfeemax(ja.getJSONObject(i).getString("MonthParkingFeeMax"));
				building.setSalepricemin(ja.getJSONObject(i).getString("SalePriceMin"));
				building.setSalepricemax(ja.getJSONObject(i).getString("SalePriceMax"));
				building.setWaterfee(ja.getJSONObject(i).getString("WaterFee"));
				building.setElectricityfee(ja.getJSONObject(i).getString("ElectricityFee"));
				building.setElevatorfee(ja.getJSONObject(i).getString("ElevatorFee"));
				building.setAirconditionfee(ja.getJSONObject(i).getString("AirConditionFee"));
				building.setPassengerliftnum(ja.getJSONObject(i).getString("PassengerLiftNum"));
				building.setCargoliftnum(ja.getJSONObject(i).getString("CargoLiftNum"));
				building.setEstablisheddate(ja.getJSONObject(i).getString("EstablishedDate"));
				building.setInvestment(ja.getJSONObject(i).getString("Investment"));
				building.setInvestmentunit(ja.getJSONObject(i).getString("InvestmentUnit"));
				building.setConstructiontelno(ja.getJSONObject(i).getString("ConstructionTelNo"));
				building.setConstructionlinkman(ja.getJSONObject(i).getString("ConstructionLinkMan"));
				building.setConstructionunit(ja.getJSONObject(i).getString("ConstructionUnit"));
				building.setConstructionfax(ja.getJSONObject(i).getString("ConstructionFax"));
				building.setManagementunit(ja.getJSONObject(i).getString("ManagementUnit"));
				building.setManagementlinkman(ja.getJSONObject(i).getString("ManagementLinkMan"));
				building.setManagementtelno(ja.getJSONObject(i).getString("ManagementTelNo"));
				building.setManagementfax(ja.getJSONObject(i).getString("ManagementFax"));
				building.setBuildingdescription(ja.getJSONObject(i).getString("BuildingDescription"));
				building.setPlanstarttime(ja.getJSONObject(i).getString("PlanStartTime"));
				building.setEstablishment(ja.getJSONObject(i).getString("Establishment"));
				building.setTraffic(ja.getJSONObject(i).getString("Traffic"));
				building.setRoadway(ja.getJSONObject(i).getString("Roadway"));
				building.setAnnexacreage(ja.getJSONObject(i).getString("AnnexAcreage"));
				building.setAnnexdesc(ja.getJSONObject(i).getString("AnnexDesc"));
				building.setPartitionkind(ja.getJSONObject(i).getString("PartitionKind"));
				building.setBuildingstatus(ja.getJSONObject(i).getString("BuildingStatus"));
				building.setCurrentprogress(ja.getJSONObject(i).getString("CurrentProgress"));
				building.setCreateuserid(ja.getJSONObject(i).getString("CreateUserId"));
				building.setCreatetime(ja.getJSONObject(i).getString("CreateTime"));
				building.setUpdateuserid(ja.getJSONObject(i).getString("UpdateUserId"));
				building.setUpdatetime(ja.getJSONObject(i).getString("UpdateTime"));
				building.setVersionno(ja.getJSONObject(i).getString("VersionNo"));
				building.setGslly(ja.getJSONObject(i).getString("Gslly"));
				building.setGsllydh(ja.getJSONObject(i).getString("Gslly_dh"));
				building.setWylxr(ja.getJSONObject(i).getString("Wylxr"));
				building.setWylxrdh(ja.getJSONObject(i).getString("Wylxr_dh"));
				building.setDjlly(ja.getJSONObject(i).getString("Djlly"));
				building.setDjllydh(ja.getJSONObject(i).getString("Djlly_dh"));
				building.setJjlly(ja.getJSONObject(i).getString("Jjlly"));
				building.setJjllydh(ja.getJSONObject(i).getString("Jjlly_dh"));
				building.setSymj(ja.getJSONObject(i).getString("Symj"));
				building.setJjbgmj(ja.getJSONObject(i).getString("Jjbgmj"));
				building.setBgpjzj(ja.getJSONObject(i).getString("Bgpjzj"));
				building.setJjbgpjzj(ja.getJSONObject(i).getString("Jjbgpjzj"));
				building.setSybgpjzj(ja.getJSONObject(i).getString("Sybgpjzj"));
				building.setBgyfsse(ja.getJSONObject(i).getString("Bgyfsse"));
				building.setSyyfxse(ja.getJSONObject(i).getString("Syyfxse"));
				building.setSyyfsse(ja.getJSONObject(i).getString("Syyfsse"));
				building.setCzlb(ja.getJSONObject(i).getString("Czlb"));
				building.setIsexport(ja.getJSONObject(i).getString("IsExport"));
				building.setDelflag(ja.getJSONObject(i).getString("DelFlag"));
				building.setWygs(ja.getJSONObject(i).getString("WYGS"));
				building.setBgpjzjmax(ja.getJSONObject(i).getString("BGPJZJMAX"));
				building.setJjbgpjzjmax(ja.getJSONObject(i).getString("JJBGPJZJMAX"));
				building.setEntiretylet(ja.getJSONObject(i).getString("Entiretylet"));
				building.setOperatortype(ja.getJSONObject(i).getString("OperatorType"));
				building.setOperationcorpname(ja.getJSONObject(i).getString("OperationCorpName"));
				building.setOperationcontactor(ja.getJSONObject(i).getString("OperationContactor"));
				building.setOperationcontactmethod(ja.getJSONObject(i).getString("OperationContactMethod"));
				building.setDepartment(ja.getJSONObject(i).getString("Department"));
				building.setEmptyrate(ja.getJSONObject(i).getString("EmptyRate"));
				building.setCheckincorpnum(ja.getJSONObject(i).getString("CheckInCorpNum"));
				building.setRegistedcorpnum(ja.getJSONObject(i).getString("RegistedCorpNum"));
				building.setUnregistedcorpnum(ja.getJSONObject(i).getString("UnRegistedCorpNum"));
				building.setPropertyfax(ja.getJSONObject(i).getString("PropertyFax"));
				building.setIsdistregin(ja.getJSONObject(i).getString("IsDistRegIn"));
				building.setCanuse(ja.getJSONObject(i).getString("CanUse"));
				dao.insert(building);
			}
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void getBuildingCorpinfo() {
		GetBuildingInfoServiceSoapProxy service = new GetBuildingInfoServiceSoapProxy();
		Response json = null;
		try {
			json = service.getAllcorpBaseInfoList("zMqAdNoRLHomm4AECoURl7gds1sUIjun", "", "", "json");
			JSONObject jsonObject = JSONObject.fromObject(json);
			JSONArray ja = jsonObject.getJSONArray("result");
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInputNum(ja.size());
			log.setInterfaceName("getAllcorpBaseInfoList");
			log.setServiceName("获取全量楼宇法人信息");
			log.setReceiveName("楼宇法人信息");
			log.setDeptName("投资办");
			log.setDeptId("DT50");
			log.setCallNum(1);
			log.setTableName("PT_TCB_BUILDING_CORPINFO");
			Sql sql = Sqls.create("truncate table PT_TCB_BUILDING_CORPINFO");
			dao.execute(sql);
			for (int i = 0; i < ja.size(); i++) {
				BuildingCorp buildingCorpinfo = new BuildingCorp();
				buildingCorpinfo.setInsertdate(date.format(new Date()));
				buildingCorpinfo.setId(ja.getJSONObject(i).getString("Id"));
				buildingCorpinfo.setIndustry(ja.getJSONObject(i).getString("industry"));
				buildingCorpinfo.setBuildname(ja.getJSONObject(i).getString("BuildName"));
				buildingCorpinfo.setCorpname(ja.getJSONObject(i).getString("CorpName"));
				buildingCorpinfo.setLicencecode(ja.getJSONObject(i).getString("LicenceCode"));
				buildingCorpinfo.setCommerceno(ja.getJSONObject(i).getString("CommerceNo"));
				buildingCorpinfo.setCorptaxpayercode(ja.getJSONObject(i).getString("CorpTaxPayerCode"));
				buildingCorpinfo.setTaxregisterno(ja.getJSONObject(i).getString("TaxRegisterNo"));
				buildingCorpinfo.setCompletetime(ja.getJSONObject(i).getString("CompleteTime"));
				buildingCorpinfo.setLicensevalidity(ja.getJSONObject(i).getString("LicenseValidity"));
				buildingCorpinfo.setRegistaddress(ja.getJSONObject(i).getString("RegistAddress"));
				buildingCorpinfo.setWorkaddress(ja.getJSONObject(i).getString("WorkAddress"));
				buildingCorpinfo.setCorplinkman(ja.getJSONObject(i).getString("CorpLinkMan"));
				buildingCorpinfo.setTelnumber(ja.getJSONObject(i).getString("TelNumber"));
				buildingCorpinfo.setMobiletelnumber(ja.getJSONObject(i).getString("MobileTelNumber"));
				buildingCorpinfo.setFaxnumber(ja.getJSONObject(i).getString("FaxNumber"));
				buildingCorpinfo.setPostalcode(ja.getJSONObject(i).getString("PostalCode"));
				buildingCorpinfo.setHomepage(ja.getJSONObject(i).getString("HomePage"));
				buildingCorpinfo.setRegistfund(ja.getJSONObject(i).getString("RegistFund"));
				buildingCorpinfo.setCurrencykind(ja.getJSONObject(i).getString("CurrencyKind"));
				buildingCorpinfo.setIsdistregin(ja.getJSONObject(i).getString("IsDistRegIn"));
				buildingCorpinfo.setTradekind(ja.getJSONObject(i).getString("TradeKind"));
				buildingCorpinfo.setCorpkind(ja.getJSONObject(i).getString("CorpKind"));
				buildingCorpinfo.setPaidincapital(ja.getJSONObject(i).getString("PaidInCapital"));
				buildingCorpinfo.setScopeofbusiness(ja.getJSONObject(i).getString("ScopeOfBusiness"));
				buildingCorpinfo.setDelflag(ja.getJSONObject(i).getString("DelFlag"));
				buildingCorpinfo.setCreateuserid(ja.getJSONObject(i).getString("CreateUserId"));
				buildingCorpinfo.setCreatetime(ja.getJSONObject(i).getString("CreateTime"));
				buildingCorpinfo.setUpdateuserid(ja.getJSONObject(i).getString("UpdateUserId"));
				buildingCorpinfo.setUpdatetime(ja.getJSONObject(i).getString("UpdateTime"));
				buildingCorpinfo.setVersionno(ja.getJSONObject(i).getString("VersionNo"));
				buildingCorpinfo.setCalculatedinrmb(ja.getJSONObject(i).getString("CalculatedInrmb"));
				buildingCorpinfo.setWorkrange(ja.getJSONObject(i).getString("WorkRange"));
				buildingCorpinfo.setFddbr(ja.getJSONObject(i).getString("Fddbr"));
				buildingCorpinfo.setIsnew(ja.getJSONObject(i).getString("IsNew"));
				buildingCorpinfo.setQyfl(ja.getJSONObject(i).getString("Qyfl"));
				buildingCorpinfo.setCzlb(ja.getJSONObject(i).getString("Czlb"));
				buildingCorpinfo.setIsexport(ja.getJSONObject(i).getString("IsExport"));
				buildingCorpinfo.setCompanystate(ja.getJSONObject(i).getString("CompanyState"));
				buildingCorpinfo.setDjjg(ja.getJSONObject(i).getString("DJJG"));
				buildingCorpinfo.setSljg(ja.getJSONObject(i).getString("SLJG"));
				buildingCorpinfo.setCorptype(ja.getJSONObject(i).getString("CorpType"));
				buildingCorpinfo.setEnterprisetype(ja.getJSONObject(i).getString("EnterpriseType"));
				dao.insert(buildingCorpinfo);
			}
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
