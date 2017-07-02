package org.baeldung.spring.data.couchbase2b;

import java.util.Arrays;
import java.util.List;

import org.baeldung.spring.data.couchbase.model.Campus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.couchbase.client.java.Bucket;

@Configuration
@EnableCouchbaseRepositories(basePackages = { "org.baeldung.spring.data.couchbase2b" })
public class MultiBucketCouchbaseConfig extends AbstractCouchbaseConfiguration {

    public static final List<String> NODE_LIST = Arrays.asList("localhost");
    public static final String DEFAULT_BUCKET_NAME = "baeldung";
    public static final String DEFAULT_BUCKET_PASSWORD = "";

    @Override
    protected List<String> getBootstrapHosts() {
        return NODE_LIST;
    }

    @Override
    protected String getBucketName() {
        return DEFAULT_BUCKET_NAME;
    }

    @Override
    protected String getBucketPassword() {
        return DEFAULT_BUCKET_PASSWORD;
    }

    @Bean
    private Bucket campusBucket() throws Exception {
        return couchbaseCluster().openBucket("baeldung2", "");
    }

    @Bean(name = "campusTemplate")
    private CouchbaseTemplate campusTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClusterInfo(), campusBucket(), mappingCouchbaseConverter(), translationService());
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping.mapEntity(Campus.class, campusTemplate());
        } catch (Exception e) {
            // custom Exception handling
        }
    }

    @Override
    protected Consistency getDefaultConsistency() {
        return Consistency.READ_YOUR_OWN_WRITES;
    }

    @Bean
    private LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingCouchbaseEventListener validatingCouchbaseEventListener() {
        return new ValidatingCouchbaseEventListener(localValidatorFactoryBean());
    }
}
