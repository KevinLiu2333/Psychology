function tobar_xAxisdata(data)
{
	var xAxis=new Array(data.length);
	var i=0;
	for(var key in data)
	{
		xAxis[i]=key;
		i++;
	}
	return xAxis;
}

function tobar_data(data)
{
	var seriesdata=new Array(data.length);
	var i=0;
	for(var key in data)
	{
		seriesdata[i]=data[key];
		i++;
	}
	return seriesdata;
}