package pe.org.cnl.gestiondoc.test.firmador;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;

public abstract interface Signer
{
  public abstract byte[] firmar(byte[] paramArrayOfByte, String notarioCert , KeyStore paramKeyStore, char[] paramArrayOfChar, String paramString2)
    throws CertificateException, KeyStoreException, NoSuchAlgorithmException, 
    UnrecoverableKeyException, InvalidKeyException, SignatureException,
    InvalidAlgorithmParameterException, CertStoreException, 
    NoSuchProviderException, IOException;

  public abstract byte[] firmar(byte[] paramArrayOfByte, PrivateKey paramPrivateKey, String paramString)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}