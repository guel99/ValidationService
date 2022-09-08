package com.devisefutures.signaturevalidator.bsl.services.validator;

import eu.europa.esig.dss.service.http.commons.CommonsDataLoader;
import eu.europa.esig.dss.service.http.commons.FileCacheDataLoader;
import eu.europa.esig.dss.spi.client.http.DSSFileLoader;
import eu.europa.esig.dss.spi.client.http.IgnoreDataLoader;
import eu.europa.esig.dss.spi.tsl.TrustedListsCertificateSource;
import eu.europa.esig.dss.tsl.cache.CacheCleaner;
import eu.europa.esig.dss.tsl.job.TLValidationJob;
import eu.europa.esig.dss.tsl.source.LOTLSource;
import eu.europa.esig.dss.tsl.sync.AcceptAllStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Handles the requests that use List of
 * trusted lists to validate the signature
 */
@Service(value = "tslHandler")
public class TSLHandler {

    public static final String EUROPEAN_LOTL_URL = "https://ec.europa.eu/tools/lotl/eu-lotl.xml";

    private File tslCache;

    @Value("${tslHandler.tslCacheFileName}")
    private String tslCacheFileName;

    @Value("${tslHandler.onlineRefresh}")
    private boolean onlineRefresh;

    @Value("${tslHandler.cleanMemoryCache}")
    private boolean cleanMemoryCache;

    @Value("${tslHandler.cleanFileSystemCache}")
    private boolean cleanFileSystemCache;

    @Value("${tslHandler.onlineLoaderExpirationTimeOfCache}")
    private long onlineLoaderExpirationTimeOfCache;

    /**
     * The certificate source obtained by the
     * instance of TSLHandler
     */
    private TrustedListsCertificateSource certificateSource;

    @PostConstruct
    private void init(){
        File root = new File(System.getProperty("java.io.tmpdir"));
        tslCache = new File(root,"dss-tls-loader");
    }

    /**
     * Gets the European List of trusted Lists
     * @param lotlURL The List of Trusted Lists URI
     * @return LOTLSource
     */
    private LOTLSource getLOTL(String lotlURL){
        LOTLSource europeanLotl = new LOTLSource();
        europeanLotl.setUrl(lotlURL);
        europeanLotl.setPivotSupport(true);
        return europeanLotl;
    }

    /**
     * Builds an object used to download the trusted
     * list contents to file-system. This one is the offline refresh,
     * where the internet download is disabled and the cache has
     * an expiration time
     * @return offlineFileLoader
     */
    private DSSFileLoader buildOfflineLoader(){
        FileCacheDataLoader offlineFileLoader = new FileCacheDataLoader();
        offlineFileLoader.setCacheExpirationTime(Long.MAX_VALUE);
        offlineFileLoader.setDataLoader(new IgnoreDataLoader());
        offlineFileLoader.setFileCacheDirectory(this.tslCache);
        return offlineFileLoader;
    }

    /**
     * Builds an object used to download the trusted
     * list contents to file-system. This one is the online refresh,
     * where the internet download is enabled and the cache has
     * an expiration time
     * @return offlineFileLoader
     */
    private DSSFileLoader buildOnlineLoader(){
        FileCacheDataLoader onlineFileLoader = new FileCacheDataLoader();
        onlineFileLoader.setCacheExpirationTime(this.onlineLoaderExpirationTimeOfCache);
        onlineFileLoader.setDataLoader(new CommonsDataLoader());
        onlineFileLoader.setFileCacheDirectory(this.tslCache);
        return onlineFileLoader;
    }

    /**
     * Builds an object that specifies how DSS
     * clear the cache when expired URL
     * @return CacheCleaner object
     */
    private CacheCleaner buildCacheCleaner(DSSFileLoader fileLoader){
        CacheCleaner cacheCleaner = new CacheCleaner();
        cacheCleaner.setCleanMemory(this.cleanMemoryCache);
        cacheCleaner.setCleanFileSystem(this.cleanFileSystemCache);
        cacheCleaner.setDSSFileLoader(fileLoader);
        return cacheCleaner;
    }

    /**
     * Gets an objects that allows to download, parse and validate
     * the Trusted Lists and Lists Of Trusted Lists. Once the task is done,
     * the result is stored in the TrustedListsCertificateSource
     * @param lotlURL The List of Trusted Lists URI
     * @return
     */
    private TLValidationJob buildTLValidationJob(String lotlURL){
        TLValidationJob job = new TLValidationJob();
        DSSFileLoader offlineFileLoader = buildOfflineLoader();
        job.setOfflineDataLoader(offlineFileLoader);
        job.setOnlineDataLoader(buildOnlineLoader());
        job.setSynchronizationStrategy(new AcceptAllStrategy());
        job.setCacheCleaner(buildCacheCleaner(offlineFileLoader));
        job.setListOfTrustedListSources(this.getLOTL(lotlURL));

        return job;
    }

    /**
     * Builds the object necessary to collect
     * trusted certificates from trusted lists.
     * @param lotlURL The List of Trusted Lists URI
     * @return
     */
    @Cacheable("tsl")
    public TrustedListsCertificateSource buildTrustedListsCertificateSource(String lotlURL){
        TrustedListsCertificateSource trustedListsCertificateSource = new TrustedListsCertificateSource();
        TLValidationJob tlValidationJob = this.buildTLValidationJob(lotlURL);
        tlValidationJob.setTrustedListCertificateSource(trustedListsCertificateSource);
        if(this.onlineRefresh)
            tlValidationJob.onlineRefresh();
        else
            tlValidationJob.offlineRefresh();
        return trustedListsCertificateSource;
    }
}
