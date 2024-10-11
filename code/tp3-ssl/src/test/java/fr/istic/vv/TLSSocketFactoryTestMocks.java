package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    private TLSSocketFactory factory;
    private SSLSocket mockSSLSocket;

    @BeforeEach
    public void setUp() {
        factory = new TLSSocketFactory();
        mockSSLSocket = mock(SSLSocket.class);  // ceration un mock de SSLSocket
    }

    @Test
    public void testPrepareSocket_NullProtocols() throws Exception {
        // Simuler un SSLSocket avec des protocoles supportés et activés null
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(mockSSLSocket);

        verify(mockSSLSocket, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    public void testPrepareSocket_TypicalCase() throws Exception {
        // Simuler un SSLSocket avec des protocoles supportés et activés
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        factory.prepareSocket(mockSSLSocket);

        // Vérifier que setEnabledProtocols est appelé avec les bons protocoles et bon ordre
        verify(mockSSLSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    @Test
    public void testPrepareSocket_SupportedButNotEnabled() throws Exception {
        // Simulation des protocoles non activés mais supportés
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1.2", "TLSv1.1"});
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(new String[0]);

        factory.prepareSocket(mockSSLSocket);

        // Vérifier que seuls les protocoles supportés sont activés
        verify(mockSSLSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1"});
    }

    @Test
    public void testPrepareSocket_EmptySupportedProtocols() throws Exception {
        // Simulation d'un SSLSocket avec des protocoles supportés vides
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(new String[0]);
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(new String[0]);

        factory.prepareSocket(mockSSLSocket);

        // Vérification de setEnabledProtocols n'est jamais appelé 
        verify(mockSSLSocket, never()).setEnabledProtocols(any(String[].class));
    }
}
