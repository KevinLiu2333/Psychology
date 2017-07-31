function toPie_legend(data)
{
	var legend=new Array(data.length);
	var i=0;
	for(var key in data)
	{
		legend[i]=key;
		i++;
	}
	return legend;
}
function toPie_Seriesdate(data,color)
{
	var seriesdata=[];
	var i=0;
	for(var key in data)
	{
		var col='';
		if(color[i]!=null)
			col=color[i];
		var dat={value:data[key],name:key,itemStyle: {
			normal: {
				color: col
			}
		}};
		seriesdata.push(dat);
		i++;
	}
	return seriesdata;
}