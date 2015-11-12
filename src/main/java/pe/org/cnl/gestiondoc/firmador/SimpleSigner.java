package pe.org.cnl.gestiondoc.firmador;

import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

class SimpleSigner
  implements Signer
{
  public byte[] firmar(byte[] mensaje, String alias, KeyStore keyStore, char[] password, String algoritmo)
    throws CertificateException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, SignatureException
  {
    byte[] firma = null;
    //Certificate cert = keyStore.getCertificate(alias);
    PrivateKey privKey = (PrivateKey)keyStore.getKey(alias, password);
    firma = firmar(mensaje, privKey, algoritmo);
    return firma;
  }

  public byte[] firmar(byte[] mensaje, PrivateKey privKey, String algoritmo)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
  {
    byte[] firma = null;

    Signature sig = Signature.getInstance(algoritmo);

    sig.initSign(privKey);
    sig.update(mensaje);
    firma = sig.sign();
    return firma;
  }
}