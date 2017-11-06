package am.ik.certificate.spring;

import am.ik.certificate.CertificateImporter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CertificateImporterApplicationContextInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger log = Logger.getLogger(CertificateImporterApplicationContextInitializer.class.getName());

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        CertificateImporter certificateImporter = new CertificateImporter();
        String[] caCerts = context.getEnvironment().getProperty("ca.certs", String[].class);
        if (caCerts != null && caCerts.length > 0) {
            try {
                certificateImporter.doImport(caCerts);
            } catch (Exception e) {
                log.log(Level.WARNING, e, () -> "Failed to import certificate.");
            }
        }
    }
}
