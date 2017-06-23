package com.klsw.pianoCommon.api.model;

import net.sourceforge.jtds.jdbc.DateTime;

public class SerialInfo {
	private String ID;

    private DateTime ActivationDate;

    private DateTime AddDatetime;

    private boolean IsUsed;

    private String Number;

    private String tb_UserAvailableID;

    private String Nickname;

    private boolean IsExport;

    private String ExportZone;
    
    private boolean IsOverdue;
    
    private boolean IsLocked;
    
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public DateTime getActivationDate() {
		return ActivationDate;
	}

	public void setActivationDate(DateTime activationDate) {
		ActivationDate = activationDate;
	}

	public DateTime getAddDatetime() {
		return AddDatetime;
	}

	public void setAddDatetime(DateTime addDatetime) {
		AddDatetime = addDatetime;
	}

	public boolean isIsUsed() {
		return IsUsed;
	}

	public void setIsUsed(boolean isUsed) {
		IsUsed = isUsed;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getTb_UserAvailableID() {
		return tb_UserAvailableID;
	}

	public void setTb_UserAvailableID(String tb_UserAvailableID) {
		this.tb_UserAvailableID = tb_UserAvailableID;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public boolean isIsExport() {
		return IsExport;
	}

	public void setIsExport(boolean isExport) {
		IsExport = isExport;
	}

	public String getExportZone() {
		return ExportZone;
	}

	public void setExportZone(String exportZone) {
		ExportZone = exportZone;
	}

	public boolean isIsOverdue() {
		return IsOverdue;
	}

	public void setIsOverdue(boolean isOverdue) {
		IsOverdue = isOverdue;
	}

	public boolean isIsLocked() {
		return IsLocked;
	}

	public void setIsLocked(boolean isLocked) {
		IsLocked = isLocked;
	}

	@Override
	public String toString() {
		return "SerailInfo [ID=" + ID + ", ActivationDate=" + ActivationDate + ", AddDatetime=" + AddDatetime
				+ ", IsUsed=" + IsUsed + ", Number=" + Number + ", tb_UserAvailableID=" + tb_UserAvailableID
				+ ", Nickname=" + Nickname + ", IsExport=" + IsExport + ", ExportZone=" + ExportZone + ", IsOverdue="
				+ IsOverdue + ", IsLocked=" + IsLocked + "]";
	}
    
}
