function dateChange(dateStr,days){
	var year = dateStr.substring(0,4);
	var month = dateStr.substring(5,7);
	var day = dateStr.substring(8,10);
	var date = new Date(year,month-1,day);
	var startDate = date.setDate(date.getDate() + days);
	startDate = new Date(startDate);
	var startDateYear = startDate.getFullYear();
	var startDateMonth = startDate.getMonth()+1;
	if (startDateMonth < 10){
		startDateMonth = "0" + startDateMonth;
	}
	var startDateDay = startDate.getDate();
	if (startDateDay < 10){
		startDateDay = "0" + startDateDay;
	}
	return startDateYear + "-" + startDateMonth + "-" + startDateDay;
}