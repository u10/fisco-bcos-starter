package io.github.u10.fiscobcosstarter.config;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.channel.client.P12Manager;
import org.fisco.bcos.channel.client.PEMManager;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fisco-bcos.accounts")
@Slf4j
public class AccountConfig {

    @Getter @Setter
    private String pemFile;

    @Getter @Setter
    private String p12File;

    @Getter @Setter
    private String password;

    @Bean
    public Credentials getCredentials() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchProviderException, CertificateException, IOException {
        if (p12File != null) {
            return loadP12Account();
        } else if (pemFile != null) {
            return loadPemAccount();
        } else {
            return null;
        }
    }

    // load pem account file
    private Credentials loadPemAccount() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            IOException, NoSuchProviderException, InvalidKeySpecException, UnrecoverableKeyException {
        if (pemFile.indexOf(":") == -1) pemFile = "file:" + pemFile;
        log.info("pem accounts : {}", pemFile);
        PEMManager pem = new PEMManager();
        pem.setPemFile(pemFile);
        pem.load();
        ECKeyPair keyPair = pem.getECKeyPair();
        Credentials credentials = GenCredential.create(keyPair.getPrivateKey().toString(16));
        System.out.println(credentials.getAddress());
        return credentials;
    }

    // load p12 account file
    private Credentials loadP12Account() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            IOException, NoSuchProviderException, InvalidKeySpecException, UnrecoverableKeyException {
        if (p12File.indexOf(":") == -1) p12File = "file:" + p12File;
        log.info("p12 accounts : {}", p12File);
        P12Manager p12Manager = new P12Manager();
        p12Manager.setP12File(p12File);
        p12Manager.setPassword(password);
        p12Manager.load();
        ECKeyPair keyPair = p12Manager.getECKeyPair();
        Credentials credentials = GenCredential.create(keyPair.getPrivateKey().toString(16));
        System.out.println(credentials.getAddress());
        return credentials;
    }
}
