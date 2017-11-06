# certificate-importer


## Automatic configuration with Spring Boot

```
export CA_CERTS=`cat server-cert.pem`
```

or set `ca.certs=<pem encoded certificate>`.


Here is an example of `application.yml`

```yaml
spring.datasource.driver-class-name: com.mysql.jdbc.Driver
spring.datasource.url: jdbc:mysql://mysql.example.com:3306/foobar?useSSL=true
spring.datasource.username: user
spring.datasource.password: password
ca.certs: |  # server certificate for MySQL
  -----BEGIN CERTIFICATE-----
  MIIEEzCCAnugAwIBAgIQfLIGHE1V+VWCGqT4yvNCxTANBgkqhkiG9w0BAQsFADAz
  MQwwCgYDVQQGEwNVU0ExFjAUBgNVBAoTDUNsb3VkIEZvdW5kcnkxCzAJBgNVBAMT
  AmNhMB4XDTE3MTEwNjA0MDc1MloXDTE4MTEwNjA0MDc1MlowMzEMMAoGA1UEBhMD
  VVNBMRYwFAYDVQQKEw1DbG91ZCBGb3VuZHJ5MQswCQYDVQQDEwJjYTCCAaIwDQYJ
  KoZIhvcNAQEBBQADggGPADCCAYoCggGBANXhH4N456uTQgb2EckBYZFDRxhvACEv
  xsjqnchwSV2fwuNWw3yhoSW5Sk6LGab2AfqgIo09CrZ91IZ5zzp8+y30XOdz7MH0
  bnJTsGzDglpIZ046UMPRrPywYRS9mREcPReYktCcQ/ZtN8KTb8vxKky0CcGYT9Gh
  ThyktvRXdWn75IjOKHasYzICU/RLIy3TyHNV+aJA1dDB1gD4eqK42CSE/wnkJVV+
  j7OUFBM1t5QxH4aAfUQNZrCEXACi4XBmj2vAwRvotVhCgj4DcNwFr6YPvRaQmKvg
  mGhrrKMox3oDuvmr/mqZF56YaoPZ98tbDn5GFYFn0nuVvDIAaV/RaU7RwoDYVnJX
  sJpn2b096aMNaGL3fN4sGVEdYkIikvgX1QuFDIfWXd9McCeVI8/dyJKD5XKX3cK0
  DsxJxNOTwbCssUVoe0ldW8c7XfZANqQ6+i3qZGmzlAraykvrrJ+bpBB8QFBk2Dfr
  feC/BtoAwK/B8G4nq1nb1fa1yiKdEykmqQIDAQABoyMwITAOBgNVHQ8BAf8EBAMC
  AQYwDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQsFAAOCAYEAC9nBmcft25hb
  OpdL6HFxSQWeAn6sF2h03lgnHdAzIO5Ebtp1mLjoB7UlikCP5j246UFtDvuKHcoM
  yrdEkXlN5iCGCDBwpaxKnvof2GgEMoUahPzaaK1i4419pdEq6Rr1R3vXdzLm+TZ8
  bC2BcrcDoNH4K/RmIjvH9FyD5T25wCXaJxHiwNZ+Rd263Y0vpu6vg91ybX8XimMt
  vZgqq4WXhRuTMVxVv980X7ZR5DyEE/grg1hbU7sJRckEKz3CL0XKu/FqW4P+pIYJ
  /OaOUFolsACKirbxcce4KFhmkaY40pKZ6xTJfMYubxlkcLC0peVXABZNOlc3RtLY
  laK1qq7A/BxR37y5okVycc+vera93N+Exm9QxsyFQW0d4aXdBkBdlgk+17ut64oY
  DV9qoJ28cNlEmvsLjXXFaz6nrEOCbS+z/KDVWgqOzjrjsI4m8Xw8JOLL0SJPwj05
  IsAUxECwNAgnqY7PQ8iuUzhqBhxRAABbA0RZWlE2mdZZmUkOG/oL
  -----END CERTIFICATE-----
```

