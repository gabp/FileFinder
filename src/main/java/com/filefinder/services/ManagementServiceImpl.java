package com.filefinder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service(value = "ManagementServiceImpl")
public class ManagementServiceImpl implements ManagementService
{
    @Autowired
    @Qualifier("ElasticSearchServiceImpl")
    private ElasticSearchService elasticSearchService;

    @Override
    public ElasticSearchService getElasticSearchService()
    {
        return elasticSearchService;
    }
}
