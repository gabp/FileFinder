package com.filefinder.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "ElasticSearchServiceImpl")
public class ElasticSearchServiceImpl implements ElasticSearchService
{
    final static Logger LOGGER = LogManager.getLogger(ElasticSearchServiceImpl.class);

    @Override
    public void test()
    {
        LOGGER.info("In elastic search service!");
    }
}
