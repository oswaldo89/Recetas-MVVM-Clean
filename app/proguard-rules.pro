# Agrega las siguientes reglas para suprimir las advertencias de clases faltantes
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

# Mantén los nombres de las actividades y fragmentos sin ofuscar
-keep class *Activity { *; }
-keep class *Fragment { *; }

# Mantén los nombres de las clases que terminan con "UseCase" sin ofuscar
-keep class *UseCase { *; }

# Mantén los nombres de las clases que terminan con "Repository" sin ofuscar
-keep class *Repository { *; }

# Imprime el mapeo en la consola
-printmapping build/outputs/mapping/release/mapping.txt