package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.model.TbCwkversion;
import org.springframework.stereotype.Service;

/**
 * Created by liukun on 2016/12/1.
 * 威客版本
 */
@Service
public class TbCwkVersionService extends _BaseService<TbCwkversion> {

    public TbCwkversion selectByType(String type) {
        TbCwkversion tbCwkversion = new TbCwkversion();
        tbCwkversion.setType(type);
        try {
            tbCwkversion = select(tbCwkversion).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbCwkversion;
    }

}
