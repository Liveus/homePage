package com.liveus.service.serviceImpl;

import com.liveus.dao.LiveusMapper;
import com.liveus.domain.Liveus;
import com.liveus.service.LiveusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("liveusService")
public class LiveusServiceImpl implements LiveusService {

    @Autowired
    LiveusMapper liveusMapper;

    @Override
    public Boolean updateLiveus(Liveus liveus) {
        liveusMapper.updateByPrimaryKey(liveus);
        return null;
    }
}