### Manual Configuration

``` java
String cert = "-----BEGIN CERTIFICATE-----\n" +
        "MIIEEzCCAnugAwIBAgIQfLIGHE1V+VWCGqT4yvNCxTANBgkqhkiG9w0BAQsFADAz\n" +
        "MQwwCgYDVQQGEwNVU0ExFjAUBgNVBAoTDUNsb3VkIEZvdW5kcnkxCzAJBgNVBAMT\n" +
        "AmNhMB4XDTE3MTEwNjA0MDc1MloXDTE4MTEwNjA0MDc1MlowMzEMMAoGA1UEBhMD\n" +
        "VVNBMRYwFAYDVQQKEw1DbG91ZCBGb3VuZHJ5MQswCQYDVQQDEwJjYTCCAaIwDQYJ\n" +
        "KoZIhvcNAQEBBQADggGPADCCAYoCggGBANXhH4N456uTQgb2EckBYZFDRxhvACEv\n" +
        "xsjqnchwSV2fwuNWw3yhoSW5Sk6LGab2AfqgIo09CrZ91IZ5zzp8+y30XOdz7MH0\n" +
        "bnJTsGzDglpIZ046UMPRrPywYRS9mREcPReYktCcQ/ZtN8KTb8vxKky0CcGYT9Gh\n" +
        "ThyktvRXdWn75IjOKHasYzICU/RLIy3TyHNV+aJA1dDB1gD4eqK42CSE/wnkJVV+\n" +
        "j7OUFBM1t5QxH4aAfUQNZrCEXACi4XBmj2vAwRvotVhCgj4DcNwFr6YPvRaQmKvg\n" +
        "mGhrrKMox3oDuvmr/mqZF56YaoPZ98tbDn5GFYFn0nuVvDIAaV/RaU7RwoDYVnJX\n" +
        "sJpn2b096aMNaGL3fN4sGVEdYkIikvgX1QuFDIfWXd9McCeVI8/dyJKD5XKX3cK0\n" +
        "DsxJxNOTwbCssUVoe0ldW8c7XfZANqQ6+i3qZGmzlAraykvrrJ+bpBB8QFBk2Dfr\n" +
        "feC/BtoAwK/B8G4nq1nb1fa1yiKdEykmqQIDAQABoyMwITAOBgNVHQ8BAf8EBAMC\n" +
        "AQYwDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQsFAAOCAYEAC9nBmcft25hb\n" +
        "OpdL6HFxSQWeAn6sF2h03lgnHdAzIO5Ebtp1mLjoB7UlikCP5j246UFtDvuKHcoM\n" +
        "yrdEkXlN5iCGCDBwpaxKnvof2GgEMoUahPzaaK1i4419pdEq6Rr1R3vXdzLm+TZ8\n" +
        "bC2BcrcDoNH4K/RmIjvH9FyD5T25wCXaJxHiwNZ+Rd263Y0vpu6vg91ybX8XimMt\n" +
        "vZgqq4WXhRuTMVxVv980X7ZR5DyEE/grg1hbU7sJRckEKz3CL0XKu/FqW4P+pIYJ\n" +
        "/OaOUFolsACKirbxcce4KFhmkaY40pKZ6xTJfMYubxlkcLC0peVXABZNOlc3RtLY\n" +
        "laK1qq7A/BxR37y5okVycc+vera93N+Exm9QxsyFQW0d4aXdBkBdlgk+17ut64oY\n" +
        "DV9qoJ28cNlEmvsLjXXFaz6nrEOCbS+z/KDVWgqOzjrjsI4m8Xw8JOLL0SJPwj05\n" +
        "IsAUxECwNAgnqY7PQ8iuUzhqBhxRAABbA0RZWlE2mdZZmUkOG/oL\n" +
        "-----END CERTIFICATE-----";

CertificateImporter certificateImporter = new CertificateImporter();
certificateImporter.doImport(cert);
```