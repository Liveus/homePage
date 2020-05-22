package com.liveus.core.analyze.service.impl;

import com.liveus.common.utils.JvmUtils;
import com.liveus.core.analyze.service.JvmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/13 17:13
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@Service
public class JvmServiceImpl implements JvmService {

    @Override
    public Map<String, Object> getJvmInfo() {
        return JvmUtils.getJvmInfo();
    }
}
