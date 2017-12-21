package com.wonders.wddc.tiles.tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutzSqlUtil {
	
	
	public static List rs2MapList(ResultSet rs)
    {
		if (rs == null)
            return null;

        List rsMapList = new ArrayList();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            while (rs.next()) {
                HashMap rsMap = new HashMap();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    int columnType = rsmd.getColumnType(i);

                    rsMap.put(columnName, Types.VARCHAR == columnType ? rs.getString(columnName) : rs
                            .getObject(columnName));
                }

                rsMapList.add(rsMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsMapList;
    }
	
	public static Map<String,Object> rs2Map(ResultSet rs) {
        List mapList = rs2MapList(rs);
        return mapList == null || mapList.size() <= 0 ? null : (HashMap)mapList.get(0);
	}
	
}
