package am.ik.certificate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CertificateImporter {
    private AtomicBoolean imported = new AtomicBoolean(false);
    private static final Logger log = Logger.getLogger(CertificateImporter.class.getName());

    public void doImport(String... pemFiles) throws GeneralSecurityException, IOException {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        List<Certificate> certificates = new ArrayList<>();
        for (String pemFile : pemFiles) {
            Certificate certificate = fact.generateCertificate(new ByteArrayInputStream(pemFile.getBytes()));
            certificates.add(certificate);
        }
        this.doImport(certificates);
    }

    public void doImport(List<Certificate> certificates) throws GeneralSecurityException, IOException {
        if (imported.get()) {
            log.log(Level.WARNING, "Certificates have already been imported!");
            return;
        }
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(null); // init empty keystore
        int count = 0;
        for (Certificate certificate : certificates) {
            String alias = "import" + (count++);
            log.log(Level.INFO, "Importing " + alias + "/" + certificate.getType());
            trustStore.setCertificateEntry(alias, certificate);
        }
        String password = UUID.randomUUID().toString();
        File trustStoreOutputFile = File.createTempFile("truststore", null);
        trustStoreOutputFile.deleteOnExit();
        trustStore.store(new FileOutputStream(trustStoreOutputFile), password.toCharArray());
        System.setProperty("javax.net.ssl.trustStore", trustStoreOutputFile.getAbsolutePath());
        System.setProperty("javax.net.ssl.trustStorePassword", password);
        imported.set(true);
    }

}
